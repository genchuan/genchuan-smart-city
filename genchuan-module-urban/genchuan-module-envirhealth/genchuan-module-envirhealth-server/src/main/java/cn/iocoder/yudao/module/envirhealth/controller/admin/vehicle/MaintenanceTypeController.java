package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.MaintenanceTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.maintenancetype.MaintenanceTypeService;

@Tag(name = "字典表 - 维护类型")
@RestController
@RequestMapping("/envirhealth/maintenance-type")
@Validated
public class MaintenanceTypeController {

    @Resource
    private MaintenanceTypeService maintenanceTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建维护类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:create')")
    public CommonResult<Long> createMaintenanceType(@Valid @RequestBody MaintenanceTypeSaveReqVO createReqVO) {
        return success(maintenanceTypeService.createMaintenanceType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新维护类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:update')")
    public CommonResult<Boolean> updateMaintenanceType(@Valid @RequestBody MaintenanceTypeSaveReqVO updateReqVO) {
        maintenanceTypeService.updateMaintenanceType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除维护类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:delete')")
    public CommonResult<Boolean> deleteMaintenanceType(@RequestParam("id") Long id) {
        maintenanceTypeService.deleteMaintenanceType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得维护类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:query')")
    public CommonResult<MaintenanceTypeRespVO> getMaintenanceType(@RequestParam("id") Long id) {
        MaintenanceTypeDO maintenanceType = maintenanceTypeService.getMaintenanceType(id);
        return success(BeanUtils.toBean(maintenanceType, MaintenanceTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得维护类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:query')")
    public CommonResult<PageResult<MaintenanceTypeRespVO>> getMaintenanceTypePage(@Valid MaintenanceTypePageReqVO pageReqVO) {
        PageResult<MaintenanceTypeDO> pageResult = maintenanceTypeService.getMaintenanceTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintenanceTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出维护类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintenanceTypeExcel(@Valid MaintenanceTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintenanceTypeDO> list = maintenanceTypeService.getMaintenanceTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "维护类型字典表.xls", "数据", MaintenanceTypeRespVO.class,
                        BeanUtils.toBean(list, MaintenanceTypeRespVO.class));
    }

    /**
     * 获得维护类型字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得维护类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:maintenance-type:query')")
    public CommonResult<List<OptionVO>> getMaintenanceTypeOptions() {
        return success(maintenanceTypeService.getMaintenanceTypeOptions());
    }

}
