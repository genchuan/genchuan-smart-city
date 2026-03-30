package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

/**
 * 垃圾转运站 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_transfer")
@KeySequence("garbage_transfer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarbageTransferDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 转运站主键（UUID）
     */
    private String transferId;
    /**
     * 转运站名称
     */
    private String name;
    /**
     * 转运站位置
     */
    private String location;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 核心设备IDs，JSON
     */
    private String equipmentIds;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 日转运量（单位：吨）
     */
    private BigDecimal dailyTransferVolume;
    /**
     * 设备正常运行率
     */
    private BigDecimal equipmentRate;
    /**
     * 环境达标率
     */
    private BigDecimal environmentRate;
    /**
     * 预警未处理数
     */
    private Integer unhandledAlarmCount;
    /**
     * 设备待维护数
     */
    private Integer pendingMaintenanceCount;
    /**
     * 实时环境数据，JSON
     */
    private String environmentData;
    /**
     * 流程状态
     */
    private String progressStatus;
    /**
     * 预约编号
     */
    private Long reserveId;
    /**
     * 作业编号
     */
    private Long operationId;
    /**
     * 预警编号
     */
    private Long alarmId;
    /**
     * 维护编号
     */
    private Long maintenanceId;
}