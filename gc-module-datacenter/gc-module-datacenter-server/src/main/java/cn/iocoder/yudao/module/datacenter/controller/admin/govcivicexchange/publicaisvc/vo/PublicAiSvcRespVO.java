package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 智能客服知识库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicAiSvcRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23965")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "知识库ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "30515")
    @ExcelProperty("知识库ID，唯一编码，UUID生成")
    private String aiSvcId;

    @Schema(description = "问题类型编码，问题分类，如“社保”“公积金”，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型编码，问题分类，如“社保”“公积金”，字典表（sys_dict）")
    private String questionTypeCode;

    @Schema(description = "问题类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("问题类型名称，与类型编码同步，字典表（sys_dict）")
    private String questionTypeName;

    @Schema(description = "问题关键词，多个关键词用逗号分隔，如“个税查询,个税缴纳记录”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题关键词，多个关键词用逗号分隔，如“个税查询,个税缴纳记录”")
    private String questionKeyword;

    @Schema(description = "标准问题，规范化问题表述，如“如何查询个税缴纳记录”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准问题，规范化问题表述，如“如何查询个税缴纳记录”")
    private String stdQuestion;

    @Schema(description = "标准答案，分点回复，支持富文本", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准答案，分点回复，支持富文本")
    private String stdAnswer;

    @Schema(description = "附件路径，辅助说明附件，如操作截图")
    @ExcelProperty("附件路径，辅助说明附件，如操作截图")
    private String attachPath;

    @Schema(description = "使用次数，智能回复时累计次数，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "27112")
    @ExcelProperty("使用次数，智能回复时累计次数，默认0")
    private Integer useCount;

    @Schema(description = "更新人ID，更新人ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "32347")
    @ExcelProperty("更新人ID，更新人ID，用户表（sys_user）")
    private Long updateUserId;

    @Schema(description = "更新人姓名，与更新人ID同步，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("更新人姓名，与更新人ID同步，用户表（sys_user）")
    private String updateUserName;

    @Schema(description = "启用状态：1=启用，0=禁用，标识是否参与智能匹配", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("启用状态：1=启用，0=禁用，标识是否参与智能匹配")
    private Integer enableStatus;

    @Schema(description = "备注，补充说明，如“高频问题，优先匹配”", example = "你说的对")
    @ExcelProperty("备注，补充说明，如“高频问题，优先匹配”")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识问题难度，如简单/复杂")
    @ExcelProperty("分类扩展字段1，预留，标识问题难度，如简单/复杂")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识回复类型，如文本/图文")
    @ExcelProperty("分类扩展字段2，预留，标识回复类型，如文本/图文")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储人工客服转办阈值")
    @ExcelProperty("通用扩展字段1，预留，存储人工客服转办阈值")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储关键词匹配阈值")
    @ExcelProperty("通用扩展字段2，预留，存储关键词匹配阈值")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}