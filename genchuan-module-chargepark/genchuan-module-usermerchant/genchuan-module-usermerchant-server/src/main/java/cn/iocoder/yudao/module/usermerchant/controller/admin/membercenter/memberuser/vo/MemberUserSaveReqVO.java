package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会员用户新增/修改 Request VO")
@Data
public class MemberUserSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25640")
    private Long id;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @Schema(description = "状态：0-禁用，1-正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态：0-禁用，1-正常不能为空")
    private Integer status;

    @Schema(description = "注册IP", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "注册IP不能为空")
    private String registerIp;

    @Schema(description = "注册终端")
    private Integer registerTerminal;

    @Schema(description = "最后登录IP", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "最后登录IP不能为空")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "用户昵称不能为空")
    private String nickname;

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "头像不能为空")
    private String avatar;

    @Schema(description = "真实姓名", example = "张三")
    private String name;

    @Schema(description = "性别：0-未知，1-男，2-女")
    private Integer sex;

    @Schema(description = "所在地区ID", example = "3680")
    private Long areaId;

    @Schema(description = "出生日期")
    private LocalDateTime birthday;

    @Schema(description = "会员备注")
    private String mark;

    @Schema(description = "积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "积分不能为空")
    private Integer point;

    @Schema(description = "用户标签编号列表，逗号分隔")
    private String tagIds;

    @Schema(description = "会员等级ID", example = "17077")
    private Long levelId;

    @Schema(description = "经验值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "经验值不能为空")
    private Integer experience;

    @Schema(description = "用户分组ID", example = "9201")
    private Long groupId;

    @Schema(description = "会员到期时间")
    private LocalDateTime expireTime;

    @Schema(description = "自动续费：0-关闭，1-开启", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "自动续费：0-关闭，1-开启不能为空")
    private Integer autoRenew;

}