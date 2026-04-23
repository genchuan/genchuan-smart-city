package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import cn.idev.excel.annotation.ExcelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 资产信息导入 Request VO")
@Data
public class AssetInfoImportReqVO {

    @Schema(description = "资产名称")
    @ExcelProperty("资产名称")
    private String name;

    @Schema(description = "资产类型")
    @ExcelProperty("资产类型")
    private String type;

    @Schema(description = "采购时间")
    @ExcelProperty("采购时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseTime;

    @Schema(description = "资产状态")
    @ExcelProperty("资产状态")
    private String status;

    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;
}