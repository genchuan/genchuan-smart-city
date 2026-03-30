package cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.add;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddPunishNoticeReq {

    @Schema(description = "[处罚复审台账ID]")
    @NotNull(message = "[处罚复审台账ID] 不能用")
    private Long punishReviewId; // 关联处罚复审台账ID

    //处罚复审台账获取
    @Schema(description = "[实际处罚金额]",hidden = true)
//    @NotNull(message = "[实际处罚金额] 不能空")
    private BigDecimal actualPunishAmt; // 实际处罚金额

    //后端弄
    @Schema(description = "[缴款期限]",hidden = true)
//    @NotNull(message = "[缴款期限] 不能用")
    private LocalDateTime payDeadline; // 缴款期限

//    // 以下字段用于生成HTML
//    private String entName;            // 企业名称
//    private String entUniCode;         // 企业统一社会信用代码/注册号
//    private String entAddress;         // 企业地址
//    private String legalPerson;        // 法定代表人/负责人
//    private String contactPhone;       // 联系电话
//    private String illegalBehavior;    // 违法行为描述
//    private String evidence;           // 证据说明
//    private String punishAmountChinese; // 大写金额
//    private String bankName;           // 缴款银行
//    private String bankAccount;        // 银行账户
//    private String reconsiderationAuthority; // 行政复议机关
//    private String litigationCourt;    // 法院
//    private String contactPerson;      // 联系人
//    private String contactAddress;     // 联系地址
}
