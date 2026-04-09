package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 考评管理分页 Request VO")
@Data
public class AssessMgmtPageReqVO extends PageParam {

    @Schema(description = "班级", example = "王五")
    private String className;

    @Schema(description = "考评类型：教室卫生/早操/文明班级/黑板报", example = "2")
    private String assessType;

    @Schema(description = "统计周期：周/月/学期")
    private String cycle;

    @Schema(description = "考评得分")
    private BigDecimal score;

    @Schema(description = "班级排名")
    private Integer rank;

    @Schema(description = "考评人")
    private String assessUser;

    @Schema(description = "发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishTime;

    @Schema(description = "状态：未发布/已发布", example = "1")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}