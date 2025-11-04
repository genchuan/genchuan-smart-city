package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinateRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinateSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingcoordinate.GeocodingCoordinateDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingcoordinate.GeocodingCoordinateService;

@Tag(name = "管理后台 - 坐标系配置")
@RestController
@RequestMapping("/datacenter/geocoding-coordinate")
@Validated
public class GeocodingCoordinateController {

    @Resource
    private GeocodingCoordinateService geocodingCoordinateService;

    @PostMapping("/create")
    @Operation(summary = "创建坐标系配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-coordinate:create')")
    public CommonResult<Long> createGeocodingCoordinate(@Valid @RequestBody GeocodingCoordinateSaveReqVO createReqVO) {
        return success(geocodingCoordinateService.createGeocodingCoordinate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新坐标系配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-coordinate:update')")
    public CommonResult<Boolean> updateGeocodingCoordinate(@Valid @RequestBody GeocodingCoordinateSaveReqVO updateReqVO) {
        geocodingCoordinateService.updateGeocodingCoordinate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除坐标系配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-coordinate:delete')")
    public CommonResult<Boolean> deleteGeocodingCoordinate(@RequestParam("id") Long id) {
        geocodingCoordinateService.deleteGeocodingCoordinate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得坐标系配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-coordinate:query')")
    public CommonResult<GeocodingCoordinateRespVO> getGeocodingCoordinate(@RequestParam("id") Long id) {
        GeocodingCoordinateDO geocodingCoordinate = geocodingCoordinateService.getGeocodingCoordinate(id);
        return success(BeanUtils.toBean(geocodingCoordinate, GeocodingCoordinateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得坐标系配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-coordinate:query')")
    public CommonResult<PageResult<GeocodingCoordinateRespVO>> getGeocodingCoordinatePage(@Valid GeocodingCoordinatePageReqVO pageReqVO) {
        PageResult<GeocodingCoordinateDO> pageResult = geocodingCoordinateService.getGeocodingCoordinatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingCoordinateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出坐标系配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-coordinate:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingCoordinateExcel(@Valid GeocodingCoordinatePageReqVO pageReqVO,
                                               HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingCoordinateDO> list = geocodingCoordinateService.getGeocodingCoordinatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "坐标系配置.xls", "数据", GeocodingCoordinateRespVO.class,
                BeanUtils.toBean(list, GeocodingCoordinateRespVO.class));
    }

}