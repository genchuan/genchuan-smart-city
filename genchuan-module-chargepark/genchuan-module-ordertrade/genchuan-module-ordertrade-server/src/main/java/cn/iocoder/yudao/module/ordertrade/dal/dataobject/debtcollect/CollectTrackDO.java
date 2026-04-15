package cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 追缴跟踪 DO
 * @author genchuan
 */
@TableName("collect_track")
@KeySequence("collect_track_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectTrackDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 追缴编号，唯一 */
    private String trackNo;

    /** 车牌 */
    private String plateNo;

    /** 追缴方式，字典：collect_track_collect_method */
    private String collectMethod;

    /** 追缴时间 */
    private LocalDateTime collectTime;

    /** 状态，字典：collect_track_status */
    private String status;

    /** 片区ID，关联system_dept */
    private Long areaId;

    /** 转派用户ID */
    private Long transferUserId;

    /** 追缴进度 */
    private String collectProgress;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
