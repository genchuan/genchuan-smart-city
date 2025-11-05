package cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtdatacfg;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件数据配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_evt_data_cfg")
@KeySequence("sys_mon_evt_data_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonEvtDataCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 配置ID，唯一编码，UUID生成
     */
    private String monEvtDataId;
    /**
     * 事件分类ID，关联事件分类表小类ID，监测事件分类配置表(sys_mon_evt_cat)
     */
    private String evtCatId;
    /**
     * 事件分类名称，与分类ID同步，自动填充，监测事件分类配置表(sys_mon_evt_cat)
     */
    private String evtCatName;
    /**
     * 字段名称，属性字段名称，参照GB/T XXXXX.7
     */
    private String fieldName;
    /**
     * 字段代码，蛇形命名法，与数据库字段一致
     */
    private String fieldCode;
    /**
     * 字段类型，char/varchar/int等，字段数据类型
     */
    private String fieldType;
    /**
     * 字段长度，18/32/10等，字段长度及精度
     */
    private String fieldLength;
    /**
     * 约束条件，必选/可选，标识字段是否必选
     */
    private String constraintType;
    /**
     * 值域范围，字段允许值域，如“一级/二级/三级”
     */
    private String valueRange;
    /**
     * 字段说明，字段填写说明，如“事发位置需含具体路名”
     */
    private String fieldDesc;
    /**
     * 创建人，填写创建人账号，用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，填写更新人账号，用户信息表(sys_user)
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留字段，用于存储配置额外属性（如“字段校验规则”）
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留字段，用于存储配置额外属性（如“字段校验规则”）
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留通用字段，存储额外配置信息
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留通用字段，存储额外配置信息
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}