package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 预警类型字典新增/修改 Request VO")
@Data
public class AlarmTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27030")
    private Long id;

    @Schema(description = "预警类型主键（UUID）", example = "14444")
    private String alarmTypeId;

    @Schema(description = "预警类型名称", example = "张三")
    private String alarmName;

    @Schema(description = "描述", example = "随便")
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