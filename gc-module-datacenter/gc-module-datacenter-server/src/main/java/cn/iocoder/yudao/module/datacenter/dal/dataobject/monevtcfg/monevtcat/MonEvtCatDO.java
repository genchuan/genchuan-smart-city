package cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcat;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件分类配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_evt_cat")
@KeySequence("sys_mon_evt_cat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonEvtCatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类ID，唯一编码，UUID生成
     */
    private String monEvtCatId;
    /**
     * 父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测事件分类配置表（sys_mon_evt_cat）
     */
    private String parentEvtId;
    /**
     * 分类层级，标识分类所处层级，1 (大类)/2 (中类)/3 (小类)
     */
    private String evtCatLevel;
    /**
     * 分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排
     */
    private String evtCatCode;
    /**
     * 分类名称，参照GB/T XXXXX.6标准名称，扩展类标注 “自定义”
     */
    private String evtCatName;
    /**
     * 分类说明，描述事件范围，如“燃气泄漏：燃气从管道或设备泄漏的事件”
     */
    private String evtCatDesc;
    /**
     * 启用状态，标识分类是否可用，默认1
     */
    private String enableStatus;
    /**
     * 创建人，填写创建人账号，用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，填写更新人账号，用户信息表(sys_user)
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留字段，用于存储分类额外属性（如“事件响应时效”）
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留字段，用于存储分类额外属性（如“事件响应时效”）
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