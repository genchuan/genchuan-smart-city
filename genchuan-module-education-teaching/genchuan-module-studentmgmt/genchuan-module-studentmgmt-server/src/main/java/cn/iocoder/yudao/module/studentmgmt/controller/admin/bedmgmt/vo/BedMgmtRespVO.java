package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 床位管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BedMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15953")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "楼栋", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("楼栋")
    private String building;

    @Schema(description = "楼层", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("楼层")
    private Integer floor;

    @Schema(description = "房间号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("房间号")
    private String roomNum;

    @Schema(description = "床位号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("床位号")
    private String bedNum;

    @Schema(description = "学生 ID", example = "28082")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "分配时间")
    @ExcelProperty("分配时间")
    private LocalDateTime assignTime;

    @Schema(description = "调整时间")
    @ExcelProperty("调整时间")
    private LocalDateTime adjustTime;

    @Schema(description = "状态：未分配/已分配", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：未分配/已分配")
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