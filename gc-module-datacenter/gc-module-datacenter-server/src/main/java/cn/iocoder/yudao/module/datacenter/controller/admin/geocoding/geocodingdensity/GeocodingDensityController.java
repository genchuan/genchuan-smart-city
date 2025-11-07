package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensityRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensitySaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingdensity.GeocodingDensityDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingdensity.GeocodingDensityService;

@Tag(name = "管理后台 - 数据采集密度配置")
@RestController
@RequestMapping("/datacenter/geocoding-density")
@Validated
public class GeocodingDensityController {

    @Resource
    private GeocodingDensityService geocodingDensityService;

    @PostMapping("/create")
    @Operation(summary = "创建数据采集密度配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-density:create')")
    public CommonResult<Long> createGeocodingDensity(@Valid @RequestBody GeocodingDensitySaveReqVO createReqVO) {
        return success(geocodingDensityService.createGeocodingDensity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新数据采集密度配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-density:update')")
    public CommonResult<Boolean> updateGeocodingDensity(@Valid @RequestBody GeocodingDensitySaveReqVO updateReqVO) {
        geocodingDensityService.updateGeocodingDensity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除数据采集密度配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-density:delete')")
    public CommonResult<Boolean> deleteGeocodingDensity(@RequestParam("id") Long id) {
        geocodingDensityService.deleteGeocodingDensity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得数据采集密度配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-density:query')")
    public CommonResult<GeocodingDensityRespVO> getGeocodingDensity(@RequestParam("id") Long id) {
        GeocodingDensityDO geocodingDensity = geocodingDensityService.getGeocodingDensity(id);
        return success(BeanUtils.toBean(geocodingDensity, GeocodingDensityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得数据采集密度配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-density:query')")
    public CommonResult<PageResult<GeocodingDensityRespVO>> getGeocodingDensityPage(@Valid GeocodingDensityPageReqVO pageReqVO) {
        PageResult<GeocodingDensityDO> pageResult = geocodingDensityService.getGeocodingDensityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingDensityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出数据采集密度配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-density:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingDensityExcel(@Valid GeocodingDensityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingDensityDO> list = geocodingDensityService.getGeocodingDensityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "数据采集密度配置.xls", "数据", GeocodingDensityRespVO.class,
                        BeanUtils.toBean(list, GeocodingDensityRespVO.class));
    }

}