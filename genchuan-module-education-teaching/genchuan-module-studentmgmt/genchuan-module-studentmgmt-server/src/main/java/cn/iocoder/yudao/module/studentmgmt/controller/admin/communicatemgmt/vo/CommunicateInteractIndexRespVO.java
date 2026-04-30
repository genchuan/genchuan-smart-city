

package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 互动核心指标统计 response VO")
@Data
public class CommunicateInteractIndexRespVO {

    @Schema(description = "消息类型统计数据")
    private List<JSONObject> msgTypeCount;
    @Schema(description = "各班级互动率数据")
    private List<JSONObject> classInteractRate;
    @Schema(description = "反馈时间分布数据")
    private List<JSONObject> replyTimeDistribution;


}