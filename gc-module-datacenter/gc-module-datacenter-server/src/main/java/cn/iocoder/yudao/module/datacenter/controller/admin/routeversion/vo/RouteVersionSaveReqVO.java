package cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 路线版本新增/修改 Request VO")
@Data
public class RouteVersionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "路线ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "路线ID不能为空")
    private String routeId;

    @Schema(description = "路线名称")
    private String routeName;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "版本号不能为空")
    private String versionNumber;

    @Schema(description = "版本描述")
    private String versionDescription;

    @Schema(description = "变更原因")
    private String changeReason;

    @Schema(description = "变更内容")
    private String changeContent;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

}