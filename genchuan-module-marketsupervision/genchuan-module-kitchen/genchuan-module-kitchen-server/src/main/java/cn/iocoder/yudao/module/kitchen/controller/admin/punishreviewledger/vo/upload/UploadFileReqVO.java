package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.upload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 上传资料 Request VO")
@Data
public class UploadFileReqVO {

    //
    @Schema(description = "[处罚台账ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "处罚台账ID不允许为空")
    private Long punishReviewLedgerId;

//    @Schema(description = "[企业整改说明]", example = "已进行整改")
//    private String rectifyDesc;

//    @Schema(description = "[资料说明] 资料的文字说明", example = "现场检测图片")
//    @NotEmpty(message = "[资料说明] 不允许为空")
//    private String fileDesc;



}
