package cn.iocoder.yudao.module.data.service.category;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.data.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.category.CategoryMapper;

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

    @Override
    public void deleteCategory(Long id) {
        // 校验存在
        validateCategoryExists(id);
        // 删除
        categoryMapper.deleteById(id);
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
        return categoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CategoryTreeRespVO> getCategoryTree() {
        // 1. 查询出所有分类数据
        List<CategoryDO> allCategories = categoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 使用 BeanUtils 将所有 DO 转换为 TreeRespVO
        List<CategoryTreeRespVO> treeNodeList = BeanUtils.toBean(allCategories, CategoryTreeRespVO.class);

        // 3. 构建 id 到节点的 Map，便于查找
        Map<Long, CategoryTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(CategoryTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<CategoryTreeRespVO> rootList = new ArrayList<>();
        for (CategoryTreeRespVO node : treeNodeList) {
            String parentIdStr = node.getParentId();
            // 判断是否为根节点 (parentId 为 null 或为空字符串)
            if (parentIdStr == null || parentIdStr.trim().isEmpty()) {
                rootList.add(node);
            } else {
                // 尝试将 parentId 转换为 Long，考虑到数据库中可能是数字字符串
                try {
                    Long parentId = Long.parseLong(parentIdStr);
                    CategoryTreeRespVO parentNode = nodeMap.get(parentId);
                    if (parentNode != null) {
                        // 初始化子列表
                        if (parentNode.getChildren() == null) {
                            parentNode.setChildren(new ArrayList<>());
                        }
                        parentNode.getChildren().add(node);
                    }
                    // 如果父节点不存在于本次查询结果中，则当前节点暂时作为“孤儿节点”处理，可根据业务决定是否加入根节点或忽略。
                } catch (NumberFormatException e) {
                    // 如果 parentId 不是有效数字，按父节点不存在处理
                    // 根据业务需要，可以记录日志或做其他处理
                }
            }
        }
        return rootList;
    }

}