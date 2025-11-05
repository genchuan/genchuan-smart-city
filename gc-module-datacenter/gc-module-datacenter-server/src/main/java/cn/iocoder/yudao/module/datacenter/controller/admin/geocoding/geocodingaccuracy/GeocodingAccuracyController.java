package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo.GeocodingAccuracyPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo.GeocodingAccuracyRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo.GeocodingAccuracySaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingaccuracy.GeocodingAccuracyDO;
import cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingaccuracy.GeocodingAccuracyService;

@Tag(name = "管理后台 - 位置精度标准配置")
@RestController
@RequestMapping("/datacenter/geocoding-accuracy")
@Validated
public class GeocodingAccuracyController {

    @Resource
    private GeocodingAccuracyService geocodingAccuracyService;

    @PostMapping("/create")
    @Operation(summary = "创建位置精度标准配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-accuracy:create')")
    public CommonResult<Long> createGeocodingAccuracy(@Valid @RequestBody GeocodingAccuracySaveReqVO createReqVO) {
        return success(geocodingAccuracyService.createGeocodingAccuracy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新位置精度标准配置")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-accuracy:update')")
    public CommonResult<Boolean> updateGeocodingAccuracy(@Valid @RequestBody GeocodingAccuracySaveReqVO updateReqVO) {
        geocodingAccuracyService.updateGeocodingAccuracy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除位置精度标准配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-accuracy:delete')")
    public CommonResult<Boolean> deleteGeocodingAccuracy(@RequestParam("id") Long id) {
        geocodingAccuracyService.deleteGeocodingAccuracy(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得位置精度标准配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-accuracy:query')")
    public CommonResult<GeocodingAccuracyRespVO> getGeocodingAccuracy(@RequestParam("id") Long id) {
        GeocodingAccuracyDO geocodingAccuracy = geocodingAccuracyService.getGeocodingAccuracy(id);
        return success(BeanUtils.toBean(geocodingAccuracy, GeocodingAccuracyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得位置精度标准配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-accuracy:query')")
    public CommonResult<PageResult<GeocodingAccuracyRespVO>> getGeocodingAccuracyPage(@Valid GeocodingAccuracyPageReqVO pageReqVO) {
        PageResult<GeocodingAccuracyDO> pageResult = geocodingAccuracyService.getGeocodingAccuracyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeocodingAccuracyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出位置精度标准配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:geocoding-accuracy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeocodingAccuracyExcel(@Valid GeocodingAccuracyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeocodingAccuracyDO> list = geocodingAccuracyService.getGeocodingAccuracyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "位置精度标准配置.xls", "数据", GeocodingAccuracyRespVO.class,
                        BeanUtils.toBean(list, GeocodingAccuracyRespVO.class));
    }

}