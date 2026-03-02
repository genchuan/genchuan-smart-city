package cn.iocoder.yudao.module.envirhealth.dal.mysql.team;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.team.TeamDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo.*;

/**
 * 班组 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TeamMapper extends BaseMapperX<TeamDO> {

    default PageResult<TeamDO> selectPage(TeamPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TeamDO>()
                .eqIfPresent(TeamDO::getSysTeamId, reqVO.getSysTeamId())
                .likeIfPresent(TeamDO::getName, reqVO.getName())
                .eqIfPresent(TeamDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(TeamDO::getTeamLeaderId, reqVO.getTeamLeaderId())
                .eqIfPresent(TeamDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TeamDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TeamDO::getTeamPhotoUrl, reqVO.getTeamPhotoUrl())
                .eqIfPresent(TeamDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TeamDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TeamDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TeamDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TeamDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TeamDO::getId));
    }

}