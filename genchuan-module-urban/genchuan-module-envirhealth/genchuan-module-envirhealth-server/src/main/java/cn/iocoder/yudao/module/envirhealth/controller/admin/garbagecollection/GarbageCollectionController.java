package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.importer.vo.ImportRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.area.AreaService;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection.GarbageCollectionService;
import cn.iocoder.yudao.module.envirhealth.util.importer.ImportUtils;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.StatisticsRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Tag(name = "环境卫生管理 - 收运计划")
@RestController
@RequestMapping("/envirhealth/garbage-collection")
@Validated
public class GarbageCollectionController {

    @Resource
    private GarbageCollectionService garbageCollectionService;

    @Resource
    private AreaService areaService;

    @PostMapping("/create")
    @Operation(summary = "创建收运计划")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:create')")
    public CommonResult<Long> createGarbageCollection(@Valid @RequestBody GarbageCollectionSaveReqVO createReqVO) {
        createReqVO.setId(null);
        return success(garbageCollectionService.createGarbageCollection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收运计划")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:update')")
    public CommonResult<Boolean> updateGarbageCollection(@Valid @RequestBody GarbageCollectionSaveReqVO updateReqVO) {
        garbageCollectionService.updateGarbageCollection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收运计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:delete')")
    public CommonResult<Boolean> deleteGarbageCollection(@RequestParam("id") Long id) {
        garbageCollectionService.deleteGarbageCollection(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除收运计划")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:delete')")
    public CommonResult<Boolean> deleteGarbageCollectionBatch(@RequestBody List<Long> ids) {
        garbageCollectionService.deleteGarbageCollectionBatch(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收运计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<GarbageCollectionRespVO> getGarbageCollection(@RequestParam("id") Long id) {
        GarbageCollectionDO garbageCollection = garbageCollectionService.getGarbageCollection(id);
        return success(BeanUtils.toBean(garbageCollection, GarbageCollectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收运计划分页")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<PageResult<GarbageCollectionRespVO>> getGarbageCollectionPage(@Valid GarbageCollectionPageReqVO pageReqVO) {
        PageResult<GarbageCollectionDO> pageResult = garbageCollectionService.getGarbageCollectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageCollectionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收运计划 Excel")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageCollectionExcel(@Valid GarbageCollectionPageReqVO pageReqVO,
                                             HttpServletResponse response) throws IOException {
        // 1. 构建文件名：垃圾收运任务_区域_日期.xls
        // 1.1 处理区域名称（无区域筛选则为"全部"）
        String areaName = "全部";
        if (pageReqVO.getAreaCode() != null && !pageReqVO.getAreaCode().isEmpty()) {
            try {
                // 核心：调用区域服务将编码转为名称（适配单区域/多区域）
                // 场景1：单区域编码（如 "310101"）
                if (pageReqVO.getAreaCode().contains(",") || pageReqVO.getAreaCode().contains(";")) {
                    // 场景2：多区域编码（如 "310101,310102"），拼接名称
                    String[] areaCodes = pageReqVO.getAreaCode().split("[,;]");
                    StringBuilder areaNameSb = new StringBuilder();
                    for (String code : areaCodes) {
                        String singleName = areaService.getAreaNameByCode(code.trim());
                        areaNameSb.append(singleName == null ? code.trim() : singleName).append("_");
                    }
                    areaName = areaNameSb.toString().replaceAll("_$", ""); // 移除最后一个下划线
                } else {
                    // 单区域编码转换
                    areaName = areaService.getAreaNameByCode(pageReqVO.getAreaCode().trim());
                }
                // 兜底：若转换失败（如编码不存在），使用原编码
                if (areaName == null || areaName.isEmpty()) {
                    areaName = pageReqVO.getAreaCode();
                }
            } catch (Exception e) {
                // 异常兜底：避免因区域服务调用失败导致导出报错
                areaName = pageReqVO.getAreaCode();
            }
        }
        // 1.2 处理日期（导出当天，格式：yyyyMMdd）
        String dateStr = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        String fileName = String.format("垃圾收运任务_%s_%s.xls", areaName, dateStr);

        // 2. 设置响应头（解决中文文件名乱码）
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 3. 查询所有符合筛选条件的收运计划
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageCollectionDO> list = garbageCollectionService.getGarbageCollectionPage(pageReqVO).getList();

        // 4. 导出Excel
        ExcelUtils.write(response, fileName, "收运计划数据", GarbageCollectionRespVO.class,
                BeanUtils.toBean(list, GarbageCollectionRespVO.class));
    }


    @GetMapping("/detail-page")
    @Operation(summary = "获得收运计划详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-collection:query')")
    public CommonResult<PageResult<GarbageCollectionDetailDO>> getGarbageCollectionDetailPage(
            @Valid GarbageCollectionPageReqVO pageReqVO) {
        PageResult<GarbageCollectionDetailDO> pageResult =
                garbageCollectionService.getGarbageCollectionDetailPage(pageReqVO);

        return success(pageResult);
    }

    @GetMapping("/chart/card-all")
    @Operation(summary = "获取收运计划统计数据(卡片-全部)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<GarbageCollectionCardAllVO> getGarbageCollectionCardAll() {
        return success(garbageCollectionService.getGarbageCollectionCardAll());
    }

    @GetMapping("/chart/garbage-type-circle-all")
    @Operation(summary = "获取收运品类占比(环状图-全部)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleAllVO>> getGarbageTypeCircleAll() {
        return success(garbageCollectionService.getGarbageTypeCircleAll());
    }

    @GetMapping("/chart/plan-status-circle-all")
    @Operation(summary = "获取计划状态占比(环状图-全部)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleAllVO>> getPlanStatusCircleAll() {
        return success(garbageCollectionService.getPlanStatusCircleAll());
    }

    @GetMapping("/chart/area-distribution-circle-all")
    @Operation(summary = "获取区域分布占比(环状图-全部)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleAllVO>> getAreaDistributionCircleAll() {
        return success(garbageCollectionService.getAreaDistributionCircleAll());
    }

    @GetMapping("/chart/area-completion-rate-column-all")
    @Operation(summary = "获取各区域收运完成率(柱状图-全部)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<AreaCompletionRateColumnAllVO>> getAreaCompletionRateColumnAll() {
        return success(garbageCollectionService.getAreaCompletionRateColumnAll());
    }

    @GetMapping("/chart/card-pending")
    @Operation(summary = "获取收运计划统计数据(卡片-待执行)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<GarbageCollectionCardPendingVO> getGarbageCollectionCardPending() {
        return success(garbageCollectionService.getGarbageCollectionCardPending());
    }

    /**
     * 获取待执行计划-按区域统计
     */
    @GetMapping("/chart/pending-by-area")
    @Operation(summary = "按区域统计(环状图-待执行)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleAllVO>> getPendingGarbageCollectionByArea() {
        return success(garbageCollectionService.getPendingGarbageCollectionByArea());
    }

    /**
     * 获取待执行计划-按品类统计
     */
    @GetMapping("/chart/pending-by-garbage-type")
    @Operation(summary = "按品类统计(环状图-待执行)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleAllVO>> getPendingGarbageCollectionByGarbageType() {
        return success(garbageCollectionService.getPendingGarbageCollectionByGarbageType());
    }

    @GetMapping("/chart/time-period-pending-column")
    @Operation(summary = "获取不同时段计划数量(柱状图-待执行)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<TimePeriodPendingColumnVO>> getTimePeriodPendingColumn() {
        return success(garbageCollectionService.getTimePeriodPendingColumn());
    }

    @GetMapping("/chart/statistics")
    @Operation(summary = "获取收运计划统计数据(按状态分组)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<StatisticsRespVO> getGarbageCollectionStatistics() {
        return success(garbageCollectionService.getGarbageCollectionStatistics());
    }

    @GetMapping("/chart/card-executing")
    @Operation(summary = "获取收运计划统计数据(卡片-作业中)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<GarbageCollectionCardExecutingVO> getGarbageCollectionCardExecuting() {
        return success(garbageCollectionService.getGarbageCollectionCardExecuting());
    }

    @GetMapping("/chart/trend-daily-volume")
    @Operation(summary = "当日收运量实时增长趋势(折线图-作业中)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionDailyTrendVO>> getDailyCollectionVolumeTrend() {
        return success(garbageCollectionService.getDailyCollectionVolumeTrend());
    }

    @GetMapping("/chart/card-completed")
    @Operation(summary = "获取收运计划统计数据(卡片-已完成)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<GarbageCollectionCardCompletedVO> getGarbageCollectionCardCompleted() {
        return success(garbageCollectionService.getGarbageCollectionCardCompleted());
    }

    @GetMapping("/chart/collection-volume-comparison")
    @Operation(summary = "获取收运量对比数据(柱状图-当日/当周/当月)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<Map<String, List<CollectionVolumeBarVO>>> getCollectionVolumeComparison() {
        return success(garbageCollectionService.getCollectionVolumeComparison());
    }

    @GetMapping("/chart/completion-rate-trend")
    @Operation(summary = "获取收运完成率趋势(折线图-已完成)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<CompletionRateTrendVO>> getCompletionRateTrend(
            @RequestParam @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND) LocalDateTime endTime) {
        return success(garbageCollectionService.getCompletionRateTrend(startTime, endTime));
    }

    @GetMapping("/chart/completed-volume-by-area")
    @Operation(summary = "获取已完成计划各区域收运量占比(环状图-已完成)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleCompletedVO>> getCompletedVolumeByArea() {
        return success(garbageCollectionService.getCompletedVolumeByArea());
    }

    @GetMapping("/chart/completed-volume-by-garbage-type")
    @Operation(summary = "获取已完成计划各品类收运量占比(环状图-已完成)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionCircleCompletedVO>> getCompletedVolumeByGarbageType() {
        return success(garbageCollectionService.getCompletedVolumeByGarbageType());
    }

    /**
     * 获得计划编号下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得计划编号(下拉框-执行中)")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:query')")
    public CommonResult<List<OptionVO>> getExecutingOptions() {
        return success(garbageCollectionService.getExecutingOptions());
    }

    @PostMapping("/import")
    @Operation(summary = "批量导入收运计划 Excel")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:create')")
    public CommonResult<ImportRespVO<GarbageCollectionSaveReqVO>> importGarbageCollection(
            @RequestPart("file") MultipartFile file) throws Exception {

        // 1. 使用导入VO接收Excel数据
        String importVOClassName = GarbageCollectionImportVO.class.getName();
        List<GarbageCollectionImportVO> importList = ImportUtils.importExcelAndReturnEntity(file, importVOClassName);

        // 2. 转换为SaveReqVO
        List<GarbageCollectionSaveReqVO> saveReqList = convertToSaveReqVO(importList);

        // 3. 批量导入
        ImportRespVO<GarbageCollectionSaveReqVO> result = garbageCollectionService.batchImport(saveReqList);

        return success(result);
    }

    /**
     * 将导入VO转换为保存VO
     */
    private List<GarbageCollectionSaveReqVO> convertToSaveReqVO(List<GarbageCollectionImportVO> importList) {
        return importList.stream().map(importVO -> {
            GarbageCollectionSaveReqVO saveReqVO = new GarbageCollectionSaveReqVO();

            // 字符串类型直接赋值
            saveReqVO.setCollectionId(importVO.getCollectionId());
            saveReqVO.setPlanNo(importVO.getPlanNo());
            saveReqVO.setAreaCode(importVO.getAreaCode());
            saveReqVO.setGarbageTypeId(importVO.getGarbageTypeId());
            saveReqVO.setFrequency(importVO.getFrequency());
            saveReqVO.setTimePeriod(importVO.getTimePeriod());
            saveReqVO.setVehicleId(importVO.getVehicleId());

            // staffIds已经是JSON数组格式，直接使用
            saveReqVO.setStaffIds(importVO.getStaffIds());

            // pointIds已经是JSON数组格式，直接使用
            saveReqVO.setPointIds(importVO.getPointIds());

            saveReqVO.setPlanStatusId(importVO.getPlanStatusId());

            // 数值类型转换
            if (importVO.getCompletionRate() != null) {
                saveReqVO.setCompletionRate(new BigDecimal(importVO.getCompletionRate()));
            }

            if (importVO.getAbnormalCount() != null) {
                saveReqVO.setAbnormalCount(Integer.parseInt(importVO.getAbnormalCount().replace(".0", "")));
            }

            // 创建人
            saveReqVO.setCreateBy(importVO.getCreateBy());

            // 当前进度
            if (importVO.getProgress() != null) {
                saveReqVO.setProgress(Integer.parseInt(importVO.getProgress().replace(".0", "")));
            }

            // 已收运量
            if (importVO.getCollectedVolume() != null) {
                saveReqVO.setCollectedVolume(new BigDecimal(importVO.getCollectedVolume()));
            }

            saveReqVO.setCheckinStatus(importVO.getCheckinStatus());
            saveReqVO.setTrackCoverage(importVO.getTrackCoverage());

            // 时间类型转换
            if (importVO.getLastReportTime() != null && !importVO.getLastReportTime().isEmpty()) {
                saveReqVO.setLastReportTime(LocalDateTime.parse(importVO.getLastReportTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }

            // 布尔类型转换
            if (importVO.getIsAbnormal() != null) {
                saveReqVO.setIsAbnormal(Boolean.parseBoolean(importVO.getIsAbnormal()));
            }

            // 完成时间
            if (importVO.getCompleteTime() != null && !importVO.getCompleteTime().isEmpty()) {
                saveReqVO.setCompleteTime(LocalDateTime.parse(importVO.getCompleteTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }

            // 总收运量
            if (importVO.getTotalVolume() != null) {
                saveReqVO.setTotalVolume(new BigDecimal(importVO.getTotalVolume()));
            }

            saveReqVO.setAbnormalResult(importVO.getAbnormalResult());

            // 异常办结率
            if (importVO.getAbnormalCompleteRate() != null) {
                saveReqVO.setAbnormalCompleteRate(Integer.parseInt(importVO.getAbnormalCompleteRate().replace(".0", "")));
            }

            // id是自增的，不设置
            saveReqVO.setId(null);

            return saveReqVO;
        }).collect(Collectors.toList());
    }

    @PutMapping("/batch-update")
    @Operation(summary = "批量调整收运计划")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:update')")
    public CommonResult<Boolean> batchUpdateGarbageCollection(
            @Valid @RequestBody GarbageCollectionBatchUpdateReqVO reqVO) {
        garbageCollectionService.batchUpdateGarbageCollection(reqVO);
        return success(true);
    }
}