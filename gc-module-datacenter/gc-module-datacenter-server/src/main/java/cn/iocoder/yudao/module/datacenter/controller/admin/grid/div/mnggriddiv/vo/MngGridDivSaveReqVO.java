package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理网格划分新增/修改 Request VO")
@Data
public class MngGridDivSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23779")
    private Long id;

    @Schema(description = "管理网格ID", example = "7217")
    private String mngGridId;

    @Schema(description = "管理网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "管理网格名称不能为空")
    private String mngGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10958")
    @NotEmpty(message = "所属乡镇ID不能为空")
    private String townStreetId;

    @Schema(description = "所含单元网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所含单元网格IDs不能为空")
    private String includedUnitIds;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
    private LocalDateTime divTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注", example = "你猜")
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