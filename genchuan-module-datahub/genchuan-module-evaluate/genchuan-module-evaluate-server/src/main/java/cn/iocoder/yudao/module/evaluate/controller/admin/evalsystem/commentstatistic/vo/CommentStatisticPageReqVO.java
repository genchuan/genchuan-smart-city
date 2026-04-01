package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentStatisticPageReqVO extends PageParam {

    @Schema(description = "体系ID (关联体系表主键id)", example = "1")
    private Long systemId;

    @Schema(description = "指标项ID(关联指标项表的主键id  eval_index_item.id)", example = "12354")
    private Long itemId;

    @Schema(description = "街道：评价对象ID (关联eval_object.id)", example = "19141")
    private Long objectId;

    @Schema(description = "统计指标项数量", example = "6666")
    private Long count;

    @Schema(description = "关联到规则中回填的分数")
    private Long score;

    @Schema(description = "地址编码")
    private String addressCoding;

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

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "1")
    private String status;

}