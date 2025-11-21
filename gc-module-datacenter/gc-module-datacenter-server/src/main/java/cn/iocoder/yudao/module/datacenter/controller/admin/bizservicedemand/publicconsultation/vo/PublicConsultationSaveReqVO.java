package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 咨询建议新增/修改 Request VO")
@Data
public class PublicConsultationSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4181")
    private Long id;

    @Schema(description = "咨询ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "23767")
    @NotEmpty(message = "咨询ID，唯一编码，UUID生成不能为空")
    private String consultId;

    @Schema(description = "咨询单号，格式“ZX+YYYYMMDD+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "咨询单号，格式“ZX+YYYYMMDD+6位流水号”不能为空")
    private String consultNo;

    @Schema(description = "咨询类型编码，类型（如“政策咨询”“建设建议”），字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "咨询类型编码，类型（如“政策咨询”“建设建议”），字典表（sys_dict）不能为空")
    private String consultTypeCode;

    @Schema(description = "咨询类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "咨询类型名称，与类型编码同步，字典表（sys_dict）不能为空")
    private String consultTypeName;

    @Schema(description = "咨询内容，市民咨询或建议内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "咨询内容，市民咨询或建议内容不能为空")
    private String consultContent;

    @Schema(description = "联系人姓名，咨询人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "联系人姓名，咨询人姓名不能为空")
    private String contactName;

    @Schema(description = "联系人电话，加密存储", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系人电话，加密存储不能为空")
    private String contactPhone;

    @Schema(description = "受理部门编码，受理部门，部门信息表（sys_org）")
    private String acceptDeptCode;

    @Schema(description = "受理部门名称，与部门编码同步，部门信息表（sys_org）", example = "张三")
    private String acceptDeptName;

    @Schema(description = "回复内容，部门回复内容")
    private String replyContent;

    @Schema(description = "咨询状态：1=待回复，2=已回复，3=已采纳（建议类），流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "咨询状态：1=待回复，2=已回复，3=已采纳（建议类），流程状态不能为空")
    private Integer consultStatus;

    @Schema(description = "提交时间，市民提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "提交时间，市民提交时间不能为空")
    private LocalDateTime submitTime;

    @Schema(description = "回复时间，部门回复时间")
    private LocalDateTime replyTime;

    @Schema(description = "备注，补充说明，如“建议已转规划部门”", example = "你猜")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识咨询紧急程度")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识建议领域，如交通/绿化")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储附件路径，如咨询材料")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储回复附件路径")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}