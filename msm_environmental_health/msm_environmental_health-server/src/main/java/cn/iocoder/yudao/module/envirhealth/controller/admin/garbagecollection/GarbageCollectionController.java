package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.all.GarbageCollectionCardAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.executing.GarbageCollectionCardExecutingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.pending.GarbageCollectionCardPendingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all.GarbageCollectionCircleAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.all.AreaCompletionRateColumnAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.pending.TimePeriodPendingColumnVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.statistics.GarbageCollectionStatisticsRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.trend.executing.GarbageCollectionDailyTrendVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.area.AreaService;
import cn.iocoder.yudao.module.envirhealth.util.garbagecollection.ByteArrayMultipartFile;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StreamUtils;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection.GarbageCollectionService;

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

    @PostMapping("/import-excel")
    @Operation(summary = "批量导入收运计划 Excel")
    @PreAuthorize("@ss.hasPermission('health:garbage-collection:import')")
    public CommonResult<GarbageCollectionImportRespVO> importGarbageCollection(
            @RequestPart("file") MultipartFile file) throws IOException {
        // 1. 基础校验
        if (file.isEmpty()) {
            return CommonResult.error(400, "导入文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || (!originalFilename.endsWith(".xlsx") && !originalFilename.endsWith(".xls"))) {
            return CommonResult.error(400, "仅支持.xlsx/.xls格式的Excel文件，请使用模板填写后导入");
        }

        // 2. 缓存文件流
        byte[] fileBytes = StreamUtils.copyToByteArray(file.getInputStream());
        MultipartFile cacheFile = new ByteArrayMultipartFile(
                file.getName(), originalFilename, file.getContentType(), fileBytes
        );

        // 3. 解析Excel
        List<GarbageCollectionImportReqVO> importList;
        try {
            importList = ExcelUtils.read(cacheFile, GarbageCollectionImportReqVO.class);

            // ========== 在这里添加调试代码 ==========
            System.out.println("========== Excel导入调试信息 ==========");
            System.out.println("解析到 " + importList.size() + " 条数据");
            System.out.println("Excel列名映射结果：");

            if (!importList.isEmpty()) {
                GarbageCollectionImportReqVO first = importList.get(0);
                System.out.println("第一条数据详情：");
                System.out.println("areaCode=" + first.getAreaCode());
                System.out.println("garbageTypeId=" + first.getGarbageTypeId());
                System.out.println("frequency=" + first.getFrequency());
                System.out.println("timePeriod=" + first.getTimePeriod());
                System.out.println("vehicleId=" + first.getVehicleId());
                System.out.println("staffIds=" + first.getStaffIds());
                System.out.println("pointIds=" + first.getPointIds());
                System.out.println("planStatusId=" + first.getPlanStatusId());
                System.out.println("completionRate=" + first.getCompletionRate());
            } else {
                System.out.println("警告：解析到的数据列表为空！");
            }
            System.out.println("=====================================");
            // ========== 调试代码结束 ==========

        } catch (Exception e) {
            e.printStackTrace();  // 打印异常堆栈
            return CommonResult.error(500, "Excel解析失败：请检查文件格式是否与模板一致，" + e.getMessage());
        }

        // 4. 执行导入
        GarbageCollectionImportRespVO result = garbageCollectionService.importGarbageCollection(importList);
        return CommonResult.success(result);
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
    public CommonResult<GarbageCollectionStatisticsRespVO> getGarbageCollectionStatistics() {
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
}