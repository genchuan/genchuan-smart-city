package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 垃圾异常记录批量处置状态更新 Request VO")
@Data
public class GarbageAbnormalBatchHandleReqVO {

    @Schema(description = "异常记录ID列表", required = true)
    @NotEmpty(message = "异常记录ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "处置状态", required = true, example = "已办结")
    @NotNull(message = "处置状态不能为空")
    private String handleStatus;

}