package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridSaveReqVO;
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
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetgrid.AssetGridDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetgrid.AssetGridService;

@Tag(name = "管理后台 - 资产关联网格")
@RestController
@RequestMapping("/datacenter/asset-grid")
@Validated
public class AssetGridController {

    @Resource
    private AssetGridService assetGridService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联网格")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-grid:create')")
    public CommonResult<Long> createAssetGrid(@Valid @RequestBody AssetGridSaveReqVO createReqVO) {
        return success(assetGridService.createAssetGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联网格")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-grid:update')")
    public CommonResult<Boolean> updateAssetGrid(@Valid @RequestBody AssetGridSaveReqVO updateReqVO) {
        assetGridService.updateAssetGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联网格")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-grid:delete')")
    public CommonResult<Boolean> deleteAssetGrid(@RequestParam("id") Long id) {
        assetGridService.deleteAssetGrid(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联网格")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-grid:query')")
    public CommonResult<AssetGridRespVO> getAssetGrid(@RequestParam("id") Long id) {
        AssetGridDO assetGrid = assetGridService.getAssetGrid(id);
        return success(BeanUtils.toBean(assetGrid, AssetGridRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产关联网格分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-grid:query')")
    public CommonResult<PageResult<AssetGridRespVO>> getAssetGridPage(@Valid AssetGridPageReqVO pageReqVO) {
        PageResult<AssetGridDO> pageResult = assetGridService.getAssetGridPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetGridRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联网格 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-grid:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetGridExcel(@Valid AssetGridPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetGridDO> list = assetGridService.getAssetGridPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产关联网格.xls", "数据", AssetGridRespVO.class,
                        BeanUtils.toBean(list, AssetGridRespVO.class));
    }

}