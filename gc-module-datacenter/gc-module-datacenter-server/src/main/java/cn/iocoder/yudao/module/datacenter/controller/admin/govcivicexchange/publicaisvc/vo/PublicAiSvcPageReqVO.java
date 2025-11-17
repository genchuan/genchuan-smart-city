package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 智能客服知识库分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicAiSvcPageReqVO extends PageParam {

    @Schema(description = "知识库ID，唯一编码，UUID生成", example = "30515")
    private String aiSvcId;

    @Schema(description = "问题类型编码，问题分类，如“社保”“公积金”，字典表（sys_dict）")
    private String questionTypeCode;

    @Schema(description = "问题类型名称，与类型编码同步，字典表（sys_dict）", example = "王五")
    private String questionTypeName;

    @Schema(description = "问题关键词，多个关键词用逗号分隔，如“个税查询,个税缴纳记录”")
    private String questionKeyword;

    @Schema(description = "标准问题，规范化问题表述，如“如何查询个税缴纳记录”")
    private String stdQuestion;

    @Schema(description = "标准答案，分点回复，支持富文本")
    private String stdAnswer;

    @Schema(description = "附件路径，辅助说明附件，如操作截图")
    private String attachPath;

    @Schema(description = "使用次数，智能回复时累计次数，默认0", example = "27112")
    private Integer useCount;

    @Schema(description = "更新人ID，更新人ID，用户表（sys_user）", example = "32347")
    private Long updateUserId;

    @Schema(description = "更新人姓名，与更新人ID同步，用户表（sys_user）", example = "芋艿")
    private String updateUserName;

    @Schema(description = "启用状态：1=启用，0=禁用，标识是否参与智能匹配", example = "1")
    private Integer enableStatus;

    @Schema(description = "备注，补充说明，如“高频问题，优先匹配”", example = "你说的对")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识问题难度，如简单/复杂")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识回复类型，如文本/图文")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储人工客服转办阈值")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储关键词匹配阈值")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}