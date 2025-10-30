package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppSceneRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppSceneSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetappscene.AssetAppSceneDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetappscene.AssetAppSceneService;

@Tag(name = "管理后台 - 资产关联应用场景")
@RestController
@RequestMapping("/datacenter/asset-app-scene")
@Validated
public class AssetAppSceneController {

    @Resource
    private AssetAppSceneService assetAppSceneService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联应用场景")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:create')")
    public CommonResult<Long> createAssetAppScene(@Valid @RequestBody AssetAppSceneSaveReqVO createReqVO) {
        return success(assetAppSceneService.createAssetAppScene(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联应用场景")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:update')")
    public CommonResult<Boolean> updateAssetAppScene(@Valid @RequestBody AssetAppSceneSaveReqVO updateReqVO) {
        assetAppSceneService.updateAssetAppScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联应用场景")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:delete')")
    public CommonResult<Boolean> deleteAssetAppScene(@RequestParam("id") Long id) {
        assetAppSceneService.deleteAssetAppScene(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联应用场景")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:query')")
    public CommonResult<AssetAppSceneRespVO> getAssetAppScene(@RequestParam("id") Long id) {
        AssetAppSceneDO assetAppScene = assetAppSceneService.getAssetAppScene(id);
        return success(BeanUtils.toBean(assetAppScene, AssetAppSceneRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产关联应用场景分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:query')")
    public CommonResult<PageResult<AssetAppSceneRespVO>> getAssetAppScenePage(@Valid AssetAppScenePageReqVO pageReqVO) {
        PageResult<AssetAppSceneDO> pageResult = assetAppSceneService.getAssetAppScenePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetAppSceneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联应用场景 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetAppSceneExcel(@Valid AssetAppScenePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetAppSceneDO> list = assetAppSceneService.getAssetAppScenePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产关联应用场景.xls", "数据", AssetAppSceneRespVO.class,
                        BeanUtils.toBean(list, AssetAppSceneRespVO.class));
    }

    /**
     * 批量删除资产关联应用场景
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除资产关联应用场景")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-app-scene:delete')")
    public CommonResult<Boolean> deleteAssetAppSceneBatch(@RequestBody List<Long> ids) {
        assetAppSceneService.deleteAssetAppSceneBatch(ids);
        return success(true);
    }

}