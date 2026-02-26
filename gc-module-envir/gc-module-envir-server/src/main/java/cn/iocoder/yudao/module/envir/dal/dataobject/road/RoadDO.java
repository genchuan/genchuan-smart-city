package cn.iocoder.yudao.module.envir.dal.dataobject.road;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 道路 DO
 *
 * @author 芋道源码
 */
@TableName("sys_road")
@KeySequence("sys_road_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysRoadId;
    /**
     * 道路名称
     */
    private String name;
    /**
     * 道路编码
     */
    private String code;
    /**
     * 道路长度
     */
    private BigDecimal length;
    /**
     * 道路宽度
     */
    private BigDecimal width;
    /**
     * 道路类型
     */
    private String roadType;
    /**
     * 状态：启用/禁用
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
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