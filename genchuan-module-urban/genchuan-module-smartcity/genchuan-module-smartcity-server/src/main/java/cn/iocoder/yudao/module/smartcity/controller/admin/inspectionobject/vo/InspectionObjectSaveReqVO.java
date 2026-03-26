package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 双随机行政检查新增/修改 Request VO")
@Data
public class InspectionObjectSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10134")
    private Long id;

    @Schema(description = "企业名称", example = "赵六")
    private String entName;

    @Schema(description = "统一社会信用代码")
    private String creditCode;

    @Schema(description = "法定代表人")
    private String legalPerson;

    @Schema(description = "注册地址")
    private String regAddress;

    @Schema(description = "经营范围")
    private String businessScope;

    @Schema(description = "行业类型", example = "1")
    private String industryType;

    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

}