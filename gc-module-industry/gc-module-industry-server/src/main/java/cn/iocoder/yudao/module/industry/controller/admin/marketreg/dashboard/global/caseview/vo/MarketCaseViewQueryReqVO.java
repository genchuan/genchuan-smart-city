package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 市监案件总览视图 查询 Request VO")
@Data
public class MarketCaseViewQueryReqVO {
        @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;

        @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @Schema(hidden = true, description = "案件规定完成时长，比如7天", example = "7")
        private Integer prescribedDuration;

        @Schema(description = "案件状态，多选；待受理-0/办理中-1/已办结-2/已驳回-3", example = "[0,1]")
        private List<Integer> statusList;

        @Schema(description = "案件类型，0=食药案件,1=质量案件", example = "0")
        private Integer type;
}
