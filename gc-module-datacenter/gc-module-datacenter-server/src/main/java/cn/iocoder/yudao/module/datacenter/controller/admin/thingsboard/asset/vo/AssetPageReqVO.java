package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产分页 Request VO")
@Data
public class AssetPageReqVO extends PageParam {

    @Schema(description = "租户ID", example = "10102")
    private String tenantId;

    @Schema(description = "客户ID", example = "4677")
    private String customerId;

    @Schema(description = "资产名称", example = "资产1")
    private String name;

    @Schema(description = "资产类型", example = "building")
    private String type;

    @Schema(description = "标签")
    private String label;

    @Schema(description = "资产实体ID", example = "28197")
    private String assetProfileId;

    @Schema(description = "附加信息")
    private String additionalInfo;

    @Schema(description = "外部ID", example = "21772")
    private String externalId;

    @Schema(description = "版本")
    private Long version;

    @Schema(description = "系统创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createdTime;

}