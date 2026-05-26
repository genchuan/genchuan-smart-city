package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 宣传管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PromoteMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18073")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "宣传任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("宣传任务名称")
    private String taskName;

    @Schema(description = "宣传站点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("宣传站点")
    private String site;

    @Schema(description = "宣传人数")
    @ExcelProperty("宣传人数")
    private Integer promoteNum;

    @Schema(description = "意向学生数")
    @ExcelProperty("意向学生数")
    private Integer intentNum;

    @Schema(description = "执行人")
    @ExcelProperty("执行人")
    private String executeUser;

    @Schema(description = "执行时间")
    @ExcelProperty("执行时间")
    private LocalDateTime executeTime;

    @Schema(description = "状态：未执行/已执行", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "备注", example = "你猜")
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
