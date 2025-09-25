package cn.iocoder.yudao.module.datacenter.service.sceneconfig;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.sceneconfig.SceneConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 场景分类 Service 接口
 *
 * @author zcq
 */
public interface SceneConfigService {

    /**
     * 创建场景分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSceneConfig(@Valid SceneConfigSaveReqVO createReqVO);

    /**
     * 更新场景分类
     *
     * @param updateReqVO 更新信息
     */
    void updateSceneConfig(@Valid SceneConfigSaveReqVO updateReqVO);

    /**
     * 删除场景分类
     *
     * @param id 编号
     */
    void deleteSceneConfig(Long id);

    /**
     * 获得场景分类
     *
     * @param id 编号
     * @return 场景分类
     */
    SceneConfigDO getSceneConfig(Long id);

    /**
     * 获得场景分类列表
     *
     * @param listReqVO 查询条件
     * @return 场景分类列表
     */
    List<SceneConfigDO> getSceneConfigList(SceneConfigListReqVO listReqVO);

    /**
     * 获得场景分类树形结构
     *
     * @return 场景分类树形结构
     */
    List<SceneConfigTreeRespVO> getSceneConfigTree();
}