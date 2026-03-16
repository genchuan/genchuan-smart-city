package cn.iocoder.yudao.module.data.service.scenecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.scenecategory.SceneCategoryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 应用场景分类 Service 接口
 *
 * @author zhucongquan
 */
public interface SceneCategoryService {

    /**
     * 创建应用场景分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSceneCategory(@Valid SceneCategorySaveReqVO createReqVO);

    /**
     * 更新应用场景分类
     *
     * @param updateReqVO 更新信息
     */
    void updateSceneCategory(@Valid SceneCategorySaveReqVO updateReqVO);

    /**
     * 删除应用场景分类
     *
     * @param id 编号
     */
    void deleteSceneCategory(Long id);

    /**
     * 获得应用场景分类
     *
     * @param id 编号
     * @return 应用场景分类
     */
    SceneCategoryDO getSceneCategory(Long id);

    /**
     * 获得应用场景分类分页
     *
     * @param pageReqVO 分页查询
     * @return 应用场景分类分页
     */
    PageResult<SceneCategoryDO> getSceneCategoryPage(SceneCategoryPageReqVO pageReqVO);

    /**
     * 批量删除应用场景分类
     *
     * @param ids 编号列表
     */
    void deleteSceneCategories(List<Long> ids);

    /**
     * 获得应用场景分类简化树（仅包含id、label、children）
     *
     * @return 应用场景分类简化树列表
     */
    List<SceneCategorySimpleTreeRespVO> getSceneCategorySimpleTree();

    /**
     * 获取指定父节点下的所有子节点ID（包括自身）
     *
     * @param parentId 父节点ID
     * @param includeSelf 是否包含父节点自身
     * @return 子节点ID列表
     */
    List<Long> getSubCategoryIds(String parentId, boolean includeSelf);

}