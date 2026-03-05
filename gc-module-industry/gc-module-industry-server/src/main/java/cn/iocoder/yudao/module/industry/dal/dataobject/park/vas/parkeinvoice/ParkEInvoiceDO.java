package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkeinvoice;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电子发票 DO
 *
 * @author lxs
 */
@TableName("park_e_invoice")
@KeySequence("park_e_invoice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkEInvoiceDO extends BaseDO {

    /**
     * [主键ID] 电子发票记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [发票号码] 电子发票唯一号码
     */
    private String invoiceNo;
    /**
     * [订单ID] 关联订单唯一标识
     */
    private Long orderId;
    /**
     * [订单类型] 订单业务类型
     */
    private String orderType;
    /**
     * [用户ID] 用户唯一标识
     */
    private Long userId;
    /**
     * [发票类型] 如：普通发票/增值税电子普通发票
     */
    private String invoiceType;
    /**
     * [发票抬头] 发票抬头信息
     */
    private String title;
    /**
     * [纳税人识别号] 纳税人识别号
     */
    private Long taxpayerId;
    /**
     * [发票金额] 发票开具金额
     */
    private BigDecimal amount;
    /**
     * [发票内容] 发票内容说明
     */
    private String invoiceContent;
    /**
     * [发票状态] 如：待开具/已开具/已红冲/已作废
     */
    private String status;
    /**
     * [开具时间] 发票实际开具时间
     */
    private LocalDateTime issueTime;
    /**
     * [发票PDF地址] 发票PDF存储地址
     */
    private String pdfUrl;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;
    /**
     * [备注] 电子发票相关备注说明
     */
    private String remark;

}
