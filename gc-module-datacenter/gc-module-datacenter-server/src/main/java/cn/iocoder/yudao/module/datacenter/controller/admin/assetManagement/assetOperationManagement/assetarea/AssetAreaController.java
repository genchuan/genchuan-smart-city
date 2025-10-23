package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaSaveReqVO;
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

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetarea.AssetAreaDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetarea.AssetAreaService;

@Tag(name = "管理后台 - 资产关联行政区划")
@RestController
@RequestMapping("/datacenter/asset-area")
@Validated
public class AssetAreaController {

    @Resource
    private AssetAreaService assetAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联行政区划")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-area:create')")
    public CommonResult<Long> createAssetArea(@Valid @RequestBody AssetAreaSaveReqVO createReqVO) {
        return success(assetAreaService.createAssetArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联行政区划")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-area:update')")
    public CommonResult<Boolean> updateAssetArea(@Valid @RequestBody AssetAreaSaveReqVO updateReqVO) {
        assetAreaService.updateAssetArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联行政区划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-area:delete')")
    public CommonResult<Boolean> deleteAssetArea(@RequestParam("id") Long id) {
        assetAreaService.deleteAssetArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联行政区划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-area:query')")
    public CommonResult<AssetAreaRespVO> getAssetArea(@RequestParam("id") Long id) {
        AssetAreaDO assetArea = assetAreaService.getAssetArea(id);
        return success(BeanUtils.toBean(assetArea, AssetAreaRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得资产关联行政区划列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-area:query')")
    public CommonResult<List<AssetAreaRespVO>> getAssetAreaList(@Valid AssetAreaListReqVO listReqVO) {
        List<AssetAreaDO> list = assetAreaService.getAssetAreaList(listReqVO);
        return success(BeanUtils.toBean(list, AssetAreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetAreaExcel(@Valid AssetAreaListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<AssetAreaDO> list = assetAreaService.getAssetAreaList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "资产关联行政区划.xls", "数据", AssetAreaRespVO.class,
                        BeanUtils.toBean(list, AssetAreaRespVO.class));
    }

}