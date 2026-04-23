package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 心理管理状态更新 Request VO")
@Data
public class MentalMgmtUpdateStatusReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29416")
    @ExcelProperty("主键 ID")
    private Long id;

//    status (string, required): 状态（待评估 / 咨询中 / 已干预，关联芋道字典表：mental_mgmt_status）
    @Schema(description = "状态")
    private String status;
}