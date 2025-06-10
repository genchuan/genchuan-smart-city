package cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.developmentinspectionplan.DevelopmentInspectionPlanDO;
import cn.iocoder.yudao.module.smartcity.service.developmentinspectionplan.DevelopmentInspectionPlanService;

@Tag(name = "管理后台 - 巡查计划管理")
@RestController
@RequestMapping("/smartcity/development-inspection-plan")
@Validated
public class DevelopmentInspectionPlanController {

    @Resource
    private DevelopmentInspectionPlanService developmentInspectionPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查计划管理")
    @PreAuthorize("@ss.hasPermission('smartcity:development-inspection-plan:create')")
    public CommonResult<Long> createDevelopmentInspectionPlan(@Valid @RequestBody DevelopmentInspectionPlanSaveReqVO createReqVO) {
        return success(developmentInspectionPlanService.createDevelopmentInspectionPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查计划管理")
    @PreAuthorize("@ss.hasPermission('smartcity:development-inspection-plan:update')")
    public CommonResult<Boolean> updateDevelopmentInspectionPlan(@Valid @RequestBody DevelopmentInspectionPlanSaveReqVO updateReqVO) {
        developmentInspectionPlanService.updateDevelopmentInspectionPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查计划管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:development-inspection-plan:delete')")
    public CommonResult<Boolean> deleteDevelopmentInspectionPlan(@RequestParam("id") Long id) {
        developmentInspectionPlanService.deleteDevelopmentInspectionPlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查计划管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:development-inspection-plan:query')")
    public CommonResult<DevelopmentInspectionPlanRespVO> getDevelopmentInspectionPlan(@RequestParam("id") Long id) {
        DevelopmentInspectionPlanDO developmentInspectionPlan = developmentInspectionPlanService.getDevelopmentInspectionPlan(id);
        return success(BeanUtils.toBean(developmentInspectionPlan, DevelopmentInspectionPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查计划管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:development-inspection-plan:query')")
    public CommonResult<PageResult<DevelopmentInspectionPlanRespVO>> getDevelopmentInspectionPlanPage(@Valid DevelopmentInspectionPlanPageReqVO pageReqVO) {
        PageResult<DevelopmentInspectionPlanDO> pageResult = developmentInspectionPlanService.getDevelopmentInspectionPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DevelopmentInspectionPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查计划管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:development-inspection-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDevelopmentInspectionPlanExcel(@Valid DevelopmentInspectionPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DevelopmentInspectionPlanDO> list = developmentInspectionPlanService.getDevelopmentInspectionPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查计划管理.xls", "数据", DevelopmentInspectionPlanRespVO.class,
                        BeanUtils.toBean(list, DevelopmentInspectionPlanRespVO.class));
    }

}