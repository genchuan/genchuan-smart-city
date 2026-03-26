package cn.iocoder.yudao.module.evaluate.service.standardcategory;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo.*;
import cn.iocoder.yudao.module.evaluate.controller.admin.standarditem.vo.StandardItemRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.standarditem.vo.StandardItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.standardcategory.StandardCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem.StandardItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.standardcategory.StandardCategoryMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.evalsystem.standarditem.StandardItemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.status.StatusMapper;
import cn.iocoder.yudao.module.evaluate.service.standarditem.StandardItemService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.STANDARD_CATEGORY_NOT_EXISTS;

/**
 * 标准分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StandardCategoryServiceImpl implements StandardCategoryService {

    @Resource
    private StandardCategoryMapper standardCategoryMapper;

    @Resource
    private StandardItemService standardItemService;

    @Resource
    private StandardItemMapper standardItemMapper;

    @Resource
    private StatusMapper statusMapper;

    @Resource
    private IndexSystemMapper indexSystemMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createStandardCategoryWithItems(StandardCategorySaveReqVO createReqVO) {
        // 插入分类
        StandardCategoryDO standardCategory = BeanUtils.toBean(createReqVO, StandardCategoryDO.class);
        standardCategoryMapper.insert(standardCategory);
        Long categoryId = standardCategory.getId();

        // 插入标准项列表
        List<StandardItemSaveReqVO> items = createReqVO.getItems();
        if (CollUtil.isNotEmpty(items)) {
            for (StandardItemSaveReqVO itemVO : items) {
                StandardItemDO itemDO = BeanUtils.toBean(itemVO, StandardItemDO.class);
                itemDO.setStandardCategoryId(String.valueOf(categoryId));
                standardItemMapper.insert(itemDO);
            }
        }

        return categoryId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStandardCategoryWithItems(StandardCategorySaveReqVO updateReqVO) {
        Long categoryId = updateReqVO.getId();
        // 校验分类存在
        validateStandardCategoryExists(categoryId);

        // 更新分类基本信息
        StandardCategoryDO updateObj = BeanUtils.toBean(updateReqVO, StandardCategoryDO.class);
        standardCategoryMapper.updateById(updateObj);

        // 处理标准项列表（先删后插）
        List<StandardItemSaveReqVO> items = updateReqVO.getItems();
        if (items == null) {
            items = Collections.emptyList();
        }

        // 查询已有的标准项
        List<StandardItemDO> existingItems = standardItemMapper.selectByStandardCategoryIds(
                Collections.singletonList(categoryId));

        if (items.isEmpty()) {
            // 提交为空，全部删除
            if (CollUtil.isNotEmpty(existingItems)) {
                standardItemMapper.deleteByStandardCategoryIds(
                        Collections.singletonList(categoryId));
            }
        } else {
            // 增量更新
            Set<Long> existingIds = existingItems.stream()
                    .map(StandardItemDO::getId)
                    .collect(Collectors.toSet());
            Set<Long> submittedIds = items.stream()
                    .map(StandardItemSaveReqVO::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            // 删除已移除的标准项
            for (Long existingId : existingIds) {
                if (!submittedIds.contains(existingId)) {
                    standardItemMapper.deleteById(existingId);
                }
            }

            // 遍历处理每个标准项（新增/更新）
            for (StandardItemSaveReqVO itemVO : items) {
                if (itemVO.getId() == null) {
                    StandardItemDO itemDO = BeanUtils.toBean(itemVO, StandardItemDO.class);
                    itemDO.setStandardCategoryId(String.valueOf(categoryId));
                    standardItemMapper.insert(itemDO);
                } else {
                    standardItemMapper.updateById(BeanUtils.toBean(itemVO, StandardItemDO.class));
                }
            }
        }
    }

    @Override
    public void deleteStandardCategory(Long id) {
        // 校验存在
        validateStandardCategoryExists(id);
        // 级联删除关联的标准项
        standardItemService.deleteStandardItemListByStandardCategoryIds(
                Collections.singletonList(id));
        // 删除分类
        standardCategoryMapper.deleteById(id);
    }

    @Override
    public void deleteStandardCategoryListByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 级联删除关联的标准项
        standardItemMapper.deleteByStandardCategoryIds(ids);
        // 批量删除分类
        standardCategoryMapper.deleteByIds(ids);
    }


    private void validateStandardCategoryExists(Long id) {
        if (standardCategoryMapper.selectById(id) == null) {
            throw exception(STANDARD_CATEGORY_NOT_EXISTS);
        }
    }

    /**
     * 批量解析用户姓名
     */
    private void resolveUserNames(List<? extends StandardCategoryRespVO> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        Set<String> userIds = list.stream()
                .flatMap(vo -> Stream.of(vo.getCreator(), vo.getUpdater()))
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = CollUtil.isNotEmpty(userIds)
                ? adminUserApi.getUserMap(userIds.stream().map(Long::parseLong).collect(Collectors.toSet()))
                : new HashMap<>();
        for (StandardCategoryRespVO vo : list) {
            if (StrUtil.isNotBlank(vo.getCreator())) {
                AdminUserRespDTO creator = userMap.get(Long.parseLong(vo.getCreator()));
                vo.setCreatorName(creator != null ? creator.getNickname() : vo.getCreator());
            }
            if (StrUtil.isNotBlank(vo.getUpdater())) {
                AdminUserRespDTO updater = userMap.get(Long.parseLong(vo.getUpdater()));
                vo.setUpdaterName(updater != null ? updater.getNickname() : vo.getUpdater());
            }
        }
    }

    @Override
    public StandardCategoryDO getStandardCategory(Long id) {
        return standardCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<StandardCategoryDO> getStandardCategoryPage(StandardCategoryPageReqVO pageReqVO) {
        return standardCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<StandardCategoryRespVO> getStandardCategoryPageWithJoin(StandardCategoryPageReqVO pageReqVO) {
        Page<StandardCategoryRespVO> page = new Page<>(
                pageReqVO.getPageNo() != null ? pageReqVO.getPageNo() : 1,
                pageReqVO.getPageSize() != null ? pageReqVO.getPageSize() : 10
        );
        IPage<StandardCategoryRespVO> resultPage = standardCategoryMapper.selectJoinPage(page, pageReqVO);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    public StandardCategoryRespVO getStandardCategoryWithItems(Long id) {
        StandardCategoryDO category = standardCategoryMapper.selectById(id);
        if (category == null) {
            return null;
        }
        List<StandardItemDO> items = standardItemMapper.selectByStandardCategoryIds(
                Collections.singletonList(id));
        StandardCategoryRespVO respVO = BeanUtils.toBean(category, StandardCategoryRespVO.class);
        // 填充体系名称和状态名称
        if (category.getSystemId() != null) {
            List<IndexSystemDO> systems = standardCategoryMapper.selectSystemByIds(
                    Collections.singletonList(category.getSystemId()));
            if (CollUtil.isNotEmpty(systems)) {
                respVO.setSystemName(systems.get(0).getName());
            }
        }
        if (category.getStatusId() != null) {
            List<StatusDO> statusList = statusMapper.selectByIds(
                    Collections.singletonList(category.getStatusId().longValue()));
            if (CollUtil.isNotEmpty(statusList)) {
                respVO.setStatusName(statusList.get(0).getName());
            }
        }
        // 填充用户姓名
        resolveUserNames(Collections.singletonList(respVO));
        // 转换并设置标准项
        List<StandardItemRespVO> itemVOList = BeanUtils.toBean(items, StandardItemRespVO.class);
        if (CollUtil.isNotEmpty(itemVOList)) {
            resolveItemUserNames(itemVOList);
        }
        respVO.setItems(itemVOList);
        return respVO;
    }

    /**
     * 填充标准项的用户姓名
     */
    private void resolveItemUserNames(List<StandardItemRespVO> items) {
        if (CollUtil.isEmpty(items)) {
            return;
        }
        Set<String> userIds = items.stream()
                .flatMap(item -> Stream.of(item.getCreator(), item.getUpdater()))
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = CollUtil.isNotEmpty(userIds)
                ? adminUserApi.getUserMap(userIds.stream().map(Long::parseLong).collect(Collectors.toSet()))
                : new HashMap<>();
        for (StandardItemRespVO item : items) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                AdminUserRespDTO creator = userMap.get(Long.parseLong(item.getCreator()));
                item.setCreatorName(creator != null ? creator.getNickname() : item.getCreator());
            }
            if (StrUtil.isNotBlank(item.getUpdater())) {
                AdminUserRespDTO updater = userMap.get(Long.parseLong(item.getUpdater()));
                item.setUpdaterName(updater != null ? updater.getNickname() : item.getUpdater());
            }
        }
    }

    @Override
    public StandardCategoryStatisticsVO getStandardCategoryStatistics() {
        StandardCategoryStatisticsVO vo = new StandardCategoryStatisticsVO();

        // ========== 1. 卡片数据 ==========
        StandardCategoryStatisticsVO.CardData cardData = new StandardCategoryStatisticsVO.CardData();
        Map<String, Object> categoryCountMap = standardCategoryMapper.selectTotalCategoryCount();
        Long totalCategoryCount = categoryCountMap.get("totalCategoryCount") != null
                ? ((Number) categoryCountMap.get("totalCategoryCount")).longValue() : 0L;
        Map<String, Object> itemCountMap = standardCategoryMapper.selectTotalItemCount();
        Long totalItemCount = itemCountMap.get("totalItemCount") != null
                ? ((Number) itemCountMap.get("totalItemCount")).longValue() : 0L;
        Map<String, Object> enabledCountMap = standardCategoryMapper.selectEnabledCategoryCount();
        Long enabledCategoryCount = enabledCountMap.get("enabledCategoryCount") != null
                ? ((Number) enabledCountMap.get("enabledCategoryCount")).longValue() : 0L;
        cardData.setTotalCategoryCount(totalCategoryCount);
        cardData.setTotalItemCount(totalItemCount);
        cardData.setEnabledCategoryCount(enabledCategoryCount);
        vo.setCardData(cardData);

        // ========== 2. 状态圆环图 ==========
        List<Map<String, Object>> statusGroupList = standardCategoryMapper.selectStatusGroupCount();
        if (CollUtil.isNotEmpty(statusGroupList)) {
            Set<Long> statusIds = new HashSet<>();
            for (Map<String, Object> item : statusGroupList) {
                Object statusObj = item.get("status_id");
                if (statusObj != null) {
                    statusIds.add(((Number) statusObj).longValue());
                }
            }
            Map<Long, String> statusNameMap = new HashMap<>();
            if (CollUtil.isNotEmpty(statusIds)) {
                List<StatusDO> statusList = statusMapper.selectByIds(new ArrayList<>(statusIds));
                if (statusList != null) {
                    statusNameMap = statusList.stream()
                            .collect(Collectors.toMap(StatusDO::getId, StatusDO::getName, (a, b) -> a));
                }
            }
            Map<Long, String> finalStatusNameMap = statusNameMap;
            List<StandardCategoryStatisticsVO.PieChartItem> statusPieChart = statusGroupList.stream()
                    .map(item -> {
                        StandardCategoryStatisticsVO.PieChartItem pieItem = new StandardCategoryStatisticsVO.PieChartItem();
                        Object statusObj = item.get("status_id");
                        Long statusId = statusObj != null ? ((Number) statusObj).longValue() : null;
                        pieItem.setName(finalStatusNameMap.getOrDefault(statusId, statusId != null ? String.valueOf(statusId) : "未知"));
                        pieItem.setValue(((Number) item.get("categoryCount")).longValue());
                        return pieItem;
                    })
                    .collect(Collectors.toList());
            vo.setStatusPieChart(statusPieChart);
        } else {
            vo.setStatusPieChart(new ArrayList<>());
        }

        // ========== 3. 指标体系圆环图 ==========
        List<Map<String, Object>> systemGroupList = standardCategoryMapper.selectSystemGroupCount();
        if (CollUtil.isNotEmpty(systemGroupList)) {
            Set<Long> systemIds = new HashSet<>();
            for (Map<String, Object> item : systemGroupList) {
                Object systemIdObj = item.get("system_id");
                if (systemIdObj != null) {
                    systemIds.add(((Number) systemIdObj).longValue());
                }
            }
            Map<Long, String> systemNameMap = new HashMap<>();
            if (CollUtil.isNotEmpty(systemIds)) {
                List<IndexSystemDO> systemList = standardCategoryMapper.selectSystemByIds(new ArrayList<>(systemIds));
                if (systemList != null) {
                    systemNameMap = systemList.stream()
                            .collect(Collectors.toMap(IndexSystemDO::getId, IndexSystemDO::getName, (a, b) -> a));
                }
            }
            Map<Long, String> finalSystemNameMap = systemNameMap;
            List<StandardCategoryStatisticsVO.PieChartItem> systemPieChart = systemGroupList.stream()
                    .map(item -> {
                        StandardCategoryStatisticsVO.PieChartItem pieItem = new StandardCategoryStatisticsVO.PieChartItem();
                        Object systemIdObj = item.get("system_id");
                        Long systemId = systemIdObj != null ? ((Number) systemIdObj).longValue() : null;
                        pieItem.setName(finalSystemNameMap.getOrDefault(systemId, systemId != null ? String.valueOf(systemId) : "未知"));
                        pieItem.setValue(((Number) item.get("categoryCount")).longValue());
                        return pieItem;
                    })
                    .collect(Collectors.toList());
            vo.setSystemPieChart(systemPieChart);
        } else {
            vo.setSystemPieChart(new ArrayList<>());
        }

        // ========== 4. 等级分布圆环图 ==========
        List<Map<String, Object>> gradeGroupList = standardCategoryMapper.selectGradeGroupCount();
        if (CollUtil.isNotEmpty(gradeGroupList)) {
            List<StandardCategoryStatisticsVO.PieChartItem> gradePieChart = gradeGroupList.stream()
                    .map(item -> {
                        StandardCategoryStatisticsVO.PieChartItem pieItem = new StandardCategoryStatisticsVO.PieChartItem();
                        pieItem.setName(item.get("grade") != null ? String.valueOf(item.get("grade")) : "未知");
                        pieItem.setValue(((Number) item.get("itemCount")).longValue());
                        return pieItem;
                    })
                    .collect(Collectors.toList());
            vo.setGradePieChart(gradePieChart);
        } else {
            vo.setGradePieChart(new ArrayList<>());
        }

        // ========== 5. 柱状图：按分类统计标准项数量 ==========
        List<Map<String, Object>> categoryItemCountList = standardCategoryMapper.selectCategoryItemCount();
        List<StandardCategoryDO> categories = standardCategoryMapper.selectList(
                new LambdaQueryWrapperX<StandardCategoryDO>()
                        .eq(StandardCategoryDO::getDeleted, false)
                        .orderByDesc(StandardCategoryDO::getId)
        );
        Map<Long, Long> categoryItemCountMap = new HashMap<>();
        for (Map<String, Object> item : categoryItemCountList) {
            Object categoryIdObj = item.get("standard_category_id");
            Object countObj = item.get("itemCount");
            if (categoryIdObj != null && countObj != null) {
                categoryItemCountMap.put(((Number) categoryIdObj).longValue(), ((Number) countObj).longValue());
            }
        }
        Map<Long, String> categoryNameMap = categories.stream()
                .collect(Collectors.toMap(StandardCategoryDO::getId, StandardCategoryDO::getName, (a, b) -> a));

        List<StandardCategoryStatisticsVO.BarChartItem> categoryBarChart = categoryItemCountList.stream()
                .map(item -> {
                    StandardCategoryStatisticsVO.BarChartItem barItem = new StandardCategoryStatisticsVO.BarChartItem();
                    Object categoryIdObj = item.get("standard_category_id");
                    Long categoryId = categoryIdObj != null ? ((Number) categoryIdObj).longValue() : null;
                    barItem.setCategoryName(categoryNameMap.getOrDefault(categoryId, categoryId != null ? String.valueOf(categoryId) : "未知"));
                    barItem.setItemCount(((Number) item.get("itemCount")).longValue());
                    return barItem;
                })
                .collect(Collectors.toList());
        vo.setCategoryBarChart(categoryBarChart);

        // ========== 6. 明细列表 ==========
        if (CollUtil.isEmpty(categories)) {
            vo.setCategoryList(new ArrayList<>());
            return vo;
        }

        // ========== 7. 批量查询体系名称和状态名称 ==========
        Set<Long> systemIdSet = categories.stream()
                .map(StandardCategoryDO::getSystemId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, String> systemNameByIdMap = new HashMap<>();
        if (CollUtil.isNotEmpty(systemIdSet)) {
            List<IndexSystemDO> systems = standardCategoryMapper.selectSystemByIds(new ArrayList<>(systemIdSet));
            if (systems != null) {
                systemNameByIdMap = systems.stream()
                        .collect(Collectors.toMap(IndexSystemDO::getId, IndexSystemDO::getName, (a, b) -> a));
            }
        }

        Set<Integer> statusIdSet = categories.stream()
                .map(StandardCategoryDO::getStatusId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, String> statusNameByIdMap = new HashMap<>();
        if (CollUtil.isNotEmpty(statusIdSet)) {
            List<StatusDO> statusList = statusMapper.selectByIds(
                    statusIdSet.stream().map(Integer::longValue).collect(Collectors.toList()));
            if (statusList != null) {
                statusNameByIdMap = statusList.stream()
                        .collect(Collectors.toMap(StatusDO::getId, StatusDO::getName, (a, b) -> a));
            }
        }

        // ========== 8. 查询所有标准项并按分类ID分组 ==========
        List<StandardItemDO> allItems = standardItemMapper.selectList(
                new LambdaQueryWrapperX<StandardItemDO>()
                        .eq(StandardItemDO::getDeleted, false)
        );
        Map<String, List<StandardItemDO>> itemsByCategory = allItems.stream()
                .collect(Collectors.groupingBy(StandardItemDO::getStandardCategoryId));

        // ========== 9. 组装明细数据 ==========
        final Map<Long, String> finalSystemNameByIdMap = systemNameByIdMap;
        final Map<Long, String> finalStatusNameByIdMap = statusNameByIdMap;
        List<StandardCategoryStatisticsVO.CategoryStatistics> categoryStatisticsList = categories.stream()
                .map(category -> {
                    StandardCategoryStatisticsVO.CategoryStatistics catStat = new StandardCategoryStatisticsVO.CategoryStatistics();
                    catStat.setCategoryId(category.getId());
                    catStat.setCategoryName(category.getName());
                    catStat.setSystemName(finalSystemNameByIdMap.get(category.getSystemId()));
                    catStat.setStatusName(finalStatusNameByIdMap.get(category.getStatusId() != null ? category.getStatusId().longValue() : null));
                    List<StandardItemDO> categoryItems = itemsByCategory.getOrDefault(String.valueOf(category.getId()), Collections.emptyList());
                    catStat.setItemCount((long) categoryItems.size());
                    List<StandardCategoryStatisticsVO.ItemStatistics> itemStatisticsList = categoryItems.stream()
                            .map(item -> {
                                StandardCategoryStatisticsVO.ItemStatistics itemStat = new StandardCategoryStatisticsVO.ItemStatistics();
                                itemStat.setItemId(item.getId());
                                itemStat.setGrade(item.getGrade());
                                itemStat.setScoreRange(item.getScoreRange());
                                return itemStat;
                            })
                            .collect(Collectors.toList());
                    catStat.setItems(itemStatisticsList);
                    return catStat;
                })
                .collect(Collectors.toList());
        vo.setCategoryList(categoryStatisticsList);
        return vo;
    }
    @Override
    public List<StandardCategoryExportVO> getStandardCategoryExportListWithItems(StandardCategoryPageReqVO pageReqVO) {
        // 1. 查询所有分类（分页 pageSize 为空时导出全部）
        Page<StandardCategoryRespVO> page = new Page<>(1, PageParam.PAGE_SIZE_NONE);
        IPage<StandardCategoryRespVO> resultPage = standardCategoryMapper.selectJoinPage(page, pageReqVO);
        if (resultPage == null || CollUtil.isEmpty(resultPage.getRecords())) {
            return new ArrayList<>();
        }
        List<StandardCategoryRespVO> categoryList = resultPage.getRecords();

        // 2. 批量查询体系名称和状态名称
        Set<Long> systemIdSet = categoryList.stream()
                .map(StandardCategoryRespVO::getSystemId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, String> systemNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(systemIdSet)) {
            List<IndexSystemDO> systems = standardCategoryMapper.selectSystemByIds(new ArrayList<>(systemIdSet));
            if (CollUtil.isNotEmpty(systems)) {
                systemNameMap = systems.stream()
                        .collect(Collectors.toMap(IndexSystemDO::getId, IndexSystemDO::getName, (a, b) -> a));
            }
        }

        Set<Integer> statusIdSet = categoryList.stream()
                .map(StandardCategoryRespVO::getStatusId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, String> statusNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(statusIdSet)) {
            List<StatusDO> statusList = statusMapper.selectByIds(
                    statusIdSet.stream().map(Integer::longValue).collect(Collectors.toList()));
            if (CollUtil.isNotEmpty(statusList)) {
                statusNameMap = statusList.stream()
                        .collect(Collectors.toMap(StatusDO::getId, StatusDO::getName, (a, b) -> a));
            }
        }

        // 3. 批量查询标准项
        List<Long> categoryIds = categoryList.stream()
                .map(StandardCategoryRespVO::getId)
                .collect(Collectors.toList());
        List<StandardItemDO> allItems = standardItemMapper.selectByStandardCategoryIds(categoryIds);

        // 4. 按分类 ID 分组
        Map<String, List<StandardItemDO>> itemsByCategory = allItems.stream()
                .collect(Collectors.groupingBy(StandardItemDO::getStandardCategoryId));

        // 5. 转换标准项用户姓名
        List<StandardItemRespVO> allItemVOs = BeanUtils.toBean(allItems, StandardItemRespVO.class);
        if (CollUtil.isNotEmpty(allItemVOs)) {
            resolveItemUserNames(allItemVOs);
        }

        // 6. 按分类 ID 分组标准项 VO
        Map<String, List<StandardItemRespVO>> itemVOsByCategory = allItemVOs.stream()
                .collect(Collectors.groupingBy(StandardItemRespVO::getStandardCategoryId));

        // 7. 组装导出 VO
        final Map<Long, String> finalSystemNameMap = systemNameMap;
        final Map<Long, String> finalStatusNameMap = statusNameMap;
        return categoryList.stream().map(category -> {
            StandardCategoryExportVO exportVO = BeanUtils.toBean(category, StandardCategoryExportVO.class);
            exportVO.setSystemName(finalSystemNameMap.get(category.getSystemId()));
            exportVO.setStatusName(finalStatusNameMap.get(
                    category.getStatusId() != null ? category.getStatusId().longValue() : null));
            List<StandardItemRespVO> catItems = itemVOsByCategory.getOrDefault(String.valueOf(category.getId()), Collections.emptyList());
            // 将标准项列表格式化为字符串
            String itemDetails = catItems.stream()
                    .map(item -> String.format("%s(等级:%s, 分数:%s)",
                            item.getId(), item.getGrade(), item.getScoreRange()))
                    .collect(Collectors.joining("; "));
            exportVO.setItemDetails(itemDetails);
            return exportVO;
        }).collect(Collectors.toList());
    }

}