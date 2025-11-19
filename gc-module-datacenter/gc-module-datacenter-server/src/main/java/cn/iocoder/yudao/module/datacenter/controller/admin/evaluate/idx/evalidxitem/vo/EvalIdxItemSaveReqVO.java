package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标项管理新增/修改 Request VO")
@Data
public class EvalIdxItemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "指标项ID")
    private String idxItemId;

    @Schema(description = "指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标项名称不能为空")
    private String idxItemName;

    @Schema(description = "指标项编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标项编码不能为空")
    private String idxItemCode;

    @Schema(description = "所属指标分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属指标分类ID不能为空")
    private String idxCatId;

    @Schema(description = "所属指标分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属指标分类名称不能为空")
    private String idxCatName;

    @Schema(description = "指标类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标类型不能为空")
    private String idxType;

    @Schema(description = "计算方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "计算方式不能为空")
    private String calcMethod;

    @Schema(description = "数据来源表")
    private String dataSourceTable;

    @Schema(description = "数据来源字段")
    private String dataSourceField;

    @Schema(description = "达标阈值")
    private String qualifiedThreshold;

    @Schema(description = "指标描述")
    private String idxDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

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