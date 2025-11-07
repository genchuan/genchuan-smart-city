package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 社区（村）行政区划配置新增/修改 Request VO")
@Data
public class GridCommunitySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "社区ID")
    private String communityId;

    @Schema(description = "社区代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社区代码不能为空")
    private String communityCode;

    @Schema(description = "社区名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社区名称不能为空")
    private String communityName;

    @Schema(description = "所属街道ID")
    private String streetId;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "社区边界坐标")
    private String communityBoundary;

    @Schema(description = "备注")
    private String remark;

}