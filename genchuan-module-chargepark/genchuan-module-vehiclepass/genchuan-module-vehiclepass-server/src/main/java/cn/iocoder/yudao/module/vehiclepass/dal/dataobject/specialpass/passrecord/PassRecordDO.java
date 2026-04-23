package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.passrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 放行记录 DO
 *
 * @author 亘川智城
 */
@TableName("pass_record")
@KeySequence("pass_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassRecordDO extends BaseDO {

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
     * 放行原因：人工开闸 / 特殊车辆 / 其他，关联字典：pass_record_pass_reason
     */
    private String passReason;
    /**
     * 放行时间
     */
    private LocalDateTime passTime;
    /**
     * 抓拍图片地址
     */
    private String imageUrl;
    /**
     * 状态：正常记录 / 异常记录，关联字典：pass_record_status
     */
    private String status;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 操作人ID，关联芋道用户表 system_user
     */
    private Long operatorId;
    /**
     * 操作时间
     */
    private LocalDateTime operatorTime;
    /**
     * 核查结果
     */
    private String checkResult;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}