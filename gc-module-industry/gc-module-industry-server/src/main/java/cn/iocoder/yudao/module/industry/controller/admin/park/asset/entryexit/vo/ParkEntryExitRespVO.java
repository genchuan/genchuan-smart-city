package cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalTime;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 出入口信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkEntryExitRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15346")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21088")
    @ExcelProperty("关联ID")
    private String assetExtendId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5711")
    @ExcelProperty("所属车场ID")
    private String lotId;

    @Schema(description = "方向：入口/出口/双向", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("方向：入口/出口/双向")
    private String direction;

    @Schema(description = "关联设备ID列表")
    @ExcelProperty("关联设备ID列表")
    private String deviceIds;

    @Schema(description = "通行规则ID", example = "25093")
    @ExcelProperty("通行规则ID")
    private String passRuleId;

    @Schema(description = "开放时间")
    @ExcelProperty("开放时间")
    private LocalTime openTime;

    @Schema(description = "关闭时间")
    @ExcelProperty("关闭时间")
    private LocalTime closeTime;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime entryExitCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime entryExitUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String entryExitRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}