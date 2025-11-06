package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerteamcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteamcat.EmerTeamCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 救援队伍分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerTeamCatService {

    /**
     * 创建救援队伍分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerTeamCat(@Valid EmerTeamCatSaveReqVO createReqVO);

    /**
     * 更新救援队伍分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerTeamCat(@Valid EmerTeamCatSaveReqVO updateReqVO);

    /**
     * 删除救援队伍分类配置
     *
     * @param id 编号
     */
    void deleteEmerTeamCat(Long id);

    /**
     * 获得救援队伍分类配置
     *
     * @param id 编号
     * @return 救援队伍分类配置
     */
    EmerTeamCatDO getEmerTeamCat(Long id);

    /**
     * 获得救援队伍分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 救援队伍分类配置分页
     */
    PageResult<EmerTeamCatDO> getEmerTeamCatPage(EmerTeamCatPageReqVO pageReqVO);

}