package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 热线对接 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicHotlineLinkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17702")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "热线ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22132")
    @ExcelProperty("热线ID，唯一编码，UUID生成")
    private String hotlineId;

    @Schema(description = "热线单号，热线系统原生单号，如“12345-20251014-001”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("热线单号，热线系统原生单号，如“12345-20251014-001”")
    private String hotlineNo;

    @Schema(description = "平台单号，平台生成单号，格式“RL+YYYYMMDD+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平台单号，平台生成单号，格式“RL+YYYYMMDD+6位流水号”")
    private String platformNo;

    @Schema(description = "诉求类型编码，热线诉求类型，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("诉求类型编码，热线诉求类型，字典表（sys_dict）")
    private String appealTypeCode;

    @Schema(description = "诉求类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("诉求类型名称，与类型编码同步，字典表（sys_dict）")
    private String appealTypeName;

    @Schema(description = "事发区域编码，事发区域编码，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发区域编码，事发区域编码，区域表（sys_area）")
    private String areaCode;

    @Schema(description = "事发区域名称，与区域编码同步，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("事发区域名称，与区域编码同步，区域表（sys_area）")
    private String areaName;

    @Schema(description = "诉求内容，市民诉求详情", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("诉求内容，市民诉求详情")
    private String appealContent;

    @Schema(description = "联系人姓名，热线记录的联系人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("联系人姓名，热线记录的联系人姓名")
    private String contactName;

    @Schema(description = "联系人电话，加密存储", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系人电话，加密存储")
    private String contactPhone;

    @Schema(description = "热线来源，如“12345”“12319”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("热线来源，如“12345”“12319”")
    private String hotlineSource;

    @Schema(description = "同步状态：1=待同步，2=已同步，3=同步失败，数据同步状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("同步状态：1=待同步，2=已同步，3=同步失败，数据同步状态")
    private Integer syncStatus;

    @Schema(description = "处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，处置流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，处置流程状态")
    private Integer handleStatus;

    @Schema(description = "受理部门编码，受理部门，部门信息表（sys_org）")
    @ExcelProperty("受理部门编码，受理部门，部门信息表（sys_org）")
    private String acceptDeptCode;

    @Schema(description = "受理部门名称，与部门编码同步，部门信息表（sys_org）", example = "李四")
    @ExcelProperty("受理部门名称，与部门编码同步，部门信息表（sys_org）")
    private String acceptDeptName;

    @Schema(description = "处置结果，处置完成后填写")
    @ExcelProperty("处置结果，处置完成后填写")
    private String handleResult;

    @Schema(description = "同步时间，数据同步至平台的时间")
    @ExcelProperty("同步时间，数据同步至平台的时间")
    private LocalDateTime syncTime;

    @Schema(description = "办结时间，处置完成时间")
    @ExcelProperty("办结时间，处置完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "分类扩展字段1，预留，标识热线优先级，如普通/紧急")
    @ExcelProperty("分类扩展字段1，预留，标识热线优先级，如普通/紧急")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识诉求来源，如电话/短信")
    @ExcelProperty("分类扩展字段2，预留，标识诉求来源，如电话/短信")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储热线受理时间")
    @ExcelProperty("通用扩展字段1，预留，存储热线受理时间")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储热线备注")
    @ExcelProperty("通用扩展字段2，预留，存储热线备注")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}