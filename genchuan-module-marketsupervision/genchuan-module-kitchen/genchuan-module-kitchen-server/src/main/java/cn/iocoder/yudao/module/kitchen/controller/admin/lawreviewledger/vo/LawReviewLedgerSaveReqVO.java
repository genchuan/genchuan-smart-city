package cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 执法复审总台账新增/修改 Request VO")
@Data
public class LawReviewLedgerSaveReqVO {

    @Schema(description = "[主键ID] 执法复审总台账唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13023")
    private Long id;

    @Schema(description = "[执法复审台账编号] 唯一，按执法区域编码+年份+序号自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[执法复审台账编号] 唯一，按执法区域编码+年份+序号自动生成不能为空")
    private String ledgerCode;

    @Schema(description = "[整改复审台账ID] 关联park_rectify_review.id，与punish_review_id互斥", example = "5785")
    private Long rectifyReviewId;

    @Schema(description = "[处罚复审台账ID] 关联park_punish_review_ledger.id，与rectify_review_id互斥", example = "24418")
    private Long punishReviewId;

    @Schema(description = "[企业ID] 关联park_enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17948")
    @NotNull(message = "[企业ID] 关联park_enterprise_info.id不能为空")
    private Long entId;

    @Schema(description = "[执法区域编码] 关联area_dict.area_code", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[执法区域编码] 关联area_dict.area_code不能为空")
    private String lawAreaCode;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
