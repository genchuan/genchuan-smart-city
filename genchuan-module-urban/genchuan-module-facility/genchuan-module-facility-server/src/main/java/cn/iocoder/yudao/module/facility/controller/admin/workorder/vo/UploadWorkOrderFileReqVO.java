package cn.iocoder.yudao.module.facility.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 上传工单资料 Request VO")
@Data
public class UploadWorkOrderFileReqVO {

    @Schema(description = "[工单ID] 关联的工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10001")
    @NotNull(message = "关联工单不允许为空")
    private Long workOrderId;

    @Schema(description = "[资料说明] 资料的文字说明", example = "现场检测图片")
    @NotEmpty(message = "[资料说明] 不允许为空")
    private String fileDesc;

    @Schema(description = "[处理后的指标数值] 处理超标数值后的指标数值", example = "1")
    @NotNull(message = "[处理后的指标数值]不允许为空")
    private BigDecimal afterIndexValue;

//    @Schema(description = "[上传文件] 工单资料文件", requiredMode = Schema.RequiredMode.REQUIRED)
//    private MultipartFile file;


}
