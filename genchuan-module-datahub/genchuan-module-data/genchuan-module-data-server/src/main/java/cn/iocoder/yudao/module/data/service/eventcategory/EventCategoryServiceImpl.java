package cn.iocoder.yudao.module.data.service.eventcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventcategory.EventCategoryDO;
import cn.iocoder.yudao.module.data.dal.mysql.eventcategory.EventCategoryMapper;
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
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 监测事件分类 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EventCategoryServiceImpl implements EventCategoryService {

    @Resource
    private EventCategoryMapper eventCategoryMapper;

    @Override
    public Long createEventCategory(EventCategorySaveReqVO createReqVO) {
        // 插入
        EventCategoryDO eventCategory = BeanUtils.toBean(createReqVO, EventCategoryDO.class);
        eventCategoryMapper.insert(eventCategory);
        // 返回
        return eventCategory.getId();
    }

    @Override
    public void updateEventCategory(EventCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateEventCategoryExists(updateReqVO.getId());
        // 更新
        EventCategoryDO updateObj = BeanUtils.toBean(updateReqVO, EventCategoryDO.class);
        eventCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventCategory(Long id) {
        // 校验存在
        validateEventCategoryExists(id);
        // 删除
        eventCategoryMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEventCategories(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 1. 校验所有分类是否存在
        List<EventCategoryDO> categories = eventCategoryMapper.selectListByIds(ids);
        if (categories.size() != ids.size()) {
            throw exception(EVENT_CATEGORY_NOT_EXISTS);
        }

        // 2. 检查是否有分类存在子分类
        // 获取所有分类的ID（转换为String类型，因为parentId是String类型）
        List<String> categoryIdStrs = categories.stream()
                .map(category -> String.valueOf(category.getId()))
                .collect(Collectors.toList());

        // 检查是否有子分类
        boolean hasChildren = eventCategoryMapper.hasChildrenByParentIds(categoryIdStrs);
        if (hasChildren) {
            throw exception(EVENT_CATEGORY_HAS_CHILDREN);
        }

        // 3. 执行批量删除
        int deletedCount = eventCategoryMapper.deleteBatchIds(ids);
        if (deletedCount != ids.size()) {

            throw exception(EVENT_CATEGORY_BATCH_DELETE_FAILED);
        }
    }

    @Override
    public List<EventCategorySimpleTreeRespVO> getEventCategorySimpleTree() {
        // 1. 查询出所有分类数据
        List<EventCategoryDO> allCategories = eventCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 转换为简化树节点列表
        List<EventCategorySimpleTreeRespVO> treeNodeList = allCategories.stream()
                .map(category -> {
                    EventCategorySimpleTreeRespVO node = new EventCategorySimpleTreeRespVO();
                    // 将Long类型的id转换为String类型，符合前端要求
                    node.setId(String.valueOf(category.getId()));
                    // 使用categoryName作为label
                    node.setLabel(category.getCategoryName());
                    return node;
                })
                .collect(Collectors.toList());

        // 3. 构建 id 到节点的 Map，便于查找
        Map<String, EventCategorySimpleTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(EventCategorySimpleTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<EventCategorySimpleTreeRespVO> rootList = new ArrayList<>();
        for (EventCategorySimpleTreeRespVO node : treeNodeList) {
            // 获取当前节点对应的原始DO对象，以获取parentId
            EventCategoryDO originalCategory = allCategories.stream()
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
                    EventCategorySimpleTreeRespVO parentNode = nodeMap.get(parentIdStr);
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
    public PageResult<EventCategoryDO> getEventCategoryPage(EventCategoryPageReqVO pageReqVO) {
        // 处理树形查询参数
        String treeParentId = pageReqVO.getTreeParentId();
        if (treeParentId != null && !treeParentId.trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            List<Long> subCategoryIds = getSubCategoryIds(treeParentId, pageReqVO.getIncludeSelf());

            if (CollectionUtils.isEmpty(subCategoryIds)) {
                // 如果没有找到任何节点，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }

            return eventCategoryMapper.selectPageByCategoryIds(pageReqVO, subCategoryIds);
        }

        return eventCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<Long> getSubCategoryIds(String parentId, boolean includeSelf) {
        // 1. 查询出所有分类数据
        List<EventCategoryDO> allCategories = eventCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 构建父节点到子节点的映射
        Map<String, List<EventCategoryDO>> parentToChildrenMap = allCategories.stream()
                .filter(category -> category.getParentId() != null && !category.getParentId().trim().isEmpty())
                .collect(Collectors.groupingBy(EventCategoryDO::getParentId));

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
    private void getChildrenIdsRecursive(String parentId, Map<String, List<EventCategoryDO>> parentToChildrenMap, List<Long> result) {
        List<EventCategoryDO> children = parentToChildrenMap.get(parentId);
        if (children != null && !children.isEmpty()) {
            for (EventCategoryDO child : children) {
                result.add(child.getId());
                // 递归获取孙子节点
                getChildrenIdsRecursive(String.valueOf(child.getId()), parentToChildrenMap, result);
            }
        }
    }

    private void validateEventCategoryExists(Long id) {
        if (eventCategoryMapper.selectById(id) == null) {
            throw exception(EVENT_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public EventCategoryDO getEventCategory(Long id) {
        return eventCategoryMapper.selectById(id);
    }
}