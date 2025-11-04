package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价网格划分新增/修改 Request VO")
@Data
public class EvalGridDivSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "556")
    private Long id;

    @Schema(description = "评价网格ID", example = "15263")
    private String evalGridId;

    @Schema(description = "评价网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "评价网格名称不能为空")
    private String evalGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10750")
    @NotEmpty(message = "所属乡镇ID不能为空")
    private String townStreetId;

    @Schema(description = "评价网格类型(A/B/C)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "评价网格类型(A/B/C)不能为空")
    private String gridType;

    @Schema(description = "所含管理网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所含管理网格IDs不能为空")
    private String includedMgIds;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
    private LocalDateTime divTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}