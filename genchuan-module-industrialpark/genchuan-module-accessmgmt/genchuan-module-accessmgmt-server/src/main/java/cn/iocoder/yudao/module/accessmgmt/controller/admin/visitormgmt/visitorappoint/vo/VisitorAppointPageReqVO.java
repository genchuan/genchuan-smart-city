package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 访客预约分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VisitorAppointPageReqVO extends PageParam {

    @Schema(description = "访客姓名，支持模糊查询")
    private String visitorName;

    @Schema(description = "身份证号，支持模糊查询")
    private String idCard;

    @Schema(description = "拜访企业，支持模糊查询")
    private String visitCompany;

    @Schema(description = "预约状态（待审核/已通过/已到访/已离园）")
    private String appointStatus;

    @Schema(description = "拜访开始时间，格式时间戳")
    private Long startTime;

    @Schema(description = "拜访结束时间，格式时间戳")
    private Long endTime;

}
