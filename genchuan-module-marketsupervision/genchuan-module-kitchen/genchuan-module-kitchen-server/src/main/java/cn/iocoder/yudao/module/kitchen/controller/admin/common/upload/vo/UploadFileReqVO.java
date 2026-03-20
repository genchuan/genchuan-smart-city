package cn.iocoder.yudao.module.kitchen.controller.admin.common.upload.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 上传工单资料 Request VO")
@Data
public class UploadFileReqVO {

    //
//    @Schema(description = "[企业整改记录ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @NotNull(message = "企业整改记录ID不允许为空")
//    private Long entRectifyRecordId;

//    @Schema(description = "[资料说明] 资料的文字说明", example = "现场检测图片")
//    @NotEmpty(message = "[资料说明] 不允许为空")
//    private String fileDesc;



}
