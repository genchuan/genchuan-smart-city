package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo.GeocodingUpdatecyclePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo.GeocodingUpdatecycleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo.GeocodingUpdatecycleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingupdatecycle.GeocodingUpdatecycleDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingupdatecycle.GeocodingUpdatecycleService;

@Tag(name = "管理后台 - 数据更新周期配置")
@RestController
@RequestMapping("/datacenter/geocoding-updatecycle")
@Validated
public class GeocodingUpdatecycleController {

    @Resource
    private GeocodingUpdatecycleService geocodingUpdatecycleService;

    @PostMapping("/create")
    @Operation(summary = "创建数据更新周期配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-updatecycle:create')")
    public CommonResult<Long> createGeocodingUpdatecycle(@Valid @RequestBody GeocodingUpdatecycleSaveReqVO createReqVO) {
        return success(geocodingUpdatecycleService.createGeocodingUpdatecycle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新数据更新周期配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-updatecycle:update')")
    public CommonResult<Boolean> updateGeocodingUpdatecycle(@Valid @RequestBody GeocodingUpdatecycleSaveReqVO updateReqVO) {
        geocodingUpdatecycleService.updateGeocodingUpdatecycle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除数据更新周期配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-updatecycle:delete')")
    public CommonResult<Boolean> deleteGeocodingUpdatecycle(@RequestParam("id") Long id) {
        geocodingUpdatecycleService.deleteGeocodingUpdatecycle(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得数据更新周期配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-updatecycle:query')")
    public CommonResult<GeocodingUpdatecycleRespVO> getGeocodingUpdatecycle(@RequestParam("id") Long id) {
        GeocodingUpdatecycleDO geocodingUpdatecycle = geocodingUpdatecycleService.getGeocodingUpdatecycle(id);
        return success(BeanUtils.toBean(geocodingUpdatecycle, GeocodingUpdatecycleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得数据更新周期配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-updatecycle:query')")
    public CommonResult<PageResult<GeocodingUpdatecycleRespVO>> getGeocodingUpdatecyclePage(@Valid GeocodingUpdatecyclePageReqVO pageReqVO) {
        PageResult<GeocodingUpdatecycleDO> pageResult = geocodingUpdatecycleService.getGeocodingUpdatecyclePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingUpdatecycleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出数据更新周期配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-updatecycle:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingUpdatecycleExcel(@Valid GeocodingUpdatecyclePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingUpdatecycleDO> list = geocodingUpdatecycleService.getGeocodingUpdatecyclePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "数据更新周期配置.xls", "数据", GeocodingUpdatecycleRespVO.class,
                        BeanUtils.toBean(list, GeocodingUpdatecycleRespVO.class));
    }

}