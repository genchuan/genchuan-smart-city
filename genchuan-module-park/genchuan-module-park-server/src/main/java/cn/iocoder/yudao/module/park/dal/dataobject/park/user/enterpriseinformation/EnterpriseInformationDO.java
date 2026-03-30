package cn.iocoder.yudao.module.park.dal.dataobject.park.user.enterpriseinformation;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 企业信息 DO
 *
 * @author 亘川智城
 */
@TableName("park_enterprise_information")
@KeySequence("park_enterprise_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseInformationDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [企业名称]
     */
    private String enterpriseName;
    /**
     * [统一社会信用代码]
     */
    private String creditCode;
    /**
     * [联系人]
     */
    private String contactPerson;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [注册地址]
     */
    private String registerAddress;
    /**
     * [所属行业]
     */
    private String industryType;
    /**
     * [管理员账号ID] 关联park_user.id
     */
    private Long adminId;
    /**
     * [代付规则ID] 关联park_payment_proxy.proxy_id，可为NULL
     */
    private Long proxyId;
    /**
     * [认证状态] 如:未认证/已认证
     */
    private String certStatus;
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
