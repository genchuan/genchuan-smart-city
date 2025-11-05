package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测事件分类配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonEvtCatRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5142")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "21747")
    @ExcelProperty("分类ID，唯一编码，UUID生成")
    private String monEvtCatId;

    @Schema(description = "父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测事件分类配置表（sys_mon_evt_cat）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13260")
    @ExcelProperty("父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测事件分类配置表（sys_mon_evt_cat）")
    private String parentEvtId;

    @Schema(description = "分类层级，标识分类所处层级，1 (大类)/2 (中类)/3 (小类)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级，标识分类所处层级，1 (大类)/2 (中类)/3 (小类)")
    private String evtCatLevel;

    @Schema(description = "分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排")
    private String evtCatCode;

    @Schema(description = "分类名称，参照GB/T XXXXX.6标准名称，扩展类标注 “自定义”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("分类名称，参照GB/T XXXXX.6标准名称，扩展类标注 “自定义”")
    private String evtCatName;

    @Schema(description = "分类说明，描述事件范围，如“燃气泄漏：燃气从管道或设备泄漏的事件”")
    @ExcelProperty("分类说明，描述事件范围，如“燃气泄漏：燃气从管道或设备泄漏的事件”")
    private String evtCatDesc;

    @Schema(description = "启用状态，标识分类是否可用，默认1", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("启用状态，标识分类是否可用，默认1")
    private String enableStatus;

    @Schema(description = "创建人，填写创建人账号，用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，填写创建人账号，用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成")
    private LocalDateTime createTime;

    @Schema(description = "更新人，填写更新人账号，用户信息表(sys_user)")
    @ExcelProperty("更新人，填写更新人账号，用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留字段，用于存储分类额外属性（如“事件响应时效”）")
    @ExcelProperty("分类扩展字段1，预留字段，用于存储分类额外属性（如“事件响应时效”）")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留字段，用于存储分类额外属性（如“事件响应时效”）")
    @ExcelProperty("分类扩展字段2，预留字段，用于存储分类额外属性（如“事件响应时效”）")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留通用字段，存储额外配置信息")
    @ExcelProperty("通用扩展字段1，预留通用字段，存储额外配置信息")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留通用字段，存储额外配置信息")
    @ExcelProperty("通用扩展字段2，预留通用字段，存储额外配置信息")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}