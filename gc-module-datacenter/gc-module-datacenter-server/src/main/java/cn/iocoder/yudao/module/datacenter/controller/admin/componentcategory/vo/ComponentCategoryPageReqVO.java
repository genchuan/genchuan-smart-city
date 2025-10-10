package cn.iocoder.yudao.module.datacenter.controller.admin.componentcategory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测部件分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComponentCategoryPageReqVO extends PageParam {

    @Schema(description = "分类ID，UUID", example = "7280")
    private String categoryId;

    @Schema(description = "父类ID，0表示大类", example = "22250")
    private String parentId;

    @Schema(description = "层级：1-大类/2-中类/3-小类")
    private String level;

    @Schema(description = "分类代码：01-99")
    private String code;

    @Schema(description = "分类名称", example = "赵六")
    private String name;

    @Schema(description = "分类说明", example = "随便")
    private String description;

    @Schema(description = "状态：1-启用/0-禁用", example = "2")
    private String status;

    @Schema(description = "创建人ID")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID")
    private String updateUser;

    @Schema(description = "扩展字段1")
    private String ext1;

    @Schema(description = "扩展字段2")
    private String ext2;

    @Schema(description = "扩展字段3")
    private String ext3;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}