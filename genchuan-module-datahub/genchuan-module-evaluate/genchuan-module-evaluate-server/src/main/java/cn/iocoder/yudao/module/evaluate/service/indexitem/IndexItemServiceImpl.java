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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 指标项 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
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
        // ========== 1. 基础校验 ==========
        // 1.1 校验关联的分类是否存在
        Integer categoryCount = indexItemMapper.countCategoryExists(createReqVO.getCategoryId());
        if (categoryCount == 0) {
            throw new ServiceException(INDEX_CATEGORY_NOT_EXISTS);
        }

        // 1.2 校验同一分类下名称是否重复
        Integer nameCount = indexItemMapper.countByNameAndCategoryId(
                createReqVO.getCategoryId(), createReqVO.getName(), null);
        if (nameCount > 0) {
            throw new ServiceException(INDEX_ITEM_ALREADY_EXISTS);
        }

        // 1.3 校验分类下权重总和（新增后不超过100%）
        BigDecimal existingWeightSum = indexItemMapper.sumWeightByCategoryId(
                createReqVO.getCategoryId(), null);
        BigDecimal newWeightSum = existingWeightSum.add(createReqVO.getWeight()).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (newWeightSum.compareTo(new BigDecimal("100.00")) > 0) {
            throw new ServiceException(INDEX_WEIGHT_OVER_LIMIT)
                    .setMessage("新增后该分类下指标项权重总和为" + newWeightSum + "%，超过100%上限");
        }

        // ========== 2. 组装DO对象 ==========
        IndexItemDO indexItem = BeanUtils.toBean(createReqVO, IndexItemDO.class);
        // 2.1 生成UUID的itemId（非主键）
        indexItem.setItemId(UUID.randomUUID().toString().replace("-", "_"));
        // 2.2 填充创建人（芋道框架从UserContext获取）
        indexItem.setCreateBy(createReqVO.getCreateBy() == null ?
                Objects.requireNonNull(SecurityFrameworkUtils.getLoginUserId()).toString() :
                createReqVO.getCreateBy());
        indexItem.setUpdateBy(indexItem.getCreateBy());
        // 2.3 填充时间字段
        LocalDateTime now = LocalDateTime.now();
        indexItem.setCreateTime(now);
        indexItem.setUpdateTime(now);
        indexItem.setBizCreateTime(now);
        indexItem.setBizUpdateTime(now);

        // ========== 3. 插入数据库 ==========
        indexItemMapper.insert(indexItem);

        // 3.1 新增指标项后，刷新所属指标体系的分类总数与指标项总数
        indexSystemService.refreshSystemCountsByCategoryId(createReqVO.getCategoryId());
        // 记录变更日志
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

        // ========== 4. 返回主键ID ==========
        return indexItem.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIndexItem(IndexItemSaveReqVO updateReqVO) {
        // 1. 校验指标项是否存在，并记录修改前所属分类
        IndexItemDO oldItem = indexItemMapper.selectById(updateReqVO.getId());
        validateIndexItemExists(updateReqVO.getId());

        // 2. 校验关联的分类是否存在
        Integer categoryCount = indexItemMapper.countCategoryExists(updateReqVO.getCategoryId());
        if (categoryCount == 0) {
            throw new ServiceException(INDEX_CATEGORY_NOT_EXISTS);
        }

        // 3. 校验同一分类下名称是否重复（排除自身）
        Integer nameCount = indexItemMapper.countByNameAndCategoryId(
                updateReqVO.getCategoryId(), updateReqVO.getName(), updateReqVO.getId());
        if (nameCount > 0) {
            throw new ServiceException(INDEX_ITEM_ALREADY_EXISTS);
        }

        // 4. 校验权重总和（修改后不超过100%）
        BigDecimal existingWeightSum = indexItemMapper.sumWeightByCategoryId(
                updateReqVO.getCategoryId(), updateReqVO.getId()); // 排除自身
        BigDecimal newWeightSum = existingWeightSum.add(updateReqVO.getWeight()).setScale(2, RoundingMode.HALF_UP);
        if (newWeightSum.compareTo(new BigDecimal("100.00")) > 0) {
            throw new ServiceException(INDEX_WEIGHT_OVER_LIMIT)
                    .setMessage("新增后该分类下指标项权重总和为" + newWeightSum + "%，超过100%上限");
        }

        // 5. 组装DO并更新
        IndexItemDO updateObj = BeanUtils.toBean(updateReqVO, IndexItemDO.class);
        // 5.1 填充更新人、更新时间
        updateObj.setUpdateBy(updateReqVO.getUpdateBy() == null ?
                Objects.requireNonNull(SecurityFrameworkUtils.getLoginUserId()).toString() :
                updateReqVO.getUpdateBy());
        updateObj.setUpdateTime(LocalDateTime.now());
        updateObj.setBizUpdateTime(updateObj.getUpdateTime());
        // 5.2 禁止修改itemId（UUID）
        if (oldItem != null) {
            updateObj.setItemId(oldItem.getItemId());
        }
        // 5.3 执行更新
        indexItemMapper.updateById(updateObj);

        // 5.4 更新新旧分类所属体系的统计信息
        if (oldItem != null && oldItem.getCategoryId() != null) {
            indexSystemService.refreshSystemCountsByCategoryId(oldItem.getCategoryId());
        }
        if (updateReqVO.getCategoryId() != null &&
                (oldItem == null || !updateReqVO.getCategoryId().equals(oldItem.getCategoryId()))) {
            indexSystemService.refreshSystemCountsByCategoryId(updateReqVO.getCategoryId());
        }

        // 5.5 记录变更日志（对比旧值，只记录有变化的字段）
        IndexCategoryDO category = indexCategoryMapper.selectOne(
                new LambdaQueryWrapper<IndexCategoryDO>()
                        .eq(IndexCategoryDO::getCategoryId, updateReqVO.getCategoryId())
                        .eq(IndexCategoryDO::getDeleted, 0));
        if (category != null && category.getSystemId() != null) {
            StringJoiner changeContent = new StringJoiner("；");
            // 对比指标项名称
            if (!Objects.equals(oldItem.getName(), updateReqVO.getName())) {
                changeContent.add(StrUtil.format("指标项名称由【{}】改为【{}】",
                        oldItem.getName() != null ? oldItem.getName() : "空",
                        updateReqVO.getName() != null ? updateReqVO.getName() : "空"));
            }
            // 对比指标类型ID
            if (!Objects.equals(oldItem.getIndexTypeId(), updateReqVO.getIndexTypeId())) {
                changeContent.add(StrUtil.format("指标类型ID由【{}】改为【{}】",
                        oldItem.getIndexTypeId() != null ? oldItem.getIndexTypeId() : "空",
                        updateReqVO.getIndexTypeId() != null ? updateReqVO.getIndexTypeId() : "空"));
            }
            // 对比计算方式ID
            if (!Objects.equals(oldItem.getCalcWayId(), updateReqVO.getCalcWayId())) {
                changeContent.add(StrUtil.format("计算方式ID由【{}】改为【{}】",
                        oldItem.getCalcWayId() != null ? oldItem.getCalcWayId() : "空",
                        updateReqVO.getCalcWayId() != null ? updateReqVO.getCalcWayId() : "空"));
            }
            // 对比达标阈值
            if (!Objects.equals(oldItem.getThreshold(), updateReqVO.getThreshold())) {
                changeContent.add(StrUtil.format("达标阈值由【{}】改为【{}】",
                        oldItem.getThreshold() != null ? oldItem.getThreshold() : "空",
                        updateReqVO.getThreshold() != null ? updateReqVO.getThreshold() : "空"));
            }
            // 对比指标项权重
            if (!Objects.equals(oldItem.getWeight(), updateReqVO.getWeight())) {
                changeContent.add(StrUtil.format("指标项权重由【{}】改为【{}】",
                        oldItem.getWeight() != null ? oldItem.getWeight() : "空",
                        updateReqVO.getWeight() != null ? updateReqVO.getWeight() : "空"));
            }
            // 对比排序序号
            if (!Objects.equals(oldItem.getSortNo(), updateReqVO.getSortNo())) {
                changeContent.add(StrUtil.format("排序序号由【{}】改为【{}】",
                        oldItem.getSortNo() != null ? oldItem.getSortNo() : "空",
                        updateReqVO.getSortNo() != null ? updateReqVO.getSortNo() : "空"));
            }
            // 对比分类ID（跨分类调整）
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
        // 校验存在，并记录所属分类
        IndexItemDO existing = indexItemMapper.selectById(id);
        // 获取分类信息用于记录日志
        IndexCategoryDO category = null;
        if (existing != null && existing.getCategoryId() != null) {
            category = indexCategoryMapper.selectOne(
                    new LambdaQueryWrapper<IndexCategoryDO>()
                            .eq(IndexCategoryDO::getCategoryId, existing.getCategoryId())
                            .eq(IndexCategoryDO::getDeleted, 0));
        }
        validateIndexItemExists(id);
        // 删除
        indexItemMapper.deleteById(id);
        // 删除后刷新所属体系的统计信息
        if (existing != null && existing.getCategoryId() != null) {
            indexSystemService.refreshSystemCountsByCategoryId(existing.getCategoryId());
            // 记录变更日志
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
    public PageResult<IndexItemDO> getIndexItemPage(IndexItemPageReqVO pageReqVO) {
        return indexItemMapper.selectPage(pageReqVO);
    }

}