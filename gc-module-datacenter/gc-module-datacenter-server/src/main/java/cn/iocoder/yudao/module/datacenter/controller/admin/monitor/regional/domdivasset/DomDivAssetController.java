package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivasset.DomDivAssetDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivasset.DomDivAssetService;

@Tag(name = "管理后台 - 资产分域")
@RestController
@RequestMapping("/datacenter/dom-div-asset")
@Validated
public class DomDivAssetController {

    @Resource
    private DomDivAssetService domDivAssetService;

    @PostMapping("/create")
    @Operation(summary = "创建资产分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-asset:create')")
    public CommonResult<Long> createDomDivAsset(@Valid @RequestBody DomDivAssetSaveReqVO createReqVO) {
        return success(domDivAssetService.createDomDivAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-asset:update')")
    public CommonResult<Boolean> updateDomDivAsset(@Valid @RequestBody DomDivAssetSaveReqVO updateReqVO) {
        domDivAssetService.updateDomDivAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-asset:delete')")
    public CommonResult<Boolean> deleteDomDivAsset(@RequestParam("id") Long id) {
        domDivAssetService.deleteDomDivAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-asset:query')")
    public CommonResult<DomDivAssetRespVO> getDomDivAsset(@RequestParam("id") Long id) {
        DomDivAssetDO domDivAsset = domDivAssetService.getDomDivAsset(id);
        return success(BeanUtils.toBean(domDivAsset, DomDivAssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-asset:query')")
    public CommonResult<PageResult<DomDivAssetRespVO>> getDomDivAssetPage(@Valid DomDivAssetPageReqVO pageReqVO) {
        PageResult<DomDivAssetDO> pageResult = domDivAssetService.getDomDivAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivAssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivAssetExcel(@Valid DomDivAssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivAssetDO> list = domDivAssetService.getDomDivAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产分域.xls", "数据", DomDivAssetRespVO.class,
                        BeanUtils.toBean(list, DomDivAssetRespVO.class));
    }

}