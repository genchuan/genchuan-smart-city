package cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo;

import cn.iocoder.yudao.module.smartcity.enums.DictTypeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 排水户信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DrainageUserRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "统一社会信用代码", example = "企业唯一标识")
    @ExcelProperty("统一社会信用代码")
    private String creditCode;

    @Schema(description = "排水户名称", example = "商户注册全称")
    @ExcelProperty("排水户名称")
    private String userName;

    @Schema(description = "行业类别")
    @ExcelProperty(value = "行业类别", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SM_INDUSTRY_CATEGORY)
    private String industryType;

    @Schema(description = "排水户分类")
    @ExcelProperty(value = "排水户分类", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SM_DRAINAGE_USER)
    private String userType;

    @Schema(description = "月均用水量（吨）")
    @ExcelProperty("月均用水量（吨）")
    private String waterUsage;

    @Schema(description = "排水管网接入点坐标")
    @ExcelProperty("排水管网接入点坐标")
    private String drainagePoint;

    @Schema(description = "预处理设施清单")
    @ExcelProperty("预处理设施清单")
    private String preTreatment;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}