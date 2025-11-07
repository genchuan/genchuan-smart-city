package cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 部件信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ComponentInformationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27515")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "部件编号")
    @ExcelProperty("部件编号")
    private String partNumber;

    @Schema(description = "部件名称", example = "李四")
    @ExcelProperty("部件名称")
    private String componentName;

    @Schema(description = "部件类型", example = "1")
    @ExcelProperty("部件类型")
    private String partType;

    @Schema(description = "所属区域")
    @ExcelProperty("所属区域")
    private String belongingRegion;

    @Schema(description = "安装位置")
    @ExcelProperty("安装位置")
    private String installationPosition;

    @Schema(description = "经纬度坐标")
    @ExcelProperty("经纬度坐标")
    private String latitudeLongitude;

    @Schema(description = "建设日期")
    @ExcelProperty("建设日期")
    private String constructionDate;

    @Schema(description = "管理部门")
    @ExcelProperty("管理部门")
    private String administrativeDepartment;

    @Schema(description = "维护单位")
    @ExcelProperty("维护单位")
    private String maintenanceUnit;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactNumber;

    @Schema(description = "部件状态", example = "2")
    @ExcelProperty("部件状态")
    private String componentStatus;

    @Schema(description = "使用寿命")
    @ExcelProperty("使用寿命")
    private String serviceLife;

    @Schema(description = "关联事件记录")
    @ExcelProperty("关联事件记录")
    private String relatedEventRecords;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}