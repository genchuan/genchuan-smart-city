package cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 访客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VisitorPageReqVO extends PageParam {

    @Schema(description = "[访客姓名]", example = "张三")
    private String visitorName;

    @Schema(description = "[手机号]")
    private String phone;

    @Schema(description = "[身份证号] 脱敏存储")
    private String idCard;

    @Schema(description = "[访问资源ID] 关联tb_asset_extend.asset_extend_id", example = "22891")
    private Long visitResourceId;

    @Schema(description = "[访问事由]", example = "不香")
    private String visitReason;

    @Schema(description = "[访问时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] visitTime;

    @Schema(description = "[预计离开时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expectLeaveTime;

    @Schema(description = "[实际离开时间] 可为NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] leaveTime;

    @Schema(description = "[登记人ID] 关联park_user.id")
    private Long registerBy;

    @Schema(description = "[登记时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] registerTime;

    @Schema(description = "[状态] 如:在访/已离场/未入场", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你猜")
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
