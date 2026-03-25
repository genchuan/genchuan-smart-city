package cn.iocoder.yudao.module.evaluate.service.indexcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexcategory.IndexCategoryMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.INDEX_CATEGORY_NOT_EXISTS;

/**
 * 指标分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class IndexCategoryServiceImpl implements IndexCategoryService {

    @Resource
    private IndexCategoryMapper indexCategoryMapper;

    @Override
    public Long createIndexCategory(IndexCategorySaveReqVO createReqVO) {
        IndexCategoryDO indexCategory = BeanUtils.toBean(createReqVO, IndexCategoryDO.class);
        indexCategoryMapper.insert(indexCategory);
        return indexCategory.getId();
    }

    @Override
    public void updateIndexCategory(IndexCategorySaveReqVO updateReqVO) {
        validateIndexCategoryExists(updateReqVO.getId());
        IndexCategoryDO updateObj = BeanUtils.toBean(updateReqVO, IndexCategoryDO.class);
        indexCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndexCategory(Long id) {
        validateIndexCategoryExists(id);
        indexCategoryMapper.deleteById(id);
    }

    private void validateIndexCategoryExists(Long id) {
        if (indexCategoryMapper.selectById(id) == null) {
            throw exception(INDEX_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public IndexCategoryDO getIndexCategory(Long id) {
        return indexCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<IndexCategoryDO> getIndexCategoryPage(IndexCategoryPageReqVO pageReqVO) {
        return indexCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateBatchCategoryWeight(Map<String, BigDecimal> categoryWeights) {
        if (categoryWeights == null || categoryWeights.isEmpty()) {
            return;
        }
        indexCategoryMapper.updateBatchCategoryWeight(categoryWeights);
    }

    @Override
    public List<IndexCategoryDO> getCategoryListBySystemIdFromCache(String systemUuid) {
        if (systemUuid == null || systemUuid.isEmpty()) {
            return List.of();
        }
        return indexCategoryMapper.selectListBySystemId(systemUuid);
    }

}
