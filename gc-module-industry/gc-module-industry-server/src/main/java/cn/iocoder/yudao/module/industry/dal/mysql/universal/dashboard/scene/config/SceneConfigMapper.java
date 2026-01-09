package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.config;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 获取场景配置接口 Mapper
 *
 */
@Mapper
public interface SceneConfigMapper {

    /**
     * 查询获取场景配置接口
     *
     * @param sceneConfigQueryReqVO 查询参数
     * @return SceneConfigRespVO 结果
     */
    SceneConfigRespVO getSceneConfig(SceneConfigQueryReqVO sceneConfigQueryReqVO);

}
