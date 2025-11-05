package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion.vo.GeocodingRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion.vo.GeocodingRegionRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion.vo.GeocodingRegionSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingregion.GeocodingRegionDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingregion.GeocodingRegionService;

@Tag(name = "管理后台 - 区域数据管理")
@RestController
@RequestMapping("/datacenter/geocoding-region")
@Validated
public class GeocodingRegionController {

    @Resource
    private GeocodingRegionService geocodingRegionService;

    @PostMapping("/create")
    @Operation(summary = "创建区域数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-region:create')")
    public CommonResult<Long> createGeocodingRegion(@Valid @RequestBody GeocodingRegionSaveReqVO createReqVO) {
        return success(geocodingRegionService.createGeocodingRegion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新区域数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-region:update')")
    public CommonResult<Boolean> updateGeocodingRegion(@Valid @RequestBody GeocodingRegionSaveReqVO updateReqVO) {
        geocodingRegionService.updateGeocodingRegion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除区域数据管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-region:delete')")
    public CommonResult<Boolean> deleteGeocodingRegion(@RequestParam("id") Long id) {
        geocodingRegionService.deleteGeocodingRegion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得区域数据管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-region:query')")
    public CommonResult<GeocodingRegionRespVO> getGeocodingRegion(@RequestParam("id") Long id) {
        GeocodingRegionDO geocodingRegion = geocodingRegionService.getGeocodingRegion(id);
        return success(BeanUtils.toBean(geocodingRegion, GeocodingRegionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得区域数据管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-region:query')")
    public CommonResult<PageResult<GeocodingRegionRespVO>> getGeocodingRegionPage(@Valid GeocodingRegionPageReqVO pageReqVO) {
        PageResult<GeocodingRegionDO> pageResult = geocodingRegionService.getGeocodingRegionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingRegionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出区域数据管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-region:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingRegionExcel(@Valid GeocodingRegionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingRegionDO> list = geocodingRegionService.getGeocodingRegionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "区域数据管理.xls", "数据", GeocodingRegionRespVO.class,
                        BeanUtils.toBean(list, GeocodingRegionRespVO.class));
    }

}