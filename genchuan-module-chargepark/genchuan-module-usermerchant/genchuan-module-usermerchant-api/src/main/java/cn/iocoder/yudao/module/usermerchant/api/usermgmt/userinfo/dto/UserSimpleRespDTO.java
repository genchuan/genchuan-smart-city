package cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户简要信息响应 DTO
 */
@Data
@Accessors(chain = true)
public class UserSimpleRespDTO implements Serializable {

    private Long id;
    private String nickname;  // 用户名（昵称）
    // 如有需要也可加其他字段，如手机号、真实姓名等，但按需求只需id和用户名
}