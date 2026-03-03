package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 垃圾异常类型字典新增/修改 Request VO")
@Data
public class AbnormalTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12536")
    private Long id;

    @Schema(description = "异常类型主键（UUID）", example = "16499")
    private String abnormalTypeId;

    @Schema(description = "异常类型名称", example = "张三")
    private String abnormalName;

    @Schema(description = "描述", example = "你猜")
    private String description;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}