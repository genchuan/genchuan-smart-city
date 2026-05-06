package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 会员签到新增/修改 Request VO")
@Data
public class MemberSignSaveReqVO {

    @Schema(description = "签到记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1622")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28909")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "签到日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "签到日期不能为空")
    private LocalDate signDate;

    @Schema(description = "连续签到天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "连续签到天数不能为空")
    private Integer continuousDays;

    @Schema(description = "本次签到获得积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本次签到获得积分不能为空")
    private Integer point;

    @Schema(description = "本次签到获得经验", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本次签到获得经验不能为空")
    private Integer experience;

    @Schema(description = "记录状态：1-正常，0-异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录状态：1-正常，0-异常不能为空")
    private Integer status;

}