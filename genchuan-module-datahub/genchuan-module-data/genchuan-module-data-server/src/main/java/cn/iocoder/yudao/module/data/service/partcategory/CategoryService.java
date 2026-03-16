package cn.iocoder.yudao.module.data.service.partcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.data.controller.admin.partcategory.vo.CategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.partcategory.vo.CategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.partcategory.vo.CategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.partcategory.CategoryDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 管理部件分类 Service 接口
 *
 * @author zhucongquan
 */
public interface CategoryService {

    /**
     * 创建管理部件分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid CategorySaveReqVO createReqVO);

    /**
     * 更新管理部件分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid CategorySaveReqVO updateReqVO);

    /**
     * 删除管理部件分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 批量删除管理部件分类
     *
     * @param ids 编号列表
     */
    void deleteCategories(List<Long> ids);

    /**
     * 获得管理部件分类
     *
     * @param id 编号
     * @return 管理部件分类
     */
    CategoryDO getCategory(Long id);

    /**
     * 获得管理部件分类分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件分类分页
     */
    PageResult<CategoryDO> getCategoryPage(CategoryPageReqVO pageReqVO);

    /**
     * 获得管理部件分类简化树（仅包含id、label、children）
     *
     * @return 管理部件分类简化树列表
     */
    List<CategorySimpleTreeRespVO> getCategorySimpleTree();

    /**
     * 获取指定父节点下的所有子节点ID（包括自身）
     *
     * @param parentId 父节点ID
     * @param includeSelf 是否包含父节点自身
     * @return 子节点ID列表
     */
    List<Long> getSubCategoryIds(String parentId, boolean includeSelf);
}