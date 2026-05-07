package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 执行 Request VO")
@Data
public class PromoteMgmtExecuteReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18073")
    private Long[] ids;

    @Schema(description = "宣传人数")
    private Integer promoteNum;

    @Schema(description = "意向学生数")
    private Integer intentNum;

}