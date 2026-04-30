

package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 家校协同互动看板 response VO")
@Data
public class CommunicateMgmtChartRespVO {

    @Schema(description = "总消息数")
    private Integer totalMsgCount;
    @Schema(description = "已发布消息数")
    private Integer publishedMsgCount;
    @Schema(description = "未发布消息数")
    private Integer unpublishedMsgCount;
    @Schema(description = "总反馈数")
    private Integer totalReplyCount;
    @Schema(description = "平均互动率")
    private BigDecimal avgInteractRate;
    @Schema(description = "近一周互动趋势数据")
    private List<JSONObject> recentWeekInteractTrend;


}