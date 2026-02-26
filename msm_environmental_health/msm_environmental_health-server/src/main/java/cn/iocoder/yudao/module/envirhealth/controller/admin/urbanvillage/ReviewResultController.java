/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage;

import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.ReviewResultDO;
import cn.iocoder.yudao.module.envirhealth.service.urbanvillage.reviewresult.ReviewResultService;

@Tag(name = "管理后台 - 复核结果字典表")
@RestController
@RequestMapping("/envirhealth/review-result")
@Validated
public class ReviewResultController {

    @Resource
    private ReviewResultService reviewResultService;

    @PostMapping("/create")
    @Operation(summary = "创建复核结果字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-result:create')")
    public CommonResult<Long> createReviewResult(@Valid @RequestBody ReviewResultSaveReqVO createReqVO) {
        return success(reviewResultService.createReviewResult(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新复核结果字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-result:update')")
    public CommonResult<Boolean> updateReviewResult(@Valid @RequestBody ReviewResultSaveReqVO updateReqVO) {
        reviewResultService.updateReviewResult(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除复核结果字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:review-result:delete')")
    public CommonResult<Boolean> deleteReviewResult(@RequestParam("id") Long id) {
        reviewResultService.deleteReviewResult(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得复核结果字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-result:query')")
    public CommonResult<ReviewResultRespVO> getReviewResult(@RequestParam("id") Long id) {
        ReviewResultDO reviewResult = reviewResultService.getReviewResult(id);
        return success(BeanUtils.toBean(reviewResult, ReviewResultRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得复核结果字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-result:query')")
    public CommonResult<PageResult<ReviewResultRespVO>> getReviewResultPage(@Valid ReviewResultPageReqVO pageReqVO) {
        PageResult<ReviewResultDO> pageResult = reviewResultService.getReviewResultPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReviewResultRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出复核结果字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:review-result:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReviewResultExcel(@Valid ReviewResultPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReviewResultDO> list = reviewResultService.getReviewResultPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "复核结果字典表.xls", "数据", ReviewResultRespVO.class,
                        BeanUtils.toBean(list, ReviewResultRespVO.class));
    }

}*/
