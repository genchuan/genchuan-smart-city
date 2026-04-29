package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员用户分页 Request VO")
@Data
public class MemberUserPageReqVO extends PageParam {

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "状态：0-禁用，1-正常", example = "2")
    private Integer status;

    @Schema(description = "注册IP")
    private String registerIp;

    @Schema(description = "注册终端")
    private Integer registerTerminal;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] loginDate;

    @Schema(description = "用户昵称", example = "张三")
    private String nickname;

    @Schema(description = "头像")
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

    @Schema(description = "积分")
    private Integer point;

    @Schema(description = "用户标签编号列表，逗号分隔")
    private String tagIds;

    @Schema(description = "会员等级ID", example = "17077")
    private Long levelId;

    @Schema(description = "经验值")
    private Integer experience;

    @Schema(description = "用户分组ID", example = "9201")
    private Long groupId;

    @Schema(description = "会员到期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "自动续费：0-关闭，1-开启")
    private Integer autoRenew;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}