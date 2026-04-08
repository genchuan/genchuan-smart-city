package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 学生信息分页 Request VO")
@Data
public class StudentInfoPageReqVO extends PageParam {

    @Schema(description = "学号")
    private String studentNo;

    @Schema(description = "姓名", example = "芋艿")
    private String name;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "学生照片地址")
    private String photo;

    @Schema(description = "学历层次：中专/大专/本科/研究生")
    private String educationLevel;

    @Schema(description = "学习形式：全日制/非全日制/函授")
    private String studyForm;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "年级", example = "2024")
    private String grade;


    @Schema(description = "班级", example = "李四")
    private String className;

    @Schema(description = "学生类型：普通生/特长生/转学生", example = "1")
    private String studentType;

    @Schema(description = "学籍状态：在籍/休学/退学/异动", example = "2")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}