package cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 养护人员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MaintenancePersonnelRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15874")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "人员编号", example = "7402")
    @ExcelProperty("人员编号")
    private String personnelId;

    @Schema(description = "人员姓名", example = "赵六")
    @ExcelProperty("人员姓名")
    private String personnelName;

    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "联系方式")
    @ExcelProperty("联系方式")
    private String contactInformation;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idNumber;

    @Schema(description = "养护地块")
    @ExcelProperty("养护地块")
    private String maintainTheLandParcel;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}