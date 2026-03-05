package cn.iocoder.yudao.module.data.service.partcategory;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.data.controller.admin.partcategory.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.partcategory.CategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.partcategory.CategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 管理部件分类 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Long createCategory(CategorySaveReqVO createReqVO) {
        // 插入
        CategoryDO category = BeanUtils.toBean(createReqVO, CategoryDO.class);
        categoryMapper.insert(category);
        // 返回
        return category.getId();
    }

    @Override
    public void updateCategory(CategorySaveReqVO updateReqVO) {
        // 校验存在
        validateCategoryExists(updateReqVO.getId());
        // 更新
        CategoryDO updateObj = BeanUtils.toBean(updateReqVO, CategoryDO.class);
        categoryMapper.updateById(updateObj);
    }

//    @Override
//    public void deleteCategory(Long id) {
//        // 校验存在
//        validateCategoryExists(id);
//        // 删除
//        categoryMapper.deleteById(id);
//    }

    @Override
    public void deleteCategory(Long id) {
        // 校验存在
        validateCategoryExists(id);

        // 检查是否有子分类
        CategoryDO category = categoryMapper.selectById(id);
        if (category != null) {
            // 将Long类型的id转换为String类型
            String categoryIdStr = String.valueOf(id);
            List<String> parentIds = Collections.singletonList(categoryIdStr);
            boolean hasChildren = categoryMapper.hasChildrenByParentIds(parentIds);

            if (hasChildren) {
                throw exception(CATEGORY_HAS_CHILDREN);
            }
        }

        // 删除
        int rows = categoryMapper.deleteById(id);
        if (rows == 0) {
            throw exception(CATEGORY_DELETE_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategories(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 1. 校验所有分类是否存在
        List<CategoryDO> categories = categoryMapper.selectListByIds(ids);
        if (categories.size() != ids.size()) {
            throw exception(CATEGORY_NOT_EXISTS);
        }

        // 2. 检查是否有分类存在子分类
        // 获取所有分类的ID（转换为String类型，因为parentId是String类型）
        List<String> categoryIdStrs = categories.stream()
                .map(category -> String.valueOf(category.getId()))
                .collect(Collectors.toList());

        // 检查是否有子分类
        boolean hasChildren = categoryMapper.hasChildrenByParentIds(categoryIdStrs);
        if (hasChildren) {
            throw exception(CATEGORY_HAS_CHILDREN);
        }

        // 3. 执行批量删除
        int deletedCount = categoryMapper.deleteBatchIds(ids);
        if (deletedCount != ids.size()) {
            throw exception(CATEGORY_BATCH_DELETE_FAILED);
        }
    }

    private void validateCategoryExists(Long id) {
        if (categoryMapper.selectById(id) == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public CategoryDO getCategory(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public PageResult<CategoryDO> getCategoryPage(CategoryPageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentId = pageReqVO.getTreeParentId();
        if (treeParentId != null && !treeParentId.trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            List<Long> subCategoryIds = getSubCategoryIds(treeParentId, pageReqVO.getIncludeSelf());

            if (CollectionUtils.isEmpty(subCategoryIds)) {
                // 如果没有找到任何节点，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }

            return categoryMapper.selectPageByCategoryIds(pageReqVO, subCategoryIds);
        }

        return categoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CategorySimpleTreeRespVO> getCategorySimpleTree() {
        // 1. 查询出所有分类数据
        List<CategoryDO> allCategories = categoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 转换为简化树节点列表
        List<CategorySimpleTreeRespVO> treeNodeList = allCategories.stream()
                .map(category -> {
                    CategorySimpleTreeRespVO node = new CategorySimpleTreeRespVO();
                    // 将Long类型的id转换为String类型，符合前端要求
                    node.setId(String.valueOf(category.getId()));
                    // 使用categoryName作为label
                    node.setLabel(category.getCategoryName());
                    return node;
                })
                .collect(Collectors.toList());

        // 3. 构建 id 到节点的 Map，便于查找
        Map<String, CategorySimpleTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(CategorySimpleTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<CategorySimpleTreeRespVO> rootList = new ArrayList<>();
        for (CategorySimpleTreeRespVO node : treeNodeList) {
            // 获取当前节点对应的原始DO对象，以获取parentId
            CategoryDO originalCategory = allCategories.stream()
                    .filter(c -> String.valueOf(c.getId()).equals(node.getId()))
                    .findFirst()
                    .orElse(null);

            if (originalCategory != null) {
                String parentIdStr = originalCategory.getParentId();
                // 判断是否为根节点 (parentId 为 null 或为空字符串)
                if (parentIdStr == null || parentIdStr.trim().isEmpty()) {
                    rootList.add(node);
                } else {
                    // 直接使用parentId字符串查找父节点
                    CategorySimpleTreeRespVO parentNode = nodeMap.get(parentIdStr);
                    if (parentNode != null) {
                        // 初始化子列表
                        if (parentNode.getChildren() == null) {
                            parentNode.setChildren(new ArrayList<>());
                        }
                        parentNode.getChildren().add(node);
                    }
                    // 如果父节点不存在于本次查询结果中，则当前节点暂时作为"孤儿节点"处理
                }
            }
        }
        return rootList;
    }

    @Override
    public List<Long> getSubCategoryIds(String parentId, boolean includeSelf) {
        // 1. 查询出所有分类数据
        List<CategoryDO> allCategories = categoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 构建父节点到子节点的映射
        Map<String, List<CategoryDO>> parentToChildrenMap = allCategories.stream()
                .filter(category -> category.getParentId() != null && !category.getParentId().trim().isEmpty())
                .collect(Collectors.groupingBy(CategoryDO::getParentId));

        // 3. 递归获取所有子节点ID
        List<Long> result = new ArrayList<>();
        if (includeSelf) {
            // 如果需要包含自身，先查找自身
            allCategories.stream()
                    .filter(category -> String.valueOf(category.getId()).equals(parentId))
                    .findFirst()
                    .ifPresent(category -> result.add(category.getId()));
        }

        // 4. 递归获取子节点
        getChildrenIdsRecursive(parentId, parentToChildrenMap, result);

        return result;
    }

    /**
     * 递归获取子节点ID
     */
    private void getChildrenIdsRecursive(String parentId, Map<String, List<CategoryDO>> parentToChildrenMap, List<Long> result) {
        List<CategoryDO> children = parentToChildrenMap.get(parentId);
        if (children != null && !children.isEmpty()) {
            for (CategoryDO child : children) {
                result.add(child.getId());
                // 递归获取孙子节点
                getChildrenIdsRecursive(String.valueOf(child.getId()), parentToChildrenMap, result);
            }
        }
    }

}