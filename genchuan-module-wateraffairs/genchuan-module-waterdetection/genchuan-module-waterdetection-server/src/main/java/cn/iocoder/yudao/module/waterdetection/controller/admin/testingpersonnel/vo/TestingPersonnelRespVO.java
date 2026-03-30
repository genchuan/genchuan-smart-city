package cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 检测人员信息管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TestingPersonnelRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "人员编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("人员编号")
    private String staffNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("姓名")
    private String staffName;

    @Schema(description = "职称")
    @ExcelProperty("职称")
    private String position;

    @Schema(description = "资格证书编号")
    @ExcelProperty("资格证书编号")
    private String certificateNo;

    @Schema(description = "培训记录")
    @ExcelProperty("培训记录")
    private String trainingRecord;

    @Schema(description = "所属机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属机构编号")
    private String agencyCode;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}