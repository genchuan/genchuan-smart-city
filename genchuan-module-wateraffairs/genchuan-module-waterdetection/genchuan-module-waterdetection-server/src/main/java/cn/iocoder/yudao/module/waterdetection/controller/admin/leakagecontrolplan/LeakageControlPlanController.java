package cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.leakagecontrolplan.LeakageControlPlanDO;
import cn.iocoder.yudao.module.waterdetection.service.leakagecontrolplan.LeakageControlPlanService;

@Tag(name = "管理后台 - 漏损控制方案建议")
@RestController
@RequestMapping("/waterdetection/leakage-control-plan")
@Validated
public class LeakageControlPlanController {

    @Resource
    private LeakageControlPlanService leakageControlPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建漏损控制方案建议")
    @PreAuthorize("@ss.hasPermission('waterdetection:leakage-control-plan:create')")
    public CommonResult<Long> createLeakageControlPlan(@Valid @RequestBody LeakageControlPlanSaveReqVO createReqVO) {
        return success(leakageControlPlanService.createLeakageControlPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新漏损控制方案建议")
    @PreAuthorize("@ss.hasPermission('waterdetection:leakage-control-plan:update')")
    public CommonResult<Boolean> updateLeakageControlPlan(@Valid @RequestBody LeakageControlPlanSaveReqVO updateReqVO) {
        leakageControlPlanService.updateLeakageControlPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除漏损控制方案建议")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:leakage-control-plan:delete')")
    public CommonResult<Boolean> deleteLeakageControlPlan(@RequestParam("id") Long id) {
        leakageControlPlanService.deleteLeakageControlPlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得漏损控制方案建议")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:leakage-control-plan:query')")
    public CommonResult<LeakageControlPlanRespVO> getLeakageControlPlan(@RequestParam("id") Long id) {
        LeakageControlPlanDO leakageControlPlan = leakageControlPlanService.getLeakageControlPlan(id);
        return success(BeanUtils.toBean(leakageControlPlan, LeakageControlPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得漏损控制方案建议分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:leakage-control-plan:query')")
    public CommonResult<PageResult<LeakageControlPlanRespVO>> getLeakageControlPlanPage(@Valid LeakageControlPlanPageReqVO pageReqVO) {
        PageResult<LeakageControlPlanDO> pageResult = leakageControlPlanService.getLeakageControlPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LeakageControlPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出漏损控制方案建议 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:leakage-control-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLeakageControlPlanExcel(@Valid LeakageControlPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LeakageControlPlanDO> list = leakageControlPlanService.getLeakageControlPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "漏损控制方案建议.xls", "数据", LeakageControlPlanRespVO.class,
                        BeanUtils.toBean(list, LeakageControlPlanRespVO.class));
    }

}