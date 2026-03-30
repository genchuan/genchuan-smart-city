package cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 岗位职责划分管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PositionResponsibilityPageReqVO extends PageParam {

    @Schema(description = "岗位名称")
    private String positionName;

    @Schema(description = "岗位职责描述")
    private String responsibilityDesc;

    @Schema(description = "任职要求")
    private String qualificationReq;

    @Schema(description = "所属单位")
    private String belongUnit;

    @Schema(description = "负责人")
    private String manager;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}