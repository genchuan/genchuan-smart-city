package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 公厕保洁任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ToiletCleaningTaskRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "公厕ID，关联public_toilet.id", example = "6468")
    @ExcelProperty("公厕ID，关联public_toilet.id")
    private String toiletId;

    @Schema(description = "任务编号")
    @ExcelProperty("任务编号")
    private String taskNo;

    @Schema(description = "保洁频次，如每天2次")
    @ExcelProperty("保洁频次，如每天2次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @ExcelProperty("保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁内容，如地面清洁/便池清洁/垃圾清理")
    @ExcelProperty("保洁内容，如地面清洁/便池清洁/垃圾清理")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON数组格式")
    @ExcelProperty("保洁人员IDs，JSON数组格式")
    private String cleanerIds;

    @Schema(description = "计划状态ID，关联sys_plan_status.id", example = "2017")
    @ExcelProperty("计划状态ID，关联sys_plan_status.id")
    private String planStatusId;

    @Schema(description = "完成率，%")
    @ExcelProperty("完成率，%")
    private BigDecimal completionRate;

    @Schema(description = "是否异常：0-正常，1-异常")
    @ExcelProperty("是否异常：0-正常，1-异常")
    private Integer isAbnormal;

    @Schema(description = "异常描述")
    @ExcelProperty("异常描述")
    private String abnormalDesc;

    @Schema(description = "佐证材料URL，JSON数组格式")
    @ExcelProperty("佐证材料URL，JSON数组格式")
    private String proofUrls;

}