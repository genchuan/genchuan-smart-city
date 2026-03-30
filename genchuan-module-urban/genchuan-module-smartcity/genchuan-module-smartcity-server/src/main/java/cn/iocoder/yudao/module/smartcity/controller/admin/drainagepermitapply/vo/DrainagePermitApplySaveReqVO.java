package cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 排水许可证申请新增/修改 Request VO")
@Data
public class DrainagePermitApplySaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "申请编号")
    private String applyNo;

    @Schema(description = "排水户名称")
    private String userName;

    @Schema(description = "排水水质检测报告文件")
//    private String waterQualityReport;
    private List<String> waterQualityReport;
    
    @Schema(description = "日均排水量（吨）")
    private Double dailyDrainage;

    @Schema(description = "重点排污单位证明文件路径")
    private String pollutionProof;

    @Schema(description = "历史违规记录")
    private String violationHistory;

    @Schema(description = "申请状态")
    private String applyStatus;

    @Schema(description = "审核人")
    private String approver;

    @Schema(description = "审核时间")
    private LocalDateTime approveTime;

    @Schema(description = "审核意见")
    private String approveComment;

}