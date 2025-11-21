package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultfeedback.InspectResultFeedbackDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresultfeedback.InspectResultFeedbackService;

@Tag(name = "管理后台 - 巡查巡检结果反馈")
@RestController
@RequestMapping("/datacenter/inspect-result-feedback")
@Validated
public class InspectResultFeedbackController {

    @Resource
    private InspectResultFeedbackService inspectResultFeedbackService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检结果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-feedback:create')")
    public CommonResult<Long> createInspectResultFeedback(@Valid @RequestBody InspectResultFeedbackSaveReqVO createReqVO) {
        return success(inspectResultFeedbackService.createInspectResultFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检结果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-feedback:update')")
    public CommonResult<Boolean> updateInspectResultFeedback(@Valid @RequestBody InspectResultFeedbackSaveReqVO updateReqVO) {
        inspectResultFeedbackService.updateInspectResultFeedback(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检结果反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-feedback:delete')")
    public CommonResult<Boolean> deleteInspectResultFeedback(@RequestParam("id") Long id) {
        inspectResultFeedbackService.deleteInspectResultFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检结果反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-feedback:query')")
    public CommonResult<InspectResultFeedbackRespVO> getInspectResultFeedback(@RequestParam("id") Long id) {
        InspectResultFeedbackDO inspectResultFeedback = inspectResultFeedbackService.getInspectResultFeedback(id);
        return success(BeanUtils.toBean(inspectResultFeedback, InspectResultFeedbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检结果反馈分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-feedback:query')")
    public CommonResult<PageResult<InspectResultFeedbackRespVO>> getInspectResultFeedbackPage(@Valid InspectResultFeedbackPageReqVO pageReqVO) {
        PageResult<InspectResultFeedbackDO> pageResult = inspectResultFeedbackService.getInspectResultFeedbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectResultFeedbackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检结果反馈 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-feedback:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectResultFeedbackExcel(@Valid InspectResultFeedbackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectResultFeedbackDO> list = inspectResultFeedbackService.getInspectResultFeedbackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检结果反馈.xls", "数据", InspectResultFeedbackRespVO.class,
                        BeanUtils.toBean(list, InspectResultFeedbackRespVO.class));
    }

}