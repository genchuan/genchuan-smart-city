package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Schema(description = "管理后台 - 德育资源学习看板 Request VO")
@Data
public class MoralResourceChartReqVO {

    @Schema(description = "开始时间，格式时间戳", example = "1776211200000")
    private LocalDateTime startTime;

    @Schema(description = "结束时间，格式时间戳", example = "1776211200000")
    private LocalDateTime endTime;

}