package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingpoi.GeocodingPoiDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingpoi.GeocodingPoiService;

@Tag(name = "管理后台 - 兴趣点数据管理")
@RestController
@RequestMapping("/datacenter/geocoding-poi")
@Validated
public class GeocodingPoiController {

    @Resource
    private GeocodingPoiService geocodingPoiService;

    @PostMapping("/create")
    @Operation(summary = "创建兴趣点数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-poi:create')")
    public CommonResult<Long> createGeocodingPoi(@Valid @RequestBody GeocodingPoiSaveReqVO createReqVO) {
        return success(geocodingPoiService.createGeocodingPoi(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新兴趣点数据管理")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-poi:update')")
    public CommonResult<Boolean> updateGeocodingPoi(@Valid @RequestBody GeocodingPoiSaveReqVO updateReqVO) {
        geocodingPoiService.updateGeocodingPoi(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除兴趣点数据管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-poi:delete')")
    public CommonResult<Boolean> deleteGeocodingPoi(@RequestParam("id") Long id) {
        geocodingPoiService.deleteGeocodingPoi(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得兴趣点数据管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-poi:query')")
    public CommonResult<GeocodingPoiRespVO> getGeocodingPoi(@RequestParam("id") Long id) {
        GeocodingPoiDO geocodingPoi = geocodingPoiService.getGeocodingPoi(id);
        return success(BeanUtils.toBean(geocodingPoi, GeocodingPoiRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得兴趣点数据管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-poi:query')")
    public CommonResult<PageResult<GeocodingPoiRespVO>> getGeocodingPoiPage(@Valid GeocodingPoiPageReqVO pageReqVO) {
        PageResult<GeocodingPoiDO> pageResult = geocodingPoiService.getGeocodingPoiPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingPoiRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出兴趣点数据管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-poi:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingPoiExcel(@Valid GeocodingPoiPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingPoiDO> list = geocodingPoiService.getGeocodingPoiPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "兴趣点数据管理.xls", "数据", GeocodingPoiRespVO.class,
                        BeanUtils.toBean(list, GeocodingPoiRespVO.class));
    }

}