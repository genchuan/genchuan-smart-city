package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.system.enums.DictTypeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)// 设置 chain = false，避免用户导入有问题
public class UserInfoImportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("用户姓名")
    private String nickname;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("用户类型：个人/企业")
    private String userType;

    @ExcelProperty("状态：正常/禁用")
    private String status;

    @ExcelProperty("注册时间")
    private LocalDateTime registerTime;

    @ExcelProperty("最后登录时间")
    private LocalDateTime loginTime;

    @ExcelProperty("钱包余额")
    private BigDecimal walletBalance;

    @ExcelProperty("绑定车辆数")
    private Integer carCount;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("备用字段1")
    private String reserve1;

    @ExcelProperty("备用字段2")
    private String reserve2;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}