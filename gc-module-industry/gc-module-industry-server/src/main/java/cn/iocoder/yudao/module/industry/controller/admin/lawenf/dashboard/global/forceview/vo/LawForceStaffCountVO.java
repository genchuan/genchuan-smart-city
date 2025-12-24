package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "各区域执法人员数量返回对象")
public class LawForceStaffCountVO {

    @Schema(description = "执法人员数量")
    private Integer staffCount;
}
