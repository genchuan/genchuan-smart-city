package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 请求VO
@Data
public class ManholeCoverRealTimeDetailReqVO {
    @NotBlank(message = "井盖ID不能为空")
    private String coverId;

    @NotBlank(message = "租户ID不能为空")
    private String tenantId;
}