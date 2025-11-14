package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备数据质量检查分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceDataQualCkPageReqVO extends PageParam {

    @Schema(description = "质量检查ID")
    private String qualCkId;

    @Schema(description = "设备ID")
    private String deviceId;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备编码")
    private String deviceCode;

    @Schema(description = "检查批次")
    private String ckBatch;

    @Schema(description = "检查时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] ckTime;

    @Schema(description = "检查项")
    private String ckItem;

    @Schema(description = "检查结果")
    private String ckResult;

    @Schema(description = "错误描述")
    private String errorDesc;

    @Schema(description = "整改建议")
    private String rectifySugg;

    @Schema(description = "检查人")
    private String ckUser;

    @Schema(description = "整改状态")
    private String rectifyStatus;

    @Schema(description = "整改时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyTime;

    @Schema(description = "整改人")
    private String rectifyUser;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}