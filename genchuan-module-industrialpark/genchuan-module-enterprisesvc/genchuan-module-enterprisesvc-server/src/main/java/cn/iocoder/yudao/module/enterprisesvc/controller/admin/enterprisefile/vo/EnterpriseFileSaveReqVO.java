package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 企业档案新增/修改 Request VO")
@Data
public class EnterpriseFileSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "企业名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "企业名称不能为空")
    private String enterpriseName;

    @Schema(description = "统一社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统一社会信用代码不能为空")
    private String creditCode;

    @Schema(description = "注册地址")
    private String registerAddr;

    @Schema(description = "企业类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "企业类型不能为空")
    private String enterpriseType;

    @Schema(description = "企业规模", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "企业规模不能为空")
    private String enterpriseScale;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "档案状态不能为空")
    private String fileStatus;

    @Schema(description = "员工总数")
    private Integer staffCount;

    @Schema(description = "审核人账号")
    private String checkUser;

    @Schema(description = "审核通过率")
    private BigDecimal checkRate;

    @Schema(description = "操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}