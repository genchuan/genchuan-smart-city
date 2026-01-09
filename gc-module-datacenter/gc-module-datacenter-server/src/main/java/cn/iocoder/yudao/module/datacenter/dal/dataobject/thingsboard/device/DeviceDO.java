package cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.device;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
        import java.util.*;
        import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
        import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备 DO
 *
 * @author 芋道源码
 */
@TableName("tb_device_info")
@KeySequence("device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDO extends BaseDO {

    /**
     * 设备ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * TB租户
     */
    private String tbTenantId;
    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备类型
     */
    private String type;
    /**
     * 标签
     */
    private String label;
    /**
     * 设备实体ID
     */
    private String deviceProfileId;
    /**
     * 设备配置
     */
    private byte[] deviceData;
    /**
     * firmware
     */
    private String firmwareId;
    /**
     * software
     */
    private String softwareId;
    /**
     * Additional
     */
    private String externalId;
    /**
     * 版本
     */
    private Long version;

    private String additionalInfo;

    private Boolean active;

    private String deviceProfileName;

    private String customerTitle;

    private Boolean customerIsPublic;

    @TableField(exist = false)
    private Object additionalInfoJson;

    /**
     * 属性列表（JSON格式）
     */
    private String attributes;

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

    @TableField(exist = false)
    private Object attributesJson;
}
