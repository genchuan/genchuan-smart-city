package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 社区（村）行政区划配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridCommunityRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "社区ID")
    @ExcelProperty("社区ID")
    private String communityId;

    @Schema(description = "社区代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("社区代码")
    private String communityCode;

    @Schema(description = "社区名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("社区名称")
    private String communityName;

    @Schema(description = "所属街道ID")
    @ExcelProperty("所属街道ID")
    private String streetId;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    @ExcelProperty("失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "社区边界坐标")
    @ExcelProperty("社区边界坐标")
    private String communityBoundary;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}