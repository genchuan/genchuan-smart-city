package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvfeedback.InspectSupvFeedbackDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvfeedback.InspectSupvFeedbackService;

@Tag(name = "管理后台 - 巡查巡检督办结果反馈")
@RestController
@RequestMapping("/datacenter/inspect-supv-feedback")
@Validated
public class InspectSupvFeedbackController {

    @Resource
    private InspectSupvFeedbackService inspectSupvFeedbackService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检督办结果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-feedback:create')")
    public CommonResult<Long> createInspectSupvFeedback(@Valid @RequestBody InspectSupvFeedbackSaveReqVO createReqVO) {
        return success(inspectSupvFeedbackService.createInspectSupvFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检督办结果反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-feedback:update')")
    public CommonResult<Boolean> updateInspectSupvFeedback(@Valid @RequestBody InspectSupvFeedbackSaveReqVO updateReqVO) {
        inspectSupvFeedbackService.updateInspectSupvFeedback(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检督办结果反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-feedback:delete')")
    public CommonResult<Boolean> deleteInspectSupvFeedback(@RequestParam("id") Long id) {
        inspectSupvFeedbackService.deleteInspectSupvFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检督办结果反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-feedback:query')")
    public CommonResult<InspectSupvFeedbackRespVO> getInspectSupvFeedback(@RequestParam("id") Long id) {
        InspectSupvFeedbackDO inspectSupvFeedback = inspectSupvFeedbackService.getInspectSupvFeedback(id);
        return success(BeanUtils.toBean(inspectSupvFeedback, InspectSupvFeedbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检督办结果反馈分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-feedback:query')")
    public CommonResult<PageResult<InspectSupvFeedbackRespVO>> getInspectSupvFeedbackPage(@Valid InspectSupvFeedbackPageReqVO pageReqVO) {
        PageResult<InspectSupvFeedbackDO> pageResult = inspectSupvFeedbackService.getInspectSupvFeedbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectSupvFeedbackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检督办结果反馈 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-feedback:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectSupvFeedbackExcel(@Valid InspectSupvFeedbackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectSupvFeedbackDO> list = inspectSupvFeedbackService.getInspectSupvFeedbackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检督办结果反馈.xls", "数据", InspectSupvFeedbackRespVO.class,
                        BeanUtils.toBean(list, InspectSupvFeedbackRespVO.class));
    }

}