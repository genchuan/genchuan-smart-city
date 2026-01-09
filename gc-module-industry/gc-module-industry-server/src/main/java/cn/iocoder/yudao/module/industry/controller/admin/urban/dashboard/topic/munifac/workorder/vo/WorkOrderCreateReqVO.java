package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 市政设施专题-派发工单 新增 Request VO")
@Data
public class WorkOrderCreateReqVO {
    @Schema(description = "设施ID，关联 biz_mng_comp.mng_comp_id", example = "MF-001")
    private String mngCompId;

}
