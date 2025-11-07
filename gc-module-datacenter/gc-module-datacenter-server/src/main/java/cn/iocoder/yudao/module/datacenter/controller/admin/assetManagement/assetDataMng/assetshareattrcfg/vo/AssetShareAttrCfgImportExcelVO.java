package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class AssetShareAttrCfgImportExcelVO {
    @ExcelProperty("共享属性ID")
    private String assetShareAttrId;
    @ExcelProperty("关联资产ID")
    private String relAssetId;
    @ExcelProperty("关联资产名称")
    private String relAssetName;
    @ExcelProperty("属性名称")
    private String attrName;
    @ExcelProperty("属性代码")
    private String attrCode;
    @ExcelProperty("属性值")
    private String attrValue;
    @ExcelProperty("共享对象类型")
    private String shareObjType;
    @ExcelProperty("共享对象ID")
    private String shareObjId;
    @ExcelProperty("共享对象名称")
    private String shareObjName;
    @ExcelProperty("共享权限")
    private String sharePerm;
    @ExcelProperty("配置时间")
    private java.time.LocalDateTime cfgTime;
    @ExcelProperty("操作人")
    private String operUser;
    @ExcelProperty("更新人")
    private String updateUser;
    @ExcelProperty("更新时间")
    private java.time.LocalDateTime updatedTime;
}
