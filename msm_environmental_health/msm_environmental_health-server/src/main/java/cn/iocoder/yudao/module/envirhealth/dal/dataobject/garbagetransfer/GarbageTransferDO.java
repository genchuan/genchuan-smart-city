package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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