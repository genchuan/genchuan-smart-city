package cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 接收方分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiverTablePageReqVO extends PageParam {

    @Schema(description = "[接收方名称]", example = "王五")
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

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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
