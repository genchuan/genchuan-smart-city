package cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 机构信息录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InstitutionalInformationInputRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4449")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "机构名称", example = "张三")
    @ExcelProperty("机构名称")
    private String institutionName;

    @Schema(description = "统一社会信用代码")
    @ExcelProperty("统一社会信用代码")
    private String unifiedSocialCreditCode;

    @Schema(description = "成立时间")
    @ExcelProperty("成立时间")
    private LocalDateTime establishmentTime;

    @Schema(description = "机构性质")
    @ExcelProperty("机构性质")
    private String natureOfInstitution;

    @Schema(description = "经营范围")
    @ExcelProperty("经营范围")
    private String natureOfBusiness;

    @Schema(description = "注册资本")
    @ExcelProperty("注册资本")
    private String registeredCapital;

    @Schema(description = "法定代表人")
    @ExcelProperty("法定代表人")
    private String legalRepresentative;

    @Schema(description = "注册地址")
    @ExcelProperty("注册地址")
    private String companyRegisteredAddress;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}