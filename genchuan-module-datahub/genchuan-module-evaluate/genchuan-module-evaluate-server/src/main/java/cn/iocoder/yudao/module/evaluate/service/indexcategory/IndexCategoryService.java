package cn.iocoder.yudao.module.evaluate.service.indexcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import jakarta.validation.Valid;

/**
 * 指标分类 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexCategoryService {

    /**
     * 创建指标分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndexCategory(@Valid IndexCategorySaveReqVO createReqVO);

    /**
     * 更新指标分类
     *
     * @param updateReqVO 更新信息
     */
    void updateIndexCategory(@Valid IndexCategorySaveReqVO updateReqVO);

    /**
     * 删除指标分类
     *
     * @param id 编号
     */
    void deleteIndexCategory(Long id);

    /**
     * 获得指标分类
     *
     * @param id 编号
     * @return 指标分类
     */
    IndexCategoryDO getIndexCategory(Long id);

    /**
     * 获得指标分类分页
     *
     * @param pageReqVO 分页查询
     * @return 指标分类分页
     */
    PageResult<IndexCategoryDO> getIndexCategoryPage(IndexCategoryPageReqVO pageReqVO);

}