package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理模块 - 公厕投诉 批量处理 Request VO")
@Data
public class ToiletComplaintBatchHandleReqVO {

    @Schema(description = "投诉ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "ids 不能为空")
    private List<Long> ids;

    @Schema(description = "处理状态（例如：待派单/已派单/已处置）", requiredMode = Schema.RequiredMode.REQUIRED, example = "已处置")
    @NotBlank(message = "dispatchStatus 不能为空")
    private String dispatchStatus;
}