package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会员积分新增/修改 Request VO")
@Data
public class MemberPointSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4936")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9042")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "变动积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "变动积分不能为空")
    private Integer changeAmount;

    @Schema(description = "变动后的总积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "变动后的总积分不能为空")
    private Integer totalPoint;

    @Schema(description = "变动类型：1-获取，2-消耗", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "变动类型：1-获取，2-消耗不能为空")
    private Integer changeType;

    @Schema(description = "变动原因", example = "不香")
    private String changeReason;

    @Schema(description = "记录状态：0-异常，1-正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录状态：0-异常，1-正常不能为空")
    private Integer status;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "核查时间")
    private LocalDateTime checkTime;

    @Schema(description = "核查人")
    private String checkBy;

    @Schema(description = "业务编码", example = "25120")
    private String bizId;

    @Schema(description = "业务类型", example = "1")
    private Integer bizType;

    @Schema(description = "积分标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "积分标题不能为空")
    private String title;

    @Schema(description = "积分描述", example = "随便")
    private String description;

}