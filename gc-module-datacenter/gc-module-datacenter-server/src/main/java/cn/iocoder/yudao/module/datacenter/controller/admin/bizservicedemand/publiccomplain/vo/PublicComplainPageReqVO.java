package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 投诉举报分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicComplainPageReqVO extends PageParam {

    @Schema(description = "投诉ID，唯一编码，UUID生成", example = "4619")
    private String complainId;

    @Schema(description = "投诉单号，格式“TS+YYYYMMDD+6位流水号”")
    private String complainNo;

    @Schema(description = "诉求类型编码，投诉类型，如“违章停车”，字典表（sys_dict）")
    private String appealTypeCode;

    @Schema(description = "诉求类型名称，与类型编码同步，字典表（sys_dict）", example = "王五")
    private String appealTypeName;

    @Schema(description = "事发区域编码，事发区域国标编码，区域表（sys_area）")
    private String areaCode;

    @Schema(description = "事发区域名称，与区域编码同步，区域表（sys_area）", example = "芋艿")
    private String areaName;

    @Schema(description = "事发地址，详细地址，如“XX路12号门前”")
    private String incidentAddr;

    @Schema(description = "投诉内容，问题描述")
    private String complainContent;

    @Schema(description = "上传图片路径，多张图片用逗号分隔")
    private String imgPath;

    @Schema(description = "联系人姓名，投诉人姓名", example = "李四")
    private String contactName;

    @Schema(description = "联系人电话，加密存储")
    private String contactPhone;

    @Schema(description = "受理部门编码，受理部门编码，部门信息表（sys_org）")
    private String acceptDeptCode;

    @Schema(description = "受理部门名称，与部门编码同步，部门信息表（sys_org）", example = "李四")
    private String acceptDeptName;

    @Schema(description = "处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，流程状态", example = "2")
    private Integer handleStatus;

    @Schema(description = "处置结果，处置完成后填写结果")
    private String handleResult;

    @Schema(description = "提交时间，市民提交时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

    @Schema(description = "受理时间，受理部门接单时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] acceptTime;

    @Schema(description = "办结时间，处置完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

    @Schema(description = "分类扩展字段1，预留，标识问题等级，如一般/紧急")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识处置方式，如现场/线上")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储投诉来源，如APP/热线")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储处置人员ID")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}