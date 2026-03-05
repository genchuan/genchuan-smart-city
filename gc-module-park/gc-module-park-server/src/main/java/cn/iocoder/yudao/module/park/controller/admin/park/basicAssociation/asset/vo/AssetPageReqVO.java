package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产-thingsboard分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetPageReqVO extends PageParam {

    @Schema(description = "资产编码")
    private String assetCode;

    @Schema(description = "资产名称", example = "赵六")
    private String assetName;

    @Schema(description = "资产类型", example = "2")
    private String assetType;

    @Schema(description = "所属区域编码")
    private String regionCode;

    @Schema(description = "状态：正常/停用", example = "2")
    private String assetStatus;

    @Schema(description = "入账时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] assetCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] assetUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String assetRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
