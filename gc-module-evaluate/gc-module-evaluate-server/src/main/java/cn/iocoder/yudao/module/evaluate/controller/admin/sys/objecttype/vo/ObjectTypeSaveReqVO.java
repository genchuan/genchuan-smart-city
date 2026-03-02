package cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 对象类型字典新增/修改 Request VO")
@Data
public class ObjectTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27137")
    private Long id;

    @Schema(description = "类型ID（UUID）", example = "26591")
    private String typeId;

    @Schema(description = "类型名称", example = "芋艿")
    private String name;

    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "类型描述")
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