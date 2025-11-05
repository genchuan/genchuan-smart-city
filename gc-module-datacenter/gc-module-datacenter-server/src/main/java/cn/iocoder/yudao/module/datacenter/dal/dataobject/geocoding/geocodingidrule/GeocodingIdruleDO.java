package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingidrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 标识码编码规则配置 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_idrule")
@KeySequence("gc_geocoding_idrule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingIdruleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID
     */
    private String idRuleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 县级行政代码位数
     */
    private Integer adminCodeLength;
    /**
     * 街道代码位数
     */
    private Integer streetCodeLength;
    /**
     * 图层代码位数
     */
    private Integer layerCodeLength;
    /**
     * 顺序码位数
     */
    private Integer seqCodeLength;
    /**
     * 顺序码生成规则
     */
    private String seqGenRule;
    /**
     * 启用状态(0-禁用,1-启用)
     */
    private Integer enableStatus;
    /**
     * 配置人
     */
    private String configUser;
    /**
     * 配置时间
     */
    private LocalDateTime configTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分类扩展字段1
     */
    private String extCategory1;
    /**
     * 分类扩展字段2
     */
    private String extCategory2;
    /**
     * 分类扩展字段3
     */
    private String extCategory3;

}