package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测部件分类配置新增/修改 Request VO")
@Data
public class MonCompCatSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17700")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "3100")
    @NotEmpty(message = "分类ID，唯一编码，UUID生成不能为空")
    private String monCompCatId;

    @Schema(description = "父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测部件分类配置表（sys_mon_comp_cat）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6338")
    @NotEmpty(message = "父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测部件分类配置表（sys_mon_comp_cat）不能为空")
    private String parentCatId;

    @Schema(description = "分类层级，标识分类所处层级，1对应大类、2对应中类、3对应小类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类层级，标识分类所处层级，1对应大类、2对应中类、3对应小类不能为空")
    private String catLevel;

    @Schema(description = "分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排不能为空")
    private String catCode;

    @Schema(description = "分类名称，参照GB/T XXXXX.6标准名称，扩展类标注“自定义”", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "分类名称，参照GB/T XXXXX.6标准名称，扩展类标注“自定义”不能为空")
    private String catName;

    @Schema(description = "分类说明，描述分类包含的部件范围，如“燃气压力传感器：监测燃气管道压力的设备”")
    private String catDesc;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}