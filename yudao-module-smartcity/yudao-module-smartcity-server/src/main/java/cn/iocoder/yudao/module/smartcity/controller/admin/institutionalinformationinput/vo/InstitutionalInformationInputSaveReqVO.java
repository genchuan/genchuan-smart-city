package cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 机构信息录入新增/修改 Request VO")
@Data
public class InstitutionalInformationInputSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4449")
    private Long id;

    @Schema(description = "机构名称", example = "张三")
    private String institutionName;

    @Schema(description = "统一社会信用代码")
    private String unifiedSocialCreditCode;

    @Schema(description = "成立时间")
    private LocalDateTime establishmentTime;

    @Schema(description = "机构性质")
    private String natureOfInstitution;

    @Schema(description = "经营范围")
    private String natureOfBusiness;

    @Schema(description = "注册资本")
    private String registeredCapital;

    @Schema(description = "法定代表人")
    private String legalRepresentative;

    @Schema(description = "注册地址")
    private String companyRegisteredAddress;

}