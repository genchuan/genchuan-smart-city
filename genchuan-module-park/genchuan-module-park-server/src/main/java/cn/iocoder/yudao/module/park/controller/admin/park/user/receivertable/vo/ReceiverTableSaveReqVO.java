package cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 接收方新增/修改 Request VO")
@Data
public class ReceiverTableSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "17419")
    private Long id;

    @Schema(description = "[接收方名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "[接收方名称]不能为空")
    private String receiverName;

    @Schema(description = "[接收方类型] 如:商户/企业/政府部门", example = "2")
    private String receiverType;

    @Schema(description = "[关联ID] 商户ID/企业ID/政府部门ID", example = "16652")
    private Long relatedId;

    @Schema(description = "[账户名称]", example = "张三")
    private String accountName;

    @Schema(description = "[开户银行]", example = "赵六")
    private String bankName;

    @Schema(description = "[银行账号]", example = "11301")
    private String bankAccount;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[备注]", example = "你猜")
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
