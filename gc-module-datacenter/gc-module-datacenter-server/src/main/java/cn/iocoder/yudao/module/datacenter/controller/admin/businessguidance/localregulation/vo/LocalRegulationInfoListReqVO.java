package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 管理后台 - 地方法规信息列表 Request VO
 *
 * @author Gyh
 */
@Data
@Schema(description = "管理后台 - 地方法规信息列表 Request VO")
public class LocalRegulationInfoListReqVO {

    @Schema(description = "法规名称", example = "智慧城市")
    private String regulationName;

    @Schema(description = "法规类型", example = "政府规章")
    private String regulationType;

    @Schema(description = "法规状态", example = "有效")
    private String regulationStatus;

    @Schema(description = "行政区划代码", example = "330100")
    private String adminCode;
}