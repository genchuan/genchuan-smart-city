package cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * AI告警消息 DO
 *
 * @author 亘川智城
 */
@TableName("ai_alert_message")
@KeySequence("ai_alert_message_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiAlertMessageDO extends BaseDO {

    /**
     * [主键ID] 主键，告警记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [设备关联用户ID列表] JSON格式存储的用户ID列表
     */
    private String userIds;
    /**
     * [场景实例ID] 场景实例ID
     */
    private String sceneId;
    /**
     * [功能算法编码] 功能算法编码
     */
    private String aiAbilityCode;
    /**
     * [告警类型] 告警类型
     */
    private Integer alertType;
    /**
     * [消息产生时间] 消息产生时间(特别注意）
     */
    private LocalDateTime alertCreateTime;
    /**
     * [设备编码] 设备编码
     */
    private String deviceCode;
    /**
     * [功能标识] 默认为1
     */
    private Integer featureId;
    /**
     * [消息来源] 如：1-端侧/2-云化/3-云侧/6-盒子
     */
    private Integer alertSource;
    /**
     * [图片地址] 通用图片地址或视频地址（人脸布控和车牌布控时为空），时光缩影下为视频下载地址
     */
    private String srcUrl;
    /**
     * [图片刷新token] 	通用图片刷新token（人脸布控和车牌布控时为空）时光缩影下为视频刷新token
     */
    private String srcToken;
    /**
     * [设备手机号] 设备手机号(盒子类消息为空)
     */
    private String deviceAccount;
    /**
     * [消息版本] 消息版本
     */
    private String msgVersion;
    /**
     * [平台告警ID] 能力开放平台告警ID	（可能重复）
     */
    private Long alertId;
    /**
     * [AI平台消息ID] AI平台唯一消息ID
     */
    private String aiPlatformMsgId;
    /**
     * [检测框] 所有云侧都支持画框。这个字段默认存在，端侧（云化和端侧）不支持画框的能力为：口罩识别、电动车识别、火情告警、区域入侵、设备巡检、人脸布控、车牌识别、静态客流统计、动态客流统计、客流统计（云眼专用）、车辆占道违停
     */
    private String bbox;
    /**
     * [重复告警] 1:重复告警 0:非重复告警
     */
    private Integer repeatAlarm;
    /**
     * [离岗时间] 作用：设置的离岗时间，超过这个时间触发离岗告警，单位（秒）
     */
    private Integer leaveTime;
    /**
     * [结束时间] 一天内的结束时间，格式为hh:mm，比如15:00
     */
    private String timeSlotEnd;
    /**
     * [间隔时间]
     */
    private Integer intervalTime;
    /**
     * [完整告警参数JSON] 原始alertParams数据
     */
    private String alertParams;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
