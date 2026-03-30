package cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersourcemanagement.WaterSourceManagementDO;
import cn.iocoder.yudao.module.waterdetection.service.watersourcemanagement.WaterSourceManagementService;

@Tag(name = "管理后台 - 水源类型及属性管理")
@RestController
@RequestMapping("/waterdetection/water-source-management")
@Validated
public class WaterSourceManagementController {

    @Resource
    private WaterSourceManagementService waterSourceManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建水源类型及属性管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-source-management:create')")
    public CommonResult<Long> createWaterSourceManagement(@Valid @RequestBody WaterSourceManagementSaveReqVO createReqVO) {
        return success(waterSourceManagementService.createWaterSourceManagement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新水源类型及属性管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-source-management:update')")
    public CommonResult<Boolean> updateWaterSourceManagement(@Valid @RequestBody WaterSourceManagementSaveReqVO updateReqVO) {
        waterSourceManagementService.updateWaterSourceManagement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除水源类型及属性管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-source-management:delete')")
    public CommonResult<Boolean> deleteWaterSourceManagement(@RequestParam("id") Long id) {
        waterSourceManagementService.deleteWaterSourceManagement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得水源类型及属性管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-source-management:query')")
    public CommonResult<WaterSourceManagementRespVO> getWaterSourceManagement(@RequestParam("id") Long id) {
        WaterSourceManagementDO waterSourceManagement = waterSourceManagementService.getWaterSourceManagement(id);
        return success(BeanUtils.toBean(waterSourceManagement, WaterSourceManagementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得水源类型及属性管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-source-management:query')")
    public CommonResult<PageResult<WaterSourceManagementRespVO>> getWaterSourceManagementPage(@Valid WaterSourceManagementPageReqVO pageReqVO) {
        PageResult<WaterSourceManagementDO> pageResult = waterSourceManagementService.getWaterSourceManagementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterSourceManagementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出水源类型及属性管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-source-management:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterSourceManagementExcel(@Valid WaterSourceManagementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterSourceManagementDO> list = waterSourceManagementService.getWaterSourceManagementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "水源类型及属性管理.xls", "数据", WaterSourceManagementRespVO.class,
                        BeanUtils.toBean(list, WaterSourceManagementRespVO.class));
    }

}