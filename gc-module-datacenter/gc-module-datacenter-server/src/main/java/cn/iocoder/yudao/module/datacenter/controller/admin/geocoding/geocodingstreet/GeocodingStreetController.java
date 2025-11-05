package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo.GeocodingStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo.GeocodingStreetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo.GeocodingStreetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstreet.GeocodingStreetDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingstreet.GeocodingStreetService;

@Tag(name = "管理后台 - 街巷数据管理")
@RestController
@RequestMapping("/datacenter/geocoding-street")
@Validated
public class GeocodingStreetController {

    @Resource
    private GeocodingStreetService geocodingStreetService;

    @PostMapping("/create")
    @Operation(summary = "创建街巷数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-street:create')")
    public CommonResult<Long> createGeocodingStreet(@Valid @RequestBody GeocodingStreetSaveReqVO createReqVO) {
        return success(geocodingStreetService.createGeocodingStreet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新街巷数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-street:update')")
    public CommonResult<Boolean> updateGeocodingStreet(@Valid @RequestBody GeocodingStreetSaveReqVO updateReqVO) {
        geocodingStreetService.updateGeocodingStreet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除街巷数据管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-street:delete')")
    public CommonResult<Boolean> deleteGeocodingStreet(@RequestParam("id") Long id) {
        geocodingStreetService.deleteGeocodingStreet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得街巷数据管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-street:query')")
    public CommonResult<GeocodingStreetRespVO> getGeocodingStreet(@RequestParam("id") Long id) {
        GeocodingStreetDO geocodingStreet = geocodingStreetService.getGeocodingStreet(id);
        return success(BeanUtils.toBean(geocodingStreet, GeocodingStreetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得街巷数据管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-street:query')")
    public CommonResult<PageResult<GeocodingStreetRespVO>> getGeocodingStreetPage(@Valid GeocodingStreetPageReqVO pageReqVO) {
        PageResult<GeocodingStreetDO> pageResult = geocodingStreetService.getGeocodingStreetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingStreetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出街巷数据管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-street:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingStreetExcel(@Valid GeocodingStreetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingStreetDO> list = geocodingStreetService.getGeocodingStreetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "街巷数据管理.xls", "数据", GeocodingStreetRespVO.class,
                        BeanUtils.toBean(list, GeocodingStreetRespVO.class));
    }

}