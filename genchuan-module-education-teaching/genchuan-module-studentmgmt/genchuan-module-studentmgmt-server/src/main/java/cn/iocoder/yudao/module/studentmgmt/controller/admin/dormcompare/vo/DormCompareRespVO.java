package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 宿舍评比 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormCompareRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4709")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "宿舍 ID", example = "8217")
    @ExcelProperty("宿舍 ID")
    private Long dormId;

    @Schema(description = "宿舍号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("宿舍号")
    private String dormNum;

    @Schema(description = "评比周期：周/月/学期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评比周期：周/月/学期")
    private String cycle;

    @Schema(description = "得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("得分")
    private BigDecimal score;

    @Schema(description = "排名")
    @ExcelProperty("排名")
    private Integer rankNo;

    @Schema(description = "打分人")
    @ExcelProperty("打分人")
    private String scoreUser;

    @Schema(description = "汇总时间")
    @ExcelProperty("汇总时间")
    private LocalDateTime sumTime;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "状态：打分中/已汇总", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：打分中/已汇总")
    private String status;

    @Schema(description = "备注", example = "随便")
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
