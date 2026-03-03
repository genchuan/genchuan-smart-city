package cn.iocoder.yudao.module.envirhealth.controller.admin.planstatus.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 计划状态字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlanStatusPageReqVO extends PageParam {

    @Schema(description = "计划状态主键（UUID）", example = "28005")
    private String sysPlanStatusId;

    @Schema(description = "状态名称", example = "张三")
    private String name;

    @Schema(description = "状态编码")
    private String code;

    @Schema(description = "状态：1-启用/0-禁用", example = "2")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}