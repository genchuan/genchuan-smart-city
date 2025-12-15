package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.scenestatusicon;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenestatusicon.SceneStatusIconDO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.scenestatusicon.SceneStatusIconMapper;
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
 * 场景状态字段图标配置 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class SceneStatusIconServiceImpl implements SceneStatusIconService {

    @Resource
    private SceneStatusIconMapper sceneStatusIconMapper;

    @Override
    public Long createSceneStatusIcon(SceneStatusIconSaveReqVO createReqVO) {
        // 插入
        SceneStatusIconDO sceneStatusIcon = BeanUtils.toBean(createReqVO, SceneStatusIconDO.class);
        sceneStatusIconMapper.insert(sceneStatusIcon);
        // 返回
        return sceneStatusIcon.getId();
    }

    @Override
    public void updateSceneStatusIcon(SceneStatusIconSaveReqVO updateReqVO) {
        // 校验存在
        validateSceneStatusIconExists(updateReqVO.getId());
        // 更新
        SceneStatusIconDO updateObj = BeanUtils.toBean(updateReqVO, SceneStatusIconDO.class);
        sceneStatusIconMapper.updateById(updateObj);
    }

    @Override
    public void deleteSceneStatusIcon(Long id) {
        // 校验存在
        validateSceneStatusIconExists(id);
        // 删除
        sceneStatusIconMapper.deleteById(id);
    }

    private void validateSceneStatusIconExists(Long id) {
        if (sceneStatusIconMapper.selectById(id) == null) {
            throw exception(SCENE_STATUS_ICON_NOT_EXISTS);
        }
    }

    @Override
    public SceneStatusIconDO getSceneStatusIcon(Long id) {
        return sceneStatusIconMapper.selectById(id);
    }

    @Override
    public PageResult<SceneStatusIconDO> getSceneStatusIconPage(SceneStatusIconPageReqVO pageReqVO) {
        return sceneStatusIconMapper.selectPage(pageReqVO);
    }

}
