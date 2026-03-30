package cn.iocoder.yudao.module.data.dal.mysql.monitorcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo.MonitorCategoryPageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorcategory.MonitorCategoryDO;
import com.alibaba.nacos.common.utils.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 监测部件分类 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface MonitorCategoryMapper extends BaseMapperX<MonitorCategoryDO> {

    default PageResult<MonitorCategoryDO> selectPage(MonitorCategoryPageReqVO reqVO) {
        LambdaQueryWrapperX<MonitorCategoryDO> queryWrapper = new LambdaQueryWrapperX<MonitorCategoryDO>()
                .likeIfPresent(MonitorCategoryDO::getName, reqVO.getName())
                .eqIfPresent(MonitorCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(MonitorCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(MonitorCategoryDO::getParentCategory, reqVO.getParentCategory())
                .eqIfPresent(MonitorCategoryDO::getCoreIndicators, reqVO.getCoreIndicators())
                .eqIfPresent(MonitorCategoryDO::getThresholdRules, reqVO.getThresholdRules())
                .eqIfPresent(MonitorCategoryDO::getCategoryType, reqVO.getCategoryType())
                .eqIfPresent(MonitorCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MonitorCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(MonitorCategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(MonitorCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(MonitorCategoryDO::getNotifyFlag, reqVO.getNotifyFlag())
                .eqIfPresent(MonitorCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MonitorCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonitorCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MonitorCategoryDO::getCreateTime, reqVO.getCreateTime());

        // 添加树形查询逻辑
        if (reqVO.getTreeParentId() != null && !reqVO.getTreeParentId().trim().isEmpty()) {
            // 获取指定节点及其所有子节点的ID列表
            List<String> treeNodeIds = getTreeNodeIds(reqVO.getTreeParentId(), reqVO.getIncludeSelf());
            if (CollectionUtils.isNotEmpty(treeNodeIds)) {
                // 将ID转换为Long类型进行查询
                List<Long> ids = treeNodeIds.stream()
                        .map(idStr -> {
                            try {
                                return Long.parseLong(idStr);
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                if (CollectionUtils.isNotEmpty(ids)) {
                    queryWrapper.in(MonitorCategoryDO::getId, ids);
                } else {
                    // 如果没有有效的ID，查询无结果
                    queryWrapper.eq(MonitorCategoryDO::getId, -1);
                }
            }
        }

        queryWrapper.orderByDesc(MonitorCategoryDO::getId);
        return selectPage(reqVO, queryWrapper);
    }

    /**
     * 根据父节点ID获取该节点及其所有子节点的ID列表
     *
     * @param parentId 父节点ID
     * @param includeSelf 是否包含自身
     * @return 节点ID列表
     */
    private List<String> getTreeNodeIds(String parentId, Boolean includeSelf) {
        // 查询所有分类数据
        List<MonitorCategoryDO> allCategories = selectList();
        if (CollectionUtils.isEmpty(allCategories)) {
            return Collections.emptyList();
        }

        // 构建ID到节点的映射
        Map<String, MonitorCategoryDO> categoryMap = allCategories.stream()
                .collect(Collectors.toMap(
                        category -> String.valueOf(category.getId()),
                        category -> category
                ));

        // 查找指定节点
        MonitorCategoryDO parentCategory = categoryMap.get(parentId);
        if (parentCategory == null) {
            return Collections.emptyList();
        }

        // 获取所有子节点ID（包括自身）
        List<String> allChildIds = new ArrayList<>();
        if (includeSelf == null || includeSelf) {
            allChildIds.add(parentId);
        }

        // 递归获取所有子节点
        getAllChildIds(parentId, categoryMap, allChildIds);

        return allChildIds;
    }

    /**
     * 递归获取所有子节点ID
     */
    private void getAllChildIds(String parentId, Map<String, MonitorCategoryDO> categoryMap, List<String> resultIds) {
        // 查找所有parentId为当前节点的子节点
        List<MonitorCategoryDO> children = categoryMap.values().stream()
                .filter(category -> parentId.equals(category.getParentId()))
                .collect(Collectors.toList());

        for (MonitorCategoryDO child : children) {
            String childId = String.valueOf(child.getId());
            resultIds.add(childId);
            // 递归获取孙子节点
            getAllChildIds(childId, categoryMap, resultIds);
        }
    }


    /**
     * 查询所有分类（用于构建树）
     * @return 全部分类列表
     */
    default List<MonitorCategoryDO> selectList() {
        return selectList(new LambdaQueryWrapperX<MonitorCategoryDO>()
                .orderByAsc(MonitorCategoryDO::getParentId) // 可按需调整排序
                .orderByAsc(MonitorCategoryDO::getId));
    }

}