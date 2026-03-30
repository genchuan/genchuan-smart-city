package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 进站预约 - 确认进站请求VO
 *
 * @author 芋道源码
 */
@Data
public class TransferReserveConfirmReqVO {

    /**
     * 预约ID
     */
    @NotNull(message = "预约ID不能为空")
    private Long id;

}