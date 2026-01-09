package cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 人员密集场所安全新增/修改 Request VO")
@Data
public class DenselyPopulatedAreasSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14114")
    private Long id;

    @Schema(description = "所名称", example = "芋艿")
    private String theName;

    @Schema(description = "场所类型")
    private String typeOfVenue;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "实时人数")
    private String realTimeNumberOfPeople;

    @Schema(description = "人员密度")
    private String occupantDensity;

    @Schema(description = "人员流动速度")
    private String personnelTurnoverSpeed;

    @Schema(description = "人员分布区域")
    private String personnelDistributionArea;

    @Schema(description = "预警级别")
    private String warningLevel;

}