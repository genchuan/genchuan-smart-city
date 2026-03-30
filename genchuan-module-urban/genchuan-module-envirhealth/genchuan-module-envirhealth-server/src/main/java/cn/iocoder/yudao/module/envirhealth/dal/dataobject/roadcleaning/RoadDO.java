package cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

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
     * 道路主键（UUID）
     */
    private String roadId;
    /**
     * 道路名称
     */
    private String roadName;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 道路等级
     */
    private String roadLevel;
    /**
     * 长度，单位：公里
     */
    private BigDecimal length;
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