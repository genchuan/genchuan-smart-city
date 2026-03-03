package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendancestatus.AttendanceStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤状态字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AttendanceStatusMapper extends BaseMapperX<AttendanceStatusDO> {

    default PageResult<AttendanceStatusDO> selectPage(AttendanceStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AttendanceStatusDO>()
                .eqIfPresent(AttendanceStatusDO::getAttendanceStatusId, reqVO.getAttendanceStatusId())
                .likeIfPresent(AttendanceStatusDO::getAttendanceStatusName, reqVO.getAttendanceStatusName())
                .eqIfPresent(AttendanceStatusDO::getDescription, reqVO.getDescription())
                .eqIfPresent(AttendanceStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AttendanceStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(AttendanceStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AttendanceStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AttendanceStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AttendanceStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AttendanceStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AttendanceStatusDO::getId));
    }

}