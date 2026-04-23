package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检轨迹回放 Response VO")
@Data
public class InspectTrackReplayRespVO {

    @Schema(description = "轨迹点数组，包含经纬度、时间")
    private List<PointVO> points;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "巡检人员姓名", example = "张三")
    private String userName;

    @Schema(description = "轨迹时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "1706140800000")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private LocalDateTime trackTime;

    @Schema(description = "轨迹点VO")
    @Data
    public static class PointVO {

        @Schema(description = "经度", example = "118.675324")
        private Double lon;

        @Schema(description = "纬度", example = "24.896541")
        private Double lat;

        @Schema(description = "时间戳", example = "1775011986")
        private String time;
    }
}