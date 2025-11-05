package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZonePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZoneRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZoneSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingzone.GeocodingZoneDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingzone.GeocodingZoneService;

@Tag(name = "管理后台 - 地片与区片数据管理")
@RestController
@RequestMapping("/datacenter/geocoding-zone")
@Validated
public class GeocodingZoneController {

    @Resource
    private GeocodingZoneService geocodingZoneService;

    @PostMapping("/create")
    @Operation(summary = "创建地片与区片数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-zone:create')")
    public CommonResult<Long> createGeocodingZone(@Valid @RequestBody GeocodingZoneSaveReqVO createReqVO) {
        return success(geocodingZoneService.createGeocodingZone(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地片与区片数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-zone:update')")
    public CommonResult<Boolean> updateGeocodingZone(@Valid @RequestBody GeocodingZoneSaveReqVO updateReqVO) {
        geocodingZoneService.updateGeocodingZone(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地片与区片数据管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-zone:delete')")
    public CommonResult<Boolean> deleteGeocodingZone(@RequestParam("id") Long id) {
        geocodingZoneService.deleteGeocodingZone(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地片与区片数据管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-zone:query')")
    public CommonResult<GeocodingZoneRespVO> getGeocodingZone(@RequestParam("id") Long id) {
        GeocodingZoneDO geocodingZone = geocodingZoneService.getGeocodingZone(id);
        return success(BeanUtils.toBean(geocodingZone, GeocodingZoneRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地片与区片数据管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-zone:query')")
    public CommonResult<PageResult<GeocodingZoneRespVO>> getGeocodingZonePage(@Valid GeocodingZonePageReqVO pageReqVO) {
        PageResult<GeocodingZoneDO> pageResult = geocodingZoneService.getGeocodingZonePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingZoneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出地片与区片数据管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-zone:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingZoneExcel(@Valid GeocodingZonePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingZoneDO> list = geocodingZoneService.getGeocodingZonePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "地片与区片数据管理.xls", "数据", GeocodingZoneRespVO.class,
                        BeanUtils.toBean(list, GeocodingZoneRespVO.class));
    }

}