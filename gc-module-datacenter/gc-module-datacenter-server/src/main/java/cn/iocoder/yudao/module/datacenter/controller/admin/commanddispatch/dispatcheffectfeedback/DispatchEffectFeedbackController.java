package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatcheffectfeedback.DispatchEffectFeedbackDO;
import cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatcheffectfeedback.DispatchEffectFeedbackService;

@Tag(name = "管理后台 - 调度效果反馈")
@RestController
@RequestMapping("/datacenter/dispatch-effect-feedback")
@Validated
public class DispatchEffectFeedbackController {

    @Resource
    private DispatchEffectFeedbackService dispatchEffectFeedbackService;

    @PostMapping("/create")
    @Operation(summary = "创建调度效果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-effect-feedback:create')")
    public CommonResult<Long> createDispatchEffectFeedback(@Valid @RequestBody DispatchEffectFeedbackSaveReqVO createReqVO) {
        return success(dispatchEffectFeedbackService.createDispatchEffectFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新调度效果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-effect-feedback:update')")
    public CommonResult<Boolean> updateDispatchEffectFeedback(@Valid @RequestBody DispatchEffectFeedbackSaveReqVO updateReqVO) {
        dispatchEffectFeedbackService.updateDispatchEffectFeedback(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除调度效果反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-effect-feedback:delete')")
    public CommonResult<Boolean> deleteDispatchEffectFeedback(@RequestParam("id") Long id) {
        dispatchEffectFeedbackService.deleteDispatchEffectFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得调度效果反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-effect-feedback:query')")
    public CommonResult<DispatchEffectFeedbackRespVO> getDispatchEffectFeedback(@RequestParam("id") Long id) {
        DispatchEffectFeedbackDO dispatchEffectFeedback = dispatchEffectFeedbackService.getDispatchEffectFeedback(id);
        return success(BeanUtils.toBean(dispatchEffectFeedback, DispatchEffectFeedbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得调度效果反馈分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-effect-feedback:query')")
    public CommonResult<PageResult<DispatchEffectFeedbackRespVO>> getDispatchEffectFeedbackPage(@Valid DispatchEffectFeedbackPageReqVO pageReqVO) {
        PageResult<DispatchEffectFeedbackDO> pageResult = dispatchEffectFeedbackService.getDispatchEffectFeedbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DispatchEffectFeedbackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出调度效果反馈 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-effect-feedback:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDispatchEffectFeedbackExcel(@Valid DispatchEffectFeedbackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DispatchEffectFeedbackDO> list = dispatchEffectFeedbackService.getDispatchEffectFeedbackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "调度效果反馈.xls", "数据", DispatchEffectFeedbackRespVO.class,
                        BeanUtils.toBean(list, DispatchEffectFeedbackRespVO.class));
    }

}