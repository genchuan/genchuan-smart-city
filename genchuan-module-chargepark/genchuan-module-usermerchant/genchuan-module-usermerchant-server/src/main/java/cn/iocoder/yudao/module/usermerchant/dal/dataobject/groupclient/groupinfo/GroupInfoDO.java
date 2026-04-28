package cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 集团信息 DO
 *
 * @author 亘川智城
 */
@TableName("group_info")
@KeySequence("group_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 集团名称，唯一
     */
    private String name;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系手机号
     */
    private String phone;
    /**
     * 集团类型：企业单位/事业单位/政府机构/其他
     */
    private String groupType;
    /**
     * 集团地址
     */
    private String address;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
    /**
     * 集团状态：待审核/正常/禁用/已驳回
     */
    private String status;
    /**
     * 账户余额
     */
    private BigDecimal walletBalance;
    /**
     * 审核人ID，关联system_user.id
     */
    private Long auditorId;
    /**
     * 审核意见
     */
    private String auditRemark;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}