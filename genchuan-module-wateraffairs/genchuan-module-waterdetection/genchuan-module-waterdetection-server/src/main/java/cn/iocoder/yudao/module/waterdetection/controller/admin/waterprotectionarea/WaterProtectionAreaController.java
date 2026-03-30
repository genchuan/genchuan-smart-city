package cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterprotectionarea.WaterProtectionAreaDO;
import cn.iocoder.yudao.module.waterdetection.service.waterprotectionarea.WaterProtectionAreaService;

@Tag(name = "管理后台 - 水源保护区管理")
@RestController
@RequestMapping("/waterdetection/water-protection-area")
@Validated
public class WaterProtectionAreaController {

    @Resource
    private WaterProtectionAreaService waterProtectionAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建水源保护区管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-protection-area:create')")
    public CommonResult<Long> createWaterProtectionArea(@Valid @RequestBody WaterProtectionAreaSaveReqVO createReqVO) {
        return success(waterProtectionAreaService.createWaterProtectionArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新水源保护区管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-protection-area:update')")
    public CommonResult<Boolean> updateWaterProtectionArea(@Valid @RequestBody WaterProtectionAreaSaveReqVO updateReqVO) {
        waterProtectionAreaService.updateWaterProtectionArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除水源保护区管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-protection-area:delete')")
    public CommonResult<Boolean> deleteWaterProtectionArea(@RequestParam("id") Long id) {
        waterProtectionAreaService.deleteWaterProtectionArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得水源保护区管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-protection-area:query')")
    public CommonResult<WaterProtectionAreaRespVO> getWaterProtectionArea(@RequestParam("id") Long id) {
        WaterProtectionAreaDO waterProtectionArea = waterProtectionAreaService.getWaterProtectionArea(id);
        return success(BeanUtils.toBean(waterProtectionArea, WaterProtectionAreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得水源保护区管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-protection-area:query')")
    public CommonResult<PageResult<WaterProtectionAreaRespVO>> getWaterProtectionAreaPage(@Valid WaterProtectionAreaPageReqVO pageReqVO) {
        PageResult<WaterProtectionAreaDO> pageResult = waterProtectionAreaService.getWaterProtectionAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterProtectionAreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出水源保护区管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-protection-area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterProtectionAreaExcel(@Valid WaterProtectionAreaPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterProtectionAreaDO> list = waterProtectionAreaService.getWaterProtectionAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "水源保护区管理.xls", "数据", WaterProtectionAreaRespVO.class,
                        BeanUtils.toBean(list, WaterProtectionAreaRespVO.class));
    }

}