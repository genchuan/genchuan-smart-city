package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 指标体系详情 VO")
@Data
public class IndexSystemDetailVO {

    @Schema(description = "指标体系基本信息")
    private BaseInfo baseInfo;

    @Schema(description = "分类列表")
    private List<CategoryVO> categories;

    @Schema(description = "管理后台 - 指标体系基本信息")
    @Data
    public static class BaseInfo {
        @Schema(description = "指标体系ID（UUID）", example = "12425")
        private String systemId;

        @Schema(description = "体系名称", example = "综合绩效评价指标体系")
        private String name;

        @Schema(description = "体系编码", example = "XT001")
        private String code;

        @Schema(description = "适用对象类型名称", example = "政府部门")
        private String objectTypeName;

        @Schema(description = "版本号", example = "V1.0")
        private String version;

        @Schema(description = "描述信息")
        private String description;

        @Schema(description = "状态名称", example = "启用")
        private String statusName;

        @Schema(description = "创建人姓名", example = "张三")
        private String createByName;

        @Schema(description = "创建时间（业务字段）")
        private LocalDateTime bizCreateTime;

        @Schema(description = "更新时间（业务字段）")
        private LocalDateTime bizUpdateTime;

    }

    @Schema(description = "管理后台 - 指标分类 VO")
    @Data
    public static class CategoryVO {
        @Schema(description = "指标分类ID（UUID）", example = "cate001")
        private String categoryId;

        @Schema(description = "分类名称", example = "工作业绩")
        private String name;

        @Schema(description = "分类权重", example = "40.00")
        private Double weight;

        @Schema(description = "排序序号", example = "1")
        private Integer sortNo;

        @Schema(description = "指标项列表")
        private List<IndexItemVO> items;
    }

    @Schema(description = "管理后台 - 指标项 VO")
    @Data
    public static class IndexItemVO {
        @Schema(description = "指标项ID（UUID）", example = "item001")
        private String itemId;
        @Schema(description = "所属分类ID（UUID）", example = "cate001")
        private String categoryId;
        @Schema(description = "指标项名称", example = "任务完成率")
        private String name;

        @Schema(description = "指标类型名称", example = "定量指标")
        private String indexTypeName;

        @Schema(description = "计算方式名称", example = "百分比计算")
        private String calcWayName;

        @Schema(description = "达标阈值", example = "95.00")
        private Double threshold;

        @Schema(description = "指标项权重", example = "30.00")
        private Double weight;

        @Schema(description = "排序序号", example = "1")
        private Integer sortNo;
    }
}