package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealfeedback.AppealFeedbackDO;
import cn.iocoder.yudao.module.evaluate.service.appealfeedback.AppealFeedbackService;
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
import java.util.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 申诉反馈")
@RestController
@RequestMapping("/evaluate/appeal-feedback")
@Validated
public class AppealFeedbackController {

    @Resource
    private AppealFeedbackService appealFeedbackService;

    @PostMapping("/create")
    @Operation(summary = "创建申诉反馈")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-feedback:create')")
    public CommonResult<Long> createAppealFeedback(@Valid @RequestBody AppealFeedbackSaveReqVO createReqVO) {
        return success(appealFeedbackService.createAppealFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新申诉反馈")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-feedback:update')")
    public CommonResult<Boolean> updateAppealFeedback(@Valid @RequestBody AppealFeedbackSaveReqVO updateReqVO) {
        appealFeedbackService.updateAppealFeedback(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除申诉反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-feedback:delete')")
    public CommonResult<Boolean> deleteAppealFeedback(@RequestParam("id") Long id) {
        appealFeedbackService.deleteAppealFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得申诉反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-feedback:query')")
    public CommonResult<AppealFeedbackRespVO> getAppealFeedback(@RequestParam("id") Long id) {
        AppealFeedbackDO appealFeedback = appealFeedbackService.getAppealFeedback(id);
        return success(BeanUtils.toBean(appealFeedback, AppealFeedbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得申诉反馈分页")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-feedback:query')")
    public CommonResult<PageResult<AppealFeedbackRespVO>> getAppealFeedbackPage(@Valid AppealFeedbackPageReqVO pageReqVO) {
        PageResult<AppealFeedbackDO> pageResult = appealFeedbackService.getAppealFeedbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AppealFeedbackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出申诉反馈 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-feedback:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAppealFeedbackExcel(@Valid AppealFeedbackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AppealFeedbackDO> list = appealFeedbackService.getAppealFeedbackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "申诉反馈.xls", "数据", AppealFeedbackRespVO.class,
                        BeanUtils.toBean(list, AppealFeedbackRespVO.class));
    }

}