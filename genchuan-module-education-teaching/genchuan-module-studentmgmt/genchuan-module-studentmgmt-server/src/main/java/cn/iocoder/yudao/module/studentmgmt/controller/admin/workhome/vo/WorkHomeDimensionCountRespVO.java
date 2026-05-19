package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 学工综合数据看板 Response VO")
@Data
public class WorkHomeDimensionCountRespVO {

    @Schema(description = "维度名称")
    private String dimension;
    @Schema(description = "该维度记录数量")
    private Integer count;


}