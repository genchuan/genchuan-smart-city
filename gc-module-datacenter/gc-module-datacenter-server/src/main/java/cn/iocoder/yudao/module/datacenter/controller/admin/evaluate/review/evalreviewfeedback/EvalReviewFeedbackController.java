package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreviewfeedback.EvalReviewFeedbackDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalreviewfeedback.EvalReviewFeedbackService;

@Tag(name = "管理后台 - 复核结果反馈")
@RestController
@RequestMapping("/datacenter/eval-review-feedback")
@Validated
public class EvalReviewFeedbackController {

    @Resource
    private EvalReviewFeedbackService evalReviewFeedbackService;

    @PostMapping("/create")
    @Operation(summary = "创建复核结果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review-feedback:create')")
    public CommonResult<Long> createEvalReviewFeedback(@Valid @RequestBody EvalReviewFeedbackSaveReqVO createReqVO) {
        return success(evalReviewFeedbackService.createEvalReviewFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新复核结果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review-feedback:update')")
    public CommonResult<Boolean> updateEvalReviewFeedback(@Valid @RequestBody EvalReviewFeedbackSaveReqVO updateReqVO) {
        evalReviewFeedbackService.updateEvalReviewFeedback(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除复核结果反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review-feedback:delete')")
    public CommonResult<Boolean> deleteEvalReviewFeedback(@RequestParam("id") Long id) {
        evalReviewFeedbackService.deleteEvalReviewFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得复核结果反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review-feedback:query')")
    public CommonResult<EvalReviewFeedbackRespVO> getEvalReviewFeedback(@RequestParam("id") Long id) {
        EvalReviewFeedbackDO evalReviewFeedback = evalReviewFeedbackService.getEvalReviewFeedback(id);
        return success(BeanUtils.toBean(evalReviewFeedback, EvalReviewFeedbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得复核结果反馈分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review-feedback:query')")
    public CommonResult<PageResult<EvalReviewFeedbackRespVO>> getEvalReviewFeedbackPage(@Valid EvalReviewFeedbackPageReqVO pageReqVO) {
        PageResult<EvalReviewFeedbackDO> pageResult = evalReviewFeedbackService.getEvalReviewFeedbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalReviewFeedbackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出复核结果反馈 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review-feedback:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalReviewFeedbackExcel(@Valid EvalReviewFeedbackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalReviewFeedbackDO> list = evalReviewFeedbackService.getEvalReviewFeedbackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "复核结果反馈.xls", "数据", EvalReviewFeedbackRespVO.class,
                        BeanUtils.toBean(list, EvalReviewFeedbackRespVO.class));
    }

}