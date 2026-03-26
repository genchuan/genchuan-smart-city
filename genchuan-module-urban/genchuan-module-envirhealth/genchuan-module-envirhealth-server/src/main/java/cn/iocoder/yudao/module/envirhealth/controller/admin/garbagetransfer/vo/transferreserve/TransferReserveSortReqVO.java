package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "预约排号 Request VO")
public class TransferReserveSortReqVO {

    /** 预约ID（要排序的单条预约） */
    @NotNull(message = "预约ID不能为空")
    private Long id;
}