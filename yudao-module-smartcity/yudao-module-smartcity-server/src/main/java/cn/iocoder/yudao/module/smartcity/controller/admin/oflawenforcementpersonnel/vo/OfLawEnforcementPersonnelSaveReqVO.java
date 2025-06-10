package cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 执法人员管理新增/修改 Request VO")
@Data
public class OfLawEnforcementPersonnelSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19085")
    private Long id;

    @Schema(description = "姓名", example = "芋艿")
    private String fullName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "联系方式")
    private String contactInformation;

    @Schema(description = "所属执法部门")
    private String belongingLawDepartment;

    @Schema(description = "职务")
    private String position;

    @Schema(description = "执法证编号")
    private String lawEnforcementNumber;

    @Schema(description = "执法证有效期")
    private LocalDateTime validityCertificate;

    @Schema(description = "入职时间")
    private String entryTime;

    @Schema(description = "政治面貌", example = "2")
    private String politicalStatus;

    @Schema(description = "学历")
    private String educationalBackground;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "培训经历")
    private String trainingExperience;

    @Schema(description = "考核成绩")
    private String resultOfExamination;

    @Schema(description = "奖惩记录")
    private String rewardsRecord;

    @Schema(description = "违规违纪情况")
    private String violationRegulations;

    @Schema(description = "负责区域")
    private String responsibleArea;

    @Schema(description = "执法类型", example = "2")
    private String lawEnforcementType;

    @Schema(description = "紧急联系人姓名", example = "芋艿")
    private String emergencyContactName;

    @Schema(description = "紧急联系人电话")
    private String emergencyContactPhone;

}