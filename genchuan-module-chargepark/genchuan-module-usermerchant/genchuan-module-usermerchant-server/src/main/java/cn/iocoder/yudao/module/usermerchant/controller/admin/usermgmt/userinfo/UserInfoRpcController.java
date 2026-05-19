package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo.dto.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.service.usermgmt.userinfo.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 RPC 接口（供其他模块调用）")
@RestController
@RequestMapping("/api/usermerchant/userinfo")
public class UserInfoRpcController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/list-simple")
    @Operation(summary = "获取所有用户简要列表")
    public CommonResult<List<UserSimpleRespDTO>> getSimpleUserList() {
        // 注意：这里需要查询所有未删除的用户，需要扩展 Service 方法或直接使用 Mapper
        List<UserInfoDO> userList = userInfoService.getAllUsers(); // 需要新增方法
        List<UserSimpleRespDTO> result = userList.stream()
                .map(user -> new UserSimpleRespDTO()
                        .setId(user.getId())
                        .setNickname(user.getNickname()))
                .collect(Collectors.toList());
        return success(result);
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获取单个用户完整信息")
    public CommonResult<UserInfoRespDTO> getDetailUser(@RequestParam("userId") Long userId) {
        UserInfoDO user = userInfoService.getUserInfo(userId);
        if (user == null) {
            return CommonResult.error(404, "用户不存在");
        }
        UserInfoRespDTO resp = BeanUtils.toBean(user, UserInfoRespDTO.class);
        return success(resp);
    }
}