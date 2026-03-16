package cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 企业信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EnterpriseInformationPageReqVO extends PageParam {

    @Schema(description = "[企业名称]", example = "赵六")
    private String enterpriseName;

    @Schema(description = "[统一社会信用代码]")
    private String creditCode;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[注册地址]")
    private String registerAddress;

    @Schema(description = "[所属行业]", example = "2")
    private String industryType;

    @Schema(description = "[管理员账号ID] 关联park_user.id", example = "31267")
    private Long adminId;

    @Schema(description = "[代付规则ID] 关联park_payment_proxy.proxy_id，可为NULL", example = "29660")
    private Long proxyId;

    @Schema(description = "[认证状态] 如:未认证/已认证", example = "2")
    private String certStatus;

    @Schema(description = "[创建时间] 注册时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
