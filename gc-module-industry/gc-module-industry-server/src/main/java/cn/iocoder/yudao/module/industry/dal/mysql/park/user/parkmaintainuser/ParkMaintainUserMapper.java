package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmaintainuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainuser.ParkMaintainUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运维人员 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkMaintainUserMapper extends BaseMapperX<ParkMaintainUserDO> {

    default PageResult<ParkMaintainUserDO> selectPage(ParkMaintainUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkMaintainUserDO>()
                .eqIfPresent(ParkMaintainUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkMaintainUserDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(ParkMaintainUserDO::getJobType, reqVO.getJobType())
                .eqIfPresent(ParkMaintainUserDO::getSkillTags, reqVO.getSkillTags())
                .eqIfPresent(ParkMaintainUserDO::getOnDutyStatus, reqVO.getOnDutyStatus())
                .betweenIfPresent(ParkMaintainUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkMaintainUserDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkMaintainUserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkMaintainUserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkMaintainUserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkMaintainUserDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkMaintainUserDO::getId));
    }

}
