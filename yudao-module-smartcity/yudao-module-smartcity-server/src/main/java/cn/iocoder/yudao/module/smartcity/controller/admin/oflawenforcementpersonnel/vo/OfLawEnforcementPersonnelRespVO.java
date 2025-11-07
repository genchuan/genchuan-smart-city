package cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 执法人员管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OfLawEnforcementPersonnelRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19085")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "姓名", example = "芋艿")
    @ExcelProperty("姓名")
    private String fullName;

    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idNumber;

    @Schema(description = "联系方式")
    @ExcelProperty("联系方式")
    private String contactInformation;

    @Schema(description = "所属执法部门")
    @ExcelProperty("所属执法部门")
    private String belongingLawDepartment;

    @Schema(description = "职务")
    @ExcelProperty("职务")
    private String position;

    @Schema(description = "执法证编号")
    @ExcelProperty("执法证编号")
    private String lawEnforcementNumber;

    @Schema(description = "执法证有效期")
    @ExcelProperty("执法证有效期")
    private LocalDateTime validityCertificate;

    @Schema(description = "入职时间")
    @ExcelProperty("入职时间")
    private String entryTime;

    @Schema(description = "政治面貌", example = "2")
    @ExcelProperty("政治面貌")
    private String politicalStatus;

    @Schema(description = "学历")
    @ExcelProperty("学历")
    private String educationalBackground;

    @Schema(description = "专业")
    @ExcelProperty("专业")
    private String major;

    @Schema(description = "培训经历")
    @ExcelProperty("培训经历")
    private String trainingExperience;

    @Schema(description = "考核成绩")
    @ExcelProperty("考核成绩")
    private String resultOfExamination;

    @Schema(description = "奖惩记录")
    @ExcelProperty("奖惩记录")
    private String rewardsRecord;

    @Schema(description = "违规违纪情况")
    @ExcelProperty("违规违纪情况")
    private String violationRegulations;

    @Schema(description = "负责区域")
    @ExcelProperty("负责区域")
    private String responsibleArea;

    @Schema(description = "执法类型", example = "2")
    @ExcelProperty("执法类型")
    private String lawEnforcementType;

    @Schema(description = "紧急联系人姓名", example = "芋艿")
    @ExcelProperty("紧急联系人姓名")
    private String emergencyContactName;

    @Schema(description = "紧急联系人电话")
    @ExcelProperty("紧急联系人电话")
    private String emergencyContactPhone;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}