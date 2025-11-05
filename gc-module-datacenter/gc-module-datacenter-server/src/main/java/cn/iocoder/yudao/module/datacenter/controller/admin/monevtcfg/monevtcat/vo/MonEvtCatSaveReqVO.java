package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测事件分类配置新增/修改 Request VO")
@Data
public class MonEvtCatSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5142")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "21747")
    @NotEmpty(message = "分类ID，唯一编码，UUID生成不能为空")
    private String monEvtCatId;

    @Schema(description = "父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测事件分类配置表（sys_mon_evt_cat）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13260")
    @NotEmpty(message = "父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测事件分类配置表（sys_mon_evt_cat）不能为空")
    private String parentEvtId;

    @Schema(description = "分类层级，标识分类所处层级，1 (大类)/2 (中类)/3 (小类)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类层级，标识分类所处层级，1 (大类)/2 (中类)/3 (小类)不能为空")
    private String evtCatLevel;

    @Schema(description = "分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排不能为空")
    private String evtCatCode;

    @Schema(description = "分类名称，参照GB/T XXXXX.6标准名称，扩展类标注 “自定义”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "分类名称，参照GB/T XXXXX.6标准名称，扩展类标注 “自定义”不能为空")
    private String evtCatName;

    @Schema(description = "分类说明，描述事件范围，如“燃气泄漏：燃气从管道或设备泄漏的事件”")
    private String evtCatDesc;

    @Schema(description = "启用状态，标识分类是否可用，默认1", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "启用状态，标识分类是否可用，默认1不能为空")
    private String enableStatus;

    @Schema(description = "创建人，填写创建人账号，用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，填写创建人账号，用户信息表(sys_user)不能为空")
    private String createUser;

    @Schema(description = "更新人，填写更新人账号，用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留字段，用于存储分类额外属性（如“事件响应时效”）")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留字段，用于存储分类额外属性（如“事件响应时效”）")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留通用字段，存储额外配置信息")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留通用字段，存储额外配置信息")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}