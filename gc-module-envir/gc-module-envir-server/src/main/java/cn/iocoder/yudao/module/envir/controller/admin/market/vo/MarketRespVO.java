package cn.iocoder.yudao.module.envir.controller.admin.market.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 集贸市场 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MarketRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22925")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "26726")
    @ExcelProperty("业务主键（UUID）")
    private String marketId;

    @Schema(description = "市场名称", example = "王五")
    @ExcelProperty("市场名称")
    private String name;

    @Schema(description = "市场地址")
    @ExcelProperty("市场地址")
    private String address;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    @ExcelProperty("所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "摊位数量", example = "11609")
    @ExcelProperty("摊位数量")
    private Integer stallCount;

    @Schema(description = "摊位保洁责任划分规则（含区域、摊主责任、保洁员责任、考核标准等）")
    @ExcelProperty("摊位保洁责任划分规则（含区域、摊主责任、保洁员责任、考核标准等）")
    private String stallCleaningRule;

    @Schema(description = "保洁频次（可选值：每小时/每日3次/每日2次/每日1次/营业前/营业后）")
    @ExcelProperty("保洁频次（可选值：每小时/每日3次/每日2次/每日1次/营业前/营业后）")
    private String cleaningFrequency;

    @Schema(description = "垃圾清运间隔（单位：小时）")
    @ExcelProperty("垃圾清运间隔（单位：小时）")
    private Integer wasteTransferInterval;

    @Schema(description = "污水处置方式（可选值：统一管网排放/化粪池处理/第三方清运/就地净化）")
    @ExcelProperty("污水处置方式（可选值：统一管网排放/化粪池处理/第三方清运/就地净化）")
    private String sewageDisposalWay;

    @Schema(description = "负责人（关联sys_user.id）", example = "5905")
    @ExcelProperty("负责人（关联sys_user.id）")
    private String managerId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    @ExcelProperty("业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "卫生达标率（0.00-100.00）")
    @ExcelProperty("卫生达标率（0.00-100.00）")
    private BigDecimal hygieneRate;

    @Schema(description = "垃圾清运量（单位：立方米/日）")
    @ExcelProperty("垃圾清运量（单位：立方米/日）")
    private BigDecimal wasteVolume;

    @Schema(description = "污水处置达标率（0.00-100.00）")
    @ExcelProperty("污水处置达标率（0.00-100.00）")
    private BigDecimal sewageRate;

    @Schema(description = "污水处置对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    @ExcelProperty("污水处置对比照片URL（多个用逗号分隔）")
    private String sewagePhotoUrl;

    @Schema(description = "卫生核查照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    @ExcelProperty("卫生核查照片URL（多个用逗号分隔）")
    private String hygieneCheckPhotoUrl;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}