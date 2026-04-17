package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 充停地图 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChargeParkMapRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "查询位置")
    @ExcelProperty("查询位置")
    private String queryLocation;

    @Schema(description = "查询时间")
    @ExcelProperty("查询时间")
    private LocalDateTime queryTime;

    @Schema(description = "查询结果数")
    @ExcelProperty("查询结果数")
    private Integer resultCount;

    @Schema(description = "响应时长（毫秒）")
    @ExcelProperty("响应时长（毫秒）")
    private Integer responseDuration;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
