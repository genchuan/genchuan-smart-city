package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo;


import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项分类表（含大类和小类）分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManagedMatterMajorPageReqVO extends PageParam {

    @Schema(description = "父类ID（关联本表id，大类为NULL）", example = "24213")
    private String parentId;

    @Schema(description = "事项代码（大类2位/小类3位）")
    private String matterCode;

    @Schema(description = "事项名称", example = "赵六")
    private String matterName;

    @Schema(description = "事项说明")
    private String matterDesc;

    @Schema(description = "排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态（1启用/0禁用）", example = "2")
    private String enableStatus;

    @Schema(description = "是否扩展类（仅小类使用：0标准类/1扩展类）")
    private String isExtend;

    @Schema(description = "主管部门代码（仅小类使用）")
    private String deptCode;

    @Schema(description = "主管部门名称（仅小类使用）", example = "李四")
    private String deptName;

    @Schema(description = "工作流模型标识")
    private String flowInstanceId;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
