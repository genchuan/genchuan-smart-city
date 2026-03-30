package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.assetextend.AssetExtendDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.assetextend.AssetExtendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 资产扩展")
@RestController
@RequestMapping("/park/asset-extend")
@Validated
public class AssetExtendController {

    @Resource
    private AssetExtendService assetExtendService;

    @PostMapping("/create")
    @Operation(summary = "创建资产扩展")
    @PreAuthorize("@ss.hasPermission('park:asset-extend:create')")
    public CommonResult<Long> createAssetExtend(@Valid @RequestBody AssetExtendSaveReqVO createReqVO) {
        return success(assetExtendService.createAssetExtend(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产扩展")
    @PreAuthorize("@ss.hasPermission('park:asset-extend:update')")
    public CommonResult<Boolean> updateAssetExtend(@Valid @RequestBody AssetExtendSaveReqVO updateReqVO) {
        assetExtendService.updateAssetExtend(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:asset-extend:delete')")
    public CommonResult<Boolean> deleteAssetExtend(@RequestParam("id") Long id) {
        assetExtendService.deleteAssetExtend(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产扩展")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:asset-extend:query')")
    public CommonResult<AssetExtendRespVO> getAssetExtend(@RequestParam("id") Long id) {
        AssetExtendDO assetExtend = assetExtendService.getAssetExtend(id);
        return success(BeanUtils.toBean(assetExtend, AssetExtendRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产扩展分页")
    @PreAuthorize("@ss.hasPermission('park:asset-extend:query')")
    public CommonResult<PageResult<AssetExtendRespVO>> getAssetExtendPage(@Valid AssetExtendPageReqVO pageReqVO) {
        PageResult<AssetExtendDO> pageResult = assetExtendService.getAssetExtendPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetExtendRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产扩展 Excel")
    @PreAuthorize("@ss.hasPermission('park:asset-extend:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetExtendExcel(@Valid AssetExtendPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetExtendDO> list = assetExtendService.getAssetExtendPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产扩展.xls", "数据", AssetExtendRespVO.class,
                        BeanUtils.toBean(list, AssetExtendRespVO.class));
    }

}
