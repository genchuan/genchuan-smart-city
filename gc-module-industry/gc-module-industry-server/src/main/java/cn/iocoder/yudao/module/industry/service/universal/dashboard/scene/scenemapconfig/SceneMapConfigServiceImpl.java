package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.scenemapconfig;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenemapconfig.SceneMapConfigDO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.scenemapconfig.SceneMapConfigMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 场景地图整体配置 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class SceneMapConfigServiceImpl implements SceneMapConfigService {

    @Resource
    private SceneMapConfigMapper sceneMapConfigMapper;

    @Override
    public Long createSceneMapConfig(SceneMapConfigSaveReqVO createReqVO) {
        // 插入
        SceneMapConfigDO sceneMapConfig = BeanUtils.toBean(createReqVO, SceneMapConfigDO.class);
        sceneMapConfigMapper.insert(sceneMapConfig);
        // 返回
        return sceneMapConfig.getId();
    }

    @Override
    public void updateSceneMapConfig(SceneMapConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateSceneMapConfigExists(updateReqVO.getId());
        // 更新
        SceneMapConfigDO updateObj = BeanUtils.toBean(updateReqVO, SceneMapConfigDO.class);
        sceneMapConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteSceneMapConfig(Long id) {
        // 校验存在
        validateSceneMapConfigExists(id);
        // 删除
        sceneMapConfigMapper.deleteById(id);
    }

    private void validateSceneMapConfigExists(Long id) {
        if (sceneMapConfigMapper.selectById(id) == null) {
            throw exception(SCENE_MAP_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public SceneMapConfigDO getSceneMapConfig(Long id) {
        return sceneMapConfigMapper.selectById(id);
    }

    @Override
    public PageResult<SceneMapConfigDO> getSceneMapConfigPage(SceneMapConfigPageReqVO pageReqVO) {
        return sceneMapConfigMapper.selectPage(pageReqVO);
    }

}
