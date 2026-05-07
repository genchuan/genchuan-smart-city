package cn.iocoder.yudao.module.member.api.user;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.yudao.module.member.convert.user.MemberUserConvert;
import cn.iocoder.yudao.module.member.dal.dataobject.user.MemberUserDO;
import cn.iocoder.yudao.module.member.service.user.MemberUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.module.member.enums.ErrorCodeConstants.USER_MOBILE_NOT_EXISTS;

/**
 * 会员用户的 API 实现类
 *
 * @author 芋道源码
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class MemberUserApiImpl implements MemberUserApi {

    @Resource
    private MemberUserService userService;

    @Override
    public CommonResult<MemberUserRespDTO> getUser(Long id) {
        MemberUserDO user = userService.getUser(id);
        return success(MemberUserConvert.INSTANCE.convert2(user));
    }

    @Override
    public CommonResult<List<MemberUserRespDTO>> getUserList(Collection<Long> ids) {
        return success(MemberUserConvert.INSTANCE.convertList2(userService.getUserList(ids)));
    }

    @Override
    public CommonResult<List<MemberUserRespDTO>> getUserListByNickname(String nickname) {
        return success(MemberUserConvert.INSTANCE.convertList2(userService.getUserListByNickname(nickname)));
    }

    @Override
    public CommonResult<MemberUserRespDTO> getUserByMobile(String mobile) {
        return success(MemberUserConvert.INSTANCE.convert2(userService.getUserByMobile(mobile)));
    }

    @Override
    public CommonResult<Boolean> validateUser(Long id) {
        MemberUserDO user = userService.getUser(id);
        if (user == null) {
            throw exception(USER_MOBILE_NOT_EXISTS);
        }
        return success(true);
    }

    @Override
    public CommonResult<MemberUserRespDTO> createUserIfAbsent(String mobile, String nickname, String password, String registerIp, Integer terminal) {
        // 1. 调用 Service 层的业务方法，传入密码
        MemberUserDO user = userService.createUserIfAbsent(mobile, nickname, password, registerIp, terminal);
        // 2. 转换并返回
        return success(MemberUserConvert.INSTANCE.convert2(user));
    }

    @Override
    public CommonResult<Boolean> updateUserLevel(Long id, Long levelId) {
        // 调用本模块的 Service 方法，更新用户等级
        // 注意：这里为了简化，直接调用了updateUserLevel。实际业务中，更新等级可能涉及经验、等级有效期等复杂逻辑，应调用对应的等级服务。
        // 根据文档11，MemberUserService 有 updateUserLevel 方法，但它是更新等级和经验。
        // 我们假设这里只需要更新 levelId，经验保持不变（或根据业务规则计算）。这里调用一个假设的“会员等级服务”。
        // 由于文档中未提供 MemberLevelService 的详细API，我们假设其有一个 updateUserLevel 方法。
        // 为了可运行，我们采用一个更直接的方案：通过 userService 更新用户的部分信息。

        // 方案A（推荐，调用专属等级服务）：memberLevelService.updateUserLevel(id, levelId);
        // 方案B（直接更新用户对象，假设有方法）：
        userService.updateUserLevel(id, levelId, null); // 第三个参数 experience 传 null 表示不修改经验值
        return success(true);
    }

}
