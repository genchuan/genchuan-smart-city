package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstat.GeocodingStatDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingstat.GeocodingStatService;

@Tag(name = "管理后台 - 基本地点数据统计报表")
@RestController
@RequestMapping("/datacenter/geocoding-stat")
@Validated
public class GeocodingStatController {

    @Resource
    private GeocodingStatService geocodingStatService;

    @GetMapping("/count-all")
    @Operation(summary = "统计所有表的数据总量")
    public CommonResult<Map<String, Object>> countAllTables() {
        Map<String, Object> result = geocodingStatService.countAllTables();
        return CommonResult.success(result);
    }

    @GetMapping("/summary")
    @Operation(summary = "获取数据统计摘要")
    public CommonResult<Map<String, Object>> getStatisticsSummary() {
        Map<String, Object> summary = geocodingStatService.getStatisticsSummary();
        return CommonResult.success(summary);
    }



    @PostMapping("/create")
    @Operation(summary = "创建基本地点数据统计报表")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-stat:create')")
    public CommonResult<Long> createGeocodingStat(@Valid @RequestBody GeocodingStatSaveReqVO createReqVO) {
        return success(geocodingStatService.createGeocodingStat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基本地点数据统计报表")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-stat:update')")
    public CommonResult<Boolean> updateGeocodingStat(@Valid @RequestBody GeocodingStatSaveReqVO updateReqVO) {
        geocodingStatService.updateGeocodingStat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基本地点数据统计报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-stat:delete')")
    public CommonResult<Boolean> deleteGeocodingStat(@RequestParam("id") Long id) {
        geocodingStatService.deleteGeocodingStat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基本地点数据统计报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-stat:query')")
    public CommonResult<GeocodingStatRespVO> getGeocodingStat(@RequestParam("id") Long id) {
        GeocodingStatDO geocodingStat = geocodingStatService.getGeocodingStat(id);
        return success(BeanUtils.toBean(geocodingStat, GeocodingStatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基本地点数据统计报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-stat:query')")
    public CommonResult<PageResult<GeocodingStatRespVO>> getGeocodingStatPage(@Valid GeocodingStatPageReqVO pageReqVO) {
        PageResult<GeocodingStatDO> pageResult = geocodingStatService.getGeocodingStatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingStatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基本地点数据统计报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-stat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingStatExcel(@Valid GeocodingStatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingStatDO> list = geocodingStatService.getGeocodingStatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基本地点数据统计报表.xls", "数据", GeocodingStatRespVO.class,
                        BeanUtils.toBean(list, GeocodingStatRespVO.class));
    }

}