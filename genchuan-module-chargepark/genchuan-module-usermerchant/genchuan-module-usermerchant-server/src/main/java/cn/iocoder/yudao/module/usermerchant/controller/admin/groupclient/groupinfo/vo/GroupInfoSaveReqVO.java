package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 集团信息新增/修改 Request VO")
@Data
public class GroupInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16794")
    private Long id;

    @Schema(description = "集团名称，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "集团名称，唯一不能为空")
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系人不能为空")
    private String contact;

    @Schema(description = "联系手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系手机号不能为空")
    private String phone;

    @Schema(description = "集团类型：企业单位/事业单位/政府机构/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "集团类型：企业单位/事业单位/政府机构/其他不能为空")
    private String groupType;

    @Schema(description = "集团地址")
    private String address;

    @Schema(description = "注册时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "注册时间不能为空")
    private LocalDateTime registerTime;

    @Schema(description = "集团状态：待审核/正常/禁用/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "集团状态：待审核/正常/禁用/已驳回不能为空")
    private String status;

    @Schema(description = "账户余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户余额不能为空")
    private BigDecimal walletBalance;

    @Schema(description = "审核人ID，关联system_user.id", example = "29669")
    private Long auditorId;

    @Schema(description = "审核意见", example = "随便")
    private String auditRemark;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}