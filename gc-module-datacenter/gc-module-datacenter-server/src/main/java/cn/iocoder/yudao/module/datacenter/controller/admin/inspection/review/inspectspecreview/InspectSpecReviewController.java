package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectspecreview.InspectSpecReviewDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectspecreview.InspectSpecReviewService;

@Tag(name = "管理后台 - 巡查巡检专项结果复核")
@RestController
@RequestMapping("/datacenter/inspect-spec-review")
@Validated
public class InspectSpecReviewController {

    @Resource
    private InspectSpecReviewService inspectSpecReviewService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检专项结果复核")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-review:create')")
    public CommonResult<Long> createInspectSpecReview(@Valid @RequestBody InspectSpecReviewSaveReqVO createReqVO) {
        return success(inspectSpecReviewService.createInspectSpecReview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检专项结果复核")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-review:update')")
    public CommonResult<Boolean> updateInspectSpecReview(@Valid @RequestBody InspectSpecReviewSaveReqVO updateReqVO) {
        inspectSpecReviewService.updateInspectSpecReview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检专项结果复核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-review:delete')")
    public CommonResult<Boolean> deleteInspectSpecReview(@RequestParam("id") Long id) {
        inspectSpecReviewService.deleteInspectSpecReview(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检专项结果复核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-review:query')")
    public CommonResult<InspectSpecReviewRespVO> getInspectSpecReview(@RequestParam("id") Long id) {
        InspectSpecReviewDO inspectSpecReview = inspectSpecReviewService.getInspectSpecReview(id);
        return success(BeanUtils.toBean(inspectSpecReview, InspectSpecReviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检专项结果复核分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-review:query')")
    public CommonResult<PageResult<InspectSpecReviewRespVO>> getInspectSpecReviewPage(@Valid InspectSpecReviewPageReqVO pageReqVO) {
        PageResult<InspectSpecReviewDO> pageResult = inspectSpecReviewService.getInspectSpecReviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectSpecReviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检专项结果复核 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-spec-review:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectSpecReviewExcel(@Valid InspectSpecReviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectSpecReviewDO> list = inspectSpecReviewService.getInspectSpecReviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检专项结果复核.xls", "数据", InspectSpecReviewRespVO.class,
                        BeanUtils.toBean(list, InspectSpecReviewRespVO.class));
    }

}