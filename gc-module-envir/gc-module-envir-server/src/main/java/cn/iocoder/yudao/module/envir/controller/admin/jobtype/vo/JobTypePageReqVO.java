package cn.iocoder.yudao.module.envir.controller.admin.jobtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 岗位类型字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobTypePageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "22149")
    private String sysJobTypeId;

    @Schema(description = "岗位名称（可选值：清扫工/保洁员/督导员/驾驶员/维修工/管理员/考核员/转运工）", example = "李四")
    private String name;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}