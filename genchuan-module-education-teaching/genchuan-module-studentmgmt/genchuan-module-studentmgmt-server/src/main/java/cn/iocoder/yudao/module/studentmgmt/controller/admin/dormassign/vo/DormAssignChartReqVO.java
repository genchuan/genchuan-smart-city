package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 新生宿舍分配看板 Request VO")
@Data
public class DormAssignChartReqVO {

    @Schema(description = "学年", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025")
    private Integer year;


}