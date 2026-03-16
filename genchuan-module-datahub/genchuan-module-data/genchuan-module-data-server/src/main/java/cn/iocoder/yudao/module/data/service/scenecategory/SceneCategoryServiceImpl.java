package cn.iocoder.yudao.module.data.service.scenecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.scenecategory.SceneCategoryDO;
import cn.iocoder.yudao.module.data.dal.mysql.scenecategory.SceneCategoryMapper;
import com.alibaba.nacos.common.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.SCENE_CATEGORY_HAS_CHILDREN;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.SCENE_CATEGORY_NOT_EXISTS;

/**
 * 应用场景分类 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class SceneCategoryServiceImpl implements SceneCategoryService {

    @Resource
    private SceneCategoryMapper sceneCategoryMapper;

    @Override
    public Long createSceneCategory(SceneCategorySaveReqVO createReqVO) {
        // 插入
        SceneCategoryDO sceneCategory = BeanUtils.toBean(createReqVO, SceneCategoryDO.class);
        sceneCategoryMapper.insert(sceneCategory);
        // 返回
        return sceneCategory.getId();
    }

    @Override
    public void updateSceneCategory(SceneCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateSceneCategoryExists(updateReqVO.getId());
        // 更新
        SceneCategoryDO updateObj = BeanUtils.toBean(updateReqVO, SceneCategoryDO.class);
        sceneCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteSceneCategory(Long id) {
        // 校验存在
        validateSceneCategoryExists(id);
        // 删除
        sceneCategoryMapper.deleteById(id);
    }

    private void validateSceneCategoryExists(Long id) {
        if (sceneCategoryMapper.selectById(id) == null) {
            throw exception(SCENE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public SceneCategoryDO getSceneCategory(Long id) {
        return sceneCategoryMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSceneCategories(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 1. 校验所有分类是否存在
        List<SceneCategoryDO> categories = sceneCategoryMapper.selectListByIds(ids);
        if (categories.size() != ids.size()) {
            throw exception(SCENE_CATEGORY_NOT_EXISTS);
        }

        // 2. 检查是否有分类存在子分类
        // 获取所有分类的ID（转换为String类型，因为parentId是String类型）
        List<String> categoryIdStrs = categories.stream()
                .map(category -> String.valueOf(category.getId()))
                .collect(Collectors.toList());

        // 检查是否有子分类
        boolean hasChildren = sceneCategoryMapper.hasChildrenByParentIds(categoryIdStrs);
        if (hasChildren) {
            throw exception(SCENE_CATEGORY_HAS_CHILDREN);
        }

        // 3. 执行批量删除
        int deletedCount = sceneCategoryMapper.deleteBatchIds(ids);
        if (deletedCount != ids.size()) {
            // 这里可以根据需要抛出更具体的异常，例如 SCENE_CATEGORY_BATCH_DELETE_FAILED
            throw exception(SCENE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public List<SceneCategorySimpleTreeRespVO> getSceneCategorySimpleTree() {
        // 1. 查询出所有分类数据
        List<SceneCategoryDO> allCategories = sceneCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 转换为简化树节点列表
        List<SceneCategorySimpleTreeRespVO> treeNodeList = allCategories.stream()
                .map(category -> {
                    SceneCategorySimpleTreeRespVO node = new SceneCategorySimpleTreeRespVO();
                    // 将Long类型的id转换为String类型，符合前端要求
                    node.setId(String.valueOf(category.getId()));
                    // 使用categoryName作为label
                    node.setLabel(category.getCategoryName());
                    return node;
                })
                .collect(Collectors.toList());

        // 3. 构建 id 到节点的 Map，便于查找
        Map<String, SceneCategorySimpleTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(SceneCategorySimpleTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<SceneCategorySimpleTreeRespVO> rootList = new ArrayList<>();
        for (SceneCategorySimpleTreeRespVO node : treeNodeList) {
            // 获取当前节点对应的原始DO对象，以获取parentId
            SceneCategoryDO originalCategory = allCategories.stream()
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
                    SceneCategorySimpleTreeRespVO parentNode = nodeMap.get(parentIdStr);
                    if (parentNode != null) {
                        // 初始化子列表
                        if (parentNode.getChildren() == null) {
                            parentNode.setChildren(new ArrayList<>());
                        }
                        parentNode.getChildren().add(node);
                    }
                    // 如果父节点不存在于本次查询结果中，则当前节点暂时作为“孤儿节点”处理
                }
            }
        }
        return rootList;
    }

    @Override
    public PageResult<SceneCategoryDO> getSceneCategoryPage(SceneCategoryPageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentId = pageReqVO.getTreeParentId();
        if (treeParentId != null && !treeParentId.trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            List<Long> subCategoryIds = getSubCategoryIds(treeParentId, pageReqVO.getIncludeSelf());

            if (CollectionUtils.isEmpty(subCategoryIds)) {
                // 如果没有找到任何节点，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }

            return sceneCategoryMapper.selectPageByCategoryIds(pageReqVO, subCategoryIds);
        }

        return sceneCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<Long> getSubCategoryIds(String parentId, boolean includeSelf) {
        // 1. 查询出所有分类数据
        List<SceneCategoryDO> allCategories = sceneCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 构建父节点到子节点的映射
        Map<String, List<SceneCategoryDO>> parentToChildrenMap = allCategories.stream()
                .filter(category -> category.getParentId() != null && !category.getParentId().trim().isEmpty())
                .collect(Collectors.groupingBy(SceneCategoryDO::getParentId));

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
    private void getChildrenIdsRecursive(String parentId, Map<String, List<SceneCategoryDO>> parentToChildrenMap, List<Long> result) {
        List<SceneCategoryDO> children = parentToChildrenMap.get(parentId);
        if (children != null && !children.isEmpty()) {
            for (SceneCategoryDO child : children) {
                result.add(child.getId());
                // 递归获取孙子节点
                getChildrenIdsRecursive(String.valueOf(child.getId()), parentToChildrenMap, result);
            }
        }
    }

}