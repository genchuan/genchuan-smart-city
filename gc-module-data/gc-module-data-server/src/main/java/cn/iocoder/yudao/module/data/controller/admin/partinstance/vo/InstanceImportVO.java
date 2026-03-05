package cn.iocoder.yudao.module.data.controller.admin.partinstance.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class InstanceImportVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("部件名称")
    private String partName;

    @ExcelProperty("16位标识码")
    private String uniqueCode; // 唯一标识码，用于检查记录是否存在

    @ExcelProperty("关联分类ID")
    private String parentCategoryId;

    @ExcelProperty("所属分类名称")
    private String categoryName;

    @ExcelProperty("关联网格ID")
    private String gridId;

    @ExcelProperty("所在网格")
    private String gridName;

    @ExcelProperty("经度")
    private BigDecimal longitude;

    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @ExcelProperty("坐标校验标识")
    private Boolean coordVerifyFlag;

    @ExcelProperty("坐标信息")
    private String coordinate;

    @ExcelProperty("关联运行状态ID")
    private String runStatus;

    @ExcelProperty("主管部门")
    private String deptName;

    @ExcelProperty("关联行政区划代码")
    private String areaCode;

    @ExcelProperty("行政区划归属")
    private String areaName;

    @ExcelProperty("关联监测部件ID列表")
    private String monitorIds;

    @ExcelProperty("关联监测部件数")
    private Integer monitorCount;

    @ExcelProperty("关联事件数")
    private Integer eventCount;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @ExcelProperty("创建人")
    private String creator;
}