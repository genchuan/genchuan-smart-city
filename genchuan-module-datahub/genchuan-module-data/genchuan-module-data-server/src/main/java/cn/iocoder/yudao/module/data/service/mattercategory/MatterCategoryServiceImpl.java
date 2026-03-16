package cn.iocoder.yudao.module.data.service.mattercategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.MatterCategoryDO;
import cn.iocoder.yudao.module.data.dal.mysql.mattercategory.MatterCategoryMapper;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.MATTER_CATEGORY_HAS_CHILDREN;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.MATTER_CATEGORY_NOT_EXISTS;

/**
 * 管理事项分类 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class MatterCategoryServiceImpl implements MatterCategoryService {

    @Resource
    private MatterCategoryMapper matterCategoryMapper;

    @Override
    public Long creatematterCategory(MatterCategorySaveReqVO createReqVO) {
        // 插入
        MatterCategoryDO matterCategory = BeanUtils.toBean(createReqVO, MatterCategoryDO.class);
        // 新增：自动生成36位长度的UUID作为管理事项分类ID
        matterCategory.setMatterCategoryId(java.util.UUID.randomUUID().toString());
        matterCategoryMapper.insert(matterCategory);
        // 返回
        return matterCategory.getId();
    }

    @Override
    public void updatematterCategory(MatterCategorySaveReqVO updateReqVO) {
        // 校验存在
        validatematterCategoryExists(updateReqVO.getId());
        // 更新
        MatterCategoryDO updateObj = BeanUtils.toBean(updateReqVO, MatterCategoryDO.class);
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
        List<MatterCategoryDO> categories = matterCategoryMapper.selectBatchIds(ids);
        if (categories.size() != ids.size()) {
            // 找出不存在的id
            Set<Long> foundIds = categories.stream().map(MatterCategoryDO::getId).collect(Collectors.toSet());
            List<Long> notExistIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toList());
            throw exception(MATTER_CATEGORY_NOT_EXISTS, notExistIds);
        }

        // 2. 校验是否包含子分类
        for (MatterCategoryDO category : categories) {
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
    public MatterCategoryDO getmatterCategory(Long id) {
        return matterCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<MatterCategoryDO> getmatterCategoryPage(MatterCategoryPageReqVO pageReqVO) {
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
    public List<MatterCategorySimpleTreeRespVO> getmatterCategorySimpleTree() {
        // 1. 查询出所有分类数据
        List<MatterCategoryDO> allCategories = matterCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 转换为简化树节点列表
        List<MatterCategorySimpleTreeRespVO> treeNodeList = allCategories.stream()
                .map(category -> {
                    MatterCategorySimpleTreeRespVO node = new MatterCategorySimpleTreeRespVO();
                    // 将 matterCategoryId 作为 id，符合您的测试数据
                    node.setId(category.getMatterCategoryId());
                    // 使用 categoryName 作为 label
                    node.setLabel(category.getCategoryName());
                    return node;
                })
                .collect(Collectors.toList());

        // 3. 构建 id 到节点的 Map，便于查找
        Map<String, MatterCategorySimpleTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(MatterCategorySimpleTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<MatterCategorySimpleTreeRespVO> rootList = new ArrayList<>();
        for (MatterCategorySimpleTreeRespVO node : treeNodeList) {
            // 获取当前节点对应的原始DO对象，以获取parentId
            MatterCategoryDO originalCategory = allCategories.stream()
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
                    MatterCategorySimpleTreeRespVO parentNode = nodeMap.get(parentIdStr);
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
    public List<Long> getSubMatterCategoryIds(String parentCategoryId, boolean includeSelf) {
        // 1. 尝试将参数作为业务ID查询分类
        MatterCategoryDO parentCategory = matterCategoryMapper.selectOne(
                new LambdaQueryWrapperX<MatterCategoryDO>()
                        .eq(MatterCategoryDO::getMatterCategoryId, parentCategoryId)
        );

        // 2. 如果没找到，尝试作为自增ID查询
        if (parentCategory == null) {
            try {
                // 尝试转换为Long（处理数字字符串）
                Long parentId = Long.parseLong(parentCategoryId);
                parentCategory = matterCategoryMapper.selectById(parentId);
            } catch (NumberFormatException e) {
                // 既不是业务ID也不是数字ID，返回空
                return Collections.emptyList();
            }
        }

        if (parentCategory == null) {
            return Collections.emptyList();
        }

        // 3. 获取该分类的所有子分类ID（递归）
        List<Long> allIds = new ArrayList<>();
        if (includeSelf) {
            allIds.add(parentCategory.getId());
        }

        // 递归获取子分类ID
        getChildrenIds(parentCategory.getId(), allIds);

        return allIds;
    }

    // 递归获取子分类ID
    private void getChildrenIds(Long parentId, List<Long> allIds) {
        // 1. 先根据自增ID获取父分类的业务ID
        MatterCategoryDO parentCategory = matterCategoryMapper.selectById(parentId);
        if (parentCategory == null) {
            return;
        }

        // 2. 使用业务ID查询子分类
        String parentBusinessId = parentCategory.getMatterCategoryId();
        List<MatterCategoryDO> children = matterCategoryMapper.selectList(
                new LambdaQueryWrapperX<MatterCategoryDO>()
                        .eq(MatterCategoryDO::getParentId, parentBusinessId)
        );

        for (MatterCategoryDO child : children) {
            allIds.add(child.getId());
            getChildrenIds(child.getId(), allIds); // 递归获取孙节点
        }
    }

}