package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberuser.MemberUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员用户 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberUserServiceImpl implements MemberUserService {

    @Resource
    private MemberUserMapper memberUserMapper;

    @Override
    public Long createMemberUser(MemberUserSaveReqVO createReqVO) {
        // 插入
        MemberUserDO memberUser = BeanUtils.toBean(createReqVO, MemberUserDO.class);
        memberUserMapper.insert(memberUser);

        // 返回
        return memberUser.getId();
    }

    @Override
    public void updateMemberUser(MemberUserSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberUserExists(updateReqVO.getId());
        // 更新
        MemberUserDO updateObj = BeanUtils.toBean(updateReqVO, MemberUserDO.class);
        memberUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberUser(Long id) {
        // 校验存在
        validateMemberUserExists(id);
        // 删除
        memberUserMapper.deleteById(id);
    }

    @Override
        public void deleteMemberUserListByIds(List<Long> ids) {
        // 删除
        memberUserMapper.deleteByIds(ids);
        }


    private void validateMemberUserExists(Long id) {
        if (memberUserMapper.selectById(id) == null) {
            throw exception(MEMBER_USER_NOT_EXISTS);
        }
    }

    @Override
    public MemberUserDO getMemberUser(Long id) {
        return memberUserMapper.selectById(id);
    }

    @Override
    public PageResult<MemberUserDO> getMemberUserPage(MemberUserPageReqVO pageReqVO) {
        return memberUserMapper.selectPage(pageReqVO);
    }

}