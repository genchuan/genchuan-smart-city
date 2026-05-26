package cn.iocoder.yudao.module.usermerchant.service.groupclient.groupinfo;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo.GroupInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.groupclient.groupinfo.GroupInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;

/**
 * 集团信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GroupInfoServiceImpl implements GroupInfoService {

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private GroupInfoMapper groupInfoMapper;

    @Override
    @LogRecord(type = TYPE_GROUP_INFO, subType = SUB_TYPE_CREATE_GROUP_INFO,
            bizNo = "{{#groupInfo.id}}",
            success = SUCCESS_CREATE_GROUP_INFO)
    public Boolean createGroupInfo(GroupInfoCreateReqVO createReqVO) {
        // 插入
        GroupInfoDO groupInfo = BeanUtils.toBean(createReqVO, GroupInfoDO.class);
        int rows = groupInfoMapper.insert(groupInfo);
        //记录操作日志上下文
        LogRecordContext.putVariable("groupInfo", groupInfo);
        // 返回
        return rows > 0;
    }

    @Override
    @LogRecord(type = TYPE_GROUP_INFO, subType = SUB_TYPE_UPDATE_GROUP_INFO,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_GROUP_INFO)
    public void updateGroupInfo(GroupInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateGroupInfoExists(updateReqVO.getId());
        // 更新
        GroupInfoDO updateObj = BeanUtils.toBean(updateReqVO, GroupInfoDO.class);
        groupInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteGroupInfo(Long id) {
        // 校验存在
        validateGroupInfoExists(id);
        // 删除
        groupInfoMapper.deleteById(id);
    }

    @Override
        public void deleteGroupInfoListByIds(List<Long> ids) {
        // 删除
        groupInfoMapper.deleteByIds(ids);
        }


    private void validateGroupInfoExists(Long id) {
        if (groupInfoMapper.selectById(id) == null) {
            throw exception(GROUP_INFO_NOT_EXISTS);
        }
    }

    @Override
    public GroupInfoDO getGroupInfo(Long id) {
        return groupInfoMapper.selectById(id);
    }

    @Override
    public PageResult<GroupInfoDO> getGroupInfoPage(GroupInfoPageReqVO pageReqVO) {
        PageResult<GroupInfoDO> pageResult = groupInfoMapper.selectPage(pageReqVO);
        // 对手机号进行脱敏处理
        for (GroupInfoDO info : pageResult.getList()) {
            String phone = info.getPhone();
            if (phone != null && phone.length() >= 11) {
                // 保留前3位和后4位，中间4位星号
                String masked = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
                info.setPhone(masked);
            }
            // 如果手机号长度不足11位，原样返回或置空，可根据需求调整
        }

        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_GROUP_INFO, subType = SUB_TYPE_IMPORT_GROUP_INFO,
            bizNo = "{{#list.stream().map(GroupInfoImportExcelVO::getId).collect(T(java.util.stream.Collectors).toList())}}",
            success = SUCCESS_IMPORT_GROUP_INFO)
    public Boolean importGroups(List<GroupInfoImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (GroupInfoImportExcelVO vo : list) {
            GroupInfoDO insertDO = BeanUtils.toBean(vo, GroupInfoDO.class);
            insertDO.setId(null);   // 确保 ID 由数据库自增生成
            insertDO.setRegisterTime(LocalDateTime.now());
            insertDO.setStatus("待审核");
            insertDO.setCreateTime(LocalDateTime.now());
            insertDO.setUpdateTime(LocalDateTime.now());

            groupInfoMapper.insert(insertDO);
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("list", list);
        LogRecordContext.putVariable("updateSupport", updateSupport);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_GROUP_INFO, subType = SUB_TYPE_AUDIT_GROUP_INFO,
            bizNo = "{{{#reqVO.ids}}}",
            success = SUCCESS_AUDIT_GROUP_INFO)
    public void batchUpdateGroupInfo(GroupInfoAuditReqVO reqVO) {
        List<Long> ids = reqVO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 1. 校验所有 id 存在
        for (Long id : ids) {
            validateGroupInfoExists(id);
        }

        String status = reqVO.getStatus();
        // 2. 校验状态是否合法
        if (!"正常".equals(status) && !"已驳回".equals(status)) {
            throw new ServiceException(ILLEGAL_STATUS);
        }

        // 3. 批量更新
        UpdateWrapper<GroupInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status)
                .set("audit_remark", reqVO.getAuditRemark())
                .set("audit_time", LocalDateTime.now())
                .set("auditor_id", getCurrentUserId());
        groupInfoMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("reqVO", reqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_GROUP_INFO, subType = SUB_TYPE_UPDATE_GROUP_STATUS,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_UPDATE_GROUP_STATUS)
    public void updateGroupStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<GroupInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        groupInfoMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("status", status);
    }

    @Override
    public GroupInfoChartRespVO getGroupInfoChart(GroupInfoChartReqVO chartReqVO) {
        GroupInfoChartRespVO chartRespVO = new GroupInfoChartRespVO();
        //拆分时间范围
        String timeRange = chartReqVO.getTimeRange();

        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            // 未传时间范围：全量查询，start 和 end 为 null，粒度默认 day
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 解析失败，返回空数据
                return chartRespVO;
            }
        }
        //折线图渲染
        // 折线图数据
        List<GroupInfoChartRespVO.GroupGrowthTrendVO> growthTrend = groupInfoMapper.selectGroupGrowthTrend(
                parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setGroupGrowthTrend(growthTrend);
        //总数统计
        chartRespVO.setTotalGroupCount(groupInfoMapper.selectTotalGroupCount(parsed.getStart(), parsed.getEnd()));
        // 计算近30天的时间范围
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyDaysAgo = now.minusDays(30);

        // 调用 Mapper 方法，传入开始和结束时间
        Long newGroupCount = groupInfoMapper.selectNewGroupCount(thirtyDaysAgo, now);

        // 设置到响应 VO 中
        chartRespVO.setNewGroupCount((long) (newGroupCount != null ? newGroupCount.intValue() : 0));

        return chartRespVO;
    }

    /**
     * 获取当前登录用户ID
     */
    private Long getCurrentUserId() {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

}