package cn.iocoder.yudao.module.data.dal.dataobject.monitorinstance;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 监测部件实例 DO
 *
 * @author zhucongquan
 */
@TableName("monitor_instance")
@KeySequence("monitor_instance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorInstanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 部件名称
     */
    private String name;
    /**
     * 18位标识码
     */
    private String uniqueCode;
    /**
     * 关联分类ID
     */
    private String categoryId;
    /**
     * 所属分类
     */
    private String categoryName;
    /**
     * 关联网格ID
     */
    private String gridId;
    /**
     * 所在网格
     */
    private String gridName;
    /**
     * 坐标信息
     */
    private String coordinate;
    /**
     * 运行状态
     */
    private String runStatus;
    /**
     * 协议类型
     */
    private String protocol;
    /**
     * 安装时间
     */
    private LocalDateTime installTime;
    /**
     * 校准周期（天）
     */
    private Integer calibrateCycle;
    /**
     * 下次校准时间
     */
    private LocalDateTime nextCalibrateTime;
    /**
     * 最后校准时间
     */
    private LocalDateTime lastCalibrateTime;
    /**
     * 最后数据质量校验时间
     */
    private LocalDateTime lastCheckTime;
    /**
     * 行政区划编码
     */
    private String areaCode;
    /**
     * 行政区划归属
     */
    private String areaName;
    /**
     * 关联管理部件ID
     */
    private String relatedPartId;
    /**
     * 关联管理部件
     */
    private String relatedPartName;
    /**
     * 校准日志
     */
    private String calibrateLog;
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