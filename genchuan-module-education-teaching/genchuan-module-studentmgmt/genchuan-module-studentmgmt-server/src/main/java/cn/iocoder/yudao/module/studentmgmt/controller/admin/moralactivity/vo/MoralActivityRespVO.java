package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 德育活动 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MoralActivityRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32555")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "活动名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("活动名称")
    private String activityName;

    @Schema(description = "活动类型：党团活动/志愿活动/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("活动类型：党团活动/志愿活动/其他")
    private String activityType;

    @Schema(description = "主办部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主办部门")
    private Long hostDept;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "参与人数")
    @ExcelProperty("参与人数")
    private Integer joinNum;

    @Schema(description = "活动照片地址")
    @ExcelProperty("活动照片地址")
    private String photo;

    @Schema(description = "活动详情")
    @ExcelProperty("活动详情")
    private String content;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "状态：未发布/进行中/已结束", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：未发布/进行中/已结束")
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