package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal.GarbageAbnormalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "环境卫生管理 - 垃圾异常记录")
@RestController
@RequestMapping("/envirhealth/garbage-abnormal")
@Validated
public class GarbageAbnormalController {

    @Resource
    private GarbageAbnormalService garbageAbnormalService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾异常记录")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:create')")
    public CommonResult<Long> createGarbageAbnormal(@Valid @RequestBody GarbageAbnormalSaveReqVO createReqVO) {
        return success(garbageAbnormalService.createGarbageAbnormal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾异常记录")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:update')")
    public CommonResult<Boolean> updateGarbageAbnormal(@Valid @RequestBody GarbageAbnormalSaveReqVO updateReqVO) {
        garbageAbnormalService.updateGarbageAbnormal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾异常记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:delete')")
    public CommonResult<Boolean> deleteGarbageAbnormal(@RequestParam("id") Long id) {
        garbageAbnormalService.deleteGarbageAbnormal(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除垃圾异常记录")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:garbage-abnormal:delete')")
    public CommonResult<Boolean> deleteGarbageAbnormalBatch(@RequestBody List<Long> ids) {
        garbageAbnormalService.deleteGarbageAbnormalBatch(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾异常记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<GarbageAbnormalRespVO> getGarbageAbnormal(@RequestParam("id") Long id) {
        GarbageAbnormalDO garbageAbnormal = garbageAbnormalService.getGarbageAbnormal(id);
        return success(BeanUtils.toBean(garbageAbnormal, GarbageAbnormalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾异常记录分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<PageResult<GarbageAbnormalRespVO>> getGarbageAbnormalPage(@Valid GarbageAbnormalPageReqVO pageReqVO) {
        PageResult<GarbageAbnormalDO> pageResult = garbageAbnormalService.getGarbageAbnormalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageAbnormalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾异常记录 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageAbnormalExcel(@Valid GarbageAbnormalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageAbnormalDO> list = garbageAbnormalService.getGarbageAbnormalPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("垃圾异常记录_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "垃圾异常记录.xls", "数据", GarbageAbnormalRespVO.class,
                        BeanUtils.toBean(list, GarbageAbnormalRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获取垃圾异常记录详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<PageResult<GarbageAbnormalDetailDO>> getGarbageAbnormalDetailPage(
            @Valid GarbageAbnormalPageReqVO pageReqVO) {
        PageResult<GarbageAbnormalDetailDO> pageResult =
                garbageAbnormalService.getGarbageAbnormalDetailPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/chart/card-abnormal")
    @Operation(summary = "获取异常统计卡片数据(卡片-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<GarbageAbnormalCardRespVO> getGarbageAbnormalCardAbnormal() {
        return success(garbageAbnormalService.getGarbageAbnormalCardAbnormal());
    }

    @GetMapping("/chart/circle-abnormal-type")
    @Operation(summary = "获取异常类型占比(环状图-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<GarbageAbnormalCircleAbnormalVO>> getGarbageAbnormalTypeCircle() {
        return success(garbageAbnormalService.getGarbageAbnormalTypeCircleAbnormal());
    }

    @GetMapping("/chart/circle-area-distribution")
    @Operation(summary = "获取区域分布占比(环状图-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<GarbageAbnormalCircleAbnormalVO>> getGarbageAbnormalAreaDistributionCircle() {
        return success(garbageAbnormalService.getGarbageAbnormalAreaDistributionCircle());
    }

    @GetMapping("/chart/column-abnormal")
    @Operation(summary = "获取不同责任人待处置异常数量对比(柱状图-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<GarbageAbnormalColumnAbnormalVO>> getHandlerAbnormalColumn() {
        return success(garbageAbnormalService.getHandlerAbnormalColumn());
    }

    @GetMapping("/chart/card-review")
    @Operation(summary = "获取待复核统计卡片数据(卡片-待复核)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<GarbageAbnormalCardReviewRespVO> getGarbageAbnormalCardReview() {
        return success(garbageAbnormalService.getGarbageAbnormalCardReview());
    }

    @GetMapping("/chart/circle-review-result")
    @Operation(summary = "获取复核结果占比(环状图-待复核)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<GarbageAbnormalCircleReviewVO>> getGarbageAbnormalReviewResultCircle() {
        return success(garbageAbnormalService.getGarbageAbnormalReviewResultCircle());
    }

    @GetMapping("/chart/circle-abnormal-type-for-review")
    @Operation(summary = "获取异常类型占比(环状图-待复核)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<GarbageAbnormalCircleAbnormalVO>> getGarbageAbnormalTypeCircleForReview() {
        return success(garbageAbnormalService.getGarbageAbnormalTypeCircleForReview());
    }

    @GetMapping("/chart/column-avg-handle-time")
    @Operation(summary = "获取异常处置平均时长对比(柱状图-待复核)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<GarbageAbnormalColumnHandleTimeVO>> getAvgHandleTimeColumn() {
        return success(garbageAbnormalService.getAvgHandleTimeColumn());
    }

    @PostMapping("/batch-review")
    @Operation(summary = "批量复核垃圾异常记录")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:review')")
    public CommonResult<Boolean> batchReviewGarbageAbnormal(@Valid @RequestBody GarbageAbnormalBatchReviewReqVO batchReviewReqVO) {
        garbageAbnormalService.batchReviewGarbageAbnormal(batchReviewReqVO);
        return success(true);
    }

    @PostMapping("/batch-handle")
    @Operation(summary = "批量更新垃圾异常记录处置状态")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:update')")
    public CommonResult<Boolean> batchUpdateHandleStatus(@Valid @RequestBody GarbageAbnormalBatchHandleReqVO batchHandleReqVO) {
        garbageAbnormalService.batchUpdateHandleStatus(batchHandleReqVO);
        return success(true);
    }
}
