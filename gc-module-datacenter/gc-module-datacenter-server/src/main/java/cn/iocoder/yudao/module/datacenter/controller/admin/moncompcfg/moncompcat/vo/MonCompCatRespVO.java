package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件分类配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonCompCatRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17700")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "3100")
    @ExcelProperty("分类ID，唯一编码，UUID生成")
    private String monCompCatId;

    @Schema(description = "父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测部件分类配置表（sys_mon_comp_cat）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6338")
    @ExcelProperty("父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测部件分类配置表（sys_mon_comp_cat）")
    private String parentCatId;

    @Schema(description = "分类层级，标识分类所处层级，1对应大类、2对应中类、3对应小类", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级，标识分类所处层级，1对应大类、2对应中类、3对应小类")
    private String catLevel;

    @Schema(description = "分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排")
    private String catCode;

    @Schema(description = "分类名称，参照GB/T XXXXX.6标准名称，扩展类标注“自定义”", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("分类名称，参照GB/T XXXXX.6标准名称，扩展类标注“自定义”")
    private String catName;

    @Schema(description = "分类说明，描述分类包含的部件范围，如“燃气压力传感器：监测燃气管道压力的设备”")
    @ExcelProperty("分类说明，描述分类包含的部件范围，如“燃气压力传感器：监测燃气管道压力的设备”")
    private String catDesc;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}