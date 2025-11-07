package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 应急物资信息新增/修改 Request VO")
@Data
public class EmerMatInfoSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16247")
    private Long id;

    @Schema(description = "物资ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9092")
    @NotEmpty(message = "物资ID，唯一编码，UUID不能为空")
    private String matId;

    @Schema(description = "物资编码，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "物资编码，系统自动生成不能为空")
    private String matCode;

    @Schema(description = "物资名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "物资名称，标准名称不能为空")
    private String matName;

    @Schema(description = "分类ID，关联应急物资分类配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "15435")
    @NotEmpty(message = "分类ID，关联应急物资分类配置表不能为空")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "分类名称，与分类ID同步不能为空")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用区域代码，关联行政区划表不能为空")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "适用区域名称，与区域代码同步不能为空")
    private String applyRegionName;

    @Schema(description = "物资规格，如“5kg装”")
    private String matSpec;

    @Schema(description = "物资单位，如“个”“箱”")
    private String matUnit;

    @Schema(description = "总数量，物资的总库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总数量，物资的总库存数量不能为空")
    private Integer totalQuantity;

    @Schema(description = "可用数量，正常状态可使用的物资数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可用数量，正常状态可使用的物资数量不能为空")
    private Integer availableQuantity;

    @Schema(description = "物资状态，1（正常）/0（异常），默认1", example = "1")
    private String matStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}