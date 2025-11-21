package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectrectifyreview.InspectRectifyReviewDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectrectifyreview.InspectRectifyReviewService;

@Tag(name = "管理后台 - 巡查巡检整改结果复核")
@RestController
@RequestMapping("/datacenter/inspect-rectify-review")
@Validated
public class InspectRectifyReviewController {

    @Resource
    private InspectRectifyReviewService inspectRectifyReviewService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检整改结果复核")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-review:create')")
    public CommonResult<Long> createInspectRectifyReview(@Valid @RequestBody InspectRectifyReviewSaveReqVO createReqVO) {
        return success(inspectRectifyReviewService.createInspectRectifyReview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检整改结果复核")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-review:update')")
    public CommonResult<Boolean> updateInspectRectifyReview(@Valid @RequestBody InspectRectifyReviewSaveReqVO updateReqVO) {
        inspectRectifyReviewService.updateInspectRectifyReview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检整改结果复核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-review:delete')")
    public CommonResult<Boolean> deleteInspectRectifyReview(@RequestParam("id") Long id) {
        inspectRectifyReviewService.deleteInspectRectifyReview(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检整改结果复核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-review:query')")
    public CommonResult<InspectRectifyReviewRespVO> getInspectRectifyReview(@RequestParam("id") Long id) {
        InspectRectifyReviewDO inspectRectifyReview = inspectRectifyReviewService.getInspectRectifyReview(id);
        return success(BeanUtils.toBean(inspectRectifyReview, InspectRectifyReviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检整改结果复核分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-review:query')")
    public CommonResult<PageResult<InspectRectifyReviewRespVO>> getInspectRectifyReviewPage(@Valid InspectRectifyReviewPageReqVO pageReqVO) {
        PageResult<InspectRectifyReviewDO> pageResult = inspectRectifyReviewService.getInspectRectifyReviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRectifyReviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检整改结果复核 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-review:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRectifyReviewExcel(@Valid InspectRectifyReviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRectifyReviewDO> list = inspectRectifyReviewService.getInspectRectifyReviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检整改结果复核.xls", "数据", InspectRectifyReviewRespVO.class,
                        BeanUtils.toBean(list, InspectRectifyReviewRespVO.class));
    }

}