package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 人员状态字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PersonStatusPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "27424")
    private String personStatusId;

    @Schema(description = "状态名称（可选值：在岗/休假/请假/离职/待入职/调岗/停薪留职）", example = "赵六")
    private String name;

    @Schema(description = "状态编码")
    private String statusCode;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}