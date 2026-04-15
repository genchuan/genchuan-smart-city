package cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AI告警消息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiAlertMessagePageReqVO extends PageParam {

    @Schema(description = "[整改台账ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long rectifyReviewId;

    @Schema(description = "[设备关联用户ID列表] JSON格式存储的用户ID列表")
    private String userIds;

    @Schema(description = "[场景实例ID] 场景实例ID", example = "5141")
    private String sceneId;

    @Schema(description = "[功能算法编码] 功能算法编码")
    private String aiAbilityCode;

    @Schema(description = "[告警类型] 告警类型", example = "1")
    private Integer alertType;

    @Schema(description = "[消息产生时间] 消息产生时间(特别注意）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] alertCreateTime;

    @Schema(description = "[设备编码] 设备编码")
    private String deviceCode;

    @Schema(description = "[功能标识] 默认为1", example = "8585")
    private Integer featureId;

    @Schema(description = "[消息来源] 如：1-端侧/2-云化/3-云侧/6-盒子")
    private Integer alertSource;

    @Schema(description = "[消息来源] 如：1-端侧/2-云化/3-云侧/6-盒子")
    private String alertSourceName;

    @Schema(description = "[图片地址] 通用图片地址或视频地址（人脸布控和车牌布控时为空），时光缩影下为视频下载地址", example = "https://www.iocoder.cn")
    private String srcUrl;

    @Schema(description = "[图片刷新token] 	通用图片刷新token（人脸布控和车牌布控时为空）时光缩影下为视频刷新token")
    private String srcToken;

    @Schema(description = "[设备手机号] 设备手机号(盒子类消息为空)", example = "28212")
    private String deviceAccount;

    @Schema(description = "[消息版本] 消息版本")
    private String msgVersion;

    @Schema(description = "[平台告警ID] 能力开放平台告警ID	（可能重复）", example = "6455")
    private Long alertId;

    @Schema(description = "[AI平台消息ID] AI平台唯一消息ID", example = "6397")
    private String aiPlatformMsgId;

    @Schema(description = "[检测框] 所有云侧都支持画框。这个字段默认存在，端侧（云化和端侧）不支持画框的能力为：口罩识别、电动车识别、火情告警、区域入侵、设备巡检、人脸布控、车牌识别、静态客流统计、动态客流统计、客流统计（云眼专用）、车辆占道违停")
    private String bbox;

    @Schema(description = "[重复告警] 1:重复告警 0:非重复告警")
    private Integer repeatAlarm;

    @Schema(description = "[离岗时间] 作用：设置的离岗时间，超过这个时间触发离岗告警，单位（秒）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] leaveTime;

    @Schema(description = "[结束时间] 一天内的结束时间，格式为hh:mm，比如15:00")
    private String timeSlotEnd;

    @Schema(description = "[间隔时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] intervalTime;

    @Schema(description = "[完整告警参数JSON] 原始alertParams数据")
    private String alertParams;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
