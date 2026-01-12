package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 运维人员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkMaintainUserRespVO {

    @Schema(description = "[主键ID] 运维人员唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24557")
    @ExcelProperty("[主键ID] 运维人员唯一标识")
    private Long id;

    @Schema(description = "[用户ID] 关联 park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31734")
    @ExcelProperty("[用户ID] 关联 park_user.id")
    private Long userId;

    @Schema(description = "[部门ID] 关联 sys_dept.id，所属部门", example = "4407")
    @ExcelProperty("[部门ID] 关联 sys_dept.id，所属部门")
    private Long deptId;

    @Schema(description = "[岗位类型] 设备维护 / 故障排查 / 工单处理", example = "2")
    @ExcelProperty("[岗位类型] 设备维护 / 故障排查 / 工单处理")
    private String jobType;

    @Schema(description = "[技能标签] JSON 型 varchar 存储")
    @ExcelProperty("[技能标签] JSON 型 varchar 存储")
    private String skillTags;

    @Schema(description = "[值班状态] 在岗 / 休假 / 请假", example = "2")
    @ExcelProperty("[值班状态] 在岗 / 休假 / 请假")
    private String onDutyStatus;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 运维人员相关备注说明", example = "你猜")
    @ExcelProperty("[备注] 运维人员相关备注说明")
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
