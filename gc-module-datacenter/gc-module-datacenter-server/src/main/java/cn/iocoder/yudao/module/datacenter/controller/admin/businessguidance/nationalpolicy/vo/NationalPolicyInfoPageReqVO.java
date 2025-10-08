package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDate;

@Schema(description = "管理后台 - 国家政策信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NationalPolicyInfoPageReqVO extends PageParam {

    @Schema(description = "政策名称", example = "智慧城市")
    private String policyName;

    @Schema(description = "政策编号", example = "国办发")
    private String policyNo;

    @Schema(description = "政策类型", example = "规划类")
    private String policyType;

    @Schema(description = "政策状态", example = "有效")
    private String policyStatus;

    @Schema(description = "发布单位", example = "国务院")
    private String issueDept;

    @Schema(description = "开始发布时间")
    private LocalDate startIssueTime;

    @Schema(description = "结束发布时间")
    private LocalDate endIssueTime;
}