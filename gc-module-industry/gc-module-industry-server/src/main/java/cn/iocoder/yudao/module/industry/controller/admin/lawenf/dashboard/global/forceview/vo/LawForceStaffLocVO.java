package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "执法人员定位信息返回对象")
public class LawForceStaffLocVO {

    @Schema(description = "执法人员ID")
    private String lawEnfUserId;

    @Schema(description = "执法人员姓名")
    private String userName;

    @Schema(description = "经度坐标")
    private Double coordX;

    @Schema(description = "纬度坐标")
    private Double coordY;

    @Schema(description = "12位地区码，省市县街道社区")
    private String regionFullCode;
}
