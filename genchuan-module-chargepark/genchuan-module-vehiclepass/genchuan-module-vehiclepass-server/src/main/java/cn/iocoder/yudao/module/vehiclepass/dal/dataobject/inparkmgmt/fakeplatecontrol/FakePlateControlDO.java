package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.fakeplatecontrol;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 套牌管控 DO
 *
 * @author 亘川智城
 */
@TableName("fake_plate_control")
@KeySequence("fake_plate_control_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FakePlateControlDO extends BaseDO {

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
     * 识别时间
     */
    private LocalDateTime identifyTime;
    /**
     * 匹配场景：同牌多停 / 车牌车型不匹配，关联字典fake_plate_control_match_scene
     */
    private String matchScene;
    /**
     * 处置状态：未处理 / 处理中 / 已关闭，关联字典fake_plate_control_status
     */
    private String status;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 处置人ID，关联system_user用户表
     */
    private Long handleUserId;
    /**
     * 处置时间
     */
    private LocalDateTime handleTime;
    /**
     * 处置进度
     */
    private String handleProgress;
    /**
     * 忽略理由
     */
    private String ignoreReason;
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