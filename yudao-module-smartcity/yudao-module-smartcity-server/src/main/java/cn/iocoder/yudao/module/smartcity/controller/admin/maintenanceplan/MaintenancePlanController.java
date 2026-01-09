package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo.MaintenancePlanPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo.MaintenancePlanRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo.MaintenancePlanSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplan.MaintenancePlanDO;
import cn.iocoder.yudao.module.smartcity.service.maintenanceplan.MaintenancePlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 养护计划")
@RestController
@RequestMapping("/smartcity/maintenance-plan")
@Validated
public class MaintenancePlanController {

    @Resource
    private MaintenancePlanService maintenancePlanService;

    @PostMapping("/create")
    @Operation(summary = "创建养护计划")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plan:create')")
    public CommonResult<Long> createMaintenancePlan(@Valid @RequestBody MaintenancePlanSaveReqVO createReqVO) {
        return success(maintenancePlanService.createMaintenancePlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新养护计划")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plan:update')")
    public CommonResult<Boolean> updateMaintenancePlan(@Valid @RequestBody MaintenancePlanSaveReqVO updateReqVO) {
        maintenancePlanService.updateMaintenancePlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除养护计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plan:delete')")
    public CommonResult<Boolean> deleteMaintenancePlan(@RequestParam("id") Long id) {
        maintenancePlanService.deleteMaintenancePlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得养护计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plan:query')")
    public CommonResult<MaintenancePlanRespVO> getMaintenancePlan(@RequestParam("id") Long id) {
        MaintenancePlanDO maintenancePlan = maintenancePlanService.getMaintenancePlan(id);
        return success(BeanUtils.toBean(maintenancePlan, MaintenancePlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得养护计划分页")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plan:query')")
    public CommonResult<PageResult<MaintenancePlanRespVO>> getMaintenancePlanPage(@Valid MaintenancePlanPageReqVO pageReqVO) {
        PageResult<MaintenancePlanDO> pageResult = maintenancePlanService.getMaintenancePlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintenancePlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出养护计划 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintenancePlanExcel(@Valid MaintenancePlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintenancePlanDO> list = maintenancePlanService.getMaintenancePlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "养护计划.xls", "数据", MaintenancePlanRespVO.class,
                        BeanUtils.toBean(list, MaintenancePlanRespVO.class));
    }

}