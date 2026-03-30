package cn.iocoder.yudao.module.evaluate.dal.dataobject.area;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 区域编码 DO
 *
 * @author 亘川智城
 */
@TableName("sys_area")
@KeySequence("sys_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 区域编码（业务主键）
     */
    private String areaCode;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 上级区域编码（关联sys_area.area_code）
     */
    private String parentCode;
    /**
     * 区域层级（1-省级，2-市级，3-区级/县级等）
     */
    private Integer level;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
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