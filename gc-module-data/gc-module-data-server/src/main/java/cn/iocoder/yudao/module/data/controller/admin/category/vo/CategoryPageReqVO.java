package cn.iocoder.yudao.module.data.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理部件分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryPageReqVO extends PageParam {

    @Schema(description = "分类名称", example = "王五")
    private String name;

    @Schema(description = "分类代码")
    private String code;

    @Schema(description = "编码排序类型", example = "1")
    private String codeSortType;

    @Schema(description = "上级分类ID", example = "14362")
    private String parentId;

    @Schema(description = "上级分类名称", example = "张三")
    private String parentName;

    @Schema(description = "关联图标ID", example = "26428")
    private String iconId;

    @Schema(description = "图示审核状态ID", example = "655")
    private String iconAuditStatusId;

    @Schema(description = "分类类型ID", example = "27463")
    private String categoryTypeId;

    @Schema(description = "关联状态ID", example = "6219")
    private String statusId;

    @Schema(description = "关联审核状态ID", example = "26882")
    private String auditStatusId;

    @Schema(description = "关联实例数", example = "9526")
    private Integer instanceCount;

    @Schema(description = "用途说明")
    private String purpose;

    @Schema(description = "分类变更通知标识")
    private Boolean notifyFlag;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}