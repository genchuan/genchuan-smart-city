package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo;


import cn.idev.excel.ExcelWriter;
import cn.idev.excel.write.metadata.WriteSheet;
import cn.idev.excel.write.metadata.WriteWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.net.URLEncoder;
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

import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleInfoDO;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleResultDO;
import cn.iocoder.yudao.module.waterdetection.service.watersampleinfo.WaterSampleInfoService;

@Tag(name = "管理后台 - 水质检测信息")
@RestController
@RequestMapping("/waterdetection/water-sample-info")
@Validated
public class WaterSampleInfoController {

    @Resource
    private WaterSampleInfoService waterSampleInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建水质检测信息")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:create')")
    public CommonResult<Long> createWaterSampleInfo(@Valid @RequestBody WaterSampleInfoSaveReqVO createReqVO) {
        return success(waterSampleInfoService.createWaterSampleInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新水质检测信息")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:update')")
    public CommonResult<Boolean> updateWaterSampleInfo(@Valid @RequestBody WaterSampleInfoSaveReqVO updateReqVO) {
        waterSampleInfoService.updateWaterSampleInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除水质检测信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:delete')")
    public CommonResult<Boolean> deleteWaterSampleInfo(@RequestParam("id") Long id) {
        waterSampleInfoService.deleteWaterSampleInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得水质检测信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:query')")
    public CommonResult<WaterSampleInfoRespVO> getWaterSampleInfo(@RequestParam("id") Long id) {
        WaterSampleInfoDO waterSampleInfo = waterSampleInfoService.getWaterSampleInfo(id);
        return success(BeanUtils.toBean(waterSampleInfo, WaterSampleInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得水质检测信息分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:query')")
    public CommonResult<PageResult<WaterSampleInfoRespVO>> getWaterSampleInfoPage(@Valid WaterSampleInfoPageReqVO pageReqVO) {
        PageResult<WaterSampleInfoDO> pageResult = waterSampleInfoService.getWaterSampleInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterSampleInfoRespVO.class));
    }

//    @GetMapping("/export-excel")
//    @Operation(summary = "导出水质检测信息 Excel")
//    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportWaterSampleInfoExcel(@Valid WaterSampleInfoPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<WaterSampleInfoDO> list = waterSampleInfoService.getWaterSampleInfoPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "水样信息表.xls", "数据", WaterSampleInfoRespVO.class,
//                        BeanUtils.toBean(list, WaterSampleInfoRespVO.class));
//    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出水质检测信息 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterSampleInfoExcel(@Valid WaterSampleInfoPageReqVO pageReqVO,
                                           HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);

        // 获取主表数据
        List<WaterSampleInfoDO> mainList = waterSampleInfoService.getWaterSampleInfoPage(pageReqVO).getList();

        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("水质检测信息表_" + System.currentTimeMillis() + ".xlsx", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 创建WriteWorkbook
        WriteWorkbook writeWorkbook = new WriteWorkbook();
        writeWorkbook.setOutputStream(response.getOutputStream());
        writeWorkbook.setAutoCloseStream(true);

        // 创建ExcelWriter
        ExcelWriter excelWriter = new ExcelWriter(writeWorkbook);

        // 创建主表Sheet
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("水质检测信息");
        writeSheet.setClazz(WaterSampleExportVO.class);

        // 准备数据
        List<WaterSampleExportVO> exportData = new ArrayList<>();

        for (WaterSampleInfoDO mainData : mainList) {
            // 获取子表数据（所有指标）
            PageParam pageParam = new PageParam();
            pageParam.setPageSize(PageParam.PAGE_SIZE_NONE);
            List<WaterSampleResultDO> subList = waterSampleInfoService.getWaterSampleResultPage(
                    pageParam, mainData.getId()).getList();

            if (subList.isEmpty()) {
                // 如果没有指标数据，至少导出基本信息
                WaterSampleExportVO vo = BeanUtils.toBean(mainData, WaterSampleExportVO.class);
                exportData.add(vo);
            } else {
                // 为每个指标创建一行数据
                for (WaterSampleResultDO result : subList) {
                    WaterSampleExportVO vo = BeanUtils.toBean(mainData, WaterSampleExportVO.class);
                    // 设置指标数据
//                    vo.setWaterSampleId(result.getWaterSampleId());
                    vo.setIndexName(result.getIndexName());
                    vo.setActualValue(result.getActualValue());
                    exportData.add(vo);
                }
            }
        }

        // 写入数据
        excelWriter.write(exportData, writeSheet);
        excelWriter.finish();
    }


    // ==================== 子表（出厂水检测结果） ====================

    @GetMapping("/water-sample-result/page")
    @Operation(summary = "获得出厂水检测结果分页")
    @Parameter(name = "waterSampleId", description = "样品编号")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:query')")
    public CommonResult<PageResult<WaterSampleResultDO>> getWaterSampleResultPage(PageParam pageReqVO,
                                                                                        @RequestParam("waterSampleId") Long waterSampleId) {
        return success(waterSampleInfoService.getWaterSampleResultPage(pageReqVO, waterSampleId));
    }

    @PostMapping("/water-sample-result/create")
    @Operation(summary = "创建出厂水检测结果")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:create')")
    public CommonResult<Long> createWaterSampleResult(@Valid @RequestBody WaterSampleResultDO waterSampleResult) {
        return success(waterSampleInfoService.createWaterSampleResult(waterSampleResult));
    }

    @PutMapping("/water-sample-result/update")
    @Operation(summary = "更新出厂水检测结果")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:update')")
    public CommonResult<Boolean> updateWaterSampleResult(@Valid @RequestBody WaterSampleResultDO waterSampleResult) {
        waterSampleInfoService.updateWaterSampleResult(waterSampleResult);
        return success(true);
    }

    @DeleteMapping("/water-sample-result/delete")
    @Parameter(name = "id", description = "编号", required = true)
    @Operation(summary = "删除出厂水检测结果")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:delete')")
    public CommonResult<Boolean> deleteWaterSampleResult(@RequestParam("id") Long id) {
        waterSampleInfoService.deleteWaterSampleResult(id);
        return success(true);
    }

	@GetMapping("/water-sample-result/get")
	@Operation(summary = "获得出厂水检测结果")
	@Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-info:query')")
	public CommonResult<WaterSampleResultDO> getWaterSampleResult(@RequestParam("id") Long id) {
	    return success(waterSampleInfoService.getWaterSampleResult(id));
	}

}