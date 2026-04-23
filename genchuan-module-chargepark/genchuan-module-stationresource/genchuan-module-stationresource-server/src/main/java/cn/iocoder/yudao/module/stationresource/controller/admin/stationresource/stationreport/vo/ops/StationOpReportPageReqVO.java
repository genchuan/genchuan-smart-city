package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "场站资源报表生成")
public class StationOpReportPageReqVO extends PageParam {

    @Schema(description = "报表周期", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportCycle;

////    @NotNull(message = "开始时间不能为空")
//    @Schema(description = "开始时间 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
//    private LocalDateTime reportStartTime;
//
////    @NotNull(message = "结束时间不能为空")
//    @Schema(description = "结束时间 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
//    private LocalDateTime reportEndTime;


    @Schema(description = "生成状态")
    private String generateStatus;
}
