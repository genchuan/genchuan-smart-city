package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.BaseIdsVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 商户周期报表生成 Response VO")
@Data
public class CycleReportCreateRespVO{

    @Schema(description = "生成的报表ID", example = "1")
    private Long id;

    @Schema(description = "是否成功", example = "true")
    private Boolean success;

}
