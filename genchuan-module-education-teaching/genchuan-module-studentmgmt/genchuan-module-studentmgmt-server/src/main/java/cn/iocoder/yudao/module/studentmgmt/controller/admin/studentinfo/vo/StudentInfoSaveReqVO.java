package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 学生信息新增/修改 Request VO")
@Data
public class StudentInfoSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18139")
    private Long id;

    @Schema(description = "学号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学号不能为空")
    private String studentNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "身份证号不能为空")
    private String idCard;

    @Schema(description = "学生照片地址")
    private String photo;

    @Schema(description = "学历层次：中专/大专/本科/研究生", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学历层次：中专/大专/本科/研究生不能为空")
    private String educationLevel;

    @Schema(description = "学习形式：全日制/非全日制/函授", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学习形式：全日制/非全日制/函授不能为空")
    private String studyForm;

    @Schema(description = "专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "专业不能为空")
    private String major;

    @Schema(description = "年级", example = "2024" , requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "年级不能为空")
    private String grade;

    @Schema(description = "班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "班级不能为空")
    private String className;

    @Schema(description = "学生类型：普通生/特长生/转学生", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "学生类型：普通生/特长生/转学生不能为空")
    private String studentType;

    @Schema(description = "学籍状态：在籍/休学/退学/异动", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "学籍状态：在籍/休学/退学/异动不能为空")
    private String status;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "家长联系电话")
    private String parentPhone;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}