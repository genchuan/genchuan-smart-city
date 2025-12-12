package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.field;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.field.SceneFieldDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 场景字段 Service 接口
 *
 * @author lxs
 */
public interface SceneFieldService {

    /**
     * 创建场景字段
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSceneField(@Valid SceneFieldSaveReqVO createReqVO);

    /**
     * 更新场景字段
     *
     * @param updateReqVO 更新信息
     */
    void updateSceneField(@Valid SceneFieldSaveReqVO updateReqVO);

    /**
     * 删除场景字段
     *
     * @param id 编号
     */
    void deleteSceneField(Long id);

    /**
     * 获得场景字段
     *
     * @param id 编号
     * @return 场景字段
     */
    SceneFieldDO getSceneField(Long id);

    /**
     * 获得场景字段分页
     *
     * @param pageReqVO 分页查询
     * @return 场景字段分页
     */
    PageResult<SceneFieldDO> getSceneFieldPage(SceneFieldPageReqVO pageReqVO);

    List<SceneFieldRespVO> listFiledBySceneId(String sceneCode);
}
