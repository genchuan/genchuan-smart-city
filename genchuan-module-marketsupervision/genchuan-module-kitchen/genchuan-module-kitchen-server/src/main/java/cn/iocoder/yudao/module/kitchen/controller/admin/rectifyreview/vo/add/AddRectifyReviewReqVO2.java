package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.add;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改通知书复审台账新增/修改 Request VO")
@Data
public class AddRectifyReviewReqVO2 {
    @Schema(description = "[预警id]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1",hidden = true)
//    @NotNull(message = "[预警ID] 不能为空")
    private Long aiAlertMessageId;


    @Schema(description = "[企业ID] 关联park_enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1",hidden = true)
//    @NotNull(message = "[企业ID] 关联park_enterprise_info.id不能为空")
    private Long entId;


    //--------------------------------------------------------------------------------------

    @Schema(description = "[台账编号] 整改通知书复审台账唯一编号", hidden = true)
//    @NotEmpty(message = "[台账编号] 整改通知书复审台账唯一编号不能为空")
    private String ledgerCode;

    //根据告警类型映射获取，目前先暂时1 TODO
    @Schema(description = "[违规类型ID] 关联park_illegal_type_dict.id",hidden = true)
//    @NotNull(message = "[违规类型ID] 关联park_illegal_type_dict.id不能为空")
    private Long illegalTypeId;

    //根据告警类型映射获取，目前先暂时1 TODO
    @Schema(description = "[违规等级ID] 关联park_illegal_level_dict.id", hidden = true)
//    @NotNull(message = "[违规等级ID] 关联park_illegal_level_dict.id不能为空")
    private Long illegalLevelId;

    //根据预警图片获取，目前先随机图或者自己上传 TODO
    @Schema(description = "[违规证据链接] 可多链接，JSON格式的字符串，varchar类型", hidden = true)
//    @NotEmpty(message = "[违规证据链接] 多链接以英文逗号分隔，varchar类型不能为空")
    private String evidenceUrl;

    //用当前时间
    @Schema(description = "[草拟时间] 整改通知书草拟时间", hidden = true)
//    @NotNull(message = "[草拟时间] 整改通知书草拟时间不能为空")
    private LocalDateTime draftTime;

    //赋值待复审
    @Schema(description = "[复审状态] 如：待复审/已下发/已撤销", hidden = true)
//    @NotEmpty(message = "[复审状态] 如：待复审/已下发/已撤销不能为空")
    private String reviewStatus;

    //用当前用户
    @Schema(description = "[复审人ID] 关联park_user.id",hidden = true)
    private Long reviewBy;

    //暂时用66
    @Schema(description = "[执法复审台账编号] 关联park_law_review_ledger.ledger_code",hidden = true)
    private String lawLedgerCode;





}
