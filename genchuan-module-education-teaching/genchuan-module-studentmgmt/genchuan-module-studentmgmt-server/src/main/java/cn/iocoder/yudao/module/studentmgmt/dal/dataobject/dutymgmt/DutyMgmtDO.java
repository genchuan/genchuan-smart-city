package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 值班管理 DO
 *
 * @author 芋道源码
 */
@TableName("duty_mgmt")
@KeySequence("duty_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DutyMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 值班日期
     */
    private LocalDate dutyDate;
    /**
     * 值班人
     */
    private String dutyUser;
    /**
     * 打卡时间
     */
    private LocalDateTime checkInTime;
    /**
     * 打卡状态：未打卡/已打卡
     */
    private String checkInStatus;
    /**
     * 调班原因
     */
    private String transferReason;
    /**
     * 调班替代人
     */
    private String transferUser;
    /**
     * 调班状态：无/待审批/已通过/已驳回
     */
    private String transferStatus;
    /**
     * 出车事由
     */
    private String carReason;
    /**
     * 出车目的地
     */
    private String carDestination;
    /**
     * 出车状态：无/待审批/已通过
     */
    private String carStatus;
    /**
     * 值班记录
     */
    private String recordContent;
    /**
     * 记录上传时间
     */
    private LocalDateTime recordUploadTime;
    /**
     * 状态：待打卡/待调班审批/待出车审批/已完成
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}
