package cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 企业信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnterpriseInformationRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "31884")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[企业名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[企业名称]")
    private String enterpriseName;

    @Schema(description = "[统一社会信用代码]")
    @ExcelProperty("[统一社会信用代码]")
    private String creditCode;

    @Schema(description = "[联系人]")
    @ExcelProperty("[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    @ExcelProperty("[联系电话]")
    private String contactPhone;

    @Schema(description = "[注册地址]")
    @ExcelProperty("[注册地址]")
    private String registerAddress;

    @Schema(description = "[所属行业]", example = "2")
    @ExcelProperty("[所属行业]")
    private String industryType;

    @Schema(description = "[管理员账号ID] 关联park_user.id", example = "31267")
    @ExcelProperty("[管理员账号ID] 关联park_user.id")
    private Long adminId;

    @Schema(description = "[代付规则ID] 关联park_payment_proxy.proxy_id，可为NULL", example = "29660")
    @ExcelProperty("[代付规则ID] 关联park_payment_proxy.proxy_id，可为NULL")
    private Long proxyId;

    @Schema(description = "[认证状态] 如:未认证/已认证", example = "2")
    @ExcelProperty("[认证状态] 如:未认证/已认证")
    private String certStatus;

    @Schema(description = "[创建时间] 注册时间")
    @ExcelProperty("[创建时间] 注册时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你说的对")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
