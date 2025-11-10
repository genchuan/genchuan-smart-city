package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicedataqualck;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备数据质量检查 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_data_qual_ck")
@KeySequence("sys_device_data_qual_ck_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDataQualCkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 质量检查ID
     */
    private String qualCkId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 检查批次
     */
    private String ckBatch;
    /**
     * 检查时间
     */
    private LocalDateTime ckTime;
    /**
     * 检查项
     */
    private String ckItem;
    /**
     * 检查结果
     */
    private String ckResult;
    /**
     * 错误描述
     */
    private String errorDesc;
    /**
     * 整改建议
     */
    private String rectifySugg;
    /**
     * 检查人
     */
    private String ckUser;
    /**
     * 整改状态
     */
    private String rectifyStatus;
    /**
     * 整改时间
     */
    private LocalDateTime rectifyTime;
    /**
     * 整改人
     */
    private String rectifyUser;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}