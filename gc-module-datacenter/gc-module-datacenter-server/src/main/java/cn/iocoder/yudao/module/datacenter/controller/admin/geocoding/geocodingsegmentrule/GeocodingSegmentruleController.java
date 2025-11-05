package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentruleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentruleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingsegmentrule.GeocodingSegmentruleDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingsegmentrule.GeocodingSegmentruleService;

@Tag(name = "管理后台 - 地理编码分段组合规则配置")
@RestController
@RequestMapping("/datacenter/geocoding-segmentrule")
@Validated
public class GeocodingSegmentruleController {

    @Resource
    private GeocodingSegmentruleService geocodingSegmentruleService;

    @PostMapping("/create")
    @Operation(summary = "创建地理编码分段组合规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-segmentrule:create')")
    public CommonResult<Long> createGeocodingSegmentrule(@Valid @RequestBody GeocodingSegmentruleSaveReqVO createReqVO) {
        return success(geocodingSegmentruleService.createGeocodingSegmentrule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地理编码分段组合规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-segmentrule:update')")
    public CommonResult<Boolean> updateGeocodingSegmentrule(@Valid @RequestBody GeocodingSegmentruleSaveReqVO updateReqVO) {
        geocodingSegmentruleService.updateGeocodingSegmentrule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地理编码分段组合规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-segmentrule:delete')")
    public CommonResult<Boolean> deleteGeocodingSegmentrule(@RequestParam("id") Long id) {
        geocodingSegmentruleService.deleteGeocodingSegmentrule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地理编码分段组合规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-segmentrule:query')")
    public CommonResult<GeocodingSegmentruleRespVO> getGeocodingSegmentrule(@RequestParam("id") Long id) {
        GeocodingSegmentruleDO geocodingSegmentrule = geocodingSegmentruleService.getGeocodingSegmentrule(id);
        return success(BeanUtils.toBean(geocodingSegmentrule, GeocodingSegmentruleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地理编码分段组合规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-segmentrule:query')")
    public CommonResult<PageResult<GeocodingSegmentruleRespVO>> getGeocodingSegmentrulePage(@Valid GeocodingSegmentrulePageReqVO pageReqVO) {
        PageResult<GeocodingSegmentruleDO> pageResult = geocodingSegmentruleService.getGeocodingSegmentrulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingSegmentruleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出地理编码分段组合规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-segmentrule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingSegmentruleExcel(@Valid GeocodingSegmentrulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingSegmentruleDO> list = geocodingSegmentruleService.getGeocodingSegmentrulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "地理编码分段组合规则配置.xls", "数据", GeocodingSegmentruleRespVO.class,
                        BeanUtils.toBean(list, GeocodingSegmentruleRespVO.class));
    }

}