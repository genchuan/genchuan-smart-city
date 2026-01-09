package cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalTime;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 出入口信息新增/修改 Request VO")
@Data
public class ParkEntryExitSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15346")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21088")
    @NotEmpty(message = "关联ID不能为空")
    private String assetExtendId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5711")
    @NotEmpty(message = "所属车场ID不能为空")
    private String lotId;

    @Schema(description = "方向：入口/出口/双向", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "方向：入口/出口/双向不能为空")
    private String direction;

    @Schema(description = "关联设备ID列表")
    private String deviceIds;

    @Schema(description = "通行规则ID", example = "25093")
    private String passRuleId;

    @Schema(description = "开放时间")
    private LocalTime openTime;

    @Schema(description = "关闭时间")
    private LocalTime closeTime;

    @Schema(description = "业务创建时间")
    private LocalDateTime entryExitCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime entryExitUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String entryExitRemark;

}