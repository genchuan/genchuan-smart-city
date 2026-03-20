package cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 预警类型字典 DO
 *
 * @author 芋道源码
 */
@TableName("sys_alarm_type")
@KeySequence("sys_alarm_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 预警类型主键（UUID）
     */
    private String alarmTypeId;
    /**
     * 预警类型名称
     */
    private String alarmName;
    /**
     * 描述
     */
    private String description;
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