package cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 参与单位分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParticipatingUnitPageReqVO extends PageParam {

    @Schema(description = "[单位名称]", example = "李四")
    private String unitName;

    @Schema(description = "[单位类型] 如:技术支持/运维服务/合作商户", example = "2")
    private String unitType;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[合作内容]")
    private String cooperationContent;

    @Schema(description = "[合作开始时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[合作结束时间] 长期合作为NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[合作状态] 如:进行中/已终止/待开始", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
