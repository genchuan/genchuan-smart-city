package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 道路监测分页 Request VO")
@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BatchUpdateRoadMonitorStatusReqVO {

    @Schema(description = "[道路ID列表]，为null表示修改全部", example = "[1]")
    private List<Long> roadIdList;


    @Schema(description = "[监测状态]如:运行中/已停止不能为空", example = "运行中")
    @NotEmpty(message = "[监测状态] 如:运行中/已停止不能为空")
    private String monitorStatus;
}
