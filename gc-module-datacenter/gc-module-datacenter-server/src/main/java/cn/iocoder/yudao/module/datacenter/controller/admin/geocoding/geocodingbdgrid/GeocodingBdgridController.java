package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingbdgrid.GeocodingBdgridDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingbdgrid.GeocodingBdgridService;

@Tag(name = "管理后台 - 北斗网格位置码配置")
@RestController
@RequestMapping("/datacenter/geocoding-bdgrid")
@Validated
public class GeocodingBdgridController {

    @Resource
    private GeocodingBdgridService geocodingBdgridService;

    @PostMapping("/create")
    @Operation(summary = "创建北斗网格位置码配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-bdgrid:create')")
    public CommonResult<Long> createGeocodingBdgrid(@Valid @RequestBody GeocodingBdgridSaveReqVO createReqVO) {
        return success(geocodingBdgridService.createGeocodingBdgrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新北斗网格位置码配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-bdgrid:update')")
    public CommonResult<Boolean> updateGeocodingBdgrid(@Valid @RequestBody GeocodingBdgridSaveReqVO updateReqVO) {
        geocodingBdgridService.updateGeocodingBdgrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除北斗网格位置码配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-bdgrid:delete')")
    public CommonResult<Boolean> deleteGeocodingBdgrid(@RequestParam("id") Long id) {
        geocodingBdgridService.deleteGeocodingBdgrid(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得北斗网格位置码配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-bdgrid:query')")
    public CommonResult<GeocodingBdgridRespVO> getGeocodingBdgrid(@RequestParam("id") Long id) {
        GeocodingBdgridDO geocodingBdgrid = geocodingBdgridService.getGeocodingBdgrid(id);
        return success(BeanUtils.toBean(geocodingBdgrid, GeocodingBdgridRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得北斗网格位置码配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-bdgrid:query')")
    public CommonResult<PageResult<GeocodingBdgridRespVO>> getGeocodingBdgridPage(@Valid GeocodingBdgridPageReqVO pageReqVO) {
        PageResult<GeocodingBdgridDO> pageResult = geocodingBdgridService.getGeocodingBdgridPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingBdgridRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出北斗网格位置码配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-bdgrid:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingBdgridExcel(@Valid GeocodingBdgridPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingBdgridDO> list = geocodingBdgridService.getGeocodingBdgridPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "北斗网格位置码配置.xls", "数据", GeocodingBdgridRespVO.class,
                        BeanUtils.toBean(list, GeocodingBdgridRespVO.class));
    }

}