package cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentasset;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备资产台账管理 DO
 *
 * @author zcq
 */
@TableName("gc_equipment_asset")
@KeySequence("gc_equipment_asset_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentAssetDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 设备编号
     */
    private String equipmentCode;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 型号
     */
    private String model;
    /**
     * 规格
     */
    private String specification;
    /**
     * 安装位置
     */
    private String installLocation;
    /**
     * 安装日期
     */
    private LocalDateTime installDate;
    /**
     * 生产厂家
     */
    private String manufacturer;
    /**
     * 维护记录
     */
    private String maintenanceRecord;

}