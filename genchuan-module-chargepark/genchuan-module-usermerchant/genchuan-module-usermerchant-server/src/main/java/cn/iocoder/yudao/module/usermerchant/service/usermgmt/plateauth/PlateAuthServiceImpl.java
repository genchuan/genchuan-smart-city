package cn.iocoder.yudao.module.usermerchant.service.usermgmt.plateauth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.plateauth.PlateAuthMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 车牌认证 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PlateAuthServiceImpl implements PlateAuthService {

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private PlateAuthMapper plateAuthMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public PageResult<PlateAuthDO> getPlateAuthPage(PlateAuthPageReqVO pageReqVO) {
        // 1. 处理昵称筛选：如果前端传了 nickname，则转换为 userId 并设置到查询条件
        if (StrUtil.isNotBlank(pageReqVO.getNickname())) {
            Long userId = userInfoMapper.getIdByNickname(pageReqVO.getNickname());
            if (userId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setUserId(userId);
        }
        if (StrUtil.isNotBlank(pageReqVO.getAuditorName())) {
            Long auditorId = userInfoMapper.getIdByNickname(pageReqVO.getAuditorName());
            if (auditorId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setAuditorId(auditorId);
        }

        PageResult<PlateAuthDO> pageResult = plateAuthMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                PlateAuthDO::getUserId,
                PlateAuthDO::setNickname,
                "user_info", "id", "nickname"
        );

        // 批量填充审核人昵称（通过 Feign 调用 system-server）
        NameQueryHelper.fillUserNames(pageResult.getList(),
                PlateAuthDO::getAuditorId,
                PlateAuthDO::setAuditorName,
                adminUserApi);

        return pageResult;
    }

    @Override
    public void batchUpdatePlateAuth(PlateAuthSaveReqVO updateReqVO) {
        List<Long> ids = updateReqVO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 1. 校验所有 id 存在
        for (Long id : ids) {
            validatePlateAuthExists(id);
        }

        // 2. 处理不同的审核结果
        String auditResult = updateReqVO.getAuditResult();
        String targetStatus;
        UpdateWrapper<PlateAuthDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);

        if ("通过".equals(auditResult)) {
            targetStatus = "已认证";
            updateWrapper.set("status", targetStatus)
                    .set("audit_remark", updateReqVO.getAuditRemark())
                    .set("audit_time", LocalDateTime.now())
                    .set("auditor_id", getCurrentUserId());
        } else if ("驳回".equals(auditResult)) {
            targetStatus = "已驳回";
            updateWrapper.set("status", targetStatus)
                    .set("audit_remark", updateReqVO.getAuditRemark())
                    .set("audit_time", LocalDateTime.now())
                    .set("auditor_id", getCurrentUserId());
        } else if ("待审核".equals(auditResult)) {
            targetStatus = "待审核";
            // 重新认证：只更新状态，清空审核人、审核时间、审核备注
            updateWrapper.set("status", targetStatus)
                    .set("auditor_id", null)
                    .set("audit_time", null)
                    .set("audit_remark", null);
        } else {
            throw new ServiceException(ILLEGAL_STATUS);
        }

        // 3. 执行更新
        plateAuthMapper.update(null, updateWrapper);
    }

    @Override
    public Long createPlateAuth(PlateAuthSaveReqVO createReqVO) {
        // 插入
        PlateAuthDO plateAuth = BeanUtils.toBean(createReqVO, PlateAuthDO.class);
        plateAuthMapper.insert(plateAuth);

        // 返回
        return plateAuth.getId();
    }

    @Override
    public void updatePlateAuth(PlateAuthSaveReqVO updateReqVO) {
        // 校验存在
        validatePlateAuthExists(updateReqVO.getId());
        // 更新
        PlateAuthDO updateObj = BeanUtils.toBean(updateReqVO, PlateAuthDO.class);
        plateAuthMapper.updateById(updateObj);
    }

    @Override
    public void deletePlateAuth(Long id) {
        // 校验存在
        validatePlateAuthExists(id);
        // 删除
        plateAuthMapper.deleteById(id);
    }

    @Override
        public void deletePlateAuthListByIds(List<Long> ids) {
        // 删除
        plateAuthMapper.deleteByIds(ids);
        }

    private void validatePlateAuthExists(Long id) {
        if (plateAuthMapper.selectById(id) == null) {
            throw exception(PLATE_AUTH_NOT_EXISTS);
        }
    }

    @Override
    public PlateAuthDO getPlateAuth(Long id) {
        return plateAuthMapper.selectById(id);
    }

    @Override
    public PlateAuthChartRespVO getPlateAuthChart(PlateAuthChartReqVO chartReqVO) {
        PlateAuthChartRespVO chartRespVO = new PlateAuthChartRespVO();
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
        List<PlateAuthChartRespVO.AuthTrendVO> authTrend = plateAuthMapper.selectAuthTrend(
                parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setAuthTrend(authTrend);
        //总数统计
        chartRespVO.setAuthCount(plateAuthMapper.selectAuthCount(parsed.getStart(), parsed.getEnd()));
        // 计算比率
        chartRespVO.setAuthPassRate(plateAuthMapper.selectAuthPassRate(parsed.getStart(), parsed.getEnd()));
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