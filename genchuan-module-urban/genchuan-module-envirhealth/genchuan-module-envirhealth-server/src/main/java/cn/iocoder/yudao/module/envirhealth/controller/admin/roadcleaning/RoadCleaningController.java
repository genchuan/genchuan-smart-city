package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.module.envirhealth.service.roadcleaning.roadcleaning.RoadCleaningService;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.StatisticsRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 道路清扫计划")
@RestController
@RequestMapping("/envirhealth/road-cleaning")
@Validated
public class RoadCleaningController {

    @Resource
    private RoadCleaningService roadCleaningService;

    @PostMapping("/create")
    @Operation(summary = "创建道路清扫计划")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:create')")
    public CommonResult<Long> createRoadCleaning(@Valid @RequestBody RoadCleaningSaveReqVO createReqVO) {
        return success(roadCleaningService.createRoadCleaning(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路清扫计划")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:update')")
    public CommonResult<Boolean> updateRoadCleaning(@Valid @RequestBody RoadCleaningSaveReqVO updateReqVO) {
        roadCleaningService.updateRoadCleaning(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路清扫计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:delete')")
    public CommonResult<Boolean> deleteRoadCleaning(@RequestParam("id") Long id) {
        roadCleaningService.deleteRoadCleaning(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除道路清扫计划")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:road-cleaning:delete')")
    public CommonResult<Boolean> deleteRoadCleaningBatch(@RequestBody List<Long> ids) {
        roadCleaningService.deleteRoadCleaningBatch(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路清扫计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningRespVO> getRoadCleaning(@RequestParam("id") Long id) {
        RoadCleaningDO roadCleaning = roadCleaningService.getRoadCleaning(id);
        return success(BeanUtils.toBean(roadCleaning, RoadCleaningRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路清扫计划分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<PageResult<RoadCleaningRespVO>> getRoadCleaningPage(@Valid RoadCleaningPageReqVO pageReqVO) {
        PageResult<RoadCleaningDO> pageResult = roadCleaningService.getRoadCleaningPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadCleaningRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路清扫计划 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadCleaningExcel(@Valid RoadCleaningPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadCleaningDO> list = roadCleaningService.getRoadCleaningPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("道路清扫计划_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "道路清扫计划.xls", "数据", RoadCleaningRespVO.class,
                        BeanUtils.toBean(list, RoadCleaningRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得道路清扫详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<PageResult<RoadCleaningDetailDO>> getRoadCleaningDetailPage(
            @Valid RoadCleaningPageReqVO pageReqVO) {
        PageResult<RoadCleaningDetailDO> pageResult =
                roadCleaningService.getRoadCleaningDetailPage(pageReqVO);

        return success(pageResult);
    }

    /**
     * 获得道路清扫编号下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得道路清扫编号(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:road-cleaning:query')")
    public CommonResult<List<OptionVO>> getRoadCleaningNameOptions() {
        return success(roadCleaningService.getRoadCleaningNameOptions());
    }

    @PutMapping("/batch-adjust")
    @Operation(summary = "批量调整道路清扫计划(复核结果)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:update')")
    public CommonResult<Boolean> batchAdjustRoadCleaning(@Valid @RequestBody RoadCleaningBatchAdjustReqVO adjustReqVO) {
        roadCleaningService.batchAdjustRoadCleaning(adjustReqVO);
        return success(true);
    }

    @GetMapping("/chart/all")
    @Operation(summary = "卡片/圆环图/柱状图(全部)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningAllRespVO> getAll() {
        return success(roadCleaningService.getAll());
    }

    @GetMapping("/chart/pending")
    @Operation(summary = "卡片/圆环图/柱状图(待执行)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningPendingRespVO> getPendingData() {
        return success(roadCleaningService.getPendingData());
    }

    @GetMapping("/chart/executing")
    @Operation(summary = "卡片/圆环图/柱状图(执行中)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningExecutingRespVO> getExecutingData() {
        return success(roadCleaningService.getExecutingData());
    }

    @GetMapping("/chart/check")
    @Operation(summary = "卡片/圆环图/柱状图(核查统计)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningCheckRespVO> getCheckData() {
        return success(roadCleaningService.getCheckData());
    }

    @GetMapping("/chart/completed")
    @Operation(summary = "卡片/柱状图/折线图/圆环图(已完成)")
    @Parameters({@Parameter(name = "timeRange", description = "时间范围：day-日周对比/week-周月对比/month-月年对比", example = "day", required = true)})
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningCompletedRespVO> getCompletedData(@RequestParam(defaultValue = "day") String timeRange) {
        return success(roadCleaningService.getCompletedData(timeRange));
    }

    @GetMapping("/chart/statistics")
    @Operation(summary = "获取道路清扫统计数据(按状态分组)")
    @PreAuthorize("@ss.hasPermission('health:road-cleaning:query')")
    public CommonResult<StatisticsRespVO> getRoadCleaningStatistics() {
        return success(roadCleaningService.getRoadCleaningStatistics());
    }
}