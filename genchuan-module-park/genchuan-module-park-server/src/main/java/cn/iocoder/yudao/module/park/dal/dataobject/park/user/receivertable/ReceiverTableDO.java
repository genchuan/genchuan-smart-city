package cn.iocoder.yudao.module.park.dal.dataobject.park.user.receivertable;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 接收方 DO
 *
 * @author 亘川智城
 */
@TableName("park_receiver_table")
@KeySequence("park_receiver_table_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverTableDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [接收方名称]
     */
    private String receiverName;
    /**
     * [接收方类型] 如:商户/企业/政府部门
     */
    private String receiverType;
    /**
     * [关联ID] 商户ID/企业ID/政府部门ID
     */
    private Long relatedId;
    /**
     * [账户名称]
     */
    private String accountName;
    /**
     * [开户银行]
     */
    private String bankName;
    /**
     * [银行账号]
     */
    private String bankAccount;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
