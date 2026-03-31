package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 巡查巡检统计新增/修改 Request VO")
@Data
public class CommentStatisticSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5509")
    private Long id;

    @Schema(description = "指标项ID(关联指标项表的主键id  eval_index_item.id)", example = "12354")
    private Long itemId;

    @Schema(description = "街道：评价对象ID (关联eval_object.id)", example = "19141")
    private Long objectId;

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

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "1")
    private String status;

}