package cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 系统首页快捷菜单配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HomeQuickMenuRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "菜单名称")
    @ExcelProperty("菜单名称")
    private String name;

    @Schema(description = "菜单路径")
    @ExcelProperty("菜单路径")
    private String path;

    @Schema(description = "菜单类型")
    @ExcelProperty("菜单类型")
    private String type;

    @Schema(description = "菜单图标")
    @ExcelProperty("菜单图标")
    private String icon;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private String userId;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态")
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "菜单描述")
    @ExcelProperty("菜单描述")
    private String menuDesc;

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