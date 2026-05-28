package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 学籍档案分页 Request VO")
@Data
public class ArchivePageReqVO extends PageParam {

    @Schema(description = "学生编号")
    private String studentNo;

    @Schema(description = "姓名", example = "李四")
    private String name;

    @Schema(description = "班级ID，关联system_dept.id", example = "10143")
    private Long classId;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "层次")
    private String level;

    @Schema(description = "学习形式", example = "1")
    private String studyType;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "家长电话")
    private String parentPhone;

    @Schema(description = "学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label", example = "2")
    private String status;

    @Schema(description = "建档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] archiveTime;

    @Schema(description = "流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label", example = "1")
    private String processStatus;

    @Schema(description = "驳回原因", example = "不对")
    private String rejectReason;

    @Schema(description = "异动原因", example = "不对")
    private String changeReason;

    @Schema(description = "佐证材料", example = "https://www.iocoder.cn")
    private String evidenceUrl;

    @Schema(description = "处分有效期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] punishValidTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "扩展字段，JSON格式（插入时需显式提供值）")
    private String extension;

    @Schema(description = "创建人ID，关联system_user.id")
    private String creator;

    @Schema(description = "更新人ID，关联system_user.id")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}