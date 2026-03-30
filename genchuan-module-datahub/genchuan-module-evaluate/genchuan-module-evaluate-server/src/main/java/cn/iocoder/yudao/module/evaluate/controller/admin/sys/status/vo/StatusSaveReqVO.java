package cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 状态字典新增/修改 Request VO")
@Data
public class StatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9359")
    private Long id;

    @Schema(description = "状态ID（UUID）", example = "2597")
    private String statusId;

    @Schema(description = "状态名称", example = "王五")
    private String name;

    @Schema(description = "状态编码")
    private String code;

    @Schema(description = "状态描述")
    private String desc;

    @Schema(description = "业务创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}