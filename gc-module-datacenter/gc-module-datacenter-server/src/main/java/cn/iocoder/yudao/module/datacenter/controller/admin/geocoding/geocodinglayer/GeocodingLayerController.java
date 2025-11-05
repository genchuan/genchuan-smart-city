package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo.GeocodingLayerPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo.GeocodingLayerRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo.GeocodingLayerSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinglayer.GeocodingLayerDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodinglayer.GeocodingLayerService;

@Tag(name = "管理后台 - 图层代码配置")
@RestController
@RequestMapping("/datacenter/geocoding-layer")
@Validated
public class GeocodingLayerController {

    @Resource
    private GeocodingLayerService geocodingLayerService;

    @PostMapping("/create")
    @Operation(summary = "创建图层代码配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-layer:create')")
    public CommonResult<Long> createGeocodingLayer(@Valid @RequestBody GeocodingLayerSaveReqVO createReqVO) {
        return success(geocodingLayerService.createGeocodingLayer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新图层代码配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-layer:update')")
    public CommonResult<Boolean> updateGeocodingLayer(@Valid @RequestBody GeocodingLayerSaveReqVO updateReqVO) {
        geocodingLayerService.updateGeocodingLayer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除图层代码配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-layer:delete')")
    public CommonResult<Boolean> deleteGeocodingLayer(@RequestParam("id") Long id) {
        geocodingLayerService.deleteGeocodingLayer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得图层代码配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-layer:query')")
    public CommonResult<GeocodingLayerRespVO> getGeocodingLayer(@RequestParam("id") Long id) {
        GeocodingLayerDO geocodingLayer = geocodingLayerService.getGeocodingLayer(id);
        return success(BeanUtils.toBean(geocodingLayer, GeocodingLayerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得图层代码配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-layer:query')")
    public CommonResult<PageResult<GeocodingLayerRespVO>> getGeocodingLayerPage(@Valid GeocodingLayerPageReqVO pageReqVO) {
        PageResult<GeocodingLayerDO> pageResult = geocodingLayerService.getGeocodingLayerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingLayerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出图层代码配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-layer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingLayerExcel(@Valid GeocodingLayerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingLayerDO> list = geocodingLayerService.getGeocodingLayerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "图层代码配置.xls", "数据", GeocodingLayerRespVO.class,
                        BeanUtils.toBean(list, GeocodingLayerRespVO.class));
    }

}