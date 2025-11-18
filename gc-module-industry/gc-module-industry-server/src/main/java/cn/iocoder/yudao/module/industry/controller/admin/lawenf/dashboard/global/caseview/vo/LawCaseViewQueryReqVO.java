package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Schema(description = "管理后台 - 执法案件总览 查询 Request VO")
@Data
public class LawCaseViewQueryReqVO {


        @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startDate;

        @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endDate;

        @Schema(description = "案件类型。",
                example = "违法建设")
        private String caseType;

        @Schema(description = "案件来源。",
                example = "热线举报")
        private String caseSource;

        @Schema(description = "案件办理进度。",
                example = "调查取证")
        private String progressStage;
}
