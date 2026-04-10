package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import cn.iocoder.yudao.framework.desensitize.core.slider.annotation.MobileDesensitize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 学生信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StudentInfoRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18139")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("学号")
    private String studentNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("姓名")
    private String name;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("身份证号")
    private String idCard;

    @Schema(description = "学生照片地址")
    @ExcelProperty("学生照片地址")
    private String photo;

    @Schema(description = "学历层次：中专/大专/本科/研究生", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("学历层次：中专/大专/本科/研究生")
    private String educationLevel;

    @Schema(description = "学习形式：全日制/非全日制/函授", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("学习形式：全日制/非全日制/函授")
    private String studyForm;

    @Schema(description = "专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("专业")
    private String major;

    @Schema(description = "年级", example = "2024")
    @ExcelProperty("年级")
    private String grade;

    @Schema(description = "班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("班级")
    private String className;

    @Schema(description = "学生类型：普通生/特长生/转学生", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("学生类型：普通生/特长生/转学生")
    private String studentType;

    @Schema(description = "学籍状态：在籍/休学/退学/异动", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("学籍状态：在籍/休学/退学/异动")
    private String status;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "家长联系电话")
    @ExcelProperty("家长联系电话")
    private String parentPhone;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
