package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.enterrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 入场记录 DO
 *
 * @author 亘川智城
 */
@TableName("enter_record")
@KeySequence("enter_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车牌
     */
    private String plateNo;
    /**
     * 车牌颜色：蓝牌/黄牌/绿牌/其他，关联字典enter_record_plate_color
     */
    private String plateColor;
    /**
     * 车位编号
     */
    private String spaceNo;
    /**
     * 入场时间
     */
    private LocalDateTime enterTime;
    /**
     * 记录类型：自动识别/人工补录，关联字典enter_record_record_type
     */
    private String recordType;
    /**
     * 记录状态：正常记录/异常记录，关联字典enter_record_status
     */
    private String status;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 佐证图片地址
     */
    private String proofImage;
    /**
     * 修正日志标记：0-未修正 1-已修正
     */
    private Boolean isCorrected;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}