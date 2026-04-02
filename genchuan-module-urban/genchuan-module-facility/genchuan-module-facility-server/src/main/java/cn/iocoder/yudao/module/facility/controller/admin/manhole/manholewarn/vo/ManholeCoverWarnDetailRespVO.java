package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "井盖预警详情 - 响应")
public class ManholeCoverWarnDetailRespVO {

    @Schema(description = "预警主键ID")
    private String warnId;

    @Schema(description = "井盖唯一ID")
    private String coverId;

    @Schema(description = "井盖编码")
    private String coverCode;

    @Schema(description = "井盖名称")
    private String coverName;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "预警类型")
    private Integer warnType;

    @Schema(description = "预警类型名称")
    private String warnTypeName;

    @Schema(description = "预警级别")
    private Integer warnLevel;

    @Schema(description = "预警级别名称")
    private String warnLevelName;

    @Schema(description = "预警指标值")
    private String warnValue;

    @Schema(description = "指标阈值")
    private String thresholdValue;

    @Schema(description = "预警发生时间")
    private String warnTime;

    @Schema(description = "报警记录")
    private AlarmRecord alarmRecord;

    @Schema(description = "处置记录")
    private HandleRecord handleRecord;

    @Schema(description = "预警状态")
    private Integer warnStatus;

    @Schema(description = "预警状态名称")
    private String warnStatusName;

    @Schema(description = "区块链存证哈希")
    private String chainHash;

    @Schema(description = "租户ID")
    private String tenantId;

    // ================= 内部类 =================
    @Data
    public static class AlarmRecord {
        @Schema(description = "报警方式")
        private List<Integer> alarmType;
        @Schema(description = "报警时间")
        private String alarmTime;
        @Schema(description = "接收人")
        private List<String> alarmRecipient;
    }

    @Data
    public static class HandleRecord {
        @Schema(description = "处理人ID")
        private String handleUserId;
        @Schema(description = "处理人姓名")
        private String handleUserName;
        @Schema(description = "处理时间")
        private String handleTime;
        @Schema(description = "处理备注")
        private String handleRemark;
    }
}
