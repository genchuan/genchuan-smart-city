package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 运维人员分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkMaintainUserPageReqVO extends PageParam {

    @Schema(description = "[用户ID] 关联 park_user.id", example = "31734")
    private Long userId;

    @Schema(description = "[部门ID] 关联 sys_dept.id，所属部门", example = "4407")
    private Long deptId;

    @Schema(description = "[岗位类型] 设备维护 / 故障排查 / 工单处理", example = "2")
    private String jobType;

    @Schema(description = "[技能标签] JSON 型 varchar 存储")
    private String skillTags;

    @Schema(description = "[值班状态] 在岗 / 休假 / 请假", example = "2")
    private String onDutyStatus;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 运维人员相关备注说明", example = "你猜")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
