package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 缴费核验 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PayCheckRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21602")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "停车费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停车费用")
    private BigDecimal parkFee;

    @Schema(description = "缴费状态：已缴清/欠费 关联字典pay_check_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("缴费状态：已缴清/欠费 关联字典pay_check_status")
    private String status;

    @Schema(description = "核验时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("核验时间")
    private LocalDateTime checkTime;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "核验人姓名")
    @ExcelProperty("核验人姓名")
    private String checkUserName;

    @Schema(description = "核验结果")
    @ExcelProperty("核验结果")
    private String checkResult;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}