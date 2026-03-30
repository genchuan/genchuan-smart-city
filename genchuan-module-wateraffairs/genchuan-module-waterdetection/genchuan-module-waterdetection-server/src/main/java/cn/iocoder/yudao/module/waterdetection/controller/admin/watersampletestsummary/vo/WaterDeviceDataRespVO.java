package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Schema(description = "设备数据接收 Response VO")
@Data
@Builder
public class WaterDeviceDataRespVO {

    @Schema(description = "接收状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean success;

    @Schema(description = "状态码", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    private String code;

    @Schema(description = "消息", requiredMode = Schema.RequiredMode.REQUIRED, example = "数据接收成功")
    private String message;

    @Schema(description = "成功接收的数据条数", example = "5")
    private Integer successCount;

    @Schema(description = "失败的数据条数", example = "2")
    private Integer failureCount;

    @Schema(description = "成功接收的样品编号列表")
    private List<String> successSampleNos;

    @Schema(description = "失败的样品信息，key 为样品编号，value 为失败原因")
    private Map<String, String> failureSamples;

    @Schema(description = "接收时间戳")
    private Long timestamp;
}