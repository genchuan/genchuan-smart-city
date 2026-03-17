package cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.*;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PatrolInspectionPageReqVO extends PageParam {

    @Schema(description = "巡检人ID(关联sys_user.id)", example = "7549")
    private Long userId;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "14827")
    private Long systemId;

    @Schema(description = "评价对象ID (关联eval_object.id)", example = "27902")
    private Long objectId;

    @Schema(description = "指标项ID(关联eval_index_item.id)", example = "6841")
    private Long itemId;

    @Schema(description = "规则分类ID(关联eval_index_category.id)", example = "27032")
    private Long categoryId;

    @Schema(description = "评价说明")
    private String details;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "2")
    private String status;

    @Schema(description = "图片")
    private byte[] image;

    @Schema(description = "地址编码")
    private String addressCoding;

}