package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.ReviewResultDO;
import cn.iocoder.yudao.module.envirhealth.service.urbanvillage.reviewresult.ReviewResultService;
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
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "字典表 - 复核结果")
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

    /**
     * 获得复核结果下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得复核结果(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:review-result:query')")
    public CommonResult<List<ReviewResultOptionVO>> getReviewResultOptions() {
        return success(reviewResultService.getReviewResultOptions());
    }
}
