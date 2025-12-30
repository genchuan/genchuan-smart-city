package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.AlarmRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetService;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "管理后台 - 资产")
@RestController
@RequestMapping("/datacenter/thingsboard/asset")
@Validated
public class AssetController {

    @Resource
    private AssetService assetService;

    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    @PostMapping("/create")
    @Operation(summary = "创建资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:create')")
    public CommonResult<Long> createAsset(@Valid @RequestBody AssetSaveReqVO createReqVO) {
        return success(assetService.createAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:update')")
    public CommonResult<Boolean> updateAsset(@Valid @RequestBody AssetSaveReqVO updateReqVO) {
        assetService.updateAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset:delete')")
    public CommonResult<Boolean> deleteAsset(@RequestParam("id") Long id) {
        assetService.deleteAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:query')")
    public CommonResult<AssetRespVO> getAsset(@RequestParam("id") Long id) {
        AssetDO asset = assetService.getAsset(id);
        return success(BeanUtils.toBean(asset, AssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-info:query')")
    public CommonResult<PageResult<AssetRespVO>> getAssetInfoPage(@Valid AssetPageReqVO pageReqVO) {
        PageResult<AssetDO> pageResult = assetService.getAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetRespVO.class));

    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetExcel(@Valid AssetPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetDO> list = assetService.getAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产.xls", "数据", AssetRespVO.class,
                BeanUtils.toBean(list, AssetRespVO.class));
    }

    /**
     * 获取资产简单信息
     */
    @GetMapping("/list-simple")
    @Operation(summary = "获取资产简单信息列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:query')")
    public CommonResult<List<AssetSimpleRespVO>> getAssetList(){
        List<AssetSimpleRespVO> list = assetService.getAssetList();
        return success(list);
    }

    @GetMapping("/asset-page")
    @Operation(summary = "获取tb资产分页（包含属性和设备信息）")
    @PreAuthorize("@ss.hasPermission('device:alarm:query')")
    public CommonResult<PageResult<AssetDetailRespVO>> getAssetPage(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        PageResult<AssetDetailRespVO> pageResult = assetService.getAssetPage1(pageSize, page);
        return success(pageResult);
    }

    @PostMapping("/sync")
    @Operation(summary = "同步ThingsBoard资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:sync')")
    public CommonResult<Map<String, Object>> syncAssetsFromThingsBoard() {
        Map<String, Object> result = assetService.syncAssetsFromThingsBoard();
        return success(result);
    }

    @GetMapping("/asset-profiles")
    @Operation(summary = "获取资产配置分页列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:query')")
    public CommonResult<PageData<AssetProfile>> getAssetProfiles(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "sortProperty", required = false) String sortProperty,
            @RequestParam(value = "sortOrder", required = false) String sortOrder) {

        PageData<AssetProfile> assetProfiles = assetService.getAssetProfiles(pageSize, page, sortProperty, sortOrder);
        return success(assetProfiles);
    }

    @PostMapping("/{assetId}/attributes")
    @Operation(summary = "添加资产属性")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:update')")
    public CommonResult<Boolean> addAssetAttributes(
            @Parameter(description = "资产ID", required = true)
            @PathVariable("assetId") String assetId,
            @RequestBody Map<String, Object> attributes) {

        assetService.addAssetAttributes(assetId, attributes);
        return success(true);
    }

    @DeleteMapping("/{assetId}/attributes")
    @Operation(summary = "删除资产属性")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:update')")
    public CommonResult<Boolean> deleteAssetAttributes(
            @Parameter(description = "资产ID", required = true)
            @PathVariable("assetId") String assetId,
            @Parameter(description = "属性作用域", example = "SERVER_SCOPE")
            @RequestParam(value = "scope", defaultValue = "SERVER_SCOPE") String scope,
            @Parameter(description = "要删除的属性键，用逗号分隔", required = true)
            @RequestParam("keys") String keys) {

        // 将逗号分隔的字符串转换为List
        List<String> keyList = Arrays.asList(keys.split(","));

        assetService.deleteAssetAttributes(assetId, scope, keyList);
        return success(true);
    }

}