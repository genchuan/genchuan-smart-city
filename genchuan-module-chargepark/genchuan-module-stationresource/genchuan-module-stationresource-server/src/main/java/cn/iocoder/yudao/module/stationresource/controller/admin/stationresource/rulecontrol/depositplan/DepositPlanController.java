package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan.DepositPlanDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.depositplan.DepositPlanService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 押金方案")
@RestController
@RequestMapping("/stationresource/deposit-plan")
@Validated
//@Hidden
public class DepositPlanController {

    @Resource
    private DepositPlanService depositPlanService;

    @GetMapping("/chart")
    @Operation(summary = "押金方案统计（柱状图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:query')")
    public CommonResult<DepositPlanChartRespVO> getDepositPlanChart() {
        DepositPlanChartRespVO chartData = depositPlanService.getDepositPlanChart();
        return CommonResult.success(chartData);
    }

    @PutMapping("/enable")
    @Operation(summary = "批量生效押金方案")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:update')")
    public CommonResult<Boolean> enableDepositPlan(@RequestBody List<Long> ids) {
        depositPlanService.enableDepositPlan(ids);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用押金方案")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:update')")
    public CommonResult<Boolean> disableDepositPlan(@RequestBody List<Long> ids) {
        depositPlanService.disableDepositPlan(ids);
        return CommonResult.success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新押金方案")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:update')")
    public CommonResult<Boolean> updateDepositPlan(@Valid @RequestBody DepositPlanUpdateReqVO updateReqVO) {
        depositPlanService.updateDepositPlanBiz(updateReqVO);
        return success(true);
    }

    @GetMapping("/import-template")
    @Operation(summary = "下载押金方案导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:import')")
    public void importDepositPlanTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, DepositPlanCreateReqVO.class);
    }

    @PostMapping("/import")
    @Operation(summary = "导入押金方案", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:import')")
    public CommonResult<DepositPlanImportResp> importDepositPlan(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        DepositPlanImportResp result = depositPlanService.importDepositPlan(file, updateSupport);
        return success(result);
    }

    @PostMapping("/create")
    @Operation(summary = "新增押金方案")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:create')")
    public CommonResult<Boolean> createDepositPlan(@Valid @RequestBody DepositPlanCreateReqVO reqVO) {
        depositPlanService.createDepositPlan(reqVO);
        return CommonResult.success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得押金方案分页")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:query')")
    public CommonResult<PageResult<DepositPlanRespVO>> getDepositPlanPage(@Valid DepositPlanPageReqVO pageReqVO) {
        PageResult<DepositPlanDO> pageResult = depositPlanService.getDepositPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DepositPlanRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得押金方案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:query')")
    public CommonResult<DepositPlanRespVO> getDepositPlan(@RequestParam("id") Long id) {
        DepositPlanDO depositPlan = depositPlanService.getDepositPlan(id);
        return success(BeanUtils.toBean(depositPlan, DepositPlanRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出押金方案 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDepositPlanExcel(@Valid DepositPlanPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DepositPlanDO> list = depositPlanService.getDepositPlanPage(pageReqVO).getList();
        ExcelUtils.write(response, "押金方案.xls", "数据", DepositPlanRespVO.class,
                BeanUtils.toBean(list, DepositPlanRespVO.class));
    }
}
