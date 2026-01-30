package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产扩展新增/修改 Request VO")
@Data
public class AssetExtendSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26436")
    private Long id;

    @Schema(description = "资产类型：车场/车库/路侧泊位/车位/出入口", example = "1")
    private String assetType;

    @Schema(description = "资产名称", example = "王五")
    private String assetName;

    @Schema(description = "唯一资产编码")
    private String assetCode;

    @Schema(description = "状态：正常/故障/停用", example = "2")
    private String assetStatus;

    @Schema(description = "所属区域编码")
    private String regionCode;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "业务创建时间")
    private LocalDateTime assetCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime assetUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    private String assetRemark;

}