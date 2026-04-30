package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberuser.MemberUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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

    @Override
    public Boolean importUsers(List<MemberUserImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (MemberUserImportExcelVO vo : list) {
            if (vo.getId() != null) {
                MemberUserDO existDO = memberUserMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        MemberUserDO updateDO = BeanUtils.toBean(vo, MemberUserDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        memberUserMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    MemberUserDO insertDO = BeanUtils.toBean(vo, MemberUserDO.class);
                    insertDO.setId(null);
                    memberUserMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                MemberUserDO insertDO = BeanUtils.toBean(vo, MemberUserDO.class);
                memberUserMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    public void updateUserStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberUserDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberUserMapper.update(null, updateWrapper);
    }

}