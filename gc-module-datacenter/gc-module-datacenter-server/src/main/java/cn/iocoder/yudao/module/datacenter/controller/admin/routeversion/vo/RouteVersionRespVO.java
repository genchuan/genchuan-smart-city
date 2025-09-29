package cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 路线版本 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RouteVersionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "路线ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("路线ID")
    private String routeId;

    @Schema(description = "路线名称")
    @ExcelProperty("路线名称")
    private String routeName;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("版本号")
    private String versionNumber;

    @Schema(description = "版本描述")
    @ExcelProperty("版本描述")
    private String versionDescription;

    @Schema(description = "变更原因")
    @ExcelProperty("变更原因")
    private String changeReason;

    @Schema(description = "变更内容")
    @ExcelProperty("变更内容")
    private String changeContent;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}