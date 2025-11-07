package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerteaminfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo.EmerTeamInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteaminfo.EmerTeamInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援队伍信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerTeamInfoMapper extends BaseMapperX<EmerTeamInfoDO> {

    default PageResult<EmerTeamInfoDO> selectPage(EmerTeamInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerTeamInfoDO>()
                .eqIfPresent(EmerTeamInfoDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(EmerTeamInfoDO::getTeamCode, reqVO.getTeamCode())
                .likeIfPresent(EmerTeamInfoDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(EmerTeamInfoDO::getCatId, reqVO.getCatId())
                .likeIfPresent(EmerTeamInfoDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerTeamInfoDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerTeamInfoDO::getApplyRegionName, reqVO.getApplyRegionName())
                .eqIfPresent(EmerTeamInfoDO::getTeamMemberCount, reqVO.getTeamMemberCount())
                .eqIfPresent(EmerTeamInfoDO::getAvailableMemberCount, reqVO.getAvailableMemberCount())
                .eqIfPresent(EmerTeamInfoDO::getTeamStatus, reqVO.getTeamStatus())
                .eqIfPresent(EmerTeamInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(EmerTeamInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EmerTeamInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .orderByDesc(EmerTeamInfoDO::getId));
    }

}