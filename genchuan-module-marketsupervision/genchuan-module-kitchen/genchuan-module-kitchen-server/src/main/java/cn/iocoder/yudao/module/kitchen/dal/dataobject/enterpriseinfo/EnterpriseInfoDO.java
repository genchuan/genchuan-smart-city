package cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 企业信息 DO
 *
 * @author 亘川智城
 */
@TableName("enterprise_info")
@KeySequence("enterprise_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseInfoDO extends BaseDO {

    /**
     * [主键ID] 企业信息唯一标识
     */
    @TableId
    private Long id;
    /**
     * [企业编码] 唯一编码
     */
    private String entCode;
    /**
     * [企业名称] 企业全称
     */
    private String entName;
    /**
     * [所属区域ID] 关联area_dict.id
     */
    private Long areaId;
    /**
     * [地区名] 冗余的地区名称
     */
    private String areaName;
    /**
     * [企业类型ID] 关联ent_type_dict.id
     */
    private Long entTypeId;
    /**
     * [详细地址] 企业注册或经营地址
     */
    private String address;
    /**
     * [联系人] 企业联系人姓名
     */
    private String contactPerson;
    /**
     * [联系电话] 企业联系电话
     */
    private String contactPhone;
    /**
     * [企业经营状态] 如：正常/停业/注销
     */
    private String status;
    /**
     * [通用扩展字段1] 预留
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留
     */
    private String extCommon4;

}
