package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 指标体系完整保存 VO")
@Data
public class IndexSystemSaveFullReqVO {

    @Schema(description = "指标体系ID（UUID），新增时为空，修改时必填", example = "system_005")
    private String systemId;

    @Schema(description = "体系名称", example = "社会组织评估体系")
    private String name;

    @Schema(description = "体系编码", example = "SOCIAL_ORG_EVAL")
    private String code;

    @Schema(description = "适用对象类型ID", example = "obj_type_005")
    private String objectTypeId;

    @Schema(description = "版本号", example = "V1.1")
    private String version;

    @Schema(description = "描述信息")
    private String desc;

    @Schema(description = "状态ID", example = "1")
    private Integer statusId;

    @Schema(description = "分类列表（含指标项）")
    @Valid
    private List<CategoryVO> categories;

    @Schema(description = "管理后台 - 指标分类（含指标项）VO")
    @Data
    public static class CategoryVO {
        @Schema(description = "指标分类ID（UUID），新增时为空，修改时必填", example = "cate_005")
        private String categoryId;

        @Schema(description = "分类名称", example = "内部治理")
        private String name;

        @Schema(description = "分类权重", example = "100")
        private Double weight;

        @Schema(description = "排序序号", example = "1")
        private Integer sortNo;

        @Schema(description = "指标项列表")
        @Valid
        private List<IndexItemVO> items;
    }

    @Schema(description = "管理后台 - 指标项 VO")
    @Data
    public static class IndexItemVO {
        @Schema(description = "指标项ID（UUID），新增时为空，修改时必填", example = "item_005")
        private String itemId;

        @Schema(description = "指标项名称", example = "章程制度完善度")
        private String name;

        @Schema(description = "指标类型，支持传入typeId或typeName", example = "idx_type_005 或 定量指标")
        private String indexType;

        @Schema(description = "计算方式，支持传入wayId或wayName", example = "calc_way_005 或 累加计算")
        private String calcWay;

        @Schema(description = "达标阈值", example = "95")
        private Double threshold;

        @Schema(description = "指标项权重", example = "100")
        private Double weight;

        @Schema(description = "排序序号", example = "1")
        private Integer sortNo;
    }
}
