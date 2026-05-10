package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 黑白名单创建 Request VO")
@Data
public class BlackWhiteListCreateReqVO {

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C12345")
    @NotEmpty(message = "车牌号码不能为空")
    private String plateNo;

    @Schema(description = "名单类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "白名单")
    @NotEmpty(message = "名单类型不能为空")
    private String type;

    @Schema(description = "细分类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "业主车")
    @NotEmpty(message = "细分类型不能为空")
    private String subType;

    @Schema(description = "生效时间", example = "2025-01-01 01:00:00")
    private LocalDateTime startTime;

    @Schema(description = "失效时间", example = "2025-02-01 01:00:00")
    private LocalDateTime endTime;

    @Schema(description = "证件信息", example = "身份证350500199001011234")
    private String certInfo;

    @Schema(description = "备注", example = "业主长期车辆")
    private String remark;

    @Schema(description = "备用字段1", example = "备用信息1")
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用信息2")
    private String reserve2;
}
