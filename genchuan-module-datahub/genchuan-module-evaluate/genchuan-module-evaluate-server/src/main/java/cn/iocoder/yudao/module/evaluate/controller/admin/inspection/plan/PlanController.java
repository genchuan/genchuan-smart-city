package cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.plan.PlanDO;
import cn.iocoder.yudao.module.evaluate.service.plan.PlanService;
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

@Tag(name = "管理后台 - 考察计划")
@RestController
@RequestMapping("/evaluate/plan")
@Validated
public class PlanController {

    @Resource
    private PlanService planService;

    @PostMapping("/create")
    @Operation(summary = "创建考察计划")
    @PreAuthorize("@ss.hasPermission('evaluate:plan:create')")
    public CommonResult<Long> createPlan(@Valid @RequestBody PlanSaveReqVO createReqVO) {
        return success(planService.createPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考察计划")
    @PreAuthorize("@ss.hasPermission('evaluate:plan:update')")
    public CommonResult<Boolean> updatePlan(@Valid @RequestBody PlanSaveReqVO updateReqVO) {
        planService.updatePlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考察计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:plan:delete')")
    public CommonResult<Boolean> deletePlan(@RequestParam("id") Long id) {
        planService.deletePlan(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除考察计划")
                @PreAuthorize("@ss.hasPermission('evaluate:plan:delete')")
    public CommonResult<Boolean> deletePlanList(@RequestParam("ids") List<Long> ids) {
        planService.deletePlanListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考察计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:plan:query')")
    public CommonResult<PlanRespVO> getPlan(@RequestParam("id") Long id) {
        PlanDO plan = planService.getPlan(id);
        return success(BeanUtils.toBean(plan, PlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考察计划分页")
    @PreAuthorize("@ss.hasPermission('evaluate:plan:query')")
    public CommonResult<PageResult<PlanRespVO>> getPlanPage(@Valid PlanPageReqVO pageReqVO) {
        PageResult<PlanDO> pageResult = planService.getPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考察计划 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlanExcel(@Valid PlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PlanDO> list = planService.getPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考察计划.xls", "数据", PlanRespVO.class,
                        BeanUtils.toBean(list, PlanRespVO.class));
    }

}