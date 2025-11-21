package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 民意征集 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicOpinionCollectRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19988")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "征集ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "28075")
    @ExcelProperty("征集ID，唯一编码，UUID生成")
    private String collectId;

    @Schema(description = "征集主题，征集核心主题，如“XX片区改造规划征集”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("征集主题，征集核心主题，如“XX片区改造规划征集”")
    private String collectTopic;

    @Schema(description = "征集单位编码，发布征集的部门编码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("征集单位编码，发布征集的部门编码，部门信息表（sys_org）")
    private String deptCode;

    @Schema(description = "征集单位名称，与部门编码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("征集单位名称，与部门编码同步，部门信息表（sys_org）")
    private String deptName;

    @Schema(description = "征集范围编码，征集覆盖区域编码，如“330100”=杭州市，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("征集范围编码，征集覆盖区域编码，如“330100”=杭州市，区域表（sys_area）")
    private String areaCode;

    @Schema(description = "征集范围名称，与区域编码同步，区域表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("征集范围名称，与区域编码同步，区域表（sys_area）")
    private String areaName;

    @Schema(description = "征集内容，征集背景、需反馈的问题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("征集内容，征集背景、需反馈的问题")
    private String collectContent;

    @Schema(description = "开始时间，征集开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("开始时间，征集开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间，征集结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("结束时间，征集结束时间")
    private LocalDateTime endTime;

    @Schema(description = "征集状态：1=未开始，2=征集ing，3=已结束，4=结果公示，流程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("征集状态：1=未开始，2=征集ing，3=已结束，4=结果公示，流程状态")
    private Integer collectStatus;

    @Schema(description = "结果公示内容，征集结束后公示的汇总结果")
    @ExcelProperty("结果公示内容，征集结束后公示的汇总结果")
    private String resultContent;

    @Schema(description = "公示时间，结果公示时间")
    @ExcelProperty("公示时间，结果公示时间")
    private LocalDateTime publishResultTime;

    @Schema(description = "发布人ID，发布征集的人员ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15639")
    @ExcelProperty("发布人ID，发布征集的人员ID，用户表（sys_user）")
    private Long publishUserId;

    @Schema(description = "发布时间，发布征集的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布时间，发布征集的时间")
    private LocalDateTime publishTime;

    @Schema(description = "备注，补充说明，如“纸质意见可邮寄至XX地址”", example = "随便")
    @ExcelProperty("备注，补充说明，如“纸质意见可邮寄至XX地址”")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识征集类型，如规划/政策")
    @ExcelProperty("分类扩展字段1，预留，标识征集类型，如规划/政策")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识征集级别，如市级/区级")
    @ExcelProperty("分类扩展字段2，预留，标识征集级别，如市级/区级")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储意见提交方式，如线上/线下")
    @ExcelProperty("通用扩展字段1，预留，存储意见提交方式，如线上/线下")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储意见统计数量")
    @ExcelProperty("通用扩展字段2，预留，存储意见统计数量")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}