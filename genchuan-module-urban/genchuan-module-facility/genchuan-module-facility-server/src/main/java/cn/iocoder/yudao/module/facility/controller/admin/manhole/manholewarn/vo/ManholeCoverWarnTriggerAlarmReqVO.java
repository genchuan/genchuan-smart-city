package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "窨井盖异常联动报警触发请求")
public class ManholeCoverWarnTriggerAlarmReqVO {

    @Schema(description = "报警方式 0-平台提醒，1-短信，2-电话", example = "[0,1,2]")
    @NotNull(message = "报警方式不能为空")
    private List<Integer> alarmType;

    @Schema(description = "报警接收人", example = "[\"13800138000\",\"13900139000\"]")
    @NotNull(message = "接收人不能为空")
    private List<String> alarmRecipient;

    @Schema(description = "报警级别", example = "2")
    @NotNull(message = "报警级别不能为空")
    private Integer alarmLevel;

    @Schema(description = "报警内容", example = "XX大道与YY路交叉口东侧井盖水位超标，当前35cm，阈值30cm")
    @NotNull(message = "报警内容不能为空")
    private String alarmContent;

    @Schema(description = "租户ID", example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    @NotNull(message = "租户ID不能为空")
    private String tenantId;

    @Schema(description = "操作人ID", example = "f5g6h7i8-j9k0-1234-fghi-456789abcdef")
    @NotNull(message = "操作人ID不能为空")
    private String operateUserId;
}
