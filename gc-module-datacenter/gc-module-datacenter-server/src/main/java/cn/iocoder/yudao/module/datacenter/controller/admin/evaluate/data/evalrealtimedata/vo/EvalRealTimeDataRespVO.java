package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 实时监测数据接入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalRealTimeDataRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "数据ID")
    @ExcelProperty("数据ID")
    private String realTimeDataId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务名称")
    private String evalTaskName;

    @Schema(description = "关联指标项ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联指标项名称")
    private String idxItemName;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "数据值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据值")
    private String dataValue;

    @Schema(description = "数据时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据时间")
    private LocalDateTime dataTime;

    @Schema(description = "接入状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("接入状态")
    private String accessStatus;

    @Schema(description = "失败原因")
    @ExcelProperty("失败原因")
    private String failReason;

    @Schema(description = "同步频率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("同步频率")
    private Integer syncFrequency;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}