package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应急资源总览 Response VO")
@Data
public class EmergResoverViewRespVO {

    @Schema(description = "资源ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "RES20251106001")
    @ExcelProperty("资源ID")
    private String resId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "消防泵")
    @ExcelProperty("资源名称")
    private String resName;

    @Schema(description = "资源类型，如应急物资/救援队伍/设备", requiredMode = Schema.RequiredMode.REQUIRED, example = "应急物资")
    @ExcelProperty("资源类型")
    private String resType;

    @Schema(description = "资源总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @ExcelProperty("资源总数量")
    private Integer totalQty;

    @Schema(description = "可用数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    @ExcelProperty("可用数量")
    private Integer availableQty;

    @Schema(description = "调度数量，应急资源中已被调用的数量",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "20")
    @ExcelProperty("调度数量")
    private Integer dispatchQty;


    @Schema(description = "存放位置", requiredMode = Schema.RequiredMode.REQUIRED, example = "仓库A区")
    @ExcelProperty("存放位置")
    private String storageLoc;

    @Schema(description = "负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("负责人")
    private String mngrName;

    @Schema(description = "库存状态，充足/紧张/短缺", requiredMode = Schema.RequiredMode.REQUIRED, example = "充足")
    @ExcelProperty("库存状态")
    private String stockStatus;

    @Schema(description = "最小库存量阈值", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @ExcelProperty("最小库存量阈值")
    private Integer minStock;


    @Schema(description = "数据更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2025-11-19 10:00:00")
    @ExcelProperty("数据更新时间")
    private LocalDateTime updateTime;
}
