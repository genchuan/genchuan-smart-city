package cn.iocoder.yudao.module.evaluate.dal.dataobject.scope;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 范围字典 DO
 *
 * @author 亘川智城
 */
@TableName("sys_scope")
@KeySequence("sys_scope_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScopeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 范围ID（UUID）
     */
    private String scopeId;
    /**
     * 范围名称
     */
    private String name;
    /**
     * 范围编码
     */
    private String code;
    /**
     * 范围描述
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