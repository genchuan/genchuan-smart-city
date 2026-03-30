package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "收运计划批量更新 Request VO")
@Data
public class GarbageCollectionBatchUpdateReqVO {

    @Schema(description = "计划ID列表", required = true)
    @NotEmpty(message = "计划ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "要更新的字段和值", required = true)
    private Map<String, Object> updateFields;
}