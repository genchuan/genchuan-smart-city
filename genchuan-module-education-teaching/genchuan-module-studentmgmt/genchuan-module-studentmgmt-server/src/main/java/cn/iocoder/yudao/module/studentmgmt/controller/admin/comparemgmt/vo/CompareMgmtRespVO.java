package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 评比管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompareMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14512")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("班级")
    private String className;

    @Schema(description = "评比周期：周/月/学期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评比周期：周/月/学期")
    private String cycle;

    @Schema(description = "总得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总得分")
    private BigDecimal totalScore;

    @Schema(description = "排名")
    @ExcelProperty("排名")
    private Integer rank;

    @Schema(description = "授予称号", example = "赵六")
    @ExcelProperty("授予称号")
    private String awardName;

    @Schema(description = "授予时间")
    @ExcelProperty("授予时间")
    private LocalDateTime awardTime;

    @Schema(description = "打分人")
    @ExcelProperty("打分人")
    private String scoreUser;

    @Schema(description = "状态：打分中/已汇总", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：打分中/已汇总")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}