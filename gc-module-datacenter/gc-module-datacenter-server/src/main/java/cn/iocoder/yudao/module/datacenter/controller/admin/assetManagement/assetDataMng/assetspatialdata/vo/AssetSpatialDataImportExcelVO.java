package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class AssetSpatialDataImportExcelVO {

    @ExcelProperty("空间数据ID")
    private String assetSpatialId;

    @ExcelProperty("关联资产ID")
    private String relAssetId;

    @ExcelProperty("关联资产名称")
    private String relAssetName;

    @ExcelProperty("坐标系类型")
    private String coordSystemType;

    @ExcelProperty("坐标X")
    private BigDecimal coordX;

    @ExcelProperty("坐标Y")
    private BigDecimal coordY;

    @ExcelProperty("高程")
    private BigDecimal elevation;

    @ExcelProperty("边界坐标")
    private String boundaryCoords;

    @ExcelProperty("空间数据来源")
    private String spatialDataSource;

    @ExcelProperty("录入时间")
    private LocalDateTime inputTime;

    @ExcelProperty("操作人")
    private String operUser;

    @ExcelProperty("更新人")
    private String updateUser;

    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;
}
