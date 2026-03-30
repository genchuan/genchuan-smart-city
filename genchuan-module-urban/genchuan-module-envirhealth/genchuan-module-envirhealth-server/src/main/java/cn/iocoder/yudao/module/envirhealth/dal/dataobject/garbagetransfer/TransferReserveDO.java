package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 进站预约 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_transfer_reserve")
@KeySequence("garbage_transfer_reserve_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferReserveDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 预约主键（UUID）
     */
    private String reserveId;
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleId;
    /**
     * 关联sys_garbage_type.id
     */
    private String garbageTypeId;
    /**
     * 预计进站时间
     */
    private LocalDateTime expectedTime;
    /**
     * 垃圾重量（单位：吨）
     */
    private BigDecimal garbageWeight;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 预约状态：待排序/已排序/已进站
     */
    private String reserveStatus;
    /**
     * 排序序号
     */
    private Integer sortNo;
    /**
     * 创建时间（业务字段）
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 转运站编号
     */
    private String transferId;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;

}