package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceAbnormalTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤异常类型字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AttendanceAbnormalTypeMapper extends BaseMapperX<AttendanceAbnormalTypeDO> {

    default PageResult<AttendanceAbnormalTypeDO> selectPage(AttendanceAbnormalTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AttendanceAbnormalTypeDO>()
                .eqIfPresent(AttendanceAbnormalTypeDO::getAttendanceAbnormalTypeId, reqVO.getAttendanceAbnormalTypeId())
                .likeIfPresent(AttendanceAbnormalTypeDO::getAbnormalTypeName, reqVO.getAbnormalTypeName())
                .eqIfPresent(AttendanceAbnormalTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(AttendanceAbnormalTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AttendanceAbnormalTypeDO::getSort, reqVO.getSort())
                .eqIfPresent(AttendanceAbnormalTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AttendanceAbnormalTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AttendanceAbnormalTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AttendanceAbnormalTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AttendanceAbnormalTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AttendanceAbnormalTypeDO::getId));
    }

}