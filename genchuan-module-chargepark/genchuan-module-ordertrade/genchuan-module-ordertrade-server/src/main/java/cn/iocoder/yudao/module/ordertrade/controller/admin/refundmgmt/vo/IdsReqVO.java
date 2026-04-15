package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "批量操作 Request VO")
@Data
public class IdsReqVO {
    @Schema(description = "主键ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "ID列表不能为空")
    private List<Long> ids;
    @Schema(description = "备注/原因")
    private String remark;
}
