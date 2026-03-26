package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo.*;
import cn.iocoder.yudao.module.waterdetection.service.watersampletestsummary.WaterSampleTestSummaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;

@Slf4j
@Tag(name = "设备数据接收")
@RestController
@RequestMapping("/waterdetection/device")
@Validated
public class WaterDeviceController {

    @Resource
    private WaterSampleTestSummaryService waterSampleTestSummaryService;

    @Resource
    private ObjectMapper objectMapper;

    @PermitAll
    @PostMapping("/upload")
    @Operation(summary = "接收设备数据")
    @Parameter(name = "modelJson", description = "设备数据JSON字符串", required = true)
    public String uploadDeviceData( // 修改返回类型为 String
                                    @RequestParam("modelJson") String modelJson) {
        // 设置默认租户ID为1
        TenantContextHolder.setTenantId(1L);
        try {
            // 解析JSON字符串
            WaterDeviceDataReqVO deviceData = objectMapper.readValue(modelJson, WaterDeviceDataReqVO.class);

            // 调用服务并直接返回字符串
            return waterSampleTestSummaryService.receiveDeviceData(deviceData);
        } catch (JsonProcessingException e) {
            log.error("设备数据解析失败: {}", modelJson, e);
            return "error"; // 直接返回错误字符串
        }
    }
}