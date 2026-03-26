package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

// 请求VO
@Data
public class ManholeCoverRealTimeTrendReqVO {
    @NotNull(message = "指标类型不能为空")
    private Integer indicatorType; // 0-倾斜角度，1-位移距离，2-井内水位
    @NotNull(message = "租户ID不能为空")
    private String tenantId;
}