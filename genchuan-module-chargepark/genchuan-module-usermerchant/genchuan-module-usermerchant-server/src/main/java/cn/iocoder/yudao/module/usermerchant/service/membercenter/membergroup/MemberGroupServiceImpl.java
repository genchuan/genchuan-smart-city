package cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membergroup.MemberGroupMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员分组 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberGroupServiceImpl implements MemberGroupService {

    @Resource
    private MemberGroupMapper memberGroupMapper;

    @Override
    public Long createMemberGroup(MemberGroupSaveReqVO createReqVO) {
        // 插入
        MemberGroupDO memberGroup = BeanUtils.toBean(createReqVO, MemberGroupDO.class);
        memberGroupMapper.insert(memberGroup);

        // 返回
        return memberGroup.getId();
    }

    @Override
    public void updateMemberGroup(MemberGroupSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberGroupExists(updateReqVO.getId());
        // 更新
        MemberGroupDO updateObj = BeanUtils.toBean(updateReqVO, MemberGroupDO.class);
        memberGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberGroup(Long id) {
        // 校验存在
        validateMemberGroupExists(id);
        // 删除
        memberGroupMapper.deleteById(id);
    }

    @Override
        public void deleteMemberGroupListByIds(List<Long> ids) {
        // 删除
        memberGroupMapper.deleteByIds(ids);
        }


    private void validateMemberGroupExists(Long id) {
        if (memberGroupMapper.selectById(id) == null) {
            throw exception(MEMBER_GROUP_NOT_EXISTS);
        }
    }

    @Override
    public MemberGroupDO getMemberGroup(Long id) {
        return memberGroupMapper.selectById(id);
    }

    @Override
    public PageResult<MemberGroupDO> getMemberGroupPage(MemberGroupPageReqVO pageReqVO) {
        return memberGroupMapper.selectPage(pageReqVO);
    }

}