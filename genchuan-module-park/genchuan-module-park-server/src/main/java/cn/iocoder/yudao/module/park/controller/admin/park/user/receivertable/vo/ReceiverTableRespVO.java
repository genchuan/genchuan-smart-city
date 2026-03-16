package cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 接收方 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ReceiverTableRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "17419")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[接收方名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[接收方名称]")
    private String receiverName;

    @Schema(description = "[接收方类型] 如:商户/企业/政府部门", example = "2")
    @ExcelProperty("[接收方类型] 如:商户/企业/政府部门")
    private String receiverType;

    @Schema(description = "[关联ID] 商户ID/企业ID/政府部门ID", example = "16652")
    @ExcelProperty("[关联ID] 商户ID/企业ID/政府部门ID")
    private Long relatedId;

    @Schema(description = "[账户名称]", example = "张三")
    @ExcelProperty("[账户名称]")
    private String accountName;

    @Schema(description = "[开户银行]", example = "赵六")
    @ExcelProperty("[开户银行]")
    private String bankName;

    @Schema(description = "[银行账号]", example = "11301")
    @ExcelProperty("[银行账号]")
    private String bankAccount;

    @Schema(description = "[联系电话]")
    @ExcelProperty("[联系电话]")
    private String contactPhone;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你猜")
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
