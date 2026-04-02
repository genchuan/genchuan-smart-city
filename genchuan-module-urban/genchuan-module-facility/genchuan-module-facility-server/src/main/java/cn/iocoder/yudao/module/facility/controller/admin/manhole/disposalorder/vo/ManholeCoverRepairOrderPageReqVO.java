package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import groovy.transform.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "窨井盖维修工单分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ManholeCoverRepairOrderPageReqVO extends PageParam {

    @Schema(description = "工单编号", example = "MC-REPAIR-202504-00189")
    private String orderNo;

    @Schema(description = "关联窨井盖ID")
    private String coverId;

    @Schema(description = "关联预警ID")
    private String warnId;

    @Schema(description = "工单状态 0-待派单，1-已派单，2-维修中，3-已完成，4-已驳回，5-已取消", example = "2")
    private Integer orderStatus;

    @Schema(description = "工单类型 0-日常检修，1-异常维修，2-应急抢修", example = "1")
    private Integer orderType;

    @Schema(description = "创建时间-开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStart;

    @Schema(description = "创建时间-结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeEnd;

    @Schema(description = "派单人ID")
    private String assignUserId;

    @Schema(description = "维修人ID")
    private String repairUserId;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tenantId;
}
