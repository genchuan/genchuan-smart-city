package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 访客通行分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VisitorAccessPageReqVO extends PageParam {

    @Schema(description = "访客姓名，支持模糊查询")
    private String visitorName;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "凭证状态")
    private String ticketStatus;

    @Schema(description = "通行状态")
    private String accessStatus;

    @Schema(description = "通行开始时间")
    private String startTime;

    @Schema(description = "通行结束时间")
    private String endTime;

}
