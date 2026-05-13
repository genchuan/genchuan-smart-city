package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)// 设置 chain = false，避免用户导入有问题
public class MemberUserImportExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("密码")
    private String password;

    @ExcelProperty("状态：0-禁用，1-正常")
    private Integer status;

    @ExcelProperty("注册IP")
    private String registerIp;

    @ExcelProperty("注册终端")
    private Integer registerTerminal;

    @ExcelProperty("最后登录IP")
    private String loginIp;

    @ExcelProperty("最后登录时间")
    private LocalDateTime loginDate;

    @ExcelProperty("用户昵称")
    private String nickname;

    @ExcelProperty("头像")
    private String avatar;

    @ExcelProperty("真实姓名")
    private String name;

    @ExcelProperty("性别：0-未知，1-男，2-女")
    private Integer sex;

    @ExcelProperty("所在地区ID")
    private Long areaId;

    @ExcelProperty("出生日期")
    private LocalDateTime birthday;

    @ExcelProperty("会员备注")
    private String mark;

    @ExcelProperty("积分")
    private Integer point;

    @ExcelProperty("用户标签编号列表，逗号分隔")
    private String tagIds;

    @ExcelProperty("会员等级ID")
    private Long levelId;

    @ExcelProperty("经验值")
    private Integer experience;

    @ExcelProperty("用户分组ID")
    private Long groupId;

    @ExcelProperty("会员到期时间")
    private LocalDateTime expireTime;

    @ExcelProperty("自动续费：0-关闭，1-开启")
    private Integer autoRenew;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    private String updater;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
