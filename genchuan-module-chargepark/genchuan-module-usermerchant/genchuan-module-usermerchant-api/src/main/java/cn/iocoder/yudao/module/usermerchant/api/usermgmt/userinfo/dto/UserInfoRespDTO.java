package cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户完整信息响应 DTO（用于 RPC）
 */
@Data
@Accessors(chain = true)
public class UserInfoRespDTO implements Serializable {

    private Long id;
    private String userNo;
    private String nickname;
    private String phone;
    private String idCard;
    private String Avatar;
    private String userType;
    private String status;
    private LocalDateTime registerTime;
    private LocalDateTime loginTime;
    private BigDecimal walletBalance;
    private Integer carCount;
    private String remark;
    private String reserve1;
    private String reserve2;
    private String creator;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}