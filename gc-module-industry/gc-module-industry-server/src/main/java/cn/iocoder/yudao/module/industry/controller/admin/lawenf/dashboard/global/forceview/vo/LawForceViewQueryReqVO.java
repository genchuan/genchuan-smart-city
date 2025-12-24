package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 执法力量分布视图 查询 Request VO")
@Data
public class LawForceViewQueryReqVO {

        @Schema(description = "查询地区-省市县三级shortCode码，6位",example = "110000")
        private String regionShortCode;

}
