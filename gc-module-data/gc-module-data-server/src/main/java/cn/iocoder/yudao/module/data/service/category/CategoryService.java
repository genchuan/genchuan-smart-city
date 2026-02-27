package cn.iocoder.yudao.module.data.service.category;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.data.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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
}