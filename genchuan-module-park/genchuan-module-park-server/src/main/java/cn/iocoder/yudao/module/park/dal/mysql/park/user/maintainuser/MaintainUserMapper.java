package cn.iocoder.yudao.module.park.dal.mysql.park.user.maintainuser;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainuser.MaintainUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运维人员 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MaintainUserMapper extends BaseMapperX<MaintainUserDO> {

    default PageResult<MaintainUserDO> selectPage(MaintainUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintainUserDO>()
                .eqIfPresent(MaintainUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MaintainUserDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(MaintainUserDO::getJobType, reqVO.getJobType())
                .eqIfPresent(MaintainUserDO::getSkillTags, reqVO.getSkillTags())
                .eqIfPresent(MaintainUserDO::getOnDutyStatus, reqVO.getOnDutyStatus())
                .eqIfPresent(MaintainUserDO::getContactPhone, reqVO.getContactPhone())
                .betweenIfPresent(MaintainUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MaintainUserDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MaintainUserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MaintainUserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MaintainUserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MaintainUserDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(MaintainUserDO::getId));
    }

}
