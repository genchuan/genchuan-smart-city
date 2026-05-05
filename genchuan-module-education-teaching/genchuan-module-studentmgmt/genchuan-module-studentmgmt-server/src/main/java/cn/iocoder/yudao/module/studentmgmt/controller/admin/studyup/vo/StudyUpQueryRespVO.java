package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 升学管理查询 response VO")
@Data
public class StudyUpQueryRespVO {

    @Schema(description = "主键 ID", example = "20394")
    private Long id;
    @Schema(description = "学生 ID", example = "1428")
    private Long studentId;
    @Schema(description = "目标院校名称", example = "赵六")
    private String schoolName;
    @Schema(description = "院校类型：公办/民办", example = "2")
    private String schoolType;
    @Schema(description = "意向专业", example = "1")
    private String major;
    @Schema(description = "升学规划内容", example = "1")
    private String planContent;
    @Schema(description = "规划时间", example = "2022-02-08 02:02:02")
    private String planTime;
    @Schema(description = "跟踪记录时间", example = "2022-02-08 02:02:02")
    private String recordTime;
    @Schema(description = "状态：待规划/已规划", example = "2")
    private String status;
    @Schema(description = "备注", example = "你说的对")
    private String remark;


}