package cn.iocoder.yudao.module.evaluate.service.indexcategory;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexcategory.IndexCategoryMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import cn.iocoder.yudao.module.evaluate.service.indexsystem.IndexSystemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.INDEX_CATEGORY_NOT_EXISTS;

/**
 * 指标分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IndexCategoryServiceImpl implements IndexCategoryService {

    @Resource
    private IndexCategoryMapper indexCategoryMapper;

    @Resource
    private IndexItemMapper indexItemMapper;

    @Resource
    private IndexSystemService indexSystemService;

    @Override
    public Long createIndexCategory(IndexCategorySaveReqVO createReqVO) {
        // 插入
        IndexCategoryDO indexCategory = BeanUtils.toBean(createReqVO, IndexCategoryDO.class);
        indexCategoryMapper.insert(indexCategory);
        // 新增分类后，刷新所属指标体系的分类总数与指标项总数
        indexSystemService.refreshSystemCountsBySystemId(indexCategory.getSystemId());
        // 记录变更日志
        if (indexCategory.getSystemId() != null) {
            String logContent = StrUtil.format("体系名称：{}，分类名称：{}，分类权重：{}",
                    indexCategory.getSystemId(), indexCategory.getName(), indexCategory.getWeight());
            indexSystemService.updateChangeLog(indexCategory.getSystemId(), "CREATE", logContent);
        }
        // 返回
        return indexCategory.getId();
    }

    @Override
    public void updateIndexCategory(IndexCategorySaveReqVO updateReqVO) {
        // 校验存在，记录修改前所属体系
        IndexCategoryDO old = indexCategoryMapper.selectById(updateReqVO.getId());
        validateIndexCategoryExists(updateReqVO.getId());
        // 更新
        IndexCategoryDO updateObj = BeanUtils.toBean(updateReqVO, IndexCategoryDO.class);
        indexCategoryMapper.updateById(updateObj);
        // 更新新旧体系的分类总数与指标项总数（防止体系发生变更）
        if (old != null && old.getSystemId() != null) {
            indexSystemService.refreshSystemCountsBySystemId(old.getSystemId());
        }
        if (!Objects.equals(updateReqVO.getSystemId(), old != null ? old.getSystemId() : null)) {
            indexSystemService.refreshSystemCountsBySystemId(updateReqVO.getSystemId());
        }
        // 记录变更日志
        if (updateReqVO.getSystemId() != null) {
            // 构建变更内容
            StringJoiner changeContent = new StringJoiner("；");
            // 对比分类名称
            if (!Objects.equals(old.getName(), updateReqVO.getName())) {
                changeContent.add(StrUtil.format("分类名称由【{}】改为【{}】",
                        old.getName() != null ? old.getName() : "空",
                        updateReqVO.getName() != null ? updateReqVO.getName() : "空"));
            }
            // 对比分类权重
            if (!Objects.equals(old.getWeight(), updateReqVO.getWeight())) {
                changeContent.add(StrUtil.format("分类权重由【{}】改为【{}】",
                        old.getWeight() != null ? old.getWeight() : "空",
                        updateReqVO.getWeight() != null ? updateReqVO.getWeight() : "空"));
            }
            // 对比排序序号
            if (!Objects.equals(old.getSortNo(), updateReqVO.getSortNo())) {
                changeContent.add(StrUtil.format("排序序号由【{}】改为【{}】",
                        old.getSortNo() != null ? old.getSortNo() : "空",
                        updateReqVO.getSortNo() != null ? updateReqVO.getSortNo() : "空"));
            }
            // 对比体系ID（跨体系调整）
            if (!Objects.equals(old.getSystemId(), updateReqVO.getSystemId())) {
                changeContent.add(StrUtil.format("所属体系由【{}】改为【{}】",
                        old.getSystemId() != null ? old.getSystemId() : "空",
                        updateReqVO.getSystemId() != null ? updateReqVO.getSystemId() : "空"));
            }
            String logContent = changeContent.length() > 0 ? changeContent.toString() : "修改分类信息";
            indexSystemService.updateChangeLog(updateReqVO.getSystemId(), "UPDATE", logContent);
        }
    }

    @Override
    public void deleteIndexCategory(Long id) {
        // 校验存在，并记录所属体系
        IndexCategoryDO existing = indexCategoryMapper.selectById(id);
        validateIndexCategoryExists(id);

        // 1. 先删除该分类下的所有指标项
        List<IndexItemDO> items = indexItemMapper.selectList(
                new LambdaQueryWrapper<IndexItemDO>()
                        .eq(IndexItemDO::getCategoryId, existing.getCategoryId())
                        .eq(IndexItemDO::getDeleted, 0)
        );
        if (!items.isEmpty()) {
            List<Long> itemIds = items.stream()
                    .map(IndexItemDO::getId)
                    .collect(Collectors.toList());
            indexItemMapper.deleteBatchIds(itemIds);
        }

        // 2. 删除分类
        indexCategoryMapper.deleteById(id);

        // 3. 删除分类后，刷新所属体系的分类总数与指标项总数
        if (existing != null && existing.getSystemId() != null) {
            indexSystemService.refreshSystemCountsBySystemId(existing.getSystemId());
            // 记录变更日志
            String logContent = StrUtil.format("体系ID：{}，分类名称：{}，分类权重：{}",
                    existing.getSystemId(), existing.getName(), existing.getWeight());
            indexSystemService.updateChangeLog(existing.getSystemId(), "DELETE", logContent);
        }
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

}