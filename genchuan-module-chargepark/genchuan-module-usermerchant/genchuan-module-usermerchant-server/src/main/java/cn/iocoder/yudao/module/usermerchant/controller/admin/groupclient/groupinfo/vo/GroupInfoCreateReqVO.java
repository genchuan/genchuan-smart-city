package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 集团信息新增 Request VO")
@Data
public class GroupInfoCreateReqVO {

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
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime registerTime;

    @Schema(description = "集团状态：待审核/正常/禁用/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "集团状态：待审核/正常/禁用/已驳回不能为空")
    private String status;

    @Schema(description = "账户余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户余额不能为空")
    private BigDecimal walletBalance;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
