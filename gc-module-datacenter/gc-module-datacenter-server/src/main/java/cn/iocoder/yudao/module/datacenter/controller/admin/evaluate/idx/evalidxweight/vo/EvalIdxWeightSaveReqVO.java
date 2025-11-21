package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标权重管理新增/修改 Request VO")
@Data
public class EvalIdxWeightSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "权重ID")
    private String idxWeightId;

    @Schema(description = "所属指标体系ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属指标体系ID不能为空")
    private String idxSystemId;

    @Schema(description = "所属指标体系名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属指标体系名称不能为空")
    private String idxSystemName;

    @Schema(description = "权重类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "权重类型不能为空")
    private String weightType;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联ID不能为空")
    private String relateId;

    @Schema(description = "关联名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联名称不能为空")
    private String relateName;

    @Schema(description = "权重值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "权重值(%)不能为空")
    private BigDecimal weightValue;

    @Schema(description = "权重说明")
    private String weightDesc;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}