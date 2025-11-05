package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MngMatterPageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "180")
    private String statMngMatterId;

    @Schema(description = "统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", example = "芋艿")
    private String statCycleName;

    @Schema(description = "行政区划代码，符合GB/T 2260，关联行政区划表(sys_area)")
    private String areaCode;

    @Schema(description = "行政区划名称，与代码同步，关联行政区划表(sys_area)", example = "赵六")
    private String areaName;

    @Schema(description = "事项大类ID，关联管理事项大类ID，关联管理事项大类表(biz_mng_matter_major)", example = "17504")
    private String matterMajorId;

    @Schema(description = "事项大类名称，与大类ID同步，关联管理事项大类表(biz_mng_matter_major)", example = "赵六")
    private String matterMajorName;

    @Schema(description = "事项小类ID，关联管理事项小类ID(钻取时必填)，关联管理事项小类表(biz_mng_matter_minor)", example = "5897")
    private String matterMinorId;

    @Schema(description = "事项小类名称，与小类ID同步，关联管理事项小类表(biz_mng_matter_minor)", example = "芋艿")
    private String matterMinorName;

    @Schema(description = "处置部门代码，关联主管部门信用代码，关联部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "处置部门名称，与部门代码同步，关联部门信息表(sys_org)", example = "李四")
    private String deptName;

    @Schema(description = "上报总数，统计周期内上报总数，关联管理事项表(biz_mng_matter)", example = "31961")
    private Integer totalRptCount;

    @Schema(description = "待处置数，状态为“待处置”的数量，关联管理事项表（biz_mng_matter）", example = "2293")
    private Integer pendCount;

    @Schema(description = "处置中数，状态为“处置中”的数量，关联管理事项表（biz_mng_matter）", example = "7312")
    private Integer handlCount;

    @Schema(description = "已办结数，状态为“已办结”的数量，关联管理事项表（biz_mng_matter）", example = "9782")
    private Integer completedCount;

    @Schema(description = "已驳回数，状态为“已驳回”的数量，关联管理事项表（biz_mng_matter）", example = "19244")
    private Integer rejectedCount;

    @Schema(description = "办结率，（已办结数/（上报总数 - 已驳回数））×100，关联管理事项表（biz_mng_matter）")
    private BigDecimal completeRate;

    @Schema(description = "平均处置时长，已办结事项处置时长平均值，关联管理事项表（biz_mng_matter）")
    private BigDecimal avgHandleEndure;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}