package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfilePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfileRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfileSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.assetprofile.AssetProfileDO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.AssetProfileService;
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
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "管理后台 - 资产配置信息")
@RestController
@RequestMapping("/datacenter/asset-profile")
@Validated
public class AssetProfileController {

    @Resource
    private AssetProfileService assetProfileService;

    @PostMapping("/create")
    @Operation(summary = "创建资产配置信息")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-profile:create')")
    public CommonResult<Long> createAssetProfile(@Valid @RequestBody AssetProfileSaveReqVO createReqVO) {
        return success(assetProfileService.createAssetProfile(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产配置信息")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-profile:update')")
    public CommonResult<Boolean> updateAssetProfile(@Valid @RequestBody AssetProfileSaveReqVO updateReqVO) {
        assetProfileService.updateAssetProfile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产配置信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-profile:delete')")
    public CommonResult<Boolean> deleteAssetProfile(@RequestParam("id") Long id) {
        assetProfileService.deleteAssetProfile(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产配置信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-profile:query')")
    public CommonResult<AssetProfileRespVO> getAssetProfile(@RequestParam("id") Long id) {
        AssetProfileDO assetProfile = assetProfileService.getAssetProfile(id);
        return success(BeanUtils.toBean(assetProfile, AssetProfileRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产配置信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-profile:query')")
    public CommonResult<PageResult<AssetProfileRespVO>> getAssetProfilePage(@Valid AssetProfilePageReqVO pageReqVO) {
        PageResult<AssetProfileDO> pageResult = assetProfileService.getAssetProfilePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetProfileRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产配置信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-profile:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetProfileExcel(@Valid AssetProfilePageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetProfileDO> list = assetProfileService.getAssetProfilePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产配置信息.xls", "数据", AssetProfileRespVO.class,
                BeanUtils.toBean(list, AssetProfileRespVO.class));
    }

    @PostMapping("/sync")
    @Operation(summary = "同步ThingsBoard资产配置")
    public CommonResult<Map<String, Object>> syncAssetProfilesFromThingsBoard() {
        Map<String, Object> result = assetProfileService.syncAssetProfilesFromThingsBoard();
        return success(result);
    }

    @GetMapping("/thingsboard-profiles")
    @Operation(summary = "获取ThingsBoard资产配置分页列表")
    public CommonResult<PageData<AssetProfile>> getAssetProfilesFromThingsBoard(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "sortProperty", required = false) String sortProperty,
            @RequestParam(value = "sortOrder", required = false) String sortOrder) {

        PageData<AssetProfile> assetProfiles = assetProfileService.getAssetProfilesFromThingsBoard(pageSize, page, sortProperty, sortOrder);
        return success(assetProfiles);
    }

    @GetMapping("/list-all")
    @Operation(summary = "获得所有资产配置信息列表")
    public CommonResult<List<AssetProfileRespVO>> getAssetProfileList() {
        List<AssetProfileDO> list = assetProfileService.getAssetProfileList();
        return success(BeanUtils.toBean(list, AssetProfileRespVO.class));
    }

}