package cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 桥梁安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BridgeSafetyRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8638")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "桥梁名称", example = "王五")
    @ExcelProperty("桥梁名称")
    private String bridgeName;

    @Schema(description = "桥梁编号")
    @ExcelProperty("桥梁编号")
    private String bridgeNumber;

    @Schema(description = "桥梁位置")
    @ExcelProperty("桥梁位置")
    private String bridgeLocation;

    @Schema(description = "桥梁类型", example = "1")
    @ExcelProperty("桥梁类型")
    private String bridgeType;

    @Schema(description = "建造时间")
    @ExcelProperty("建造时间")
    private LocalDateTime constructionTime;

    @Schema(description = "设计荷载")
    @ExcelProperty("设计荷载")
    private String designLoad;

    @Schema(description = "结构部件状况")
    @ExcelProperty("结构部件状况")
    private String conditionStructuralComponents;

    @Schema(description = "基础沉降数据")
    @ExcelProperty("基础沉降数据")
    private String basicSettlementData;

    @Schema(description = "索力")
    @ExcelProperty("索力")
    private String cableForce;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}