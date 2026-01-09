package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.scenestatusicon;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenestatusicon.SceneStatusIconDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 场景状态字段图标配置 Service 接口
 *
 * @author lxs
 */
public interface SceneStatusIconService {

    /**
     * 创建场景状态字段图标配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSceneStatusIcon(@Valid SceneStatusIconSaveReqVO createReqVO);

    /**
     * 更新场景状态字段图标配置
     *
     * @param updateReqVO 更新信息
     */
    void updateSceneStatusIcon(@Valid SceneStatusIconSaveReqVO updateReqVO);

    /**
     * 删除场景状态字段图标配置
     *
     * @param id 编号
     */
    void deleteSceneStatusIcon(Long id);

    /**
     * 获得场景状态字段图标配置
     *
     * @param id 编号
     * @return 场景状态字段图标配置
     */
    SceneStatusIconDO getSceneStatusIcon(Long id);

    /**
     * 获得场景状态字段图标配置分页
     *
     * @param pageReqVO 分页查询
     * @return 场景状态字段图标配置分页
     */
    PageResult<SceneStatusIconDO> getSceneStatusIconPage(SceneStatusIconPageReqVO pageReqVO);

}
