package cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 系统首页快捷菜单配置新增/修改 Request VO")
@Data
public class HomeQuickMenuSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    private String name;

    @Schema(description = "菜单路径")
    @NotEmpty(message = "菜单路径不能为空")
    private String path;

    @Schema(description = "菜单类型")
    @NotEmpty(message = "菜单类型不能为空")
    private String type;

    @Schema(description = "菜单图标")
    @NotEmpty(message = "菜单图标不能为空")
    private String icon;

    @Schema(description = "用户ID")
    @NotEmpty(message = "用户ID不能为空")
    private String userId;

    @Schema(description = "排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态")
    private String enableStatus;

    @Schema(description = "菜单描述")
    private String menuDesc;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}