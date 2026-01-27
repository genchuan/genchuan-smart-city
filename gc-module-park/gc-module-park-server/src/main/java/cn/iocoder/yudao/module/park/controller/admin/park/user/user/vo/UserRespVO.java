package cn.iocoder.yudao.module.park.controller.admin.park.user.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 系统用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "22342")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[用户名]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[用户名]")
    private String userName;

    @Schema(description = "[密码] 加密存储", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[密码] 加密存储")
    private String password;

    @Schema(description = "[手机号]")
    @ExcelProperty("[手机号]")
    private String phone;

    @Schema(description = "[身份证号] 脱敏存储")
    @ExcelProperty("[身份证号] 脱敏存储")
    private String idCard;

    @Schema(description = "[性别] 如:男/女/未知")
    @ExcelProperty("[性别] 如:男/女/未知")
    private String gender;

    @Schema(description = "[用户类型] 如:个人/企业/政府/运维/管理员", example = "1")
    @ExcelProperty("[用户类型] 如:个人/企业/政府/运维/管理员")
    private String userType;

    @Schema(description = "[认证状态] 如:未认证/已认证/认证中/已驳回", example = "1")
    @ExcelProperty("[认证状态] 如:未认证/已认证/认证中/已驳回")
    private String certStatus;

    @Schema(description = "[钱包ID] 关联park_wallet.id，可为NULL", example = "31477")
    @ExcelProperty("[钱包ID] 关联park_wallet.id，可为NULL")
    private Long walletId;

    @Schema(description = "[账号状态] 如:正常/禁用/冻结", example = "2")
    @ExcelProperty("[账号状态] 如:正常/禁用/冻结")
    private String accountStatus;

    @Schema(description = "[注册时间]")
    @ExcelProperty("[注册时间]")
    private LocalDateTime registerTime;

    @Schema(description = "[最后登录时间]")
    @ExcelProperty("[最后登录时间]")
    private LocalDateTime lastLoginTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
