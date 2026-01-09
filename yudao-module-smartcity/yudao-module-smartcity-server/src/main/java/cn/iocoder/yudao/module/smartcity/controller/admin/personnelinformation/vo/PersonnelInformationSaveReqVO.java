package cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人员信息新增/修改 Request VO")
@Data
public class PersonnelInformationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "23431")
    private Long id;

    @Schema(description = "姓名", example = "王五")
    private String fullName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "出生日期")
    private LocalDateTime dateOfBirth;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "民族")
    private String nationality;

    @Schema(description = "户籍地址")
    private String registeredResidenceAddress;

    @Schema(description = "现居住地址")
    private String currentResidentialAddress;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "电子邮箱")
    private String eMail;

    @Schema(description = "婚姻状况", example = "2")
    private String maritalStatus;

    @Schema(description = "学历")
    private String educationalBackground;

    @Schema(description = "毕业院校")
    private String almaMater;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "工作单位")
    private String workUnit;

    @Schema(description = "职业")
    private String career;

    @Schema(description = "入职时间")
    private LocalDateTime entryTime;

    @Schema(description = "离职时间（若已离职）")
    private LocalDateTime resignationTime;

    @Schema(description = "紧急联系人姓名", example = "张三")
    private String emergencyContactName;

    @Schema(description = "紧急联系人电话")
    private String emergencyContactPhone;

    @Schema(description = "紧急联系人关系")
    private String emergencyContactRelationship;

}