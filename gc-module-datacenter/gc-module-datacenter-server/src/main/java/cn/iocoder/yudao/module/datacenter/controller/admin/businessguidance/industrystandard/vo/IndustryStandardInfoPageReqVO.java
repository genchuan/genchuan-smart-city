package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理后台 - 行业规范信息分页 Request VO
 *
 * @author Gyh
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "管理后台 - 行业规范信息分页 Request VO")
public class IndustryStandardInfoPageReqVO extends PageParam {

    @Schema(description = "规范名称", example = "智慧城市")
    private String standardName;

    @Schema(description = "规范编号", example = "GB/T")
    private String standardNo;

    @Schema(description = "行业领域", example = "数据安全")
    private String industryField;

    @Schema(description = "规范状态", example = "实施中")
    private String standardStatus;

    @Schema(description = "发布机构", example = "国家市场")
    private String issueOrganization;

    @Schema(description = "关联业务模块", example = "管理部件事项管理")
    private String relatedBusinessModule;
}