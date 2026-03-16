package cn.iocoder.yudao.module.data.service.monitorcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorcategory.MonitorCategoryDO;
import cn.iocoder.yudao.module.data.dal.mysql.monitorcategory.MonitorCategoryMapper;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.MONITOR_CATEGORY_DELETE_FAIL_CHILDREN_EXISTS;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.MONITOR_CATEGORY_NOT_EXISTS;

/**
 * 监测部件分类 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class MonitorCategoryServiceImpl implements MonitorCategoryService {

    @Resource
    private MonitorCategoryMapper monitorCategoryMapper;

    @Override
    public Long createMonitorCategory(MonitorCategorySaveReqVO createReqVO) {
        // 插入
        MonitorCategoryDO monitorCategory = BeanUtils.toBean(createReqVO, MonitorCategoryDO.class);
        monitorCategoryMapper.insert(monitorCategory);
        // 返回
        return monitorCategory.getId();
    }

    @Override
    public void updateMonitorCategory(MonitorCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateMonitorCategoryExists(updateReqVO.getId());
        // 更新
        MonitorCategoryDO updateObj = BeanUtils.toBean(updateReqVO, MonitorCategoryDO.class);
        monitorCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitorCategory(Long id) {
        // 校验存在
        validateMonitorCategoryExists(id);
        // 删除
        monitorCategoryMapper.deleteById(id);
    }

    @Override
    public void deleteMonitorCategories(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 1. 校验每个分类是否存在
        for (Long id : ids) {
            validateMonitorCategoryExists(id);
        }

        // 2. 检查是否有子分类存在
        List<MonitorCategoryDO> allCategories = monitorCategoryMapper.selectList();
        Set<Long> idsSet = new HashSet<>(ids);

        for (Long id : ids) {
            // 检查该分类是否有子分类
            boolean hasChildren = allCategories.stream()
                    .anyMatch(category ->
                            id.toString().equals(category.getParentId()) &&
                                    !idsSet.contains(category.getId()));

            if (hasChildren) {
                MonitorCategoryDO category = monitorCategoryMapper.selectById(id);
                throw exception(MONITOR_CATEGORY_DELETE_FAIL_CHILDREN_EXISTS, category.getName());
            }
        }

        // 3. 批量删除
        monitorCategoryMapper.deleteBatchIds(ids);
    }


    private void validateMonitorCategoryExists(Long id) {
        if (monitorCategoryMapper.selectById(id) == null) {
            throw exception(MONITOR_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public MonitorCategoryDO getMonitorCategory(Long id) {
        return monitorCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<MonitorCategoryDO> getMonitorCategoryPage(MonitorCategoryPageReqVO pageReqVO) {
        return monitorCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MonitorCategorySimpleTreeRespVO> getMonitorCategorySimpleTree() {
        // 1. 查询出所有监测分类数据
        List<MonitorCategoryDO> allCategories = monitorCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 2. 转换为简化树节点列表
        List<MonitorCategorySimpleTreeRespVO> treeNodeList = allCategories.stream()
                .map(category -> {
                    MonitorCategorySimpleTreeRespVO node = new MonitorCategorySimpleTreeRespVO();
                    // 将Long类型的id转换为String类型，符合前端要求
                    node.setId(String.valueOf(category.getId()));
                    // 使用name作为label
                    node.setLabel(category.getName());
                    return node;
                })
                .collect(Collectors.toList());

        // 3. 构建 id 到节点的 Map，便于查找
        Map<String, MonitorCategorySimpleTreeRespVO> nodeMap = treeNodeList.stream()
                .collect(Collectors.toMap(MonitorCategorySimpleTreeRespVO::getId, node -> node));

        // 4. 构建树形结构
        List<MonitorCategorySimpleTreeRespVO> rootList = new ArrayList<>();
        for (MonitorCategorySimpleTreeRespVO node : treeNodeList) {
            // 获取当前节点对应的原始DO对象，以获取parentId
            MonitorCategoryDO originalCategory = allCategories.stream()
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
                    MonitorCategorySimpleTreeRespVO parentNode = nodeMap.get(parentIdStr);
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
    public List<Long> getCategoryAndChildrenIds(Long parentId) {
        // 1. 查询所有分类，构建ID与父ID的映射关系
        List<MonitorCategoryDO> allCategories = monitorCategoryMapper.selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 使用Map加速查找：Key为父ID，Value为该父ID下的所有直接子分类列表
        Map<String, List<MonitorCategoryDO>> parentChildrenMap = allCategories.stream()
                .collect(Collectors.groupingBy(
                        category -> category.getParentId() == null ? "" : category.getParentId(),
                        Collectors.toList()
                ));

        // 2. 递归收集所有子孙分类ID
        Set<Long> resultIds = new HashSet<>();
        // 如果includeSelf的逻辑在调用方控制，这里默认包含自身。调用方（MonitorInstanceServiceImpl）可以根据 pageReqVO.getIncludeSelf() 决定是否添加 parentId 自身。
        // 为简化，此方法始终包含传入的parentId节点自身。调用方可根据需要处理。
        if (parentId != null) {
            resultIds.add(parentId);
            collectChildrenIds(String.valueOf(parentId), parentChildrenMap, resultIds);
        }
        return new ArrayList<>(resultIds);
    }

    /**
     * 递归收集子分类ID
     * @param currentParentIdStr 当前父ID (String类型，因为数据库中是String)
     * @param parentChildrenMap 父->子列表映射
     * @param resultIds 结果集
     */
    private void collectChildrenIds(String currentParentIdStr, Map<String, List<MonitorCategoryDO>> parentChildrenMap, Set<Long> resultIds) {
        List<MonitorCategoryDO> children = parentChildrenMap.get(currentParentIdStr);
        if (!CollectionUtils.isEmpty(children)) {
            for (MonitorCategoryDO child : children) {
                Long childId = child.getId();
                if (resultIds.add(childId)) { // 避免循环引用
                    collectChildrenIds(String.valueOf(childId), parentChildrenMap, resultIds);
                }
            }
        }
    }

}