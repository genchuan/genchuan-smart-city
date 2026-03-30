package cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 检测机构资质管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TestingAgencyRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("机构编号")
    private String agencyCode;

    @Schema(description = "机构名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("机构名称")
    private String agencyName;

    @Schema(description = "资质证书编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资质证书编号")
    private String certificateNo;

    @Schema(description = "检测范围")
    @ExcelProperty("检测范围")
    private String testingScope;

    @Schema(description = "有效期至")
    @ExcelProperty("有效期至")
    private LocalDateTime validDate;

    @Schema(description = "发证单位")
    @ExcelProperty("发证单位")
    private String issuingAuthority;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}