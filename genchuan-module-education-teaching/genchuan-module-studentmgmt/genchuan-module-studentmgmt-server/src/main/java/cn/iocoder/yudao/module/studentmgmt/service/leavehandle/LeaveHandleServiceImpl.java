package cn.iocoder.yudao.module.studentmgmt.service.leavehandle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle.LeaveHandleDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.leavehandle.LeaveHandleMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.LeaveHandleStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.LEAVE_HANDLE_NOT_EXISTS;

/**
 * 离校办理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class LeaveHandleServiceImpl implements LeaveHandleService {

    @Resource
    private LeaveHandleMapper leaveHandleMapper;

    @Override
    public Long createLeaveHandle(LeaveHandleCreateReqVO createReqVO) {
        // 插入
        LeaveHandleDO leaveHandle = BeanUtils.toBean(createReqVO, LeaveHandleDO.class);
        leaveHandle.setStatus(LeaveHandleStatusEnum.PENDING_CONFIRM.getStatus());
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        leaveHandle.setCreator(loginUserNickname);
        leaveHandle.setCreateTime(LocalDateTime.now());
        leaveHandle.setUpdateTime(LocalDateTime.now());
        leaveHandle.setFinishRate(BigDecimal.ZERO);
        leaveHandleMapper.insert(leaveHandle);

        // 返回
        return leaveHandle.getId();
    }

    @Override
    public void updateLeaveHandle(LeaveHandleSaveReqVO updateReqVO) {
        // 校验存在
        validateLeaveHandleExists(updateReqVO.getId());
        // 更新
        LeaveHandleDO updateObj = BeanUtils.toBean(updateReqVO, LeaveHandleDO.class);
        leaveHandleMapper.updateById(updateObj);
    }

    @Override
    public void deleteLeaveHandle(Long id) {
        // 校验存在
        validateLeaveHandleExists(id);
        // 删除
        leaveHandleMapper.deleteById(id);
    }

    @Override
    public void deleteLeaveHandleListByIds(List<Long> ids) {
        // 删除
        leaveHandleMapper.deleteByIds(ids);
    }


    private LeaveHandleDO validateLeaveHandleExists(Long id) {
        LeaveHandleDO leaveHandleDO = leaveHandleMapper.selectById(id);
        if (leaveHandleDO == null) {
            throw exception(LEAVE_HANDLE_NOT_EXISTS);
        }
        return leaveHandleDO;
    }

    @Override
    public LeaveHandleDO getLeaveHandle(Long id) {
        return leaveHandleMapper.selectById(id);
    }

    @Override
    public PageResult<LeaveHandleDO> getLeaveHandlePage(LeaveHandlePageReqVO pageReqVO) {
        return leaveHandleMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean confirm(LeaveHandleConfirmReqVO updateReqVO) {
        // 校验存在
        LeaveHandleDO leaveHandleDO = leaveHandleMapper.selectOne(LeaveHandleDO::getId, updateReqVO.getId());
        if (leaveHandleDO == null) {
            throw exception(LEAVE_HANDLE_NOT_EXISTS);
        }
        leaveHandleDO.setParentConfirmTime(updateReqVO.getParentConfirmTime());
        leaveHandleDO.setStatus(LeaveHandleStatusEnum.PENDING_HANDLE.getStatus());
        leaveHandleDO.setUpdateTime(LocalDateTime.now());
        leaveHandleDO.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        // 更新
        int i = leaveHandleMapper.updateById(leaveHandleDO);
        return i > 0;
    }

    @Override
    public Boolean handle(LeaveHandleHandleReqVO reqVO) {
        // 校验存在
        LeaveHandleDO leaveHandleDO = leaveHandleMapper.selectOne(LeaveHandleDO::getId, reqVO.getId());
        if (leaveHandleDO == null) {
            throw exception(LEAVE_HANDLE_NOT_EXISTS);
        }
        leaveHandleDO.setCheckoutStatus(reqVO.getCheckoutStatus());
        leaveHandleDO.setStatus(LeaveHandleStatusEnum.LEFT.getStatus());
        leaveHandleDO.setUpdateTime(LocalDateTime.now());
        leaveHandleDO.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        //TODO 自动计算办理完成率
        leaveHandleDO.setFinishRate(new BigDecimal("100"));
        // 更新
        int i = leaveHandleMapper.updateById(leaveHandleDO);
        return i > 0;
    }

    @Override
    public LeaveHandleCharRespVO chart(BaseChartReqVO reqVO) {
        LeaveHandleCharRespVO vo = new LeaveHandleCharRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 1. 卡片数据
        vo = leaveHandleMapper.selectTotalCount(startTime, endTime,
                LeaveHandleStatusEnum.PENDING_CONFIRM.getStatus(),
                LeaveHandleStatusEnum.PENDING_HANDLE.getStatus(),
                LeaveHandleStatusEnum.LEFT.getStatus()
        );
        // 如果统计为空，则设置为0
        if (vo == null) {
            vo = new LeaveHandleCharRespVO();
        }
        if (vo.getFinishRate() == null) {
            vo.setFinishRate(BigDecimal.ZERO);
        }
        if (vo.getWaitHandle() == null) {
            vo.setWaitHandle(0);
        }
        if (vo.getTotalGraduate() == null) {
            vo.setTotalGraduate(0);
        }
        if (vo.getWaitConfirm() == null) {
            vo.setWaitConfirm(0);
        }
        if (vo.getFinishedLeave() == null) {
            vo.setFinishedLeave(0);
        }

        return vo;
    }

    @Override
    public LeaveHandleIndexRespVO leaveIndex(BaseChartReqVO reqVO) {
        LeaveHandleIndexRespVO vo = new LeaveHandleIndexRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 1. 卡片数据
        vo = leaveHandleMapper.selectIndexCount(startTime, endTime);
        // 如果统计为空，则设置为0
        if (vo.getCheckoutRate() == null) {
            vo.setCheckoutRate(BigDecimal.ZERO);
        }
        if (vo.getParentConfirmRate() == null) {
            vo.setParentConfirmRate(BigDecimal.ZERO);
        }
        if (vo.getHandleFinishRate() == null) {
            vo.setHandleFinishRate(BigDecimal.ZERO);
        }

        List<ChartTrendVO> list = leaveHandleMapper.selectDailyLeaveCount(startTime, endTime);
        if (list != null && list.size() > 0) {
            vo.setDailyLeaveCount(list);
        } else {
            vo.setDailyLeaveCount(new ArrayList<>());
        }

        return vo;
    }

}