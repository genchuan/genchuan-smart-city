package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检人员信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectPersonInfoRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "人员ID")
    @ExcelProperty("人员ID")
    private String personId;

    @Schema(description = "人员姓名")
    @ExcelProperty("人员姓名")
    private String personName;

    @Schema(description = "人员账号")
    @ExcelProperty("人员账号")
    private String personAccount;

    @Schema(description = "所属部门代码")
    @ExcelProperty("所属部门代码")
    private String deptCode;

    @Schema(description = "所属部门名称")
    @ExcelProperty("所属部门名称")
    private String deptName;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String personPhone;

    @Schema(description = "负责网格ID")
    @ExcelProperty("负责网格ID")
    private String gridId;

    @Schema(description = "负责网格名称")
    @ExcelProperty("负责网格名称")
    private String gridName;

    @Schema(description = "人员角色")
    @ExcelProperty("人员角色")
    private String personRole;

    @Schema(description = "关联设备编码")
    @ExcelProperty("关联设备编码")
    private String deviceCode;

    @Schema(description = "人员状态")
    @ExcelProperty("人员状态")
    private Boolean personStatus;

    @Schema(description = "入职日期")
    @ExcelProperty("入职日期")
    private LocalDate entryDate;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间（业务）")
    @ExcelProperty("创建时间（业务）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "更新时间（业务）")
    @ExcelProperty("更新时间（业务）")
    private LocalDateTime bizUpdateTime;

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