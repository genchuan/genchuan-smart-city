package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分页 Request VO")
@Data
public class DevicePageReqVO extends PageParam {

    @Schema(description = "TB租户", example = "10102")
    private String tbTenantId;

    @Schema(description = "客户ID", example = "4677")
    private String customerId;

    @Schema(description = "设备名称", example = "芋艿")
    private String name;

    @Schema(description = "设备类型", example = "2")
    private String type;

    @Schema(description = "标签")
    private String label;

    @Schema(description = "设备实体ID", example = "28197")
    private String deviceProfileId;

    @Schema(description = "设备配置")
    private byte[] deviceData;

    @Schema(description = "firmware", example = "7123")
    private String firmwareId;

    @Schema(description = "software", example = "23691")
    private String softwareId;

    @Schema(description = "Additional", example = "21772")
    private String externalId;

    @Schema(description = "版本")
    private Long version;

    @Schema(description = "属性列表")
    private String attributes;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;



}