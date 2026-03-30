package cn.iocoder.yudao.module.data.dal.dataobject.eventinstance;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 监测事件实例 DO
 *
 * @author zhucongquan
 */
@TableName("event_instance")
@KeySequence("event_instance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInstanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 事件名称
     */
    private String name;
    /**
     * 18位标识码
     */
    private String uniqueCode;
    /**
     * 所属分类ID
     */
    private String categoryId;
    /**
     * 所属分类
     */
    private String categoryName;
    /**
     * 关联监测实例ID
     */
    private String monitorId;
    /**
     * 监测实例名称
     */
    private String monitorName;
    /**
     * 坐标信息
     */
    private String coordinate;
    /**
     * 事件等级
     */
    private String eventLevel;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 状态
     */
    private String status;
    /**
     * 行政区划代码
     */
    private String areaCode;
    /**
     * 行政区划归属
     */
    private String areaName;
    /**
     * 关联管理事项ID
     */
    private String matterId;
    /**
     * 关联管理事项
     */
    private String matterName;
    /**
     * 预警方式
     */
    private String warningWay;
    /**
     * 上报来源
     */
    private String reportSource;
    /**
     * 处置日志
     */
    private String disposeLog;
    /**
     * 处置人
     */
    private String handler;
    /**
     * 处置时间
     */
    private LocalDateTime dealTime;
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
     * 创建人
     */
    private String creator;

}