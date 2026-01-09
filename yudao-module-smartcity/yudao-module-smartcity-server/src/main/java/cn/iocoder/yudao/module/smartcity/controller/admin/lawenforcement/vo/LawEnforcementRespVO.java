package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 城市管理执法 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LawEnforcementRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7475")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "执法案件编号")
    @ExcelProperty("执法案件编号")
    private String lawEnforcementNumber;

    @Schema(description = "案件发生地点")
    @ExcelProperty("案件发生地点")
    private String locationTheCase;

    @Schema(description = "发生时间")
    @ExcelProperty("发生时间")
    private String occurrenceTime;

    @Schema(description = "涉事主体信息")
    @ExcelProperty("涉事主体信息")
    private String informationInvolved;

    @Schema(description = "案件详情描述", example = "随便")
    @ExcelProperty("案件详情描述")
    private String caseDetailsDescription;

    @Schema(description = "案件处理时长")
    @ExcelProperty("案件处理时长")
    private String durationOfCaseHandling;

    @Schema(description = "投诉举报次数")
    @ExcelProperty("投诉举报次数")
    private String numberComplaintsReports;

    @Schema(description = "投诉内容")
    @ExcelProperty("投诉内容")
    private String complaintContent;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}