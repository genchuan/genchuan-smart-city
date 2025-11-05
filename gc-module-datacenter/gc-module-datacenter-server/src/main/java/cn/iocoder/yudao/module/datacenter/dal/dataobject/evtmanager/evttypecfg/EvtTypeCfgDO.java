package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evttypecfg;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件类型配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_type_cfg")
@KeySequence("biz_evt_type_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtTypeCfgDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 类型ID，唯一编码，UUID
     */
    private String evtTypeId;
    /**
     * 父类型ID，关联本表“事件类型ID”，大类父ID为“0”
     */
    private String parentTypeId;
    /**
     * 类型层级，1（大类）/2（小类）
     */
    private String typeLevel;
    /**
     * 类型编码，001 - 999（大类01 - 99、小类001 - 999，扩展类080 - 999倒排），同一父类型下编码唯一
     */
    private String typeCode;
    /**
     * 类型名称，标准名称，扩展类加“（自定义）”
     */
    private String typeName;
    /**
     * 类型说明，描述类型范围，如“占道经营：商户违规占用道路经营”
     */
    private String typeDesc;
    /**
     * 所属区域代码，关联行政区划表，限定区域适用
     */
    private String regionCode;
    /**
     * 所属区域名称，与区域代码同步
     */
    private String regionName;
    /**
     * 启用状态，1（启用）/0（禁用），默认1
     */
    private String enableStatus;
    /**
     * 创建人，创建人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，更新人账号，关联用户信息表(sys_user)
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“处置时限”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“处置时限”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储参考标准
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储参考标准
     */
    private String extCommon2;

}