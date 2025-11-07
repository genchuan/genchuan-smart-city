package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 扩展网格划分新增/修改 Request VO")
@Data
public class ExtGridDivSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14541")
    private Long id;

    @Schema(description = "扩展网格ID", example = "395")
    private String extGridId;

    @Schema(description = "扩展网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "扩展网格名称不能为空")
    private String extGridName;

    @Schema(description = "扩展网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "扩展网格类型不能为空")
    private String extType;

    @Schema(description = "所含基础网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所含基础网格IDs不能为空")
    private String includedBasicIds;

    @Schema(description = "基础网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "基础网格类型不能为空")
    private String basicGridType;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "边界坐标不能为空")
    private String boundaryCoords;

    @Schema(description = "申请原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不香")
    @NotEmpty(message = "申请原因不能为空")
    private String applyReason;

    @Schema(description = "审核状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "审核状态不能为空")
    private String auditStatus;

    @Schema(description = "申请人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20045")
    @NotEmpty(message = "申请人ID不能为空")
    private String applyUserId;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人ID", example = "21987")
    private String auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    private String auditOpinion;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

}