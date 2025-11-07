package cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerteamcat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteamcat.EmerTeamCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援队伍分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerTeamCatMapper extends BaseMapperX<EmerTeamCatDO> {

    default PageResult<EmerTeamCatDO> selectPage(EmerTeamCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerTeamCatDO>()
                .eqIfPresent(EmerTeamCatDO::getTeamCatId, reqVO.getTeamCatId())
                .eqIfPresent(EmerTeamCatDO::getParentCatId, reqVO.getParentCatId())
                .eqIfPresent(EmerTeamCatDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(EmerTeamCatDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(EmerTeamCatDO::getCatName, reqVO.getCatName())
                .eqIfPresent(EmerTeamCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EmerTeamCatDO::getApplyRegionCode, reqVO.getApplyRegionCode())
                .likeIfPresent(EmerTeamCatDO::getApplyRegionName, reqVO.getApplyRegionName())
                .orderByDesc(EmerTeamCatDO::getId));
    }

}