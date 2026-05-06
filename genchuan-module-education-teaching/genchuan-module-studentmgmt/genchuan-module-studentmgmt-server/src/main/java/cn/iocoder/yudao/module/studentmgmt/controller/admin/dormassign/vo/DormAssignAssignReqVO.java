package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分配 Request VO")
@Data
public class DormAssignAssignReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long[] ids;

    @Schema(description = "宿舍号")
    private String dormNum;

    @Schema(description = "床位 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long[] bedIds;

    @Schema(description = "分配规则")
    private String ruleContent;


}