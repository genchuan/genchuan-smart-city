package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评比管理新增/修改 Request VO")
@Data
public class CompareMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14512")
    private Long id;

    @Schema(description = "班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "班级不能为空")
    private String className;

    @Schema(description = "评比周期：周/月/学期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评比周期：周/月/学期不能为空")
    private String cycle;

    @Schema(description = "总得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总得分不能为空")
    private BigDecimal totalScore;

    @Schema(description = "排名")
    private Integer rank;

    @Schema(description = "授予称号", example = "赵六")
    private String awardName;

    @Schema(description = "授予时间")
    private LocalDateTime awardTime;

    @Schema(description = "打分人")
    private String scoreUser;

    @Schema(description = "状态：打分中/已汇总", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：打分中/已汇总不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}