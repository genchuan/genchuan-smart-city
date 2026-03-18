package cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PunishNoticeTemplateReqVO {


    @Schema(description = "[整改通知书编号]", requiredMode = Schema.RequiredMode.REQUIRED, example = "福头企业")
    @NotEmpty(message = "[整改通知书编号] 不能为空")
    private String noticeCode;                  // 通知书编号


    //punish_notice获取punish_review_ledger获取enterprise_info，然后enterprise_info有以下
    private String entName;                     // 企业名称
    private String entCode;                  // 企业统一社会信用代码/注册号
    private String entAddress;                  // 企业地址
    private String legalPerson;                 // 法定代表人/负责人
    private String contactPhone;                // 联系电话

    //punish_notice获取punish_review_ledger获取illegal_type_dict，有以下
    private String illegalBehavior;             // 违法行为描述
    private String evidence="整改通知书";                    // 证据说明

    //punish_review_ledger有以下
    private BigDecimal punishAmount;            // 罚款金额

    private String punishAmountChinese;         // 大写金额

    private String bankName = "泉州丰泽区农业银行";  // 缴款银行
    private String bankAccount = "3500123456789012"; // 银行账户


    private String punishSupervisionContactPerson="张三和";               // 处罚监管联系人
    private String punishSupervisionContactAddress = "泉州市丰泽区东街路88号"; // 处罚监管联系地址
    private String punishSupervisionContactPhone = "0591-12372923"; // 处罚监管联系地址

    private LocalDateTime issueTime;            // 下发时间

    // 市场监管局相关
    @Schema(description = "[市场监督管理局名称]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String marketSupervisionBureauName = "泉州市丰泽区市场监督管理局";

    @Schema(description = "[监督的市名]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String regionAbbreviation = "泉州";

    @Schema(description = "[行政复议机关]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String reconsiderationAuthority = "泉州市市场监督管理局行政复议科";

    @Schema(description = "[行政诉讼法院]", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String litigationCourt = "泉州市丰泽区人民法院";
}
