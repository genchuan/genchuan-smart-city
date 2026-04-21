package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 巡检轨迹 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectTrackRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检人员ID")
    private Long userId;

    @Schema(description = "巡检人员姓名")
    @ExcelProperty("巡检人员姓名")
    private String userName;  // 新增字段

    @Schema(description = "轨迹时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("轨迹时间")
    private LocalDateTime trackTime;

    @Schema(description = "巡检里程（公里）")
    @ExcelProperty("巡检里程（公里）")
    private BigDecimal mileage;

    @Schema(description = "巡检时长（分钟）")
    @ExcelProperty("巡检时长（分钟）")
    private Integer duration;

    @Schema(description = "所属片区")
    @ExcelProperty("所属片区")
    private String area;

    @Schema(description = "轨迹状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("轨迹状态")
    private String status;

    @Schema(description = "轨迹点")
    @ExcelProperty("轨迹点")
    private String points;

    @Schema(description = "核查状态：0-未核查 1-已核查 2-核查中")
    @ExcelProperty("核查状态")
    private Integer checkStatus;

    @Schema(description = "核查备注")
    @ExcelProperty("核查备注")
    private String checkRemark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
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