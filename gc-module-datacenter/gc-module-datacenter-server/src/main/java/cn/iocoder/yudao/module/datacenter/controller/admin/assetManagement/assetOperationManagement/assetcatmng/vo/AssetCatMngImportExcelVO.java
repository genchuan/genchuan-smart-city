package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

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
public class AssetCatMngImportExcelVO {


    @ExcelProperty("资产分类ID")
    private String assetCatId;

    @ExcelProperty("关联分类规则ID")
    private String relCatRuleId;

    @ExcelProperty("资产分类编码")
    private String assetCatCode;

    @ExcelProperty("资产分类名称")
    private String assetCatName;

    @ExcelProperty("分类层级")
    private String catLevel;

    @ExcelProperty("上级分类ID")
    private String parentCatId;

    @ExcelProperty("上级分类名称")
    private String parentCatName;

    @ExcelProperty("分类说明")
    private String catDesc;

    @ExcelProperty("启用状态")
    private String enableStatus;

    @ExcelProperty("创建人")
    private String createUser;

    @ExcelProperty("创建时间")
    private LocalDateTime createdTime;

    @ExcelProperty("更新人")
    private String updateUser;

    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;
}
