package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.config;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo.SceneConfigRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.config.SceneConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 获取场景配置接口 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 SceneConfigService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class SceneConfigServiceImpl implements SceneConfigService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private SceneConfigMapper sceneConfigMapper;

    /**
     * 查询获取场景配置接口数据
     *
     * @param sceneConfigQueryReqVO 查询条件 VO 对象
     * @return SceneConfigRespVO 查询结果 VO 对象
     */
    @Override
    public SceneConfigRespVO getSceneConfig(SceneConfigQueryReqVO sceneConfigQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return sceneConfigMapper.getSceneConfig(sceneConfigQueryReqVO);
    }
}
