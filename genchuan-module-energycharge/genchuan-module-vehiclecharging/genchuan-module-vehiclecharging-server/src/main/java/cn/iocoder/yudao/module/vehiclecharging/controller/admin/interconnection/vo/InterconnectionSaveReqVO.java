package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 互联互通表新增/修改 Request VO")
@Data
public class InterconnectionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28503")
    private Long id;

    @Schema(description = "对接编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "对接编号不能为空")
    private String connectCode;

    @Schema(description = "第三方平台名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "第三方平台名称不能为空")
    private String thirdPlatform;

    @Schema(description = "对接类型", example = "1")
    private String connectType;

    @Schema(description = "API参数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "API参数不能为空")
    private String apiParam;

    @Schema(description = "同步频率，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "同步频率，单位：分钟不能为空")
    private Integer syncFreq;

    @Schema(description = "同步成功率，单位：%")
    private BigDecimal syncSuccessRate;

    @Schema(description = "对接状态：未申请/审核中/已开通/已关闭", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "对接状态：未申请/审核中/已开通/已关闭不能为空")
    private String connectStatus;

    @Schema(description = "审核人员")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注", example = "你说的对")
    private String auditRemark;

    @Schema(description = "关闭原因", example = "不喜欢")
    private String closeReason;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}