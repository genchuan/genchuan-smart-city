
package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 宿舍床位分布看板 Request VO")
@Data
public class BedMgmtChartReqVO {

    @Schema(description = "楼栋", example = "1号楼")
    private String building;

}