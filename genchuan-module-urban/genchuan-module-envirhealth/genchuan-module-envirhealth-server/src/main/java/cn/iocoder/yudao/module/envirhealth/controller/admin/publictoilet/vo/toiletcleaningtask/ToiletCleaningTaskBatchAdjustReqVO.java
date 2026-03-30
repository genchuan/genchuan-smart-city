package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/10 11:12
 */
@Schema(description = "环境卫生管理 - 公厕保洁任务批量调整 Request VO")
@Data
public class ToiletCleaningTaskBatchAdjustReqVO {

    @Schema(description = "任务ID列表", required = true)
    @NotEmpty(message = "任务ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "要更新的字段和值", required = true)
    private Map<String, Object> updateFields;
}