package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 公厕 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicToiletRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22794")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "公厕编码", example = "uuid-toilet-001")
    @ExcelProperty("公厕编码")
    private String toiletId;

    @Schema(description = "公厕名称", example = "中山公园公厕")
    @ExcelProperty("公厕名称")
    private String name;

    @Schema(description = "公厕位置", example = "中山公园东门旁")
    @ExcelProperty("公厕位置")
    private String location;

    @Schema(description = "所属区域编码", example = "1001")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "开放时段", example = "06:00-22:00")
    @ExcelProperty("开放时段")
    private String openHours;

    @Schema(description = "蹲位数量", example = "12")
    @ExcelProperty("蹲位数量")
    private Integer stallCount;

    @Schema(description = "运营状态ID", example = "uuid-op-status-001")
    @ExcelProperty("运营状态ID")
    private String operationStatusId;

    @Schema(description = "负责人ID", example = "uuid-user-001")
    @ExcelProperty("负责人ID")
    private String managerId;

    @Schema(description = "保洁达标率", example = "98.50")
    @ExcelProperty("保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "投诉办结率", example = "100.00")
    @ExcelProperty("投诉办结率")
    private BigDecimal complaintRate;

    @Schema(description = "设施完好率", example = "95.00")
    @ExcelProperty("设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "耗材库存预警数", example = "2")
    @ExcelProperty("耗材库存预警数")
    private Integer warningCount;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}