package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车系统用户新增/修改 Request VO")
@Data
public class ParkUserSaveReqVO {

    @Schema(description = "主键ID[系统用户唯一标识]", requiredMode = Schema.RequiredMode.REQUIRED, example = "27951")
    private Long id;

    @Schema(description = "登录账号[用户登录系统使用的账号名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "30747")
    @NotEmpty(message = "登录账号[用户登录系统使用的账号名称]不能为空")
    private String userAccount;

    @Schema(description = "手机号[加密手机号，用于用户联系与登录校验]")
    private String userPhone;

    @Schema(description = "用户类型[个人/企业/政府/运维人员/商户管理员]", example = "1")
    private String userType;

    @Schema(description = "身份证号[加密身份证号，仅个人用户使用]")
    private String idCard;

    @Schema(description = "企业名称[仅企业用户使用]", example = "赵六")
    private String enterpriseName;

    @Schema(description = "统一社会信用代码[仅企业用户使用]")
    private String enterpriseCode;

    @Schema(description = "政府部门[政府部门名称，仅政府用户使用]")
    private String govDepartment;

    @Schema(description = "认证状态[未认证/待审核/已认证/认证失败]", example = "1")
    private String certStatus;

    @Schema(description = "钱包余额[用户钱包可用余额]")
    private BigDecimal walletBalance;

    @Schema(description = "冻结余额[当前被冻结不可用的余额]")
    private BigDecimal freezeBalance;

    @Schema(description = "状态[正常/禁用]", example = "2")
    private String status;

    @Schema(description = "通用扩展字段1[预留扩展字段]")
    private String extCommon1;

    @Schema(description = "通用扩展字段2[预留扩展字段]")
    private String extCommon2;

    @Schema(description = "通用扩展字段3[预留扩展字段]")
    private String extCommon3;

    @Schema(description = "通用扩展字段4[预留扩展字段]")
    private String extCommon4;

    @Schema(description = "备注[用户相关备注说明]", example = "你说的对")
    private String remark;

}
