package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 确认 Request VO")
@Data
public class StayMgmtConfirmReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10528")
    private Long[] ids;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}