package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.base;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalScenePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalSceneRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalSceneSaveReqVO;
import jakarta.validation.*;

import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.base.UniversalSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 通用场景表，一级和二级场景 Service 接口
 *
 * @author lxs
 */
public interface UniversalSceneService {

    /**
     * 树形数据：优先调用模块A获取数据
     */
    List<UniversalSceneRespVO> listTreeByParentId();
    /**
     * 创建通用场景表，一级和二级场景
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUniversalScene(@Valid UniversalSceneSaveReqVO createReqVO);

    /**
     * 更新通用场景表，一级和二级场景
     *
     * @param updateReqVO 更新信息
     */
    void updateUniversalScene(@Valid UniversalSceneSaveReqVO updateReqVO);

    /**
     * 删除通用场景表，一级和二级场景
     *
     * @param id 编号
     */
    void deleteUniversalScene(Long id);

    /**
     * 获得通用场景表，一级和二级场景
     *
     * @param parentId 编号
     * @return 通用场景表，一级和二级场景
     */
    List<UniversalSceneRespVO> listByParentId(Long parentId);

    /**
     * 获得通用场景表，一级和二级场景
     *
     * @param id 编号
     * @return 通用场景表，一级和二级场景
     */
    UniversalSceneDO getUniversalScene(Long id);

    /**
     * 获得通用场景表，一级和二级场景分页
     *
     * @param pageReqVO 分页查询
     * @return 通用场景表，一级和二级场景分页
     */
    PageResult<UniversalSceneDO> getUniversalScenePage(UniversalScenePageReqVO pageReqVO);
}
