/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.reviewstatus.ReviewStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.reviewstatus.ReviewStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.reviewstatus.ReviewStatusSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ReviewStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.user.reviewstatus.ReviewStatusService;

@Tag(name = "管理后台 - 审核状态字典表")
@RestController
@RequestMapping("/envirhealth/review-status")
@Validated
public class ReviewStatusController {

    @Resource
    private ReviewStatusService reviewStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建审核状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-status:create')")
    public CommonResult<Long> createReviewStatus(@Valid @RequestBody ReviewStatusSaveReqVO createReqVO) {
        return success(reviewStatusService.createReviewStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新审核状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-status:update')")
    public CommonResult<Boolean> updateReviewStatus(@Valid @RequestBody ReviewStatusSaveReqVO updateReqVO) {
        reviewStatusService.updateReviewStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除审核状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:review-status:delete')")
    public CommonResult<Boolean> deleteReviewStatus(@RequestParam("id") Long id) {
        reviewStatusService.deleteReviewStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得审核状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-status:query')")
    public CommonResult<ReviewStatusRespVO> getReviewStatus(@RequestParam("id") Long id) {
        ReviewStatusDO reviewStatus = reviewStatusService.getReviewStatus(id);
        return success(BeanUtils.toBean(reviewStatus, ReviewStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得审核状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-status:query')")
    public CommonResult<PageResult<ReviewStatusRespVO>> getReviewStatusPage(@Valid ReviewStatusPageReqVO pageReqVO) {
        PageResult<ReviewStatusDO> pageResult = reviewStatusService.getReviewStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReviewStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出审核状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReviewStatusExcel(@Valid ReviewStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReviewStatusDO> list = reviewStatusService.getReviewStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "审核状态字典表.xls", "数据", ReviewStatusRespVO.class,
                        BeanUtils.toBean(list, ReviewStatusRespVO.class));
    }

}*/
