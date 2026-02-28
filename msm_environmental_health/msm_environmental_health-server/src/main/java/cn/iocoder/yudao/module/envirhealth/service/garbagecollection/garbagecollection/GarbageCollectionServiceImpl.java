package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionImportReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionImportRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.all.GarbageCollectionCardAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.executing.GarbageCollectionCardExecutingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.pending.GarbageCollectionCardPendingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all.GarbageCollectionCircleAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.all.AreaCompletionRateColumnAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.pending.TimePeriodPendingColumnVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.statistics.GarbageCollectionStatisticsRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.trend.executing.GarbageCollectionDailyTrendVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.garbagecollection.GarbageCollectionCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.util.garbagecollection.vo.NameValueVO;
import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import org.springframework.util.CollectionUtils;


import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageCollectionMapper;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 收运计划 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageCollectionServiceImpl implements GarbageCollectionService {

    @Resource
    private GarbageCollectionMapper garbageCollectionMapper;
    @Resource
    private GarbageCollectionCodeGenerator codeGenerator;
    @Resource
    private Validator validator;

    @Override
    public Long createGarbageCollection(GarbageCollectionSaveReqVO createReqVO) {
        // 插入
        GarbageCollectionDO garbageCollection = BeanUtils.toBean(createReqVO, GarbageCollectionDO.class);

        garbageCollection.setPlanNo(codeGenerator.generatePlanNo());
        garbageCollection.setCollectionId(codeGenerator.generateCollectionId());

        garbageCollectionMapper.insert(garbageCollection);
        // 返回
        return garbageCollection.getId();
    }

    @Override
    public void updateGarbageCollection(GarbageCollectionSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageCollectionExists(updateReqVO.getId());
        // 更新
        GarbageCollectionDO updateObj = BeanUtils.toBean(updateReqVO, GarbageCollectionDO.class);
        garbageCollectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageCollection(Long id) {
        // 校验存在
        validateGarbageCollectionExists(id);
        // 删除
        garbageCollectionMapper.deleteById(id);
    }

    private void validateGarbageCollectionExists(Long id) {
        if (garbageCollectionMapper.selectById(id) == null) {
            throw exception(GARBAGE_COLLECTION_NOT_EXISTS);
        }
    }

    @Override
    public GarbageCollectionDO getGarbageCollection(Long id) {
        return garbageCollectionMapper.selectById(id);
    }

    @Override
    public void deleteGarbageCollectionBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 校验所有计划是否存在
        List<GarbageCollectionDO> garbageCollections = garbageCollectionMapper.selectBatchIds(ids);
        if (garbageCollections.size() != ids.size()) {
            throw exception(GARBAGE_COLLECTION_NOT_EXISTS);
        }

        // 批量删除
        garbageCollectionMapper.deleteBatchIds(ids);
    }

    @Override
    public PageResult<GarbageCollectionDO> getGarbageCollectionPage(GarbageCollectionPageReqVO pageReqVO) {
        return garbageCollectionMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GarbageCollectionDetailDO> getGarbageCollectionDetailPage(GarbageCollectionPageReqVO pageReqVO) {
        Long total = garbageCollectionMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageCollectionDetailDO> list = garbageCollectionMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public GarbageCollectionCardAllVO getGarbageCollectionCardAll() {
        return garbageCollectionMapper.selectCardAll();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getGarbageTypeCircleAll() {
        return garbageCollectionMapper.selectGarbageTypeCircleAll();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getPlanStatusCircleAll() {
        return garbageCollectionMapper.selectPlanStatusCircleAll();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getAreaDistributionCircleAll() {
        return garbageCollectionMapper.selectAreaDistributionCircleAll();
    }

    @Override
    public List<AreaCompletionRateColumnAllVO> getAreaCompletionRateColumnAll() {
        return garbageCollectionMapper.selectAreaCompletionRateColumnAll();
    }

    @Override
    public GarbageCollectionCardPendingVO getGarbageCollectionCardPending() {
        GarbageCollectionCardPendingVO vo = new GarbageCollectionCardPendingVO();

        // 1. 查询待执行计划总数
        Long totalPendingCount = garbageCollectionMapper.selectPendingTotalCount();
        vo.setTotalPendingCount(totalPendingCount == null ? 0L : totalPendingCount);

        // 2. 按区域统计待执行数量（转换为List<NameValueVO>：区域名称 -> 数量）
        List<Map<String, Object>> areaList = garbageCollectionMapper.selectPendingCountByArea();
        List<NameValueVO> areaPendingList = areaList.stream()
                .map(item -> new NameValueVO(
                        item.get("areaName").toString(),
                        Long.valueOf(item.get("count").toString())
                ))
                .collect(Collectors.toList());
        vo.setAreaPendingCountMap(areaPendingList); // 赋值给List类型字段

        // 3. 按品类统计待执行数量（转换为List<NameValueVO>：品类名称 -> 数量）
        List<Map<String, Object>> garbageTypeList = garbageCollectionMapper.selectPendingCountByGarbageType();
        List<NameValueVO> garbageTypePendingList = garbageTypeList.stream()
                .map(item -> new NameValueVO(
                        item.get("garbageTypeName").toString(),
                        Long.valueOf(item.get("count").toString())
                ))
                .collect(Collectors.toList());
        vo.setGarbageTypePendingCountMap(garbageTypePendingList); // 赋值给List类型字段

        return vo;
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getPendingGarbageCollectionByArea() {
        return garbageCollectionMapper.selectPendingByArea();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getPendingGarbageCollectionByGarbageType() {
        return garbageCollectionMapper.selectPendingByGarbageType();
    }

    @Override
    public List<TimePeriodPendingColumnVO> getTimePeriodPendingColumn() {
        return garbageCollectionMapper.selectTimePeriodPendingColumn();
    }

    @Override
    public GarbageCollectionImportRespVO importGarbageCollection(List<GarbageCollectionImportReqVO> importList) {
        if (CollectionUtils.isEmpty(importList)) {
            return GarbageCollectionImportRespVO.builder()
                    .successCount(0)
                    .failCount(0)
                    .errorDetails(new ArrayList<>())
                    .build();
        }

        int successCount = 0;
        int failCount = 0;
        List<GarbageCollectionImportRespVO.ImportErrorDetail> errorDetails = new ArrayList<>();

        // 示例：预加载字典/关联表数据（避免循环查询DB）
        // Map<String, String> areaCodeMap = areaService.getAreaCodeToNameMap();
        // Map<String, String> vehicleIdMap = vehicleService.getVehicleIdMap();

        for (int i = 0; i < importList.size(); i++) {
            GarbageCollectionImportReqVO importReqVO = importList.get(i);
            int rowNum = i + 2; // Excel行号（表头行是1）

            // 1. JSR380校验（必填字段）
            Set<ConstraintViolation<GarbageCollectionImportReqVO>> violations = validator.validate(importReqVO);
            if (!violations.isEmpty()) {
                failCount++;
                String errorMsg = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((msg1, msg2) -> msg1 + "；" + msg2)
                        .orElse("数据格式校验失败");
                errorDetails.add(GarbageCollectionImportRespVO.ImportErrorDetail.builder()
                        .rowNum(rowNum)
                        .errorMsg(errorMsg)
                        .build());
                continue;
            }

            try {
                // 2. 业务校验（示例：校验区域编码是否存在）
                // if (!areaCodeMap.containsKey(importReqVO.getAreaCode())) {
                //     throw new IllegalArgumentException("区域编码不存在：" + importReqVO.getAreaCode());
                // }
                // if (!vehicleIdMap.containsKey(importReqVO.getVehicleId())) {
                //     throw new IllegalArgumentException("车辆ID不存在：" + importReqVO.getVehicleId());
                // }

                // 3. 转换为DO并填充默认值/自动生成字段
                GarbageCollectionDO garbageCollection = BeanUtils.toBean(importReqVO, GarbageCollectionDO.class);

                // 自动生成业务主键和计划编号
                garbageCollection.setCollectionId(codeGenerator.generateCollectionId());
                garbageCollection.setPlanNo(codeGenerator.generatePlanNo());

                // 填充默认值
                if (garbageCollection.getPlanStatusId() == null || garbageCollection.getPlanStatusId().isEmpty()) {
                    garbageCollection.setPlanStatusId("uuid-plan-status-001");
                }
                if (garbageCollection.getCompletionRate() == null) {
                    garbageCollection.setCompletionRate(BigDecimal.ZERO);
                }
                if (garbageCollection.getAbnormalCount() == null) {
                    garbageCollection.setAbnormalCount(0);
                }
                if (garbageCollection.getCreateTime() == null) {
                    garbageCollection.setCreateTime(LocalDateTime.now());
                }
                // 空值处理：JSON字段默认空数组
                if (garbageCollection.getStaffIds() == null || garbageCollection.getStaffIds().trim().isEmpty()) {
                    garbageCollection.setStaffIds("[]");
                }
                if (garbageCollection.getPointIds() == null || garbageCollection.getPointIds().trim().isEmpty()) {
                    garbageCollection.setPointIds("[]");
                }

                // 4. 插入数据库
                garbageCollectionMapper.insert(garbageCollection);
                successCount++;

            } catch (Exception e) {
                failCount++;
                errorDetails.add(GarbageCollectionImportRespVO.ImportErrorDetail.builder()
                        .rowNum(rowNum)
                        .errorMsg("数据导入失败：" + e.getMessage())
                        .build());
            }
        }

        return GarbageCollectionImportRespVO.builder()
                .successCount(successCount)
                .failCount(failCount)
                .errorDetails(errorDetails)
                .build();
    }

    @Override
    public GarbageCollectionStatisticsRespVO getGarbageCollectionStatistics() {
        GarbageCollectionStatisticsRespVO respVO = new GarbageCollectionStatisticsRespVO();

        // 1. 查询总计划数
        Long totalCount = garbageCollectionMapper.selectTotalCount();
        respVO.setTotal(totalCount == null ? 0 : totalCount.intValue());

        // 2. 查询各状态统计
        List<Map<String, Object>> statusStats = garbageCollectionMapper.selectStatisticsByPlanStatus();

        // 3. 转换为Map格式
        Map<String, Integer> planStatusCounts = new java.util.LinkedHashMap<>();

        // 初始化所有状态为0
        planStatusCounts.put("待执行", 0);
        planStatusCounts.put("执行中", 0);
        planStatusCounts.put("已完成", 0);
        planStatusCounts.put("异常", 0);
        planStatusCounts.put("待复核", 0);

        // 填充实际数据
        for (Map<String, Object> stat : statusStats) {
            String statusName = (String) stat.get("status_name");
            Long count = (Long) stat.get("count");
            planStatusCounts.put(statusName, count.intValue());
        }

        respVO.setPlanStatusCounts(planStatusCounts);

        return respVO;
    }

    @Override
    public GarbageCollectionCardExecutingVO getGarbageCollectionCardExecuting() {
        // 1. 查询统计数据
        Map<String, Object> stats = garbageCollectionMapper.selectCardExecuting();

        // 2. 提取数据
        Long normalRunningCount = stats.get("normal_running_count") == null ? 0L :
                ((Number) stats.get("normal_running_count")).longValue();
        Long abnormalCount = stats.get("abnormal_count") == null ? 0L :
                ((Number) stats.get("abnormal_count")).longValue();

        // 3. 计算当前作业任务数（执行中 + 异常）
        Long currentTaskCount = normalRunningCount + abnormalCount;

        // 4. 构建返回对象
        return GarbageCollectionCardExecutingVO.builder()
                .currentTaskCount(currentTaskCount.intValue())
                .normalRunningCount(normalRunningCount.intValue())
                .abnormalCount(abnormalCount.intValue())
                .build();
    }

    @Override
    public List<GarbageCollectionDailyTrendVO> getDailyCollectionVolumeTrend() {
        return garbageCollectionMapper.selectDailyCollectionVolumeTrend();
    }
}