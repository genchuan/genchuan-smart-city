// DataMapRespVO.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "管理后台 - 文旅全域数据地图 Response VO")
public class CultureDataMapRespVO {
    // 文旅资源位置
    @Schema(description = "资源坐标X", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源坐标X")
    private BigDecimal coordX;

    @Schema(description = "资源坐标Y", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源坐标Y")
    private BigDecimal coordY;

    // 资源类型
    @Schema(description = "资源类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源类型名称")
    private String compCatName;

    // 实时客流
    @Schema(description = "统计周期内客流总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客流总数")
    private Integer totalRptCount;

    // 设施状态
    @Schema(description = "设施运行状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设施状态")
    private String runStatus;

    // 异常事件位置
    @Schema(description = "异常事件坐标X")
    @ExcelProperty("事件坐标X")
    private BigDecimal incidentX;

    @Schema(description = "异常事件坐标Y")
    @ExcelProperty("事件坐标Y")
    private BigDecimal incidentY;

    // getter/setter省略
}