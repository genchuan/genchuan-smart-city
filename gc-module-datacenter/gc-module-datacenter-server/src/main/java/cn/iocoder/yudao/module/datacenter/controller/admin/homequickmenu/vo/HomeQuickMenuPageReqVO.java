package cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统首页快捷菜单配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HomeQuickMenuPageReqVO extends PageParam {

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单类型")
    private String type;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "用户ID")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}