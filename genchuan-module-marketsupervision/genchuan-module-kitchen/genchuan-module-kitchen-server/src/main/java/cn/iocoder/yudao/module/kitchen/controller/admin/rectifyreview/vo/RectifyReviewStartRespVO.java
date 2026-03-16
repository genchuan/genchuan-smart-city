package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "管理后台 - 开始复审 Response VO")
public class RectifyReviewStartRespVO {

    @Schema(description = "台账ID")
    private Long ledgerId;

    @Schema(description = "台账编号")
    private String ledgerCode;

    @Schema(description = "企业ID")
    private Long entId;

    @Schema(description = "企业名称")
    private String entName;

    @Schema(description = "违规类型ID")
    private Long illegalTypeId;

    @Schema(description = "违规类型名称")
    private String illegalTypeName;

    @Schema(description = "违规等级ID")
    private Long illegalLevelId;

    @Schema(description = "违规等级名称")
    private String illegalLevelName;

    @Schema(description = "草拟时间")
    private LocalDateTime draftTime;

    @Schema(description = "历史证据链接列表")
    private List<String> evidenceUrls;

    @Schema(description = "复审人ID（当前用户）")
    private Long reviewBy;

    @Schema(description = "复审人名称")
    private String reviewByName;
}
