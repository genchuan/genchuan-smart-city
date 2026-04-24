
package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 楼栋床位占比统计 response VO")
@Data
public class BedMgmtBedDistributionRespVO {

    @Schema(description = "各楼栋床位统计列表")
    private List<String> labels;
    @Schema(description = "各楼栋床位统计列表")
    private List<BigDecimal> data;

}