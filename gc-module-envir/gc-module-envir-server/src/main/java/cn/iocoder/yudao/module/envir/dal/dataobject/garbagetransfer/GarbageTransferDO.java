package cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
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
     * 业务主键（UUID）
     */
    private String garbageTransferId;
    /**
     * 转运站名称
     */
    private String name;
    /**
     * 转运站位置（含经纬度）
     */
    private String location;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 核心设备（关联sys_equipment.sys_equipment_id，多个用逗号分隔）
     */
    private String equipmentIds;
    /**
     * 环境监测阈值
     */
    private String environmentThreshold;
    /**
     * 实时环境监测数据（含温度/湿度/异味浓度）
     */
    private String environmentData;
    /**
     * 转运去向（关联处理单位表ID）
     */
    private String transferDestination;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerId;
    /**
     * 运营状态（关联sys_operation_status.sys_operation_status_id）
     */
    private String operationStatusId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String abnormalCreateBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 日转运量
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