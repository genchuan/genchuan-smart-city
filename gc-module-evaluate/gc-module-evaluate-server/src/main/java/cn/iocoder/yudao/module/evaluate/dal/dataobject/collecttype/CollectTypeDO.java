package cn.iocoder.yudao.module.evaluate.dal.dataobject.collecttype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采集方式字典 DO
 *
 * @author 亘川智城
 */
@TableName("sys_collect_type")
@KeySequence("sys_collect_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 采集方式ID（UUID）
     */
    private String typeId;
    /**
     * 采集方式名称
     */
    private String name;
    /**
     * 采集方式编码
     */
    private String code;
    /**
     * 采集方式描述
     */
    private String desc;
    /**
     * 业务创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}