package cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户信息 RPC 接口
 */
@FeignClient(name = "usermerchant-server")  // 服务名，注意与 Nacos 注册的服务名一致
public interface UserInfoApi {

    /**
     * 查询所有用户（简要信息）
     * @return 用户列表
     */
    @GetMapping("/usermerchant/userinfo/list-simple")
    CommonResult<List<UserSimpleRespDTO>> getSimpleUserList();

    /**
     * 查询单个用户详情
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/usermerchant/userinfo/get-detail")
    CommonResult<UserInfoRespDTO> getDetailUser(@RequestParam("userId") Long userId);
}