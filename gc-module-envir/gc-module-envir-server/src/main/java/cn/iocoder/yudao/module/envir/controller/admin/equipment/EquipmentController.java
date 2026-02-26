package cn.iocoder.yudao.module.envir.controller.admin.equipment;

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

import cn.iocoder.yudao.module.envir.controller.admin.equipment.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.equipment.EquipmentDO;
import cn.iocoder.yudao.module.envir.service.equipment.EquipmentService;

@Tag(name = "管理后台 - 设备")
@RestController
@RequestMapping("/envir/equipment")
@Validated
public class EquipmentController {

    @Resource
    private EquipmentService equipmentService;

    @PostMapping("/create")
    @Operation(summary = "创建设备")
    @PreAuthorize("@ss.hasPermission('envir:equipment:create')")
    public CommonResult<Long> createEquipment(@Valid @RequestBody EquipmentSaveReqVO createReqVO) {
        return success(equipmentService.createEquipment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备")
    @PreAuthorize("@ss.hasPermission('envir:equipment:update')")
    public CommonResult<Boolean> updateEquipment(@Valid @RequestBody EquipmentSaveReqVO updateReqVO) {
        equipmentService.updateEquipment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:equipment:delete')")
    public CommonResult<Boolean> deleteEquipment(@RequestParam("id") Long id) {
        equipmentService.deleteEquipment(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:equipment:query')")
    public CommonResult<EquipmentRespVO> getEquipment(@RequestParam("id") Long id) {
        EquipmentDO equipment = equipmentService.getEquipment(id);
        return success(BeanUtils.toBean(equipment, EquipmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分页")
    @PreAuthorize("@ss.hasPermission('envir:equipment:query')")
    public CommonResult<PageResult<EquipmentRespVO>> getEquipmentPage(@Valid EquipmentPageReqVO pageReqVO) {
        PageResult<EquipmentDO> pageResult = equipmentService.getEquipmentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EquipmentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备 Excel")
    @PreAuthorize("@ss.hasPermission('envir:equipment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEquipmentExcel(@Valid EquipmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EquipmentDO> list = equipmentService.getEquipmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备.xls", "数据", EquipmentRespVO.class,
                        BeanUtils.toBean(list, EquipmentRespVO.class));
    }

}