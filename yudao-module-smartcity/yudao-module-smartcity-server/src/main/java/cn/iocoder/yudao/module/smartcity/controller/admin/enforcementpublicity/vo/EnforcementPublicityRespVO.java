package cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 执法公示 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnforcementPublicityRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4786")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "执法公示编号")
    @ExcelProperty("执法公示编号")
    private String publicityNumber;

    @Schema(description = "公示标题")
    @ExcelProperty("公示标题")
    private String publicityTitle;

    @Schema(description = "公示内容")
    @ExcelProperty("公示内容")
    private String publicityContent;

    @Schema(description = "公示类型", example = "2")
    @ExcelProperty("公示类型")
    private String publicityType;

    @Schema(description = "执法部门")
    @ExcelProperty("执法部门")
    private String enforcement;

    @Schema(description = "执法地点")
    @ExcelProperty("执法地点")
    private String enforcementLocation;

    @Schema(description = "执法依据")
    @ExcelProperty("执法依据")
    private String enforcementBasis;

    @Schema(description = "执法结果")
    @ExcelProperty("执法结果")
    private String enforcementResults;

    @Schema(description = "公示开始时间")
    @ExcelProperty("公示开始时间")
    private LocalDateTime announcementStartTime;

    @Schema(description = "公示截止时间")
    @ExcelProperty("公示截止时间")
    private LocalDateTime announcementDeadline;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}