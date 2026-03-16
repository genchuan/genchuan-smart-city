package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

    default PageResult<UserDO> selectPage(UserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserDO>()
                .eqIfPresent(UserDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserDO::getUserPhone, reqVO.getUserPhone())
                .likeIfPresent(UserDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(UserDO::getRoleId, reqVO.getRoleId())
                .eqIfPresent(UserDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(UserDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(UserDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(UserDO::getSkillTags, reqVO.getSkillTags())
                .eqIfPresent(UserDO::getWorkTrajectoryId, reqVO.getWorkTrajectoryId())
                .eqIfPresent(UserDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(UserDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(UserDO::getJobTypeId, reqVO.getJobTypeId())
                .eqIfPresent(UserDO::getPersonStatusId, reqVO.getPersonStatusId())
                .eqIfPresent(UserDO::getPhone, reqVO.getPhone())
                .betweenIfPresent(UserDO::getEntryTime, reqVO.getEntryTime())
                .eqIfPresent(UserDO::getTotalAttendanceDays, reqVO.getTotalAttendanceDays())
                .eqIfPresent(UserDO::getAverageScore, reqVO.getAverageScore())
                .eqIfPresent(UserDO::getLastWorkTrace, reqVO.getLastWorkTrace())
                .eqIfPresent(UserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(UserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(UserDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(UserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserDO::getId));
    }

}