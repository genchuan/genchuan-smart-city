package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameters;
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
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampletestsummary.WaterSampleTestSummaryDO;
import cn.iocoder.yudao.module.waterdetection.service.watersampletestsummary.WaterSampleTestSummaryService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 外检统计水质检测结果汇总")
@RestController
@RequestMapping("/waterdetection/water-sample-test-summary")
@Validated
public class WaterSampleTestSummaryController {

    @Resource
    private WaterSampleTestSummaryService waterSampleTestSummaryService;

    @Resource
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    @Operation(summary = "创建外检统计水质检测结果汇总")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:create')")
    public CommonResult<Long> createWaterSampleTestSummary(@Valid @RequestBody WaterSampleTestSummarySaveReqVO createReqVO) {
        return success(waterSampleTestSummaryService.createWaterSampleTestSummary(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新外检统计水质检测结果汇总")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:update')")
    public CommonResult<Boolean> updateWaterSampleTestSummary(@Valid @RequestBody WaterSampleTestSummarySaveReqVO updateReqVO) {
        waterSampleTestSummaryService.updateWaterSampleTestSummary(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除外检统计水质检测结果汇总")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:delete')")
    public CommonResult<Boolean> deleteWaterSampleTestSummary(@RequestParam("id") Long id) {
        waterSampleTestSummaryService.deleteWaterSampleTestSummary(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得外检统计水质检测结果汇总")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:query')")
    public CommonResult<WaterSampleTestSummaryRespVO> getWaterSampleTestSummary(@RequestParam("id") Long id) {
        WaterSampleTestSummaryDO waterSampleTestSummary = waterSampleTestSummaryService.getWaterSampleTestSummary(id);
        return success(BeanUtils.toBean(waterSampleTestSummary, WaterSampleTestSummaryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得外检统计水质检测结果汇总分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:query')")
    public CommonResult<PageResult<WaterSampleTestSummaryRespVO>> getWaterSampleTestSummaryPage(@Valid WaterSampleTestSummaryPageReqVO pageReqVO) {
        PageResult<WaterSampleTestSummaryDO> pageResult = waterSampleTestSummaryService.getWaterSampleTestSummaryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterSampleTestSummaryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出外检统计水质检测结果汇总 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterSampleTestSummaryExcel(@Valid WaterSampleTestSummaryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterSampleTestSummaryDO> list = waterSampleTestSummaryService.getWaterSampleTestSummaryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "外检统计水质检测结果汇总.xls", "数据", WaterSampleTestSummaryRespVO.class,
                        BeanUtils.toBean(list, WaterSampleTestSummaryRespVO.class));
    }

    @PostMapping("/import")
    @Operation(summary = "导入外检统计水质检测结果汇总")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:import')")
    public CommonResult<WaterSampleTestImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                      @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<WaterSampleTestImportExcelVO> list = ExcelUtils.read(file, WaterSampleTestImportExcelVO.class);
        return success(waterSampleTestSummaryService.importWaterSampleList(list,updateSupport));
    }

//    @PostMapping("/device/receive")
//    @Operation(summary = "接收设备数据")
//    @Parameters({
//            @Parameter(name = "modelJson", description = "设备数据JSON字符串", required = true)
//    })
//    @PreAuthorize("@ss.hasPermission('waterdetection:water-sample-test-summary:create')")
//    public CommonResult<WaterDeviceDataRespVO> receiveDeviceData(
//            @RequestParam("modelJson") String modelJson) {
//
//        // 解析JSON字符串
//        try {
//            WaterDeviceDataReqVO deviceData = objectMapper.readValue(modelJson, WaterDeviceDataReqVO.class);
//            return success(waterSampleTestSummaryService.receiveDeviceData(deviceData));
//        } catch (JsonProcessingException e) {
//            // 处理JSON解析错误
//            log.error("设备数据解析失败", e);
//            return CommonResult.error(new ErrorCode(400, "设备数据格式错误"));
//        }
//    }

    @PostMapping("/upload")
    @Operation(summary = "接收设备数据")
    @Parameter(name = "modelJson", description = "设备数据JSON字符串", required = true)
    public CommonResult<String> uploadDeviceData(
            @RequestParam("modelJson") String modelJson) {

        try {
            // 解析JSON字符串
            WaterDeviceDataReqVO deviceData = objectMapper.readValue(modelJson, WaterDeviceDataReqVO.class);

            // 调用服务并直接返回字符串
            String result = waterSampleTestSummaryService.receiveDeviceData(deviceData);
            return CommonResult.success(result);
        } catch (JsonProcessingException e) {
//            log.error("设备数据解析失败: {}", modelJson, e);
            return CommonResult.error(new ErrorCode(400, "设备数据格式错误"));
        }
    }

}