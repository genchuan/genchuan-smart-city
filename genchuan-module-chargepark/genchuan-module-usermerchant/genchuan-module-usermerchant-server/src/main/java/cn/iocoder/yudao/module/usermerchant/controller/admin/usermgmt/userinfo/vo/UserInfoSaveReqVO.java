package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户信息新增/修改 Request VO")
@Data
public class UserInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用户编号不能为空")
    private String userNo;

    @Schema(description = "用户姓名", example = "张三")
    private String nickname;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "用户类型：个人/企业", example = "个人用户")
    private String userType;

    @Schema(description = "状态：正常/禁用", example = "正常")
    private String status;

    @Schema(description = "注册时间")
    private LocalDateTime registerTime;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "钱包余额", example = "100")
    private BigDecimal walletBalance;

    @Schema(description = "绑定车辆数", example = "2")
    private Integer carCount;

    @Schema(description = "备注", example = "普通用户")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}