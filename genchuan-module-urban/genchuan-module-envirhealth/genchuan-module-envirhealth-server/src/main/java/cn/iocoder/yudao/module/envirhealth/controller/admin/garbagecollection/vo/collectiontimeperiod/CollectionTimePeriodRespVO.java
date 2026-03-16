package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收运时段字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CollectionTimePeriodRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29897")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "时段编码（如：uuid-time-period-001）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("时段编码（如：uuid-time-period-001）")
    private String periodCode;

    @Schema(description = "时段名称（如：07:30-11:30）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("时段名称（如：07:30-11:30）")
    private String periodName;

    @Schema(description = "时段开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("时段开始时间")
    private LocalDateTime startTime;

    @Schema(description = "时段结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("时段结束时间")
    private LocalDateTime endTime;

    @Schema(description = "排序号")
    @ExcelProperty("排序号")
    private Integer sort;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}