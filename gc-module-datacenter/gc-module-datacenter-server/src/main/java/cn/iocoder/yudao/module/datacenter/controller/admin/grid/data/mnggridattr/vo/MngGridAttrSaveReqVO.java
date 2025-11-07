package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理网格属性新增/修改 Request VO")
@Data
public class MngGridAttrSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "属性ID")
    private String mgAttrId;

    @Schema(description = "管理网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理网格ID不能为空")
    private String mngGridId;

    @Schema(description = "管理网格编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理网格编码不能为空")
    private String mgGridCode;

    @Schema(description = "面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "面积(m²)不能为空")
    private Integer area;

    @Schema(description = "初始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "初始时间不能为空")
    private LocalDateTime initTime;

    @Schema(description = "终止时间")
    private LocalDateTime endTime;

    @Schema(description = "所含单元网格列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所含单元网格列表不能为空")
    private String unitGridList;

    @Schema(description = "网格员ID")
    private String gridUserId;

    @Schema(description = "网格员姓名")
    private String gridUserName;

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

}