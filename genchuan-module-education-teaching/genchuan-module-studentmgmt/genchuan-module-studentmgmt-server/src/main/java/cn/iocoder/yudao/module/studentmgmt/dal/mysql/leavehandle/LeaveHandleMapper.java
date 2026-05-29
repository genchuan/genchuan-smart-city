package cn.iocoder.yudao.module.studentmgmt.dal.mysql.leavehandle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.DormCheckPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.DormCheckRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.LeaveHandleCharRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.LeaveHandleIndexRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.LeaveHandlePageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.LeaveHandleRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle.LeaveHandleDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 离校办理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface LeaveHandleMapper extends BaseMapperX<LeaveHandleDO> {


    default PageResult<LeaveHandleDO> selectPage(LeaveHandlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeaveHandleDO>()
                .eqIfPresent(LeaveHandleDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(LeaveHandleDO::getLeaveTime, reqVO.getLeaveTime())
                .eqIfPresent(LeaveHandleDO::getLeaveAddress, reqVO.getLeaveAddress())
                .betweenIfPresent(LeaveHandleDO::getParentConfirmTime, reqVO.getParentConfirmTime())
                .eqIfPresent(LeaveHandleDO::getHandleUser, reqVO.getHandleUser())
                .betweenIfPresent(LeaveHandleDO::getHandleTime, reqVO.getHandleTime())
                .betweenIfPresent(LeaveHandleDO::getCheckoutTime, reqVO.getCheckoutTime())
                .eqIfPresent(LeaveHandleDO::getCheckoutStatus, reqVO.getCheckoutStatus())
                .eqIfPresent(LeaveHandleDO::getFinishRate, reqVO.getFinishRate())
                .eqIfPresent(LeaveHandleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(LeaveHandleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(LeaveHandleDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(LeaveHandleDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(LeaveHandleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LeaveHandleDO::getId));
    }

    default PageResult<LeaveHandleRespVO> selectJoinPage(LeaveHandlePageReqVO reqVO) {
        // 1. 构建分页对象
        Page<LeaveHandleRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<LeaveHandleDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(LeaveHandleDO.class);
        wrapper.selectAs(StudentInfoDO::getName, ClubMgmtRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, LeaveHandleDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(LeaveHandleDO::getStudentId, reqVO.getStudentId());
        }
        if (null != reqVO.getLeaveTime()) {
            wrapper.between(LeaveHandleDO::getLeaveTime, reqVO.getLeaveTime()[0], reqVO.getLeaveTime()[1]);
        }
        if (StringUtils.isNotBlank(reqVO.getCheckoutStatus())) {
            wrapper.eq(LeaveHandleDO::getCheckoutStatus, reqVO.getCheckoutStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(LeaveHandleDO::getStatus, reqVO.getStatus());
        }
        wrapper.orderByDesc(LeaveHandleDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<LeaveHandleRespVO> resultPage = selectJoinPage(page, LeaveHandleRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }


    LeaveHandleCharRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime,
                                           String pending_confirm, String pending_handle, String left);

    LeaveHandleIndexRespVO selectIndexCount(LocalDateTime startTime, LocalDateTime endTime);

    List<ChartTrendVO> selectDailyLeaveCount(LocalDateTime startTime, LocalDateTime endTime);
}