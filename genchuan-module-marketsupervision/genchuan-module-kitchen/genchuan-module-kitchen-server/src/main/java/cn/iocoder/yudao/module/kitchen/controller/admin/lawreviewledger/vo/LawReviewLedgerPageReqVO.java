package cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 执法复审总台账分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LawReviewLedgerPageReqVO extends PageParam {

    @Schema(description = "[执法复审台账编号] 唯一，按执法区域编码+年份+序号自动生成")
    private String ledgerCode;

    @Schema(description = "[整改复审台账ID] 关联park_rectify_review.id，与punish_review_id互斥", example = "5785")
    private Long rectifyReviewId;

    @Schema(description = "[处罚复审台账ID] 关联park_punish_review_ledger.id，与rectify_review_id互斥", example = "24418")
    private Long punishReviewId;

    @Schema(description = "[企业ID] 关联park_enterprise_info.id", example = "17948")
    private Long entId;

    @Schema(description = "[执法区域编码] 关联area_dict.area_code")
    private String lawAreaCode;

    @Schema(description = "[创建时间] 台账生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
