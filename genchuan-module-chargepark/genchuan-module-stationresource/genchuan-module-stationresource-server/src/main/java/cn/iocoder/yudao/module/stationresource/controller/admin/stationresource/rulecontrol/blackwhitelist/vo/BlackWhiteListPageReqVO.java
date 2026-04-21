package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 黑白名单分页 Request VO")
@Data
public class BlackWhiteListPageReqVO extends PageParam {

    @Schema(description = "[车牌号码] 唯一车牌号")
    private String plateNo;

    @Schema(description = "[名单类型] 如：白名单/黑名单", example = "1")
    private String type;

    @Schema(description = "[细分类型] 如：公务车/业主车/残疾人车/欠费车/逃费车", example = "2")
    private String subType;

    @Schema(description = "[生效时间] 名单生效开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[失效时间] 名单失效截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", example = "1")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "7329")
    private Long auditUserId;

    @Schema(description = "[拦截次数] 黑名单车辆被拦截次数", example = "3994")
    private Integer interceptCount;

    @Schema(description = "[证件信息] 相关证件信息描述")
    private String certInfo;

    @Schema(description = "[备注] 扩展说明", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
