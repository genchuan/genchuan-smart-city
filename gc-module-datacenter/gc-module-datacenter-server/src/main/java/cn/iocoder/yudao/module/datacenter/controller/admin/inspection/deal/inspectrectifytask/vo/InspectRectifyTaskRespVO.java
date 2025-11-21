package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检整改任务派发 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRectifyTaskRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改任务编码")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题ID")
    private String problemId;

    @Schema(description = "关联问题编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题编码")
    private String problemCode;

    @Schema(description = "关联问题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题名称")
    private String problemName;

    @Schema(description = "责任部门代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任部门代码")
    private String deptCode;

    @Schema(description = "责任部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任部门名称")
    private String deptName;

    @Schema(description = "整改负责人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改负责人ID")
    private String leaderUserId;

    @Schema(description = "整改负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "整改时限（小时）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改时限（小时）")
    private Integer rectifyDeadlineHour;

    @Schema(description = "整改截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改截止时间")
    private LocalDateTime rectifyDeadlineTime;

    @Schema(description = "派发说明")
    @ExcelProperty("派发说明")
    private String dispatchDesc;

    @Schema(description = "整改任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改任务状态")
    private String rectifyStatus;

    @Schema(description = "派发人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派发人")
    private String dispatchUser;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}