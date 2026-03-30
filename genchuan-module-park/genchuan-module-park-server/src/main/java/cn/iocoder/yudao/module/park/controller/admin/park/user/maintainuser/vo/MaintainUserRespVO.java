package cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 运维人员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MaintainUserRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "24638")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[用户ID] 关联park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10341")
    @ExcelProperty("[用户ID] 关联park_user.id")
    private Long userId;

    @Schema(description = "[部门ID] 关联park_dept.id", example = "1488")
    @ExcelProperty("[部门ID] 关联park_dept.id")
    private Long deptId;

    @Schema(description = "[岗位类型] 如:设备维护/故障排查/工单处理", example = "2")
    @ExcelProperty("[岗位类型] 如:设备维护/故障排查/工单处理")
    private String jobType;

    @Schema(description = "[技能标签] JSON格式varchar")
    @ExcelProperty("[技能标签] JSON格式varchar")
    private String skillTags;

    @Schema(description = "[值班状态] 如:在岗/休假/请假", example = "2")
    @ExcelProperty("[值班状态] 如:在岗/休假/请假")
    private String onDutyStatus;

    @Schema(description = "[联系电话]")
    @ExcelProperty("[联系电话]")
    private String contactPhone;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
