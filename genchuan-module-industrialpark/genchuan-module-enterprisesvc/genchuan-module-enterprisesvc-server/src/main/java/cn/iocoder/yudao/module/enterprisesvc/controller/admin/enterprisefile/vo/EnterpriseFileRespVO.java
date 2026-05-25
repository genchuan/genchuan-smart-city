package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 企业档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnterpriseFileRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "企业名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("企业名称")
    private String enterpriseName;

    @Schema(description = "统一社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统一社会信用代码")
    private String creditCode;

    @Schema(description = "注册地址")
    @ExcelProperty("注册地址")
    private String registerAddr;

    @Schema(description = "企业类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("企业类型")
    private String enterpriseType;

    @Schema(description = "企业规模", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("企业规模")
    private String enterpriseScale;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案状态")
    private String fileStatus;

    @Schema(description = "员工总数")
    @ExcelProperty("员工总数")
    private Integer staffCount;

    @Schema(description = "审核人账号")
    @ExcelProperty("审核人账号")
    private String checkUser;

    @Schema(description = "审核通过率")
    @ExcelProperty("审核通过率")
    private BigDecimal checkRate;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}