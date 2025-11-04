package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHousePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHouseRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHouseSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinghouse.GeocodingHouseDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodinghouse.GeocodingHouseService;

@Tag(name = "管理后台 - 门(楼)牌数据管理")
@RestController
@RequestMapping("/datacenter/geocoding-house")
@Validated
public class GeocodingHouseController {

    @Resource
    private GeocodingHouseService geocodingHouseService;

    @PostMapping("/create")
    @Operation(summary = "创建门(楼)牌数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-house:create')")
    public CommonResult<Long> createGeocodingHouse(@Valid @RequestBody GeocodingHouseSaveReqVO createReqVO) {
        return success(geocodingHouseService.createGeocodingHouse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门(楼)牌数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-house:update')")
    public CommonResult<Boolean> updateGeocodingHouse(@Valid @RequestBody GeocodingHouseSaveReqVO updateReqVO) {
        geocodingHouseService.updateGeocodingHouse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门(楼)牌数据管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-house:delete')")
    public CommonResult<Boolean> deleteGeocodingHouse(@RequestParam("id") Long id) {
        geocodingHouseService.deleteGeocodingHouse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门(楼)牌数据管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-house:query')")
    public CommonResult<GeocodingHouseRespVO> getGeocodingHouse(@RequestParam("id") Long id) {
        GeocodingHouseDO geocodingHouse = geocodingHouseService.getGeocodingHouse(id);
        return success(BeanUtils.toBean(geocodingHouse, GeocodingHouseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门(楼)牌数据管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-house:query')")
    public CommonResult<PageResult<GeocodingHouseRespVO>> getGeocodingHousePage(@Valid GeocodingHousePageReqVO pageReqVO) {
        PageResult<GeocodingHouseDO> pageResult = geocodingHouseService.getGeocodingHousePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingHouseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门(楼)牌数据管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-house:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingHouseExcel(@Valid GeocodingHousePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingHouseDO> list = geocodingHouseService.getGeocodingHousePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "门(楼)牌数据管理.xls", "数据", GeocodingHouseRespVO.class,
                        BeanUtils.toBean(list, GeocodingHouseRespVO.class));
    }

}