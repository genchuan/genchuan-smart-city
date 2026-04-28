package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberauth.AuthConvert;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberuser.MemberUserConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberuser.MemberUserMapper;
import cn.iocoder.yudao.module.usermerchant.mq.producer.user.MemberUserProducer;
import cn.iocoder.yudao.module.system.api.sms.SmsCodeApi;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.iocoder.yudao.module.system.api.social.SocialClientApi;
import cn.iocoder.yudao.module.system.api.social.dto.SocialWxPhoneNumberInfoRespDTO;
import cn.iocoder.yudao.module.system.enums.sms.SmsSceneEnum;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.servlet.ServletUtils.getClientIP;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员 User Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Valid
@Slf4j
@DS("member")
public class MemberUserServiceImpl implements MemberUserService {

    @Resource
    private MemberUserMapper memberUserMapper;

    @Resource
    private SmsCodeApi smsCodeApi;

    @Resource
    private SocialClientApi socialClientApi;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MemberUserProducer memberUserProducer;

    @Override
    public MemberUserDO getUserByMobile(String mobile) {
        return memberUserMapper.selectByMobile(mobile);
    }

    @Override
    public List<MemberUserDO> getUserListByNickname(String nickname) {
        return memberUserMapper.selectListByNicknameLike(nickname);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberUserDO createUserIfAbsent(String mobile, String registerIp, Integer terminal) {
        // 用户已经存在
        MemberUserDO user = memberUserMapper.selectByMobile(mobile);
        if (user != null) {
            return user;
        }
        // 用户不存在，则进行创建
        return createUser(mobile, null, null, registerIp, terminal);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberUserDO createUser(String nickname, String avtar, String registerIp, Integer terminal) {
        return createUser(null, nickname, avtar, registerIp, terminal);
    }

    private MemberUserDO createUser(String mobile, String nickname, String avtar,
                                    String registerIp, Integer terminal) {
        // 生成密码
        String password = IdUtil.fastSimpleUUID();
        // 插入用户
        MemberUserDO user = new MemberUserDO();
        user.setMobile(mobile);
        user.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 默认开启
        user.setPassword(encodePassword(password)); // 加密密码
        user.setRegisterIp(registerIp).setRegisterTerminal(terminal);
        user.setNickname(nickname).setAvatar(avtar); // 基础信息
        if (StrUtil.isEmpty(nickname)) {
            // 昵称为空时，随机一个名字，避免一些依赖 nickname 的逻辑报错，或者有点丑。例如说，短信发送有昵称时~
            user.setNickname("用户" + RandomUtil.randomNumbers(6));
        }
        memberUserMapper.insert(user);

        // 发送 MQ 消息：用户创建
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                memberUserProducer.sendUserCreateMessage(user.getId());
            }

        });
        return user;
    }

    @Override
    public void updateUserLogin(Long id, String loginIp) {
        memberUserMapper.updateById(new MemberUserDO().setId(id)
                .setLoginIp(loginIp).setLoginDate(LocalDateTime.now()));
    }

    @Override
    public MemberUserDO getUser(Long id) {
        return memberUserMapper.selectById(id);
    }

    @Override
    public List<MemberUserDO> getUserList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return memberUserMapper.selectByIds(ids);
    }

    @Override
    public void updateUser(Long userId, AppMemberUserUpdateReqVO reqVO) {
        MemberUserDO updateObj = BeanUtils.toBean(reqVO, MemberUserDO.class).setId(userId);
        memberUserMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserMobile(Long userId, AppMemberUserUpdateMobileReqVO reqVO) {
        // 1.1 检测用户是否存在
        MemberUserDO user = validateUserExists(userId);
        // 1.2 校验新手机是否已经被绑定
        validateMobileUnique(null, reqVO.getMobile());

        // 2.1 校验旧手机和旧验证码
        // 补充说明：从安全性来说，老手机也校验 oldCode 验证码会更安全。但是由于 uni-app 商城界面暂时没做，所以这里不强制校验
        if (StrUtil.isNotEmpty(reqVO.getOldCode())) {
            smsCodeApi.useSmsCode(new SmsCodeUseReqDTO().setMobile(user.getMobile()).setCode(reqVO.getOldCode())
                    .setScene(SmsSceneEnum.MEMBER_UPDATE_MOBILE.getScene()).setUsedIp(getClientIP())).checkError();
        }
        // 2.2 使用新验证码
        smsCodeApi.useSmsCode(new SmsCodeUseReqDTO().setMobile(reqVO.getMobile()).setCode(reqVO.getCode())
                .setScene(SmsSceneEnum.MEMBER_UPDATE_MOBILE.getScene()).setUsedIp(getClientIP())).checkError();

        // 3. 更新用户手机
        memberUserMapper.updateById(MemberUserDO.builder().id(userId).mobile(reqVO.getMobile()).build());
    }

    @Override
    public void updateUserMobileByWeixin(Long userId, AppMemberUserUpdateMobileByWeixinReqVO reqVO) {
        // 1.1 获得对应的手机号信息
        SocialWxPhoneNumberInfoRespDTO phoneNumberInfo = socialClientApi.getWxMaPhoneNumberInfo(
                UserTypeEnum.MEMBER.getValue(), reqVO.getCode()).getCheckedData();
        Assert.notNull(phoneNumberInfo, "获得手机信息失败，结果为空");
        // 1.2 校验新手机是否已经被绑定
        validateMobileUnique(userId, phoneNumberInfo.getPhoneNumber());

        // 2. 更新用户手机
        memberUserMapper.updateById(MemberUserDO.builder().id(userId).mobile(phoneNumberInfo.getPhoneNumber()).build());
    }

    @Override
    public void updateUserPassword(Long userId, AppMemberUserUpdatePasswordReqVO reqVO) {
        // 检测用户是否存在
        MemberUserDO user = validateUserExists(userId);
        // 校验验证码
        smsCodeApi.useSmsCode(new SmsCodeUseReqDTO().setMobile(user.getMobile()).setCode(reqVO.getCode())
                .setScene(SmsSceneEnum.MEMBER_UPDATE_PASSWORD.getScene()).setUsedIp(getClientIP())).checkError();

        // 更新用户密码
        memberUserMapper.updateById(MemberUserDO.builder().id(userId)
                .password(passwordEncoder.encode(reqVO.getPassword())).build());
    }

    @Override
    public void resetUserPassword(AppMemberUserResetPasswordReqVO reqVO) {
        // 检验用户是否存在
        MemberUserDO user = validateUserExists(reqVO.getMobile());

        // 使用验证码
        smsCodeApi.useSmsCode(AuthConvert.INSTANCE.convert(reqVO, SmsSceneEnum.MEMBER_RESET_PASSWORD,
                getClientIP())).checkError();

        // 更新密码
        memberUserMapper.updateById(MemberUserDO.builder().id(user.getId())
                .password(passwordEncoder.encode(reqVO.getPassword())).build());
    }

    private MemberUserDO validateUserExists(String mobile) {
        MemberUserDO user = memberUserMapper.selectByMobile(mobile);
        if (user == null) {
            throw new ServiceException(USER_MOBILE_NOT_EXISTS);
        }
        return user;
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(MemberUserUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserExists(updateReqVO.getId());
        // 校验手机唯一
        validateMobileUnique(updateReqVO.getId(), updateReqVO.getMobile());

        // 更新
        MemberUserDO updateObj = MemberUserConvert.INSTANCE.convert(updateReqVO);
        memberUserMapper.updateById(updateObj);
    }

    @VisibleForTesting
    MemberUserDO validateUserExists(Long id) {
        if (id == null) {
            return null;
        }
        MemberUserDO user = memberUserMapper.selectById(id);
        if (user == null) {
            throw new ServiceException(USER_NOT_EXISTS);
        }
        return user;
    }

    @VisibleForTesting
    void validateMobileUnique(Long id, String mobile) {
        if (StrUtil.isBlank(mobile)) {
            return;
        }
        MemberUserDO user = memberUserMapper.selectByMobile(mobile);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw new ServiceException(USER_MOBILE_USED.getCode(), mobile);
        }
        if (!user.getId().equals(id)) {
            throw new ServiceException(USER_MOBILE_USED.getCode(), mobile);
        }
    }

    @Override
    public PageResult<MemberUserDO> getUserPage(MemberUserPageReqVO pageReqVO) {
        return memberUserMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateUserLevel(Long id, Long levelId, Integer experience) {
        // 0 代表无等级：防止UpdateById时，会被过滤掉的问题
        levelId = ObjectUtil.defaultIfNull(levelId, 0L);
        memberUserMapper.updateById(new MemberUserDO()
                .setId(id)
                .setLevelId(levelId).setExperience(experience)
        );
    }

    @Override
    public Long getUserCountByGroupId(Long groupId) {
        return memberUserMapper.selectCountByGroupId(groupId);
    }

    @Override
    public Long getUserCountByLevelId(Long levelId) {
        return memberUserMapper.selectCountByLevelId(levelId);
    }

    @Override
    public Long getUserCountByTagId(Long tagId) {
        return memberUserMapper.selectCountByTagId(tagId);
    }

    @Override
    public boolean updateUserPoint(Long id, Integer point) {
        if (point > 0) {
            memberUserMapper.updatePointIncr(id, point);
        } else if (point < 0) {
            return memberUserMapper.updatePointDecr(id, point) > 0;
        }
        return true;
    }

//    private MemberUserDO createUser(String mobile, String nickname, String avatar,
//                                    String registerIp, Integer terminal) {
//        // 重载一个原有方法，保持随机密码逻辑，供其他场景（如三方登录）使用
//        return createUser(mobile, nickname, avatar, registerIp, terminal, null);
//    }

    /**
     * 创建用户（支持自定义密码）
     * @param password 明文密码。为 null 时则随机生成。
     */
    private MemberUserDO createUser(String mobile, String nickname, String avatar,
                                    String registerIp, Integer terminal, String password) {
        // 生成密码：如果未传入，则随机生成
        if (StrUtil.isEmpty(password)) {
            password = IdUtil.fastSimpleUUID();
        }
        // 插入用户
        MemberUserDO user = new MemberUserDO();
        user.setMobile(mobile);
        user.setStatus(CommonStatusEnum.ENABLE.getStatus());
        user.setPassword(encodePassword(password)); // 对传入的或随机的密码进行加密
        user.setRegisterIp(registerIp).setRegisterTerminal(terminal);
        user.setNickname(nickname).setAvatar(avatar);
        if (StrUtil.isEmpty(nickname)) {
            user.setNickname("用户" + RandomUtil.randomNumbers(6));
        }
        memberUserMapper.insert(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberUserDO createUserIfAbsent(String mobile, String nickname, String password, String registerIp, Integer terminal) {
        MemberUserDO user = memberUserMapper.selectByMobile(mobile);
        if (user != null) {
            return user;
        }
        // 调用私有的 createUser 方法，传入昵称和密码
        return createUser(mobile, nickname, null, registerIp, terminal, password);
    }

}
