package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 能耗采集新增/修改 Request VO")
@Data
public class EnergyCollectSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30458")
    private Long id;

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "设备类型：电表/水表/气表", example = "2")
    private String deviceType;

    @Schema(description = "能耗类型：电/水/气/热", example = "1")
    private String energyType;

    @Schema(description = "采集时间")
    private LocalDateTime collectTime;

    @Schema(description = "采集状态：采集正常/采集异常", example = "2")
    private String collectStatus;

    @Schema(description = "能耗数值")
    private BigDecimal energyValue;

    @Schema(description = "采集频率，单位分钟")
    private Integer collectFreq;

    @Schema(description = "异常次数", example = "26647")
    private Integer exceptionCount;

    @Schema(description = "操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}