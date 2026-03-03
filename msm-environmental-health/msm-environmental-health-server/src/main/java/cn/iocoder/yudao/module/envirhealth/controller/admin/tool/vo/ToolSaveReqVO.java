package cn.iocoder.yudao.module.envirhealth.controller.admin.tool.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 工具字典新增/修改 Request VO")
@Data
public class ToolSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19946")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "10730")
    private String sysToolId;

    @Schema(description = "工具名称", example = "赵六")
    private String name;

    @Schema(description = "工具编码")
    private String code;

    @Schema(description = "工具类型", example = "2")
    private String type;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "状态：启用/禁用", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}