package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 市政设施专题-标记故障处置完成 更新 Request VO")
@Data
public class FaultHdlUpdateReqVO {

    @Schema(description = "要标记的故障的部件的部件ID，mng_comp_id", example = "MF-001")
    private String mngCompId;
}
