package cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 《生活饮用水卫生标准》GB 5749-2022标准新增/修改 Request VO")
@Data
public class Gb5749StandardSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标名称不能为空")
    private String itemName;

    @Schema(description = "标准值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "标准值不能为空")
    private String limitValue;

    @Schema(description = "排序序号")
    private Integer itemOrder;

}