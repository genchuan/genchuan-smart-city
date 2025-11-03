package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngSimpleVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdatamng.AssetDataMngDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetdatamng.AssetDataMngService;

@Tag(name = "管理后台 - 资产数据管理")
@RestController
@RequestMapping("/datacenter/asset-data-mng")
@Validated
public class AssetDataMngController {

    @Resource
    private AssetDataMngService assetDataMngService;

    @PostMapping("/create")
    @Operation(summary = "创建资产数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:create')")
    public CommonResult<Long> createAssetDataMng(@Valid @RequestBody AssetDataMngSaveReqVO createReqVO) {
        return success(assetDataMngService.createAssetDataMng(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:update')")
    public CommonResult<Boolean> updateAssetDataMng(@Valid @RequestBody AssetDataMngSaveReqVO updateReqVO) {
        assetDataMngService.updateAssetDataMng(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产数据管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:delete')")
    public CommonResult<Boolean> deleteAssetDataMng(@RequestParam("id") Long id) {
        assetDataMngService.deleteAssetDataMng(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产数据管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:query')")
    public CommonResult<AssetDataMngRespVO> getAssetDataMng(@RequestParam("id") Long id) {
        AssetDataMngDO assetDataMng = assetDataMngService.getAssetDataMng(id);
        return success(BeanUtils.toBean(assetDataMng, AssetDataMngRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产数据管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:query')")
    public CommonResult<PageResult<AssetDataMngRespVO>> getAssetDataMngPage(@Valid AssetDataMngPageReqVO pageReqVO) {
        PageResult<AssetDataMngDO> pageResult = assetDataMngService.getAssetDataMngPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetDataMngRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产数据管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetDataMngExcel(@Valid AssetDataMngPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetDataMngDO> list = assetDataMngService.getAssetDataMngPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产数据管理.xls", "数据", AssetDataMngRespVO.class,
                        BeanUtils.toBean(list, AssetDataMngRespVO.class));
    }

    /**
     * 获得所有可用的资产数据管理列表
     * @return
     */
    @GetMapping("/enabled-list")
    @Operation(summary = "获得所有可用的资产数据管理列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-mng:query')")
    public CommonResult<List<AssetDataMngSimpleVO>> getEnabledAssetDataMngList() {
        List<AssetDataMngSimpleVO> list = assetDataMngService.getEnabledAssetDataMngList();
        return success(list);
    }

}