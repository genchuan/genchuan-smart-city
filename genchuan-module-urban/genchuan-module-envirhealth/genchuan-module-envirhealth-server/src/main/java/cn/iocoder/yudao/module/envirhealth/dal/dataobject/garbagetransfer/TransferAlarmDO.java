package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 转运站预警 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_transfer_alarm")
@KeySequence("garbage_transfer_alarm_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferAlarmDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 预警主键（UUID）
     */
    private String alarmId;
    /**
     * 关联garbage_transfer.transfer_id
     */
    private String transferId;
    /**
     * 关联sys_alarm_type.id
     */
    private String alarmTypeId;
    /**
     * 发生时间
     */
    private LocalDateTime alarmTime;
    /**
     * 预警内容
     */
    private String alarmContent;
    /**
     * 预警照片
     */
    private String alarmPhoto;
    /**
     * 关联设备/区域
     */
    private String relevantInfo;
    /**
     * 处置状态：待处置/处理中/已解除
     */
    private String handleStatus;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 超时提醒：是/否
     */
    private String abnormalIsTimeout;
    /**
     * 处置进度
     */
    private String handleProgress;
    /**
     * 处置结果
     */
    private String handleResult;
    /**
     * 佐证材料URL，JSON
     */
    private String proofMaterial;
    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
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