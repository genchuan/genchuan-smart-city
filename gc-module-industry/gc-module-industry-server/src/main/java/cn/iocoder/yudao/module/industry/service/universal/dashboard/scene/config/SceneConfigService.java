package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.config;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigSaveReqVO;

/**
 * 获取场景配置接口 Service 接口
 *
 */
public interface SceneConfigService {

    /**
     * 查询获取场景配置接口
     *
     * @param sceneConfigQueryReqVO 查询条件
     * @return 查询结果
     */
    SceneConfigRespVO getSceneConfig(SceneConfigQueryReqVO sceneConfigQueryReqVO);

    /**
     * 保存场景配置（覆盖式）
     */
    void saveSceneConfig(SceneConfigSaveReqVO reqVO);
    // 清除场景配置
    void clearSceneConfig(String sceneKey);


}
