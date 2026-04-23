package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "学生管理 - 学生违纪看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolateWarnIndexRespVO {

    // ========== 卡片数据 ==========

    @Schema(description = "周期名称", example = "")
    private String cycleName;
    @Schema(description = "新增违纪数", example = "")
    private Integer newViolateCount;
    @Schema(description = "新增预警数", example = "")
    private Integer newWarnCount;
    @Schema(description = "违纪处理率", example = "")
    private Double handleRate;


}