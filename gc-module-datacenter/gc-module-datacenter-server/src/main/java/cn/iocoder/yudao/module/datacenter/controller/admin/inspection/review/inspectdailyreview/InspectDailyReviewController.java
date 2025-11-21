package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectdailyreview.InspectDailyReviewDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectdailyreview.InspectDailyReviewService;

@Tag(name = "管理后台 - 巡查巡检日常结果复核")
@RestController
@RequestMapping("/datacenter/inspect-daily-review")
@Validated
public class InspectDailyReviewController {

    @Resource
    private InspectDailyReviewService inspectDailyReviewService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检日常结果复核")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-review:create')")
    public CommonResult<Long> createInspectDailyReview(@Valid @RequestBody InspectDailyReviewSaveReqVO createReqVO) {
        return success(inspectDailyReviewService.createInspectDailyReview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检日常结果复核")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-review:update')")
    public CommonResult<Boolean> updateInspectDailyReview(@Valid @RequestBody InspectDailyReviewSaveReqVO updateReqVO) {
        inspectDailyReviewService.updateInspectDailyReview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检日常结果复核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-review:delete')")
    public CommonResult<Boolean> deleteInspectDailyReview(@RequestParam("id") Long id) {
        inspectDailyReviewService.deleteInspectDailyReview(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检日常结果复核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-review:query')")
    public CommonResult<InspectDailyReviewRespVO> getInspectDailyReview(@RequestParam("id") Long id) {
        InspectDailyReviewDO inspectDailyReview = inspectDailyReviewService.getInspectDailyReview(id);
        return success(BeanUtils.toBean(inspectDailyReview, InspectDailyReviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检日常结果复核分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-review:query')")
    public CommonResult<PageResult<InspectDailyReviewRespVO>> getInspectDailyReviewPage(@Valid InspectDailyReviewPageReqVO pageReqVO) {
        PageResult<InspectDailyReviewDO> pageResult = inspectDailyReviewService.getInspectDailyReviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectDailyReviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检日常结果复核 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-review:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectDailyReviewExcel(@Valid InspectDailyReviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectDailyReviewDO> list = inspectDailyReviewService.getInspectDailyReviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检日常结果复核.xls", "数据", InspectDailyReviewRespVO.class,
                        BeanUtils.toBean(list, InspectDailyReviewRespVO.class));
    }

}