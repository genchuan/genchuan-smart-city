package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 集团信息分页 Request VO")
@Data
public class GroupInfoPageReqVO extends PageParam {

    @Schema(description = "集团名称，唯一", example = "张三")
    private String name;

    @Schema(description = "联系人")
    private String contact;

    @Schema(description = "联系手机号")
    private String phone;

    @Schema(description = "集团类型：企业单位/事业单位/政府机构/其他", example = "2")
    private String groupType;

    @Schema(description = "集团地址")
    private String address;

    @Schema(description = "注册时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] registerTime;

    @Schema(description = "集团状态：待审核/正常/禁用/已驳回", example = "2")
    private String status;

    @Schema(description = "账户余额")
    private BigDecimal walletBalance;

    @Schema(description = "审核人ID，关联system_user.id", example = "29669")
    private Long auditorId;

    @Schema(description = "审核意见", example = "随便")
    private String auditRemark;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}