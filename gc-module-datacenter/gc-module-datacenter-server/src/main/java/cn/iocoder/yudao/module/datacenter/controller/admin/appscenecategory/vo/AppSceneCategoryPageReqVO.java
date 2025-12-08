package cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应用场景分类配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppSceneCategoryPageReqVO extends PageParam {

    @Schema(description = "应用场景分类代码")
    private String sceneCatCode;

    @Schema(description = "应用场景分类名称")
    private String sceneCatName;

    @Schema(description = "分类层级：1-大类/2-中类/3-小类")
    private String catLevel;

    @Schema(description = "父级分类ID")
    private String parentCatId;

    @Schema(description = "父级分类名称")
    private String parentCatName;

    @Schema(description = "应用场景分类说明")
    private String sceneCatDesc;

    @Schema(description = "是否扩展类：0-标准类/1-扩展类")
    private String isExt;

    @Schema(description = "启用状态：1-启用/0-禁用")
    private String enableStatus;

    @Schema(description = "关联监测部件类型ID")
    private String relCompCatId;

    @Schema(description = "关联监测部件类型名称")
    private String relCompCatName;

    @Schema(description = "关联监测事件类型ID")
    private String relEvtCatId;

    @Schema(description = "关联监测事件类型名称")
    private String relEvtCatName;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "关联的菜单编号")
    private String menuIds;
}