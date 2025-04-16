package cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡査人员管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManagementOfPatrolPersonnelRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19813")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "姓名", example = "赵六")
    @ExcelProperty("姓名")
    private String fullName;

    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDateTime dateOfBirth;

    @Schema(description = "联系方式")
    @ExcelProperty("联系方式")
    private String contactInformation;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idNumber;

    @Schema(description = "所属部门")
    @ExcelProperty("所属部门")
    private String department;

    @Schema(description = "岗位名称")
    @ExcelProperty("岗位名称")
    private String position;

    @Schema(description = "入职时间")
    @ExcelProperty("入职时间")
    private LocalDateTime entryTime;

    @Schema(description = "巡查区域")
    @ExcelProperty("巡查区域")
    private String patrolArea;

    @Schema(description = "巡查路线")
    @ExcelProperty("巡查路线")
    private String patrolRoute;

    @Schema(description = "巡查班次")
    @ExcelProperty("巡查班次")
    private String inspectionShift;

    @Schema(description = "资质证书编号")
    @ExcelProperty("资质证书编号")
    private String qualificationCertificateNumber;

    @Schema(description = "证书有效期")
    @ExcelProperty("证书有效期")
    private String certificateValidityPeriod;

    @Schema(description = "培训记录")
    @ExcelProperty("培训记录")
    private String trainingRecords;

    @Schema(description = "巡查设备编号")
    @ExcelProperty("巡查设备编号")
    private String inspectionEquipmentNumber;

    @Schema(description = "违规记录")
    @ExcelProperty("违规记录")
    private String violationRecord;

    @Schema(description = "奖惩情况")
    @ExcelProperty("奖惩情况")
    private String rewardAndPunishmentSituation;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}