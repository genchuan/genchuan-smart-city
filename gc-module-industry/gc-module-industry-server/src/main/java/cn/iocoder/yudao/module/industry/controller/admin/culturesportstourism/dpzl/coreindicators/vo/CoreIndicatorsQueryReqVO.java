package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "管理后台 - 文旅核心指标查询 Request VO")
public class CoreIndicatorsQueryReqVO {

    @Schema(description = "时间周期筛选（today/yesterday/recent7/recent30）", example = "today")
    @NotBlank(message = "时间周期不能为空")
    private String timeCycle;
}