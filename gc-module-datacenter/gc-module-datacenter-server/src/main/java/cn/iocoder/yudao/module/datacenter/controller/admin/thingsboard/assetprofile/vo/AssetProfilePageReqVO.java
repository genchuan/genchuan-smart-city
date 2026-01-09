package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产配置信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetProfilePageReqVO extends PageParam {

    @Schema(description = "资产配置ID", example = "17085")
    private String profileId;

    @Schema(description = "实体类型", example = "1")
    private String entityType;

    @Schema(description = "创建时间戳")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] createdTime;

    @Schema(description = "租户ID")
    private String tenantIdTb;

    @Schema(description = "租户实体类型", example = "2")
    private String tenantEntityType;

    @Schema(description = "资产配置名称", example = "张三")
    private String profileName;

    @Schema(description = "资产配置描述", example = "随便")
    private String profileDescription;

    @Schema(description = "资产配置图片")
    private String profileImage;

    @Schema(description = "默认规则链ID", example = "28630")
    private String defaultRuleChainId;

    @Schema(description = "默认仪表板ID", example = "2833")
    private String defaultDashboardId;

    @Schema(description = "默认队列名称", example = "赵六")
    private String defaultQueueName;

    @Schema(description = "默认边缘规则链ID", example = "15077")
    private String defaultEdgeRuleChainId;

    @Schema(description = "外部ID", example = "25671")
    private String externalId;

    @Schema(description = "外部实体类型", example = "1")
    private String externalEntityType;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "是否默认配置")
    private Boolean isDefault;

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