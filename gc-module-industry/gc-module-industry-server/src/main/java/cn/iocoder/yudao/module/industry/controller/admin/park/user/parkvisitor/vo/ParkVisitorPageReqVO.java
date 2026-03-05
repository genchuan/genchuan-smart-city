package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo;

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
public class ParkVisitorPageReqVO extends PageParam {

    @Schema(description = "访客姓名[访客真实姓名]", example = "赵六")
    private String visitorName;

    @Schema(description = "手机号[访客手机号]")
    private String phone;

    @Schema(description = "身份证号[访客身份证号]")
    private String idCard;

    @Schema(description = "访问资源ID[访问资源标识，关联 tb_asset_extend.asset_extend_id]", example = "7349")
    private Long visitAssetId;

    @Schema(description = "访问事由[本次访问的具体事由说明]", example = "不对")
    private String visitReason;

    @Schema(description = "访问时间[访客进入访问的时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] visitTime;

    @Schema(description = "离开时间[访客离开访问资源的时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] leaveTime;

    @Schema(description = "状态[待审核/已通过/已拒绝/已结束]", example = "2")
    private String status;

    @Schema(description = "审核人[审核人，关联 park_user.id]")
    private Long approveBy;

    @Schema(description = "审核时间[审核操作发生时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] approveTime;

    @Schema(description = "创建时间[记录创建时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "通用扩展字段1[预留扩展字段]")
    private String extCommon1;

    @Schema(description = "通用扩展字段2[预留扩展字段]")
    private String extCommon2;

    @Schema(description = "通用扩展字段3[预留扩展字段]")
    private String extCommon3;

    @Schema(description = "通用扩展字段4[预留扩展字段]")
    private String extCommon4;

    @Schema(description = "备注[访客相关备注说明]", example = "随便")
    private String remark;

}
