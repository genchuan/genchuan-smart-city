package cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 集贸市场 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MarketRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "11727")
    @ExcelProperty("市场编号")
    private String marketId;

    @Schema(description = "市场名称", example = "赵六")
    @ExcelProperty("市场名称")
    private String name;

    @Schema(description = "市场地址")
    @ExcelProperty("市场地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "摊位数量", example = "11386")
    @ExcelProperty("摊位数量")
    private Integer stallCount;

    @Schema(description = "关联sys_user.id", example = "11292")
    @ExcelProperty("管理人员")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "20957")
    @ExcelProperty("运行状态编号")
    private String operationStatusId;

    @Schema(description = "卫生达标率")
    @ExcelProperty("卫生达标率")
    private BigDecimal hygieneRate;

    @Schema(description = "收运完成率")
    @ExcelProperty("收运完成率")
    private BigDecimal wasteTransferRate;

    @Schema(description = "污水处置合格率")
    @ExcelProperty("污水处置合格率")
    private BigDecimal sewageRate;

    @Schema(description = "未完成任务数", example = "23814")
    @ExcelProperty("未完成任务数")
    private Integer unfinishedTaskCount;

    @Schema(description = "保洁频次")
    @ExcelProperty("保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段，JSON")
    @ExcelProperty("保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁区域")
    @ExcelProperty("保洁区域")
    private String cleaningArea;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员")
    private String staffIds;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "垃圾类型IDs，JSON")
    @ExcelProperty("垃圾类型")
    private String garbageTypeIds;

    @Schema(description = "收集容器数量", example = "25100")
    @ExcelProperty("收集容器数量")
    private Integer garbageContainerCount;

    @Schema(description = "收运间隔")
    @ExcelProperty("收运间隔")
    private String wasteTransferInterval;

    @Schema(description = "收运时段")
    @ExcelProperty("收运时段")
    private String wasteTransferTime;

    @Schema(description = "关联sys_vehicle.id", example = "27857")
    @ExcelProperty("车辆编号")
    private String vehicleId;

    @Schema(description = "污水排放区域")
    @ExcelProperty("污水排放区域")
    private String sewageDischargeArea;

    @Schema(description = "污水处置方式")
    @ExcelProperty("污水处置方式")
    private String sewageDisposalWay;

    @Schema(description = "清理频次")
    @ExcelProperty("清理频次")
    private String sewageCleaningFrequency;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String sewageProblemDesc;

    @Schema(description = "上次清理时间")
    @ExcelProperty("上次清理时间")
    private LocalDateTime lastSewageCleaningTime;

    @Schema(description = "下次清理时间")
    @ExcelProperty("下次清理时间")
    private LocalDateTime nextSewageCleaningTime;

    @Schema(description = "处置日志")
    @ExcelProperty("处置日志")
    private String sewageDisposalLog;

    @Schema(description = "核查时段")
    @ExcelProperty("核查时段")
    private String hygieneCheckTime;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("检查人员")
    private String checkBy;

    @Schema(description = "核查日期")
    @ExcelProperty("核查日期")
    private LocalDateTime hygieneCheckDate;

    @Schema(description = "前期问题")
    @ExcelProperty("前期问题")
    private String previousProblem;

    @Schema(description = "达标项数", example = "19155")
    @ExcelProperty("达标项数")
    private Integer qualifiedItemCount;

    @Schema(description = "不达标项数", example = "20755")
    @ExcelProperty("不达标项数")
    private Integer unqualifiedItemCount;

    @Schema(description = "整改要求")
    @ExcelProperty("整改要求")
    private String reformRequire;

    @Schema(description = "整改期限")
    @ExcelProperty("整改期限")
    private LocalDateTime reformDeadline;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}