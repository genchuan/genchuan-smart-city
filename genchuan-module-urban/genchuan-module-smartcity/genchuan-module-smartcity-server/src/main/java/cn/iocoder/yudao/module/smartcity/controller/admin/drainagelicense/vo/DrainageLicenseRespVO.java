package cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 排水电子许可证信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DrainageLicenseRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "269")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "许可证编号")
    @ExcelProperty("许可证编号")
    private String licenseNo;

    @Schema(description = "有效期开始日期")
    @ExcelProperty("有效期开始日期")
    private LocalDateTime startDate;

    @Schema(description = "有效期结束日期")
    @ExcelProperty("有效期结束日期")
    private LocalDateTime endDate;

    @Schema(description = "许可排水类型", example = "2")
    @ExcelProperty(value = "许可排水类型", converter = DictConvert.class)
    @DictFormat("sm_drainage_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String drainageType;

    @Schema(description = "审批单位")
    @ExcelProperty("审批单位")
    private String approvalUnit;

    @Schema(description = "状态", example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("crm_audit_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String licenseStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}