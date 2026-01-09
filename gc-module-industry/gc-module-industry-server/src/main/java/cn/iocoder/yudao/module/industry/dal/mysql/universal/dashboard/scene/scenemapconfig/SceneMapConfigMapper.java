package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.scenemapconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenemapconfig.SceneMapConfigDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 场景地图整体配置 Mapper
 *
 * @author lxs
 */
@Mapper
public interface SceneMapConfigMapper extends BaseMapperX<SceneMapConfigDO> {

    default PageResult<SceneMapConfigDO> selectPage(SceneMapConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SceneMapConfigDO>()
                .eqIfPresent(SceneMapConfigDO::getSceneCode, reqVO.getSceneCode())
                .eqIfPresent(SceneMapConfigDO::getDefaultIconWidth, reqVO.getDefaultIconWidth())
                .eqIfPresent(SceneMapConfigDO::getDefaultIconHeight, reqVO.getDefaultIconHeight())
                .eqIfPresent(SceneMapConfigDO::getDefaultIconUrl, reqVO.getDefaultIconUrl())
                .eqIfPresent(SceneMapConfigDO::getShowFlag, reqVO.getShowFlag())
                .eqIfPresent(SceneMapConfigDO::getPosition, reqVO.getPosition())
                .eqIfPresent(SceneMapConfigDO::getCenterLat, reqVO.getCenterLat())
                .eqIfPresent(SceneMapConfigDO::getCenterLng, reqVO.getCenterLng())
                .eqIfPresent(SceneMapConfigDO::getRotateSpeed, reqVO.getRotateSpeed())
                .eqIfPresent(SceneMapConfigDO::getLoopType, reqVO.getLoopType())
                .eqIfPresent(SceneMapConfigDO::getPitch, reqVO.getPitch())
                .eqIfPresent(SceneMapConfigDO::getZoom, reqVO.getZoom())
                .eqIfPresent(SceneMapConfigDO::getCenterType, reqVO.getCenterType())
                .eqIfPresent(SceneMapConfigDO::getCustomCenterLat, reqVO.getCustomCenterLat())
                .eqIfPresent(SceneMapConfigDO::getCustomCenterLng, reqVO.getCustomCenterLng())
                .betweenIfPresent(SceneMapConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SceneMapConfigDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SceneMapConfigDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SceneMapConfigDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SceneMapConfigDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(SceneMapConfigDO::getId));
    }

    /**
     * 根据 sceneCode 查询场景地图整体配置（唯一一条）
     */
    default SceneMapConfigDO selectBySceneCode(String sceneCode) {
        return selectOne(new LambdaQueryWrapperX<SceneMapConfigDO>()
                .eq(SceneMapConfigDO::getSceneCode, sceneCode)
                .last("limit 1"));
    }

    /**
     * 根据 sceneCode 删除场景地图整体配置
     */
    default void deleteBySceneCode(String sceneCode) {
        delete(new LambdaQueryWrapperX<SceneMapConfigDO>()
                .eq(SceneMapConfigDO::getSceneCode, sceneCode));
    }
}
