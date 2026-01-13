package cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 稽查证据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkInspectionEvidenceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31550")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "证据ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27107")
    @ExcelProperty("证据ID（UUID）")
    private String evidenceId;

    @Schema(description = "稽查记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27633")
    @ExcelProperty("稽查记录ID")
    private String inspectionId;

    @Schema(description = "证据类型：图片/视频/日志", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("证据类型：图片/视频/日志")
    private String evidenceType;

    @Schema(description = "存储地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("存储地址")
    private String evidenceUrl;

    @Schema(description = "描述")
    @ExcelProperty("描述")
    private String evidenceDesc;

    @Schema(description = "上传时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上传时间")
    private LocalDateTime uploadTime;

    @Schema(description = "上传人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上传人ID")
    private Long uploadBy;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime evidenceCreateTime;

    @Schema(description = "业务备注", example = "随便")
    @ExcelProperty("业务备注")
    private String evidenceRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}