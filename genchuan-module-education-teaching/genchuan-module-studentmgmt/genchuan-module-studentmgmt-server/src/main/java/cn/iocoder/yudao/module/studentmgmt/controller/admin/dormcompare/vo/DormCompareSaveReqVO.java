package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍评比新增/修改 Request VO")
@Data
public class DormCompareSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4709")
    private Long id;

    @Schema(description = "宿舍 ID", example = "8217")
    private Long dormId;

    @Schema(description = "宿舍号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "宿舍号不能为空")
    private String dormNum;

    @Schema(description = "评比周期：周/月/学期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评比周期：周/月/学期不能为空")
    private String cycle;

    @Schema(description = "得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "得分不能为空")
    private BigDecimal score;

    @Schema(description = "排名")
    private Integer rank;

    @Schema(description = "打分人")
    private String scoreUser;

    @Schema(description = "汇总时间")
    private LocalDateTime sumTime;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "状态：打分中/已汇总", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：打分中/已汇总不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}