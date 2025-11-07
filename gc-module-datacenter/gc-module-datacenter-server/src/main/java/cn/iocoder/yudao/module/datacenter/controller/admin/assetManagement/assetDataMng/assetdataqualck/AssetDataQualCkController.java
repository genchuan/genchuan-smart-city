package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdataqualck.AssetDataQualCkDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetdataqualck.AssetDataQualCkService;

@Tag(name = "管理后台 - 资产数据质量检查")
@RestController
@RequestMapping("/datacenter/asset-data-qual-ck")
@Validated
public class AssetDataQualCkController {

    @Resource
    private AssetDataQualCkService assetDataQualCkService;

    @PostMapping("/create")
    @Operation(summary = "创建资产数据质量检查")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-qual-ck:create')")
    public CommonResult<Long> createAssetDataQualCk(@Valid @RequestBody AssetDataQualCkSaveReqVO createReqVO) {
        return success(assetDataQualCkService.createAssetDataQualCk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产数据质量检查")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-qual-ck:update')")
    public CommonResult<Boolean> updateAssetDataQualCk(@Valid @RequestBody AssetDataQualCkSaveReqVO updateReqVO) {
        assetDataQualCkService.updateAssetDataQualCk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产数据质量检查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-qual-ck:delete')")
    public CommonResult<Boolean> deleteAssetDataQualCk(@RequestParam("id") Long id) {
        assetDataQualCkService.deleteAssetDataQualCk(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产数据质量检查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-qual-ck:query')")
    public CommonResult<AssetDataQualCkRespVO> getAssetDataQualCk(@RequestParam("id") Long id) {
        AssetDataQualCkDO assetDataQualCk = assetDataQualCkService.getAssetDataQualCk(id);
        return success(BeanUtils.toBean(assetDataQualCk, AssetDataQualCkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产数据质量检查分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-qual-ck:query')")
    public CommonResult<PageResult<AssetDataQualCkRespVO>> getAssetDataQualCkPage(@Valid AssetDataQualCkPageReqVO pageReqVO) {
        PageResult<AssetDataQualCkDO> pageResult = assetDataQualCkService.getAssetDataQualCkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetDataQualCkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产数据质量检查 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-data-qual-ck:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetDataQualCkExcel(@Valid AssetDataQualCkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetDataQualCkDO> list = assetDataQualCkService.getAssetDataQualCkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产数据质量检查.xls", "数据", AssetDataQualCkRespVO.class,
                        BeanUtils.toBean(list, AssetDataQualCkRespVO.class));
    }

}