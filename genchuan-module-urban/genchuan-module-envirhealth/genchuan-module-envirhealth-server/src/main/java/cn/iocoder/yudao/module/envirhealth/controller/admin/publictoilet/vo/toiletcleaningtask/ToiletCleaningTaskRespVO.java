package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 公厕保洁任务 Response VO")
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
    @ExcelProperty("公厕ID")
    private String toiletId;

    @Schema(description = "任务编号")
    @ExcelProperty("任务编号")
    private String taskNo;

    @Schema(description = "保洁频次，如每天2次")
    @ExcelProperty("保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @ExcelProperty("保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁内容，如地面清洁/便池清洁/垃圾清理")
    @ExcelProperty("保洁内容")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON数组格式")
    @ExcelProperty("保洁人员")
    private String cleanerIds;

    @Schema(description = "计划状态ID列表", example = "[1,2,3]")
    @ExcelProperty("计划状态")
    private String planStatusId;

    @Schema(description = "完成率，%")
    @ExcelProperty("完成率")
    private BigDecimal completionRate;

    @Schema(description = "是否异常：0-正常，1-异常")
    @ExcelProperty("是否异常")
    private Integer isAbnormal;

    @Schema(description = "异常描述")
    @ExcelProperty("异常描述")
    private String abnormalDesc;

    @Schema(description = "佐证材料URL，JSON数组格式")
    @ExcelProperty("佐证材料")
    private String proofUrls;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "任务耗时（分钟）")
    @ExcelProperty("任务耗时")
    private Integer handleDuration;

    @Schema(description = "满意度")
    @ExcelProperty("满意度")
    private String satisfaction;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statPeriod;

    @Schema(description = "复盘意见", example = "清扫十分干净")
    @ExcelProperty("复盘意见")
    private String reviewDesc;
}