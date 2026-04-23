package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 缴费核验新增/修改 Request VO")
@Data
public class PayCheckSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21602")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "停车费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "停车费用不能为空")
    private BigDecimal parkFee;

    @Schema(description = "缴费状态：已缴清/欠费 关联字典pay_check_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "缴费状态：已缴清/欠费 关联字典pay_check_status不能为空")
    private String status;

    @Schema(description = "核验时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "核验时间不能为空")
    private LocalDateTime checkTime;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "30722")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "核验人ID，关联system_user用户表", example = "7217")
    private Long checkUserId;

    @Schema(description = "核验结果")
    private String checkResult;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}