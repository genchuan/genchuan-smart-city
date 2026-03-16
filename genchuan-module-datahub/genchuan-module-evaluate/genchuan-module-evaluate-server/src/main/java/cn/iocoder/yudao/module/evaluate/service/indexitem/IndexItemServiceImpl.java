package cn.iocoder.yudao.module.evaluate.service.indexitem;

import cn.hutool.core.lang.UUID;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

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

        // ========== 4. 返回主键ID ==========
        return indexItem.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIndexItem(IndexItemSaveReqVO updateReqVO) {
        // 1. 校验指标项是否存在
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
        IndexItemDO oldItem = indexItemMapper.selectById(updateReqVO.getId());
        updateObj.setItemId(oldItem.getItemId());
        // 5.3 执行更新
        indexItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndexItem(Long id) {
        // 校验存在
        validateIndexItemExists(id);
        // 删除
        indexItemMapper.deleteById(id);
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