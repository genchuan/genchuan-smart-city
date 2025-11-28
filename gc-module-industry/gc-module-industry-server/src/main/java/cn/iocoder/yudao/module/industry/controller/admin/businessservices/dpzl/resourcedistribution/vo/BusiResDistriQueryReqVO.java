// ResourceDistributionQueryReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "资源分布查询请求 VO")
public class BusiResDistriQueryReqVO {

    @Schema(description = "区域名称", example = "110100")
    private String regionName;

    @Schema(description = "企业规模", example = "大型")
    private String scale;

    @Schema(description = "行业类型", example = "工业")
    private String industryType;

}