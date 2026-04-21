package cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan;

import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
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

import cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectplan.InspectPlanDO;
import cn.iocoder.yudao.module.inspectop.service.inspectplan.InspectPlanService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "巡查巡检 - 巡检计划")
@RestController
@RequestMapping("/inspectop/inspect-plan")
@Validated
public class InspectPlanController {

    @Resource
    private InspectPlanService inspectPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:create')")
    public CommonResult<Long> createInspectPlan(@Valid @RequestBody InspectPlanSaveReqVO createReqVO) {
        return success(inspectPlanService.createInspectPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:update')")
    public CommonResult<Boolean> updateInspectPlan(@Valid @RequestBody InspectPlanSaveReqVO updateReqVO) {
        inspectPlanService.updateInspectPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:delete')")
    public CommonResult<Boolean> deleteInspectPlan(@RequestParam("id") Long id) {
        inspectPlanService.deleteInspectPlan(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检计划")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:delete')")
    public CommonResult<Boolean> deleteInspectPlanList(@RequestParam("ids") List<Long> ids) {
        inspectPlanService.deleteInspectPlanListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:query')")
    public CommonResult<InspectPlanRespVO> getInspectPlan(@RequestParam("id") Long id) {
        InspectPlanDO inspectPlan = inspectPlanService.getInspectPlan(id);
        return success(BeanUtils.toBean(inspectPlan, InspectPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检计划分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:query')")
    public CommonResult<PageResult<InspectPlanRespVO>> getInspectPlanPage(@Valid InspectPlanPageReqVO pageReqVO) {
        PageResult<InspectPlanDO> pageResult = inspectPlanService.getInspectPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检计划 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectPlanExcel(@Valid InspectPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectPlanDO> list = inspectPlanService.getInspectPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检计划.xls", "数据", InspectPlanRespVO.class,
                        BeanUtils.toBean(list, InspectPlanRespVO.class));
    }

    @PostMapping("/import")
    @Operation(summary = "导入巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:import')")
    public CommonResult<ImportRespVO> importInspectPlan(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") Boolean updateSupport) throws IOException {

        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException("请选择要导入的文件");
        }

        // 检查文件格式
        String filename = file.getOriginalFilename();
        if (filename != null && !(filename.endsWith(".xls") || filename.endsWith(".xlsx"))) {
            throw new RuntimeException("请上传Excel文件（.xls 或 .xlsx格式）");
        }

        ImportRespVO respVO = inspectPlanService.importInspectPlan(file, updateSupport);
        return success(respVO);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:enable')")
    public CommonResult<Boolean> enableInspectPlan(@Valid @RequestBody InspectPlanStatusReqVO reqVO) {
        // 状态值 "1" 对应 "待生效"（根据您的字典值说明）
        inspectPlanService.updateInspectPlanStatus(reqVO.getId(), "1");
        return success(true);
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:pause')")
    public CommonResult<Boolean> pauseInspectPlan(@Valid @RequestBody InspectPlanStatusReqVO reqVO) {
        // 状态值 "4" 对应 "已暂停"（根据您的字典值说明）
        inspectPlanService.updateInspectPlanStatus(reqVO.getId(), "4");
        return success(true);
    }

    @PutMapping("/resume")
    @Operation(summary = "启用巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:enable')")
    public CommonResult<Boolean> resumeInspectPlan(@Valid @RequestBody InspectPlanStatusReqVO reqVO) {
        // 状态值 "2" 对应 "进行中"（根据您的字典值说明）
        // 注意：如果您的业务逻辑中"启用"应该对应其他状态，请调整此处的状态值
        inspectPlanService.updateInspectPlanStatus(reqVO.getId(), "2");
        return success(true);
    }

    @PutMapping("/complete")
    @Operation(summary = "完成巡检计划")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:complete')")
    public CommonResult<Boolean> completeInspectPlan(@Valid @RequestBody InspectPlanStatusReqVO reqVO) {
        inspectPlanService.updateInspectPlanStatus(reqVO.getId(), "3");
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取巡检计划统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-plan:chart')")
    public CommonResult<InspectPlanChartRespVO> getInspectPlanChart(@Valid InspectPlanChartReqVO reqVO) {
        InspectPlanChartRespVO chartData = inspectPlanService.getInspectPlanChart(reqVO);
        return success(chartData);
    }

}