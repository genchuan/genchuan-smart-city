package cn.iocoder.yudao.module.evaluate.service.indexitem;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexcategory.IndexCategoryMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import cn.iocoder.yudao.module.evaluate.service.indexsystem.IndexSystemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

@Service
@Validated
@Slf4j
public class IndexItemServiceImpl implements IndexItemService {

    @Resource
    private IndexItemMapper indexItemMapper;

    @Resource
    private IndexCategoryMapper indexCategoryMapper;

    @Resource
    private IndexSystemService indexSystemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createIndexItem(IndexItemSaveReqVO createReqVO) {
        Integer categoryCount = indexItemMapper.countCategoryExists(createReqVO.getCategoryId());
        if (categoryCount == 0) {
            throw new ServiceException(INDEX_CATEGORY_NOT_EXISTS);
        }
        Integer nameCount = indexItemMapper.countByNameAndCategoryId(
                createReqVO.getCategoryId(), createReqVO.getName(), null);
        if (nameCount > 0) {
            throw new ServiceException(INDEX_ITEM_ALREADY_EXISTS);
        }
        BigDecimal existingWeightSum = indexItemMapper.sumWeightByCategoryId(
                createReqVO.getCategoryId(), null);
        BigDecimal newWeightSum = existingWeightSum.add(createReqVO.getWeight()).setScale(2, RoundingMode.HALF_UP);
        if (newWeightSum.compareTo(new BigDecimal("100.00")) > 0) {
            throw new ServiceException(INDEX_WEIGHT_OVER_LIMIT)
                    .setMessage("新增后该分类下指标项权重总和为" + newWeightSum + "%，超过100%上限");
        }
        IndexItemDO indexItem = BeanUtils.toBean(createReqVO, IndexItemDO.class);
        indexItem.setItemId(UUID.randomUUID().toString().replace("-", "_"));
        indexItem.setCreateBy(createReqVO.getCreateBy() == null ?
                Objects.requireNonNull(SecurityFrameworkUtils.getLoginUserId()).toString() :
                createReqVO.getCreateBy());
        indexItem.setUpdateBy(indexItem.getCreateBy());
        LocalDateTime now = LocalDateTime.now();
        indexItem.setCreateTime(now);
        indexItem.setUpdateTime(now);
        indexItem.setBizCreateTime(now);
        indexItem.setBizUpdateTime(now);
        indexItemMapper.insert(indexItem);
        indexSystemService.refreshSystemCountsByCategoryId(createReqVO.getCategoryId());
        IndexCategoryDO category = indexCategoryMapper.selectOne(
                new LambdaQueryWrapper<IndexCategoryDO>()
                        .eq(IndexCategoryDO::getCategoryId, createReqVO.getCategoryId())
                        .eq(IndexCategoryDO::getDeleted, 0));
        if (category != null && category.getSystemId() != null) {
            String logContent = StrUtil.format("指标项名称：{}，指标类型ID：{}，计算方式ID：{}，达标阈值：{}，指标项权重：{}",
                    createReqVO.getName(), createReqVO.getIndexTypeId(), createReqVO.getCalcWayId(),
                    createReqVO.getThreshold(), createReqVO.getWeight());
            indexSystemService.updateChangeLog(category.getSystemId(), "CREATE", logContent);
        }
        return indexItem.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIndexItem(IndexItemSaveReqVO updateReqVO) {
        IndexItemDO oldItem = indexItemMapper.selectById(updateReqVO.getId());
        validateIndexItemExists(updateReqVO.getId());
        Integer categoryCount = indexItemMapper.countCategoryExists(updateReqVO.getCategoryId());
        if (categoryCount == 0) {
            throw new ServiceException(INDEX_CATEGORY_NOT_EXISTS);
        }
        Integer nameCount = indexItemMapper.countByNameAndCategoryId(
                updateReqVO.getCategoryId(), updateReqVO.getName(), updateReqVO.getId());
        if (nameCount > 0) {
            throw new ServiceException(INDEX_ITEM_ALREADY_EXISTS);
        }
        BigDecimal existingWeightSum = indexItemMapper.sumWeightByCategoryId(
                updateReqVO.getCategoryId(), updateReqVO.getId());
        BigDecimal newWeightSum = existingWeightSum.add(updateReqVO.getWeight()).setScale(2, RoundingMode.HALF_UP);
        if (newWeightSum.compareTo(new BigDecimal("100.00")) > 0) {
            throw new ServiceException(INDEX_WEIGHT_OVER_LIMIT)
                    .setMessage("新增后该分类下指标项权重总和为" + newWeightSum + "%，超过100%上限");
        }
        IndexItemDO updateObj = BeanUtils.toBean(updateReqVO, IndexItemDO.class);
        updateObj.setUpdateBy(updateReqVO.getUpdateBy() == null ?
                Objects.requireNonNull(SecurityFrameworkUtils.getLoginUserId()).toString() :
                updateReqVO.getUpdateBy());
        updateObj.setUpdateTime(LocalDateTime.now());
        updateObj.setBizUpdateTime(updateObj.getUpdateTime());
        if (oldItem != null) {
            updateObj.setItemId(oldItem.getItemId());
        }
        indexItemMapper.updateById(updateObj);
        if (oldItem != null && oldItem.getCategoryId() != null) {
            indexSystemService.refreshSystemCountsByCategoryId(oldItem.getCategoryId());
        }
        if (updateReqVO.getCategoryId() != null &&
                (oldItem == null || !updateReqVO.getCategoryId().equals(oldItem.getCategoryId()))) {
            indexSystemService.refreshSystemCountsByCategoryId(updateReqVO.getCategoryId());
        }
        IndexCategoryDO category = indexCategoryMapper.selectOne(
                new LambdaQueryWrapper<IndexCategoryDO>()
                        .eq(IndexCategoryDO::getCategoryId, updateReqVO.getCategoryId())
                        .eq(IndexCategoryDO::getDeleted, 0));
        if (category != null && category.getSystemId() != null) {
            StringJoiner changeContent = new StringJoiner("；");
            if (!Objects.equals(oldItem.getName(), updateReqVO.getName())) {
                changeContent.add(StrUtil.format("指标项名称由【{}】改为【{}】",
                        oldItem.getName() != null ? oldItem.getName() : "空",
                        updateReqVO.getName() != null ? updateReqVO.getName() : "空"));
            }
            if (!Objects.equals(oldItem.getIndexTypeId(), updateReqVO.getIndexTypeId())) {
                changeContent.add(StrUtil.format("指标类型ID由【{}】改为【{}】",
                        oldItem.getIndexTypeId() != null ? oldItem.getIndexTypeId() : "空",
                        updateReqVO.getIndexTypeId() != null ? updateReqVO.getIndexTypeId() : "空"));
            }
            if (!Objects.equals(oldItem.getCalcWayId(), updateReqVO.getCalcWayId())) {
                changeContent.add(StrUtil.format("计算方式ID由【{}】改为【{}】",
                        oldItem.getCalcWayId() != null ? oldItem.getCalcWayId() : "空",
                        updateReqVO.getCalcWayId() != null ? updateReqVO.getCalcWayId() : "空"));
            }
            if (!Objects.equals(oldItem.getThreshold(), updateReqVO.getThreshold())) {
                changeContent.add(StrUtil.format("达标阈值由【{}】改为【{}】",
                        oldItem.getThreshold() != null ? oldItem.getThreshold() : "空",
                        updateReqVO.getThreshold() != null ? updateReqVO.getThreshold() : "空"));
            }
            if (!Objects.equals(oldItem.getWeight(), updateReqVO.getWeight())) {
                changeContent.add(StrUtil.format("指标项权重由【{}】改为【{}】",
                        oldItem.getWeight() != null ? oldItem.getWeight() : "空",
                        updateReqVO.getWeight() != null ? updateReqVO.getWeight() : "空"));
            }
            if (!Objects.equals(oldItem.getSortNo(), updateReqVO.getSortNo())) {
                changeContent.add(StrUtil.format("排序序号由【{}】改为【{}】",
                        oldItem.getSortNo() != null ? oldItem.getSortNo() : "空",
                        updateReqVO.getSortNo() != null ? updateReqVO.getSortNo() : "空"));
            }
            if (!Objects.equals(oldItem.getCategoryId(), updateReqVO.getCategoryId())) {
                changeContent.add(StrUtil.format("所属分类由【{}】改为【{}】",
                        oldItem.getCategoryId() != null ? oldItem.getCategoryId() : "空",
                        updateReqVO.getCategoryId() != null ? updateReqVO.getCategoryId() : "空"));
            }
            String logContent = changeContent.length() > 0 ? changeContent.toString() : "修改指标项信息";
            indexSystemService.updateChangeLog(category.getSystemId(), "UPDATE", logContent);
        }
    }

    @Override
    public void deleteIndexItem(Long id) {
        IndexItemDO existing = indexItemMapper.selectById(id);
        IndexCategoryDO category = null;
        if (existing != null && existing.getCategoryId() != null) {
            category = indexCategoryMapper.selectOne(
                    new LambdaQueryWrapper<IndexCategoryDO>()
                            .eq(IndexCategoryDO::getCategoryId, existing.getCategoryId())
                            .eq(IndexCategoryDO::getDeleted, 0));
        }
        validateIndexItemExists(id);
        indexItemMapper.deleteById(id);
        if (existing != null && existing.getCategoryId() != null) {
            indexSystemService.refreshSystemCountsByCategoryId(existing.getCategoryId());
            if (category != null && category.getSystemId() != null) {
                String logContent = StrUtil.format("指标项名称：{}，指标类型ID：{}，计算方式ID：{}，达标阈值：{}，指标项权重：{}",
                        existing.getName(), existing.getIndexTypeId(), existing.getCalcWayId(),
                        existing.getThreshold(), existing.getWeight());
                indexSystemService.updateChangeLog(category.getSystemId(), "DELETE", logContent);
            }
        }
    }

    private void validateIndexItemExists(Long id) {
        if (indexItemMapper.selectById(id) == null) {
            throw exception(INDEX_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public IndexItemDO getIndexItem(Long id) {
        return indexItemMapper.selectById(id);
    }

    @Override
    public IndexItemDO getIndexItemByItemId(String itemId) {
        return indexItemMapper.selectByItemId(itemId);
    }

    @Override
    public PageResult<IndexItemDO> getIndexItemPage(IndexItemPageReqVO pageReqVO) {
        return indexItemMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateBatchItemWeight(Map<Long, BigDecimal> itemWeights) {
        if (itemWeights == null || itemWeights.isEmpty()) {
            return;
        }
        indexItemMapper.updateBatchItemWeight(itemWeights);
    }

    /**
     * 根据 systemUuid 获取指标项列表
     */
    public List<IndexItemDO> getItemListBySystemIdFromCache(String systemUuid) {
        if (systemUuid == null || systemUuid.isEmpty()) {
            return List.of();
        }
        List<IndexCategoryDO> categories = indexCategoryMapper.selectListBySystemId(systemUuid);
        if (categories.isEmpty()) {
            return List.of();
        }
        Set<String> categoryIds = categories.stream()
                .map(IndexCategoryDO::getCategoryId)
                .collect(Collectors.toSet());
        return indexItemMapper.selectListByCategoryIds(categoryIds);
    }

}
