package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentasset.EquipmentAssetDO;
import cn.iocoder.yudao.module.waterdetection.service.equipmentasset.EquipmentAssetService;

@Tag(name = "管理后台 - 设备资产台账管理")
@RestController
@RequestMapping("/waterdetection/equipment-asset")
@Validated
public class EquipmentAssetController {

    @Resource
    private EquipmentAssetService equipmentAssetService;

    @PostMapping("/create")
    @Operation(summary = "创建设备资产台账管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-asset:create')")
    public CommonResult<Long> createEquipmentAsset(@Valid @RequestBody EquipmentAssetSaveReqVO createReqVO) {
        return success(equipmentAssetService.createEquipmentAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备资产台账管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-asset:update')")
    public CommonResult<Boolean> updateEquipmentAsset(@Valid @RequestBody EquipmentAssetSaveReqVO updateReqVO) {
        equipmentAssetService.updateEquipmentAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备资产台账管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-asset:delete')")
    public CommonResult<Boolean> deleteEquipmentAsset(@RequestParam("id") Long id) {
        equipmentAssetService.deleteEquipmentAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备资产台账管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-asset:query')")
    public CommonResult<EquipmentAssetRespVO> getEquipmentAsset(@RequestParam("id") Long id) {
        EquipmentAssetDO equipmentAsset = equipmentAssetService.getEquipmentAsset(id);
        return success(BeanUtils.toBean(equipmentAsset, EquipmentAssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备资产台账管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-asset:query')")
    public CommonResult<PageResult<EquipmentAssetRespVO>> getEquipmentAssetPage(@Valid EquipmentAssetPageReqVO pageReqVO) {
        PageResult<EquipmentAssetDO> pageResult = equipmentAssetService.getEquipmentAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EquipmentAssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备资产台账管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEquipmentAssetExcel(@Valid EquipmentAssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EquipmentAssetDO> list = equipmentAssetService.getEquipmentAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备资产台账管理.xls", "数据", EquipmentAssetRespVO.class,
                        BeanUtils.toBean(list, EquipmentAssetRespVO.class));
    }

}