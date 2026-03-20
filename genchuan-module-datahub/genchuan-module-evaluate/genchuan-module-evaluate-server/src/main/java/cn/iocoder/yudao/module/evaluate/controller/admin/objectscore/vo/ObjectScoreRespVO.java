package cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 公司得分 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ObjectScoreRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25269")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "7259")
    @ExcelProperty("体系ID (关联eval_index_system.id)")
    private Long systemId;

    @Schema(description = "体系名称", example = "安全生产体系")
    @ExcelProperty("体系名称")
    private String systemName;

    @Schema(description = "对象ID (关联eval_object.id)", example = "20118")
    @ExcelProperty("对象ID (关联eval_object.id)")
    private Long objectId;

    @Schema(description = "对象名称", example = "XXX公司")
    @ExcelProperty("对象名称")
    private String objectName;

    @Schema(description = "巡检人ID(关联sys_user.id)", example = "17976")
    @ExcelProperty("巡检人ID(关联sys_user.id)")
    private Long userId;

    @Schema(description = "总得分")
    @ExcelProperty("总得分")
    private Long score;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "1")
    @ExcelProperty("状态: 1：待审核中，2：审核通过，3：不用审核")
    private String status;

    @Schema(description = "评价说明")
    @ExcelProperty("评价说明")
    private String details;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "变更日志")
    @ExcelProperty("变更日志")
    private String changeLog;

}