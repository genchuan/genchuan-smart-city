package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检督办进度跟踪视图 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectSupvTrackViewRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "督办任务ID")
    @ExcelProperty("督办任务ID")
    private String supvTaskId;

    @Schema(description = "督办任务编码")
    @ExcelProperty("督办任务编码")
    private String supvTaskCode;

    @Schema(description = "关联问题名称")
    @ExcelProperty("关联问题名称")
    private String problemName;

    @Schema(description = "督办人姓名")
    @ExcelProperty("督办人姓名")
    private String supvUserName;

    @Schema(description = "整改负责人姓名")
    @ExcelProperty("整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "督办任务状态")
    @ExcelProperty("督办任务状态")
    private String supvStatus;

    @Schema(description = "督办截止时间")
    @ExcelProperty("督办截止时间")
    private LocalDateTime supvDeadlineTime;

    @Schema(description = "是否超期")
    @ExcelProperty("是否超期")
    private Boolean isOverdue;

    @Schema(description = "最新督办进展时间")
    @ExcelProperty("最新督办进展时间")
    private LocalDateTime latestSupvTime;

    @Schema(description = "最新督办进展类型")
    @ExcelProperty("最新督办进展类型")
    private String latestSupvType;

    @Schema(description = "最新督办进展内容")
    @ExcelProperty("最新督办进展内容")
    private String latestSupvContent;

    @Schema(description = "督办操作次数")
    @ExcelProperty("督办操作次数")
    private Integer supvOperCount;

    @Schema(description = "关联整改任务状态")
    @ExcelProperty("关联整改任务状态")
    private String rectifyStatus;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}