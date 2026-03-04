package cn.iocoder.yudao.module.data.controller.admin.instance.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class InstanceImportVO {

    @ExcelProperty("部件名称")
    private String partName;

    @ExcelProperty("唯一编码")
    private String uniqueCode;

    @ExcelProperty("类型")
    private String type;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("备注")
    private String remark;

}