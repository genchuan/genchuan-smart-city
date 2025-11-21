package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreview.EvalReviewDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalreview.EvalReviewService;

@Tag(name = "管理后台 - 复核办理")
@RestController
@RequestMapping("/datacenter/eval-review")
@Validated
public class EvalReviewController {

    @Resource
    private EvalReviewService evalReviewService;

    @PostMapping("/create")
    @Operation(summary = "创建复核办理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review:create')")
    public CommonResult<Long> createEvalReview(@Valid @RequestBody EvalReviewSaveReqVO createReqVO) {
        return success(evalReviewService.createEvalReview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新复核办理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review:update')")
    public CommonResult<Boolean> updateEvalReview(@Valid @RequestBody EvalReviewSaveReqVO updateReqVO) {
        evalReviewService.updateEvalReview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除复核办理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review:delete')")
    public CommonResult<Boolean> deleteEvalReview(@RequestParam("id") Long id) {
        evalReviewService.deleteEvalReview(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得复核办理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review:query')")
    public CommonResult<EvalReviewRespVO> getEvalReview(@RequestParam("id") Long id) {
        EvalReviewDO evalReview = evalReviewService.getEvalReview(id);
        return success(BeanUtils.toBean(evalReview, EvalReviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得复核办理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review:query')")
    public CommonResult<PageResult<EvalReviewRespVO>> getEvalReviewPage(@Valid EvalReviewPageReqVO pageReqVO) {
        PageResult<EvalReviewDO> pageResult = evalReviewService.getEvalReviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalReviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出复核办理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-review:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalReviewExcel(@Valid EvalReviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalReviewDO> list = evalReviewService.getEvalReviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "复核办理.xls", "数据", EvalReviewRespVO.class,
                        BeanUtils.toBean(list, EvalReviewRespVO.class));
    }

}