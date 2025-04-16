package cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查人员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PatrolPersonnelRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27301")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "编号")
    @ExcelProperty("编号")
    private String number;

    @Schema(description = "姓名", example = "赵六")
    @ExcelProperty("姓名")
    private String fullName;

    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "年龄")
    @ExcelProperty("年龄")
    private String age;

    @Schema(description = "联系方式")
    @ExcelProperty("联系方式")
    private String contactInformation;

    @Schema(description = "上岗时间证书编号")
    @ExcelProperty("上岗时间证书编号")
    private LocalDateTime certificateTime;

    @Schema(description = "巡查区域备注")
    @ExcelProperty("巡查区域备注")
    private String inspectionRemarks;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}