package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.scenemapconfig;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenemapconfig.SceneMapConfigDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 场景地图整体配置 Service 接口
 *
 * @author lxs
 */
public interface SceneMapConfigService {

    /**
     * 创建场景地图整体配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSceneMapConfig(@Valid SceneMapConfigSaveReqVO createReqVO);

    /**
     * 更新场景地图整体配置
     *
     * @param updateReqVO 更新信息
     */
    void updateSceneMapConfig(@Valid SceneMapConfigSaveReqVO updateReqVO);

    /**
     * 删除场景地图整体配置
     *
     * @param id 编号
     */
    void deleteSceneMapConfig(Long id);

    /**
     * 获得场景地图整体配置
     *
     * @param id 编号
     * @return 场景地图整体配置
     */
    SceneMapConfigDO getSceneMapConfig(Long id);

    /**
     * 获得场景地图整体配置分页
     *
     * @param pageReqVO 分页查询
     * @return 场景地图整体配置分页
     */
    PageResult<SceneMapConfigDO> getSceneMapConfigPage(SceneMapConfigPageReqVO pageReqVO);

}
