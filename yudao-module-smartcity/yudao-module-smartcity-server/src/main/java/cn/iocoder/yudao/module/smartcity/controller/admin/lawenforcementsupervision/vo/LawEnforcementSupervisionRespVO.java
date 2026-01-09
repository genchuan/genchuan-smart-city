package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 执法监督 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LawEnforcementSupervisionRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24171")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "监督编号", example = "11753")
    @ExcelProperty("监督编号")
    private String supervisionId;

    @Schema(description = "执法事件编号")
    @ExcelProperty("执法事件编号")
    private String eventNumber;

    @Schema(description = "执法人员")
    @ExcelProperty("执法人员")
    private String officials;

    @Schema(description = "监督人员")
    @ExcelProperty("监督人员")
    private String personnel;

    @Schema(description = "监督时间")
    @ExcelProperty("监督时间")
    private String time;

    @Schema(description = "监督方式")
    @ExcelProperty("监督方式")
    private String method;

    @Schema(description = "证据采集完整性")
    @ExcelProperty("证据采集完整性")
    private String integrityCollection;

    @Schema(description = "当事人满意度")
    @ExcelProperty("当事人满意度")
    private String satisfactionInvolved;

    @Schema(description = "监督结果评价")
    @ExcelProperty("监督结果评价")
    private String resultEvaluation;

    @Schema(description = "违规行为描述", example = "随便")
    @ExcelProperty("违规行为描述")
    private String violationDescription;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}