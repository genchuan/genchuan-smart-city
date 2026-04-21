package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 记录上传 Request VO")
@Data
public class DutyMgmtUploadRecordReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "值班记录内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "出勤")
    private String recordContent;

}