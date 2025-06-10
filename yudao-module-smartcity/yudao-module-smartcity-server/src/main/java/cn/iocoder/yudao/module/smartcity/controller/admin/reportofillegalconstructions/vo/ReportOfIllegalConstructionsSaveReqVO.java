package cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 违建上报新增/修改 Request VO")
@Data
public class ReportOfIllegalConstructionsSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "32058")
    private Long id;

    @Schema(description = "上报编号")
    private String reportNumber;

    @Schema(description = "上报人")
    private String reportperson;

    @Schema(description = "上报人联系方式")
    private String contactReporter;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "违建详细地址")
    private String detailedAddressConstruction;

    @Schema(description = "违建所在区域")
    private String constructionIsLocated;

}