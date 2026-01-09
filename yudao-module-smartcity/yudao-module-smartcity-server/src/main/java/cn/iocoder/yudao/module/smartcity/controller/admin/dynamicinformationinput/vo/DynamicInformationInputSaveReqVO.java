package cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 动态信息录入新增/修改 Request VO")
@Data
public class DynamicInformationInputSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10409")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "发布时间")
    private LocalDateTime releaseTime;

    @Schema(description = "发布主体")
    private String publishingSubject;

    @Schema(description = "内容概述")
    private String contentOverview;

}