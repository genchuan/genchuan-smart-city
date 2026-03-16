package cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo;

import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.SysArchivePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.WorkOrderPageReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 预警分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadArchivePageReqVO extends SysArchivePageReqVO {


//    @Schema(description = "[所属设施类型] 如：道路", example = "道路")
    @Schema(hidden = true)
    @NotEmpty(message = "所属设施类型不能为空")
    private String facilityType="道路";



}
