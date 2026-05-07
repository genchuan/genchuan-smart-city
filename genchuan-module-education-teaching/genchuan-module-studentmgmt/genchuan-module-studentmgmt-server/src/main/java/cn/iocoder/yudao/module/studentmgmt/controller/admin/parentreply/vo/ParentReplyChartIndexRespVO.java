package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 家长回复核心指标 Request VO")
@Data
public class ParentReplyChartIndexRespVO {

    @Schema(description = "学生回复统计", example = "1782")
    private List<JSONObject> studentReplyCount;
    @Schema(description = "班级回复率", example = "1782")
    private List<JSONObject> classReplyRate;
    @Schema(description = "回复时间分布", example = "1782")
    private List<JSONObject> replyTimeDistribute;

}