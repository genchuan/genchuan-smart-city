package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 窨井盖预警监测分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManholeMonitorWarningPageReqVO extends PageParam {

    @Schema(description = "路段名称（模糊查询）", example = "中山路")
    private String roadName;

    @Schema(description = "井盖编号（模糊查询）", example = "YGM2024001")
    private String coverNo;

    @Schema(description = "异常类型", example = "倾斜超标")
    private String abnormalType;

    @Schema(description = "安全风险等级", example = "较重")
    private String riskLevel;

    @Schema(hidden = true)
    private Integer offset;

    @Schema(hidden = true)
    private Integer limit;

    /**
     * 设置分页偏移量和每页大小
     */
    public void setOffset(Integer pageNo, Integer pageSize) {
        if (pageNo != null && pageSize != null && pageNo > 0) {
            this.offset = (pageNo - 1) * pageSize;
            this.limit = pageSize;
        }
    }


}
