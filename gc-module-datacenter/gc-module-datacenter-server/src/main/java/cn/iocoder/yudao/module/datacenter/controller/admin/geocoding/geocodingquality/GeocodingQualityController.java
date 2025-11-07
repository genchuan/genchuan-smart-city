package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualityRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualitySaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingquality.GeocodingQualityDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingquality.GeocodingQualityService;

@Tag(name = "管理后台 - 地理编码数据质量统计报表")
@RestController
@RequestMapping("/datacenter/geocoding-quality")
@Validated
public class GeocodingQualityController {

    @Resource
    private GeocodingQualityService geocodingQualityService;

    @PostMapping("/create")
    @Operation(summary = "创建地理编码数据质量统计报表")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-quality:create')")
    public CommonResult<Long> createGeocodingQuality(@Valid @RequestBody GeocodingQualitySaveReqVO createReqVO) {
        return success(geocodingQualityService.createGeocodingQuality(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地理编码数据质量统计报表")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-quality:update')")
    public CommonResult<Boolean> updateGeocodingQuality(@Valid @RequestBody GeocodingQualitySaveReqVO updateReqVO) {
        geocodingQualityService.updateGeocodingQuality(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地理编码数据质量统计报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-quality:delete')")
    public CommonResult<Boolean> deleteGeocodingQuality(@RequestParam("id") Long id) {
        geocodingQualityService.deleteGeocodingQuality(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地理编码数据质量统计报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-quality:query')")
    public CommonResult<GeocodingQualityRespVO> getGeocodingQuality(@RequestParam("id") Long id) {
        GeocodingQualityDO geocodingQuality = geocodingQualityService.getGeocodingQuality(id);
        return success(BeanUtils.toBean(geocodingQuality, GeocodingQualityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地理编码数据质量统计报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-quality:query')")
    public CommonResult<PageResult<GeocodingQualityRespVO>> getGeocodingQualityPage(@Valid GeocodingQualityPageReqVO pageReqVO) {
        PageResult<GeocodingQualityDO> pageResult = geocodingQualityService.getGeocodingQualityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingQualityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出地理编码数据质量统计报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-quality:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingQualityExcel(@Valid GeocodingQualityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingQualityDO> list = geocodingQualityService.getGeocodingQualityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "地理编码数据质量统计报表.xls", "数据", GeocodingQualityRespVO.class,
                        BeanUtils.toBean(list, GeocodingQualityRespVO.class));
    }

}