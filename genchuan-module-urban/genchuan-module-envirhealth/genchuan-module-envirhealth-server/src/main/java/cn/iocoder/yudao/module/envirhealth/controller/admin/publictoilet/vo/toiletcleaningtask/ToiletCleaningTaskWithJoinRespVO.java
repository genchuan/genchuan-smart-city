package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ToiletCleaningTaskWithJoinRespVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "公厕名称", example = "中山公园公厕")
    private String toiletName;  // 来自public_toilet.name

    @Schema(description = "任务编号", example = "TASK-20260301-001")
    private String taskNo;

    @Schema(description = "所属区域", example = "北京市")
    private String areaName;  // 来自sys_area.area_name

    @Schema(description = "保洁频次", example = "每天2次")
    private String cleaningFrequency;  // 来自public_toilet.cleaning_frequency

    @Schema(description = "保洁时段", example = "06:00-08:00,18:00-20:00")
    private String cleaningTime;  // 来自public_toilet.cleaning_time

    @Schema(description = "保洁内容", example = "地面清洁/便池清洁/垃圾清理")
    private String cleaningContent;  // 来自public_toilet.cleaning_content

    @Schema(description = "保洁人员", example = "张三,李四")
    private String cleanerNames;  // 来自sys_user.user_name，多个用逗号拼接

    @Schema(description = "保洁标准", example = "地面无积水、便池无污渍")
    private String cleaningStandard;  // 来自public_toilet.cleaning_standard

    @Schema(description = "完成率", example = "100.00")
    private BigDecimal completionRate;

    @Schema(description = "是否异常", example = "0")
    private Integer isAbnormal;

    @Schema(description = "异常描述", example = "清洁人员请假")
    private String abnormalDesc;

    @Schema(description = "计划状态", example = "执行中")
    private String planStatusName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    // ============ 新增字段 ============
    @Schema(description = "完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "佐证材料")
    private String proofUrls;

    @Schema(description = "任务耗时（分钟）")
    private Integer handleDuration;

    @Schema(description = "满意度")
    private String satisfaction;

    @Schema(description = "统计周期")
    private String statPeriod;
}