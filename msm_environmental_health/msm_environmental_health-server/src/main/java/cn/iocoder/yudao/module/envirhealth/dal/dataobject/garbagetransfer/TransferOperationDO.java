package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 转运作业 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_transfer_operation")
@KeySequence("garbage_transfer_operation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferOperationDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 作业主键（UUID）
     */
    private String operationId;
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleId;
    /**
     * 关联sys_garbage_type.id
     */
    private String garbageTypeId;
    /**
     * 进站时间
     */
    private LocalDateTime entryTime;
    /**
     * 垃圾重量（单位：吨）
     */
    private BigDecimal garbageWeight;
    /**
     * 关联garbage_collection.collection_id
     */
    private String planId;
    /**
     * 核心设备状态，JSON
     */
    private String equipmentStatus;
    /**
     * 作业进度
     */
    private String progress;
    /**
     * 转运去向
     */
    private String destination;
    /**
     * 异常标记：是/否
     */
    private String abnormalIsAbnormal;
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