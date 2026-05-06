package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 用户信用新增/修改 Request VO")
@Data
public class UserCreditSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1021")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31349")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "信用分，默认100", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "信用分，默认100不能为空")
    private Integer creditScore;

    @Schema(description = "信用等级：优秀/良好/中等/较差/极差", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "信用等级：优秀/良好/中等/较差/极差不能为空")
    private String creditLevel;

    @Schema(description = "评分规则编码")
    private String ruleCode;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}