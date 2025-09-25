package cn.iocoder.yudao.module.datacenter.service.sceneconfig;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.sceneconfig.SceneConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.sceneconfig.SceneConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 场景分类 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class SceneConfigServiceImpl implements SceneConfigService {

    @Resource
    private SceneConfigMapper sceneConfigMapper;

    @Override
    public Long createSceneConfig(SceneConfigSaveReqVO createReqVO) {
        // 校验父级ID的有效性
        validateParentSceneConfig(null, createReqVO.getPid());
        // 校验场景名称的唯一性
        validateSceneConfigNameUnique(null, createReqVO.getPid(), createReqVO.getName());

        // 插入
        SceneConfigDO sceneConfig = BeanUtils.toBean(createReqVO, SceneConfigDO.class);
        sceneConfigMapper.insert(sceneConfig);
        // 返回
        return sceneConfig.getId();
    }

    @Override
    public void updateSceneConfig(SceneConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateSceneConfigExists(updateReqVO.getId());
        // 校验父级ID的有效性
        validateParentSceneConfig(updateReqVO.getId(), updateReqVO.getPid());
        // 校验场景名称的唯一性
        validateSceneConfigNameUnique(updateReqVO.getId(), updateReqVO.getPid(), updateReqVO.getName());

        // 更新
        SceneConfigDO updateObj = BeanUtils.toBean(updateReqVO, SceneConfigDO.class);
        sceneConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteSceneConfig(Long id) {
        // 校验存在
        validateSceneConfigExists(id);
        // 校验是否有子场景分类
        if (sceneConfigMapper.selectCountByPid(id) > 0) {
            throw exception(SCENE_CONFIG_EXITS_CHILDREN);
        }
        // 删除
        sceneConfigMapper.deleteById(id);
    }

    private void validateSceneConfigExists(Long id) {
        if (sceneConfigMapper.selectById(id) == null) {
            throw exception(SCENE_CONFIG_NOT_EXISTS);
        }
    }

    private void validateParentSceneConfig(Long id, Long pid) {
        if (pid == null || SceneConfigDO.PID_ROOT.equals(pid)) {
            return;
        }
        // 1. 不能设置自己为父场景分类
        if (Objects.equals(id, pid)) {
            throw exception(SCENE_CONFIG_PARENT_ERROR);
        }
        // 2. 父场景分类不存在
        SceneConfigDO parentSceneConfig = sceneConfigMapper.selectById(pid);
        if (parentSceneConfig == null) {
            throw exception(SCENE_CONFIG_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父场景分类，如果父场景分类是自己的子场景分类，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            pid = parentSceneConfig.getPid();
            if (Objects.equals(id, pid)) {
                throw exception(SCENE_CONFIG_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父场景分类
            if (pid == null || SceneConfigDO.PID_ROOT.equals(pid)) {
                break;
            }
            parentSceneConfig = sceneConfigMapper.selectById(pid);
            if (parentSceneConfig == null) {
                break;
            }
        }
    }

    private void validateSceneConfigNameUnique(Long id, Long pid, String name) {
        SceneConfigDO sceneConfig = sceneConfigMapper.selectByPidAndName(pid, name);
        if (sceneConfig == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的场景分类
        if (id == null) {
            throw exception(SCENE_CONFIG_NAME_DUPLICATE);
        }
        if (!Objects.equals(sceneConfig.getId(), id)) {
            throw exception(SCENE_CONFIG_NAME_DUPLICATE);
        }
    }

    @Override
    public SceneConfigDO getSceneConfig(Long id) {
        return sceneConfigMapper.selectById(id);
    }

    @Override
    public List<SceneConfigDO> getSceneConfigList(SceneConfigListReqVO listReqVO) {
        return sceneConfigMapper.selectList(listReqVO);
    }

    @Override
    public List<SceneConfigTreeRespVO> getSceneConfigTree() {
        // 获取所有场景分类数据
        List<SceneConfigDO> allConfigs = sceneConfigMapper.selectList(new SceneConfigListReqVO());

        // 转换为树形结构
        return buildTree(allConfigs, SceneConfigDO.PID_ROOT);
    }

    private List<SceneConfigTreeRespVO> buildTree(List<SceneConfigDO> allConfigs, Long parentId) {
        List<SceneConfigTreeRespVO> treeNodes = new ArrayList<>();

        for (SceneConfigDO config : allConfigs) {
            if (parentId.equals(config.getPid())) {
                SceneConfigTreeRespVO treeNode = BeanUtils.toBean(config, SceneConfigTreeRespVO.class);
                treeNode.setChildren(buildTree(allConfigs, config.getId()));
                treeNodes.add(treeNode);
            }
        }

        return treeNodes;
    }

}