package cn.iocoder.yudao.module.evaluate.service.indexcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 指标分类 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexCategoryService {

    Long createIndexCategory(@Valid IndexCategorySaveReqVO createReqVO);

    void updateIndexCategory(@Valid IndexCategorySaveReqVO updateReqVO);

    void deleteIndexCategory(Long id);

    IndexCategoryDO getIndexCategory(Long id);

    PageResult<IndexCategoryDO> getIndexCategoryPage(IndexCategoryPageReqVO pageReqVO);

    void updateBatchCategoryWeight(Map<String, BigDecimal> categoryWeights);

    /**
     * 根据 systemUuid 获取分类列表
     *
     * @param systemUuid 体系UUID
     * @return 分类列表
     */
    List<IndexCategoryDO> getCategoryListBySystemIdFromCache(String systemUuid);

}
