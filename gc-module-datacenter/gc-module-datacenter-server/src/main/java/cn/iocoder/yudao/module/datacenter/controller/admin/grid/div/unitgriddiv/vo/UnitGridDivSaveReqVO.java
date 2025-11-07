package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 单元网格划分新增/修改 Request VO")
@Data
public class UnitGridDivSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "单元网格ID")
    private String unitGridId;

    @Schema(description = "单元网格名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单元网格名称不能为空")
    private String unitGridName;

    @Schema(description = "所属社区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属社区ID不能为空")
    private String commId;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "比例尺")
    private String scale;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "边界坐标不能为空")
    private String boundaryCoords;

    @Schema(description = "划分时间")
    private LocalDateTime divTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注")
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