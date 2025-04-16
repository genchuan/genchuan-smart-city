package cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 人员信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PersonnelInformationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "23431")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "姓名", example = "王五")
    @ExcelProperty("姓名")
    private String fullName;

    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDateTime dateOfBirth;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idNumber;

    @Schema(description = "民族")
    @ExcelProperty("民族")
    private String nationality;

    @Schema(description = "户籍地址")
    @ExcelProperty("户籍地址")
    private String registeredResidenceAddress;

    @Schema(description = "现居住地址")
    @ExcelProperty("现居住地址")
    private String currentResidentialAddress;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactNumber;

    @Schema(description = "电子邮箱")
    @ExcelProperty("电子邮箱")
    private String eMail;

    @Schema(description = "婚姻状况", example = "2")
    @ExcelProperty("婚姻状况")
    private String maritalStatus;

    @Schema(description = "学历")
    @ExcelProperty("学历")
    private String educationalBackground;

    @Schema(description = "毕业院校")
    @ExcelProperty("毕业院校")
    private String almaMater;

    @Schema(description = "专业")
    @ExcelProperty("专业")
    private String major;

    @Schema(description = "工作单位")
    @ExcelProperty("工作单位")
    private String workUnit;

    @Schema(description = "职业")
    @ExcelProperty("职业")
    private String career;

    @Schema(description = "入职时间")
    @ExcelProperty("入职时间")
    private LocalDateTime entryTime;

    @Schema(description = "离职时间（若已离职）")
    @ExcelProperty("离职时间（若已离职）")
    private LocalDateTime resignationTime;

    @Schema(description = "紧急联系人姓名", example = "张三")
    @ExcelProperty("紧急联系人姓名")
    private String emergencyContactName;

    @Schema(description = "紧急联系人电话")
    @ExcelProperty("紧急联系人电话")
    private String emergencyContactPhone;

    @Schema(description = "紧急联系人关系")
    @ExcelProperty("紧急联系人关系")
    private String emergencyContactRelationship;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}