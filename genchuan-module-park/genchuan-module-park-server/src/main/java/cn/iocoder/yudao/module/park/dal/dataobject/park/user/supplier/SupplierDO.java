package cn.iocoder.yudao.module.park.dal.dataobject.park.user.supplier;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 供应商 DO
 *
 * @author 亘川智城
 */
@TableName("park_supplier")
@KeySequence("park_supplier_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [供应商名称]
     */
    private String supplierName;
    /**
     * [联系人]
     */
    private String contactPerson;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [地址]
     */
    private String address;
    /**
     * [经营范围]
     */
    private String businessScope;
    /**
     * [资质证明] JSON格式varchar
     */
    private String qualification;
    /**
     * [状态] 如:合作中/暂停合作/已终止
     */
    private String status;
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
