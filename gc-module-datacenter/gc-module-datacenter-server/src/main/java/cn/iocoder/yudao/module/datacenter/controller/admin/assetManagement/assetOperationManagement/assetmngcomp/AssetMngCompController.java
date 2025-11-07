package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetmngcomp.AssetMngCompDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetmngcomp.AssetMngCompService;

@Tag(name = "管理后台 - 资产关联管理部件")
@RestController
@RequestMapping("/datacenter/asset-mng-comp")
@Validated
public class AssetMngCompController {

    @Resource
    private AssetMngCompService assetMngCompService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联管理部件")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:create')")
    public CommonResult<Long> createAssetMngComp(@Valid @RequestBody AssetMngCompSaveReqVO createReqVO) {
        return success(assetMngCompService.createAssetMngComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联管理部件")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:update')")
    public CommonResult<Boolean> updateAssetMngComp(@Valid @RequestBody AssetMngCompSaveReqVO updateReqVO) {
        assetMngCompService.updateAssetMngComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联管理部件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:delete')")
    public CommonResult<Boolean> deleteAssetMngComp(@RequestParam("id") Long id) {
        assetMngCompService.deleteAssetMngComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联管理部件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:query')")
    public CommonResult<AssetMngCompRespVO> getAssetMngComp(@RequestParam("id") Long id) {
        AssetMngCompDO assetMngComp = assetMngCompService.getAssetMngComp(id);
        return success(BeanUtils.toBean(assetMngComp, AssetMngCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产关联管理部件分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:query')")
    public CommonResult<PageResult<AssetMngCompRespVO>> getAssetMngCompPage(@Valid AssetMngCompPageReqVO pageReqVO) {
        PageResult<AssetMngCompDO> pageResult = assetMngCompService.getAssetMngCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetMngCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联管理部件 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetMngCompExcel(@Valid AssetMngCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetMngCompDO> list = assetMngCompService.getAssetMngCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产关联管理部件.xls", "数据", AssetMngCompRespVO.class,
                        BeanUtils.toBean(list, AssetMngCompRespVO.class));
    }

    /**
     * 批量删除资产关联管理部件
     *
     * @param ids 编号列表
     */
    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除资产关联管理部件")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-mng-comp:delete')")
    public CommonResult<Boolean> deleteAssetMngCompBatch(@RequestBody List<Long> ids) {
        assetMngCompService.deleteAssetMngCompBatch(ids);
        return success(true);
    }


}