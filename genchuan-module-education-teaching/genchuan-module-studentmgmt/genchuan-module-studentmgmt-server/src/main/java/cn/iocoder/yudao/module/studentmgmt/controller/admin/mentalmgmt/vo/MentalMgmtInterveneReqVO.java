package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 心理管理跟进 Request VO")
@Data
public class MentalMgmtInterveneReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29416")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "咨询预约时间")
    private String interveneTime;
   @Schema(description = "干预内容")
    private String interveneContent;

}