package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all;

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

//新import
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import cn.iocoder.yudao.module.appearance.service.outdoorad.OutdoorAdService;

@Tag(name = "管理后台 - 户外广告 - 全部")
@RestController
@RequestMapping("/appearance/outdoor-ad-spvs/all")
@Validated
public class OutdoorAdController {

    @Resource
    private OutdoorAdService outdoorAdService;

    @PostMapping("/create")
    @Operation(summary = "创建户外广告")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:create')")
    public CommonResult<Long> createOutdoorAd(@Valid @RequestBody OutdoorAdSaveReqVO createReqVO) {
        return success(outdoorAdService.createOutdoorAd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新户外广告")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:update')")
    public CommonResult<Boolean> updateOutdoorAd(@Valid @RequestBody OutdoorAdSaveReqVO updateReqVO) {
        outdoorAdService.updateOutdoorAd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除户外广告")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:delete')")
    public CommonResult<Boolean> deleteOutdoorAd(@RequestParam("id") Long id) {
        outdoorAdService.deleteOutdoorAd(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得户外广告")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<OutdoorAdRespVO> getOutdoorAd( @RequestParam("id") Long id) {
        OutdoorAdDO outdoorAd = outdoorAdService.getOutdoorAd(id);
        return success(BeanUtils.toBean(outdoorAd, OutdoorAdRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得户外广告分页")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<PageResult<OutdoorAdRespVO>> getOutdoorAdPage( @Valid OutdoorAdPageReqVO pageReqVO) {
        PageResult<OutdoorAdDO> pageResult = outdoorAdService.getOutdoorAdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OutdoorAdRespVO.class));
    }

    @GetMapping("/page-with-relations")
    @Operation(summary = "获得户外广告关联查询分页")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<PageResult<OutdoorAdRespVO>> getOutdoorAdPageWithRelations( @Valid OutdoorAdPageReqVO pageReqVO) {
        PageResult<OutdoorAdDO> pageResult = outdoorAdService.getOutdoorAdPageWithRelations(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OutdoorAdRespVO.class));
    }

    @PostMapping("/import-excel")
    @Operation(summary = "导入户外广告 Excel")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Map<String, Object>> importOutdoorAd(@RequestParam("file") MultipartFile file) throws IOException {
        List<OutdoorAdSaveReqVO> list = ExcelUtils.read(file, OutdoorAdSaveReqVO.class);
        Map<String, Object> result = outdoorAdService.importOutdoorAd(list);
        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出户外广告 Excel")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOutdoorAdExcel(@Valid OutdoorAdPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<OutdoorAdDO> list = outdoorAdService.getOutdoorAdPage(pageReqVO).getList();
        List<OutdoorAdDO> list = outdoorAdService.getOutdoorAdPageWithRelations(pageReqVO).getList();
        // 以下新代码生成包含区域和日期的文件名
        String areaCode = pageReqVO.getAreaCode();
        String areaPart = areaCode != null ? "_" + areaCode : "";
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String fileName = "户外广告" + areaPart + "_" + datePart + ".xls";
        //以上新代码生成包含区域和日期的文件名
        // 导出 Excel
        ExcelUtils.write(response, fileName, "数据", OutdoorAdRespVO.class,//替换"户外广告.xls"为fileName
                BeanUtils.toBean(list, OutdoorAdRespVO.class));
    }

    @GetMapping("/core-indicators")
    @Operation(summary = "获取核心指标")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<Map<String, Object>> getCoreIndicators() {
        Map<String, Object> indicators = outdoorAdService.getCoreIndicators();
        return success(indicators);
    }

    @GetMapping("/area-warning-trend")
    @Operation(summary = "获取区域预警趋势")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<List<Map<String, Object>>> getAreaWarningTrend() {
        List<Map<String, Object>> trend = outdoorAdService.getAreaWarningTrend();
        return success(trend);
    }

    @GetMapping("/recent-warning-trend")
    @Operation(summary = "获取近30日预警趋势")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<List<Map<String, Object>>> getRecentWarningTrend() {
        List<Map<String, Object>> trend = outdoorAdService.getRecentWarningTrend();
        return success(trend);
    }

    @GetMapping("/ad-status-distribution")
    @Operation(summary = "获取广告状态占比")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<List<Map<String, Object>>> getAdStatusDistribution() {
        List<Map<String, Object>> distribution = outdoorAdService.getAdStatusDistribution();
        return success(distribution);
    }

    @GetMapping("/warning-type-distribution")
    @Operation(summary = "获取预警类型占比")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<List<Map<String, Object>>> getWarningTypeDistribution() {
        List<Map<String, Object>> distribution = outdoorAdService.getWarningTypeDistribution();
        return success(distribution);
    }

    @GetMapping("/review-result-distribution")
    @Operation(summary = "获取复核结果占比")
    @PreAuthorize("@ss.hasPermission('appearance:outdoor-ad-spvs:all:query')")
    public CommonResult<List<Map<String, Object>>> getReviewResultDistribution() {
        List<Map<String, Object>> distribution = outdoorAdService.getReviewResultDistribution();
        return success(distribution);
    }

}