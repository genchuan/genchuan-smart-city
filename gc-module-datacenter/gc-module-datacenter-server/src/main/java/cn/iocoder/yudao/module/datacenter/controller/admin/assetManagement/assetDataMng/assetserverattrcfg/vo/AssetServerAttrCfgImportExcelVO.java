package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class AssetServerAttrCfgImportExcelVO {

    @ExcelProperty("服务端属性ID")
    private String assetServerAttrId;

    @ExcelProperty("关联资产ID")
    private String relAssetId;

    @ExcelProperty("关联资产名称")
    private String relAssetName;

    @ExcelProperty("属性名称")
    private String attrName;

    @ExcelProperty("属性代码")
    private String attrCode;

    @ExcelProperty("属性数据类型")
    private String attrDataType;

    @ExcelProperty("属性值")
    private String attrValue;

    @ExcelProperty("采集频率")
    private String collectFreq;

    @ExcelProperty("最后采集时间")
    private LocalDateTime lastCollectTime;

    @ExcelProperty("配置时间")
    private LocalDateTime cfgTime;

    @ExcelProperty("操作人")
    private String operUser;

    @ExcelProperty("更新人")
    private String updateUser;

    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;
}
