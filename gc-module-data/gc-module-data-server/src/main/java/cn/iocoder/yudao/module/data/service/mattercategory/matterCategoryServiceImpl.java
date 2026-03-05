package cn.iocoder.yudao.module.data.service.mattercategory;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.mattercategory.matterCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 管理事项分类 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class matterCategoryServiceImpl implements matterCategoryService {

    @Resource
    private matterCategoryMapper matterCategoryMapper;

    @Override
    public Long creatematterCategory(matterCategorySaveReqVO createReqVO) {
        // 插入
        matterCategoryDO matterCategory = BeanUtils.toBean(createReqVO, matterCategoryDO.class);
        matterCategoryMapper.insert(matterCategory);
        // 返回
        return matterCategory.getId();
    }

    @Override
    public void updatematterCategory(matterCategorySaveReqVO updateReqVO) {
        // 校验存在
        validatematterCategoryExists(updateReqVO.getId());
        // 更新
        matterCategoryDO updateObj = BeanUtils.toBean(updateReqVO, matterCategoryDO.class);
        matterCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deletematterCategory(Long id) {
        // 校验存在
        validatematterCategoryExists(id);
        // 删除
        matterCategoryMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletematterCategories(List<Long> ids) {
        // 1. 校验存在性
        List<matterCategoryDO> categories = matterCategoryMapper.selectBatchIds(ids);
        if (categories.size() != ids.size()) {
            // 找出不存在的id
            Set<Long> foundIds = categories.stream().map(matterCategoryDO::getId).collect(Collectors.toSet());
            List<Long> notExistIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toList());
            throw exception(MATTER_CATEGORY_NOT_EXISTS, notExistIds);
        }

        // 2. 校验是否包含子分类
        for (matterCategoryDO category : categories) {
            // 检查是否有子节点（parentId指向当前分类）
            Long count = matterCategoryMapper.selectCountByParentId(category.getMatterCategoryId());
            if (count > 0) {
                throw exception(MATTER_CATEGORY_HAS_CHILDREN, category.getCategoryName());
            }
        }

        // 3. 执行删除
        matterCategoryMapper.deleteBatchIds(ids);
    }

    private void validatematterCategoryExists(Long id) {
        if (matterCategoryMapper.selectById(id) == null) {
            throw exception(MATTER_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public matterCategoryDO getmatterCategory(Long id) {
        return matterCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<matterCategoryDO> getmatterCategoryPage(matterCategoryPageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentId = pageReqVO.getTreeParentId();
        if (treeParentId != null && !treeParentId.trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            List<Long> subCategoryIds = getSubMatterCategoryIds(treeParentId, pageReqVO.getIncludeSelf());

            if (CollectionUtils.isEmpty(subCategoryIds)) {
                // 如果没有找到任何节点，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }

            return matterCategoryMapper.selectPageByCategoryIds(pageReqVO, subCategoryIds);
        }

        return matterCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<matterCategorySimpleTreeRespVO> getmatterCategorySimpleTree() {
        // 1. 查询出所有分类数据
        List<matterCategoryDO> allCategories = matterCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 转换为简化树节点列表
        List<matterCategorySimpleTreeRespVO> treeNodeList = allCategories.stream()
                .map(category -> {
                    matterCategorySimpleTreeRespVO node = new matterCategorySimpleTreeRespVO();
                    // 将 matterCategoryId 作为 id，符合您的测试数据
                    node.setId(category.getMatterCategoryId());
                    // 使用 categoryName 作为 label
                    node.setLabel(category.getCategoryName());
                    return node;
                })
                .collect(Collectors.toList());

        // 3. 构建 id 到节点的 Map，便于查找
        Map<String, matterCategorySimpleTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(matterCategorySimpleTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<matterCategorySimpleTreeRespVO> rootList = new ArrayList<>();
        for (matterCategorySimpleTreeRespVO node : treeNodeList) {
            // 获取当前节点对应的原始DO对象，以获取parentId
            matterCategoryDO originalCategory = allCategories.stream()
                    .filter(c -> c.getMatterCategoryId().equals(node.getId()))
                    .findFirst()
                    .orElse(null);

            if (originalCategory != null) {
                String parentIdStr = originalCategory.getParentId();
                // 判断是否为根节点 (parentId 为 null 或为空)
                if (parentIdStr == null || parentIdStr.trim().isEmpty()) {
                    rootList.add(node);
                } else {
                    // 使用 parentId 字符串查找父节点
                    matterCategorySimpleTreeRespVO parentNode = nodeMap.get(parentIdStr);
                    if (parentNode != null) {
                        // 初始化子列表
                        if (parentNode.getChildren() == null) {
                            parentNode.setChildren(new ArrayList<>());
                        }
                        parentNode.getChildren().add(node);
                    }
                    // 如果父节点不存在于本次查询结果中，则当前节点暂时作为"孤儿节点"处理，可根据业务决定是否加入根列表
                }
            }
        }
        return rootList;
    }

    @Override
    public List<Long> getSubMatterCategoryIds(String parentId, boolean includeSelf) {
        // 1. 查询出所有分类数据
        List<matterCategoryDO> allCategories = matterCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 构建父节点到子节点的映射
        Map<String, List<matterCategoryDO>> parentToChildrenMap = allCategories.stream()
                .filter(category -> category.getParentId() != null && !category.getParentId().trim().isEmpty())
                .collect(Collectors.groupingBy(matterCategoryDO::getParentId));

        // 3. 递归获取所有子节点ID
        List<Long> result = new ArrayList<>();
        if (includeSelf) {
            // 如果需要包含自身，先查找自身
            allCategories.stream()
                    .filter(category -> category.getMatterCategoryId().equals(parentId))
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
    private void getChildrenIdsRecursive(String parentId, Map<String, List<matterCategoryDO>> parentToChildrenMap, List<Long> result) {
        List<matterCategoryDO> children = parentToChildrenMap.get(parentId);
        if (children != null && !children.isEmpty()) {
            for (matterCategoryDO child : children) {
                result.add(child.getId());
                // 递归获取孙子节点
                getChildrenIdsRecursive(child.getMatterCategoryId(), parentToChildrenMap, result);
            }
        }
    }

}