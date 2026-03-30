package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AttendanceMapper extends BaseMapperX<AttendanceDO> {

    default PageResult<AttendanceDO> selectPage(AttendancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AttendanceDO>()
                .eqIfPresent(AttendanceDO::getAttendanceId, reqVO.getAttendanceId())
                .eqIfPresent(AttendanceDO::getUserId, reqVO.getUserId())
                .eqIfPresent(AttendanceDO::getJobTypeId, reqVO.getJobTypeId())
                .eqIfPresent(AttendanceDO::getTeamId, reqVO.getTeamId())
                .betweenIfPresent(AttendanceDO::getCheckDate, reqVO.getCheckDate())
                .betweenIfPresent(AttendanceDO::getOnDutyTime, reqVO.getOnDutyTime())
                .betweenIfPresent(AttendanceDO::getOffDutyTime, reqVO.getOffDutyTime())
                .eqIfPresent(AttendanceDO::getAttendanceStatusId, reqVO.getAttendanceStatusId())
                .eqIfPresent(AttendanceDO::getCheckLocation, reqVO.getCheckLocation())
                .eqIfPresent(AttendanceDO::getWorkHours, reqVO.getWorkHours())
                .eqIfPresent(AttendanceDO::getAbnormalTypeId, reqVO.getAbnormalTypeId())
                .eqIfPresent(AttendanceDO::getAbnormalDesc, reqVO.getAbnormalDesc())
                .eqIfPresent(AttendanceDO::getProofMaterial, reqVO.getProofMaterial())
                .eqIfPresent(AttendanceDO::getReviewStatusId, reqVO.getReviewStatusId())
                .eqIfPresent(AttendanceDO::getSupplementReason, reqVO.getSupplementReason())
                .betweenIfPresent(AttendanceDO::getSupplementTime, reqVO.getSupplementTime())
                .eqIfPresent(AttendanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AttendanceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AttendanceDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AttendanceDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AttendanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AttendanceDO::getId));
    }

}