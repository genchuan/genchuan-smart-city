package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "管理后台 - 户外广告删除 Request VO")
@Data
public class OutdoorAdRemoveReqVO {

    @Schema(description = "广告主键ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"k1l2m3n4-o5p6-7890-klmn-123456789012\"]")
    @NotEmpty(message = "广告ID列表不能为空")
    private List<String> ids;

    @Schema(description = "删除原因", example = "广告已拆除，数据清理")
    private String removeReason;
}