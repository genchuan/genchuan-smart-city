package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件分域 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomDivMonCompRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分域ID")
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称")
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "分极编码")
    @ExcelProperty("分极编码")
    private String domCode;

    @Schema(description = "监测部件大类ID")
    @ExcelProperty("监测部件大类ID")
    private String monCompMajorId;

    @Schema(description = "监测部件大类名称")
    @ExcelProperty("监测部件大类名称")
    private String monCompMajorName;

    @Schema(description = "监测部件小类ID")
    @ExcelProperty("监测部件小类ID")
    private String monCompMinorId;

    @Schema(description = "监测部件小类名称")
    @ExcelProperty("监测部件小类名称")
    private String monCompMinorName;

    @Schema(description = "关联点位数量(个)")
    @ExcelProperty("关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    @ExcelProperty("关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "设备在线率(%)")
    @ExcelProperty("设备在线率(%)")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "维护部门代码")
    @ExcelProperty("维护部门代码")
    private String deptCode;

    @Schema(description = "维护部门名称")
    @ExcelProperty("维护部门名称")
    private String deptName;

    @Schema(description = "分域状态")
    @ExcelProperty("分域状态")
    private Boolean domStatus;

    @Schema(description = "创建时间(业务)")
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新时间(业务)")
    @ExcelProperty("更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}