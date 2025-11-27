package cn.iocoder.yudao.module.system.controller.admin.permission.vo.menu;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "管理后台 - 菜单简单信息 Response VO")
@Data
public class MenuDetailRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "菜单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户管理")
    private String name;

    @Schema(description = "父菜单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Long parentId;

    @Schema(description = "菜单类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer type;

    @Schema(description = "路由地址", example = "/system/user")
    private String path;

    @Schema(description = "菜单图标", example = "user")
    private String icon;

    @Schema(description = "权限标识", example = "system:user:query")
    private String permission;

    @Schema(description = "显示顺序", example = "1")
    private Integer sort;

    @Schema(description = "组件路径", example = "system/user/index")
    private String component;

    @Schema(description = "组件名", example = "SystemUser")
    private String componentName;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "是否可见", example = "true")
    private Boolean visible;

    @Schema(description = "是否缓存", example = "true")
    private Boolean keepAlive;

    @Schema(description = "是否总是显示", example = "false")
    private Boolean alwaysShow;

}
