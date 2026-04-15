package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户信息分页 Request VO")
@Data
public class UserInfoPageReqVO extends PageParam {

    @Schema(description = "用户姓名", example = "张三")
    private String nickname;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "用户类型：个人/企业", example = "个人用户")
    private String userType;

    @Schema(description = "状态：正常/禁用", example = "正常")
    private String status;

    @Schema(description = "注册时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] registerTime;

    @Schema(description = "最后登录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] loginTime;

    @Schema(description = "钱包余额", example = "100")
    private BigDecimal walletBalance;

    @Schema(description = "绑定车辆数", example = "2")
    private Integer carCount;

    @Schema(description = "备注", example = "普通用户")
    private String remark;

}