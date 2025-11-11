package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项分域 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomDivMngMatterRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分域ID")
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "分域编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域编码")
    private String domCode;

    @Schema(description = "管理事项大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("管理事项大类ID")
    private String matterMajorId;

    @Schema(description = "管理事项大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("管理事项大类名称")
    private String matterMajorName;

    @Schema(description = "管理事项小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("管理事项小类ID")
    private String matterMinorId;

    @Schema(description = "管理事项小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("管理事项小类名称")
    private String matterMinorName;

    @Schema(description = "关联点位数量(个)")
    @ExcelProperty("关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联事件数量(件)")
    @ExcelProperty("关联事件数量(件)")
    private Integer relEvtCount;

    @Schema(description = "处置部门代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置部门代码")
    private String deptCode;

    @Schema(description = "处置部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置部门名称")
    private String deptName;

    @Schema(description = "分域状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域状态")
    private Boolean domStatus;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
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