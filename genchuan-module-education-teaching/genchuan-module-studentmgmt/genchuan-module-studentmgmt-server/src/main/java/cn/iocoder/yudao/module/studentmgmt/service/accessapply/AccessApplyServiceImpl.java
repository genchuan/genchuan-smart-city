package cn.iocoder.yudao.module.studentmgmt.service.accessapply;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.accessapply.AccessApplyDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.accessapply.AccessApplyMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.AccessApplyStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.AccessApplyTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.ACCESS_APPLY_NOT_EXISTS;

/**
 * 出入申请 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AccessApplyServiceImpl implements AccessApplyService {

    @Resource
    private AccessApplyMapper accessApplyMapper;
    @Resource
    private DeptApi deptApi;

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createAccessApply(AccessApplySaveReqVO createReqVO) {
        // 插入
        AccessApplyDO accessApply = BeanUtils.toBean(createReqVO, AccessApplyDO.class);
        accessApplyMapper.insert(accessApply);

        // 返回
        return accessApply.getId();
    }

    @Override
    public void updateAccessApply(AccessApplySaveReqVO updateReqVO) {
        // 校验存在
        validateAccessApplyExists(updateReqVO.getId());
        // 更新
        AccessApplyDO updateObj = BeanUtils.toBean(updateReqVO, AccessApplyDO.class);
        accessApplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteAccessApply(Long id) {
        // 校验存在
        validateAccessApplyExists(id);
        // 删除
        accessApplyMapper.deleteById(id);
    }

    @Override
        public void deleteAccessApplyListByIds(List<Long> ids) {
        // 删除
        accessApplyMapper.deleteByIds(ids);
        }


    private AccessApplyDO validateAccessApplyExists(Long id) {
        AccessApplyDO accessApplyDO = accessApplyMapper.selectById(id);
        if ( accessApplyDO== null) {
            throw exception(ACCESS_APPLY_NOT_EXISTS);
        }
        return accessApplyDO;
    }

    @Override
    public AccessApplyDO getAccessApply(Long id) {
        return accessApplyMapper.selectById(id);
    }

    @Override
    public PageResult<AccessApplyDO> getAccessApplyPage(AccessApplyPageReqVO pageReqVO) {
        return accessApplyMapper.selectPage(pageReqVO);
    }

    @Override
    public boolean audit(AccessApplyAuditReqVO updateReqVO) {
        int total = 0;
        for (Long id : updateReqVO.getIds()) {
            // 校验存在
            AccessApplyDO accessApplyDO = validateAccessApplyExists(id);
            // 仅允许审核待审核状态的申请
            if (!AccessApplyStatusEnum.PENDING.getStatus().equals(accessApplyDO.getStatus())) {
                throw exception(id + "，仅允许审核待审核状态的申请");
            }
            // 获取当前用户
            String username = SecurityFrameworkUtils.getLoginUserNickname();
            accessApplyDO.setAuditUser(username);
            //自动填充审核人、审核时间，更新申请状态为 “已通过”
            accessApplyDO.setAuditTime(LocalDateTime.now());
            accessApplyDO.setStatus(AccessApplyStatusEnum.APPROVE.getStatus());

            // 更新
            int i = accessApplyMapper.updateById(accessApplyDO);
            total = total + i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public AccessApplyChartRespVO chart(AccessApplyChartReqVO reqVO) {
        AccessApplyChartRespVO vo = new AccessApplyChartRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        Long classId = reqVO.getClassId();

        // 通过classId 查询 System模块的 dept.id 的名称，减少关联查询
        String className=null;
        if (null == classId) {
            CommonResult<DeptRespDTO> dept = deptApi.getDept(classId);
            DeptRespDTO data = dept.getData();
            if (data == null) {
                throw new ServiceException(ErrorCodeConstants.DEPT_NOT_EXISTS);
            }
             className = data.getName();

        }

        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        vo = accessApplyMapper.selectTotalApplyCount(startTime,endTime,className, AccessApplyStatusEnum.APPROVE.getStatus());

        // 2. 趋势数据
        List<JSONObject> totalList = accessApplyMapper.selectApplyTrend(startTime,endTime,className);
        // 循环打印出从startTime到endTime的每一天的记录数
        List dailyTrendList = new ArrayList();
        for (LocalDateTime date = startTime; date.isBefore(endTime); date = date.plusDays(1)) {
            System.out.println(date + ": " + date);

            JSONObject jsonObject = new JSONObject();
            // 从totalList 中查找 date
            for (JSONObject item : totalList) {
                String applyTime = item.getString("applyTime");
                if (applyTime.equals(date)) {
                    jsonObject.put("date",date);
                    jsonObject.put("count", item.getInteger("count"));
                    dailyTrendList.add(jsonObject);
                    break;
                }
            }
        }
        vo.setDailyTrend(dailyTrendList);
        // typeDistribution (array): 申请类型分布数据，包含类型、数量。
        List<JSONObject> typeDistributionList = accessApplyMapper.selectApplyTypeDistribution(startTime, endTime, className);
        vo.setTypeDistribution(typeDistributionList);
        return vo;
    }

    @Override
    public AccessApplyCountRespVO applyCount(AccessApplyCountReqVO reqVO) {
        AccessApplyCountRespVO vo = new AccessApplyCountRespVO();
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }


        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        List<JSONObject> totalList = accessApplyMapper.selectClassStatistics(startTime, endTime, AccessApplyTypeEnum.EMERGENCY.getStatus());
        vo.setClassStatistics(totalList);
        return vo;
    }

}