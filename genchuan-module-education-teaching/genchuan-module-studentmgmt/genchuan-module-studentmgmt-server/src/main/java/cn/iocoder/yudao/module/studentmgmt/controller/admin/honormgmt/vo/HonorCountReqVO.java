package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Schema(description = "管理后台 - 各班级 / 类型荣誉数量统计 Request VO")
@Data
public class HonorCountReqVO {

    @Schema(description = "统计维度", requiredMode = Schema.RequiredMode.REQUIRED, example = "type")
    private String dimension;
    @Schema(description = "统计开始时间")
    private LocalDateTime startTime;
    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;


}