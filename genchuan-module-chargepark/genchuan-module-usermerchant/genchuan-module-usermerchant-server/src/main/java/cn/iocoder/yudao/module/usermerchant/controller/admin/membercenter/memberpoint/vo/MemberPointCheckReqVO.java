package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "积分记录核查 Request VO")
@Data
public class MemberPointCheckReqVO {
    @Schema(description = "积分记录ID", required = true, example = "123")
    @NotNull(message = "积分记录ID不能为空")
    private Long id;

    @Schema(description = "核查结果", required = true, example = "经核实，该积分由正常签到获得")
    @NotBlank(message = "核查结果不能为空")
    private String checkResult;
}
