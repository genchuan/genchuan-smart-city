package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 学籍档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ArchiveRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1190")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "学生编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("学生编号")
    private String studentNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("姓名")
    private String name;

    @Schema(description = "班级ID，关联system_dept.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10143")
    @ExcelProperty("班级ID，关联system_dept.id")
    private Long classId;

    @Schema(description = "专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("专业")
    private String major;

    @Schema(description = "层次", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("层次")
    private String level;

    @Schema(description = "学习形式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("学习形式")
    private String studyType;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("身份证号")
    private String idCard;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "家长电话")
    @ExcelProperty("家长电话")
    private String parentPhone;

    @Schema(description = "学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label")
    private String status;

    @Schema(description = "建档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("建档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label")
    private String processStatus;

    @Schema(description = "驳回原因", example = "不对")
    @ExcelProperty("驳回原因")
    private String rejectReason;

    @Schema(description = "异动原因", example = "不对")
    @ExcelProperty("异动原因")
    private String changeReason;

    @Schema(description = "佐证材料", example = "https://www.iocoder.cn")
    @ExcelProperty("佐证材料")
    private String evidenceUrl;

    @Schema(description = "处分有效期")
    @ExcelProperty("处分有效期")
    private LocalDateTime punishValidTime;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "扩展字段，JSON格式（插入时需显式提供值）")
    @ExcelProperty("扩展字段，JSON格式（插入时需显式提供值）")
    private String extension;

    @Schema(description = "创建人ID，关联system_user.id")
    @ExcelProperty("创建人ID，关联system_user.id")
    private String creator;

    @Schema(description = "更新人ID，关联system_user.id")
    @ExcelProperty("更新人ID，关联system_user.id")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
