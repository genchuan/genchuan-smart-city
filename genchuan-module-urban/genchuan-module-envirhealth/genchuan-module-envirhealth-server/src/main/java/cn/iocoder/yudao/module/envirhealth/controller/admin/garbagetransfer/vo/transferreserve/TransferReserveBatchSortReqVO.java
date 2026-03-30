package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TransferReserveBatchSortReqVO {

    @Schema(description = "要参与排序的预约ID集合（garbage_transfer_reserve.id）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预约ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "排序方式：EXPECTED_TIME / GARBAGE_TYPE_TIME", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序方式不能为空")
    private String sortType;
}