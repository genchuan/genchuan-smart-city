package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检整改进度跟踪视图 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRectifyTrackRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改任务编码")
    private String rectifyTaskCode;

    @Schema(description = "关联问题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题名称")
    private String problemName;

    @Schema(description = "责任部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任部门名称")
    private String deptName;

    @Schema(description = "整改负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "整改任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改任务状态")
    private String rectifyStatus;

    @Schema(description = "整改时限（小时）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改时限（小时）")
    private Integer rectifyDeadlineHour;

    @Schema(description = "整改截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改截止时间")
    private LocalDateTime rectifyDeadlineTime;

    @Schema(description = "剩余时间（分钟）")
    @ExcelProperty("剩余时间（分钟）")
    private Integer remainTimeMin;

    @Schema(description = "是否超期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否超期")
    private Boolean isOverdue;

    @Schema(description = "最新进展时间")
    @ExcelProperty("最新进展时间")
    private LocalDateTime latestProgressTime;

    @Schema(description = "最新进展描述")
    @ExcelProperty("最新进展描述")
    private String latestProgressDesc;

    @Schema(description = "最新进展照片URL")
    @ExcelProperty("最新进展照片URL")
    private String latestProgressPhoto;

    @Schema(description = "催办次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("催办次数")
    private Integer remindCount;

    @Schema(description = "最后催办时间")
    @ExcelProperty("最后催办时间")
    private LocalDateTime lastRemindTime;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}