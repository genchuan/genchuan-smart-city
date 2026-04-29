package cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membertag.MemberTagMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员标签 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberTagServiceImpl implements MemberTagService {

    @Resource
    private MemberTagMapper memberTagMapper;

    @Override
    public Long createMemberTag(MemberTagSaveReqVO createReqVO) {
        // 插入
        MemberTagDO memberTag = BeanUtils.toBean(createReqVO, MemberTagDO.class);
        memberTagMapper.insert(memberTag);

        // 返回
        return memberTag.getId();
    }

    @Override
    public void updateMemberTag(MemberTagSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberTagExists(updateReqVO.getId());
        // 更新
        MemberTagDO updateObj = BeanUtils.toBean(updateReqVO, MemberTagDO.class);
        memberTagMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberTag(Long id) {
        // 校验存在
        validateMemberTagExists(id);
        // 删除
        memberTagMapper.deleteById(id);
    }

    @Override
        public void deleteMemberTagListByIds(List<Long> ids) {
        // 删除
        memberTagMapper.deleteByIds(ids);
        }


    private void validateMemberTagExists(Long id) {
        if (memberTagMapper.selectById(id) == null) {
            throw exception(MEMBER_TAG_NOT_EXISTS);
        }
    }

    @Override
    public MemberTagDO getMemberTag(Long id) {
        return memberTagMapper.selectById(id);
    }

    @Override
    public PageResult<MemberTagDO> getMemberTagPage(MemberTagPageReqVO pageReqVO) {
        return memberTagMapper.selectPage(pageReqVO);
    }

}