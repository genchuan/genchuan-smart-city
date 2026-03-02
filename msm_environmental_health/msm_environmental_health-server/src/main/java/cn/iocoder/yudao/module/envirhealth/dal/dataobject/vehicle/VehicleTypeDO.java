package cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆类型字典 DO
 *
 * @author 芋道源码
 */
@TableName("sys_vehicle_type")
@KeySequence("sys_vehicle_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysVehicleTypeId;
    /**
     * 类型名称（可选值：清运车/清扫车/洒水车/洗扫车/垃圾转运车/吸污车/巡查车）
     */
    private String name;
    /**
     * 类型编码
     */
    private String code;
    /**
     * 状态（可选值：0-禁用/1-启用）
     */
    private Integer status;
    /**
     * 类型描述
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