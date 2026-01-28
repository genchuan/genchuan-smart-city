package cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 政府部门 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GovernmentDepartmentRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "12283")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[部门名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[部门名称]")
    private String deptName;

    @Schema(description = "[部门编码]")
    @ExcelProperty("[部门编码]")
    private String deptCode;

    @Schema(description = "[联系人]")
    @ExcelProperty("[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    @ExcelProperty("[联系电话]")
    private String contactPhone;

    @Schema(description = "[负责区域编码] 关联park_area.area_code")
    @ExcelProperty("[负责区域编码] 关联park_area.area_code")
    private String regionCode;

    @Schema(description = "[职责范围]")
    @ExcelProperty("[职责范围]")
    private String responsibility;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你说的对")
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
