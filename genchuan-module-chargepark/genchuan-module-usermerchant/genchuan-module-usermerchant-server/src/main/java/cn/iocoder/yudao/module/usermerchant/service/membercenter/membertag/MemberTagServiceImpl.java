package cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.MemberTagCreateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.MemberTagPageReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.MemberTagUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.membertag.MemberTagConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membertag.MemberTagMapper;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser.MemberUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员标签 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MemberTagServiceImpl implements MemberTagService {

    @Resource
    private MemberTagMapper memberTagMapper;

    @Resource
    private MemberUserService memberUserService;

    @Override
    public Long createTag(MemberTagCreateReqVO createReqVO) {
        // 校验名称唯一
        validateTagNameUnique(null, createReqVO.getName());
        // 插入
        MemberTagDO tag = MemberTagConvert.INSTANCE.convert(createReqVO);
        memberTagMapper.insert(tag);
        // 返回
        return tag.getId();
    }

    @Override
    public void updateTag(MemberTagUpdateReqVO updateReqVO) {
        // 校验存在
        validateTagExists(updateReqVO.getId());
        // 校验名称唯一
        validateTagNameUnique(updateReqVO.getId(), updateReqVO.getName());
        // 更新
        MemberTagDO updateObj = MemberTagConvert.INSTANCE.convert(updateReqVO);
        memberTagMapper.updateById(updateObj);
    }

    @Override
    public void deleteTag(Long id) {
        // 校验存在
        validateTagExists(id);
        // 校验标签下是否有用户
        validateTagHasUser(id);
        // 删除
        memberTagMapper.deleteById(id);
    }

    private void validateTagExists(Long id) {
        if (memberTagMapper.selectById(id) == null) {
            throw new ServiceException(TAG_NOT_EXISTS);
        }
    }

    private void validateTagNameUnique(Long id, String name) {
        if (StrUtil.isBlank(name)) {
            return;
        }
        MemberTagDO tag = memberTagMapper.selelctByName(name);
        if (tag == null) {
            return;
        }

        // 如果 id 为空，说明不用比较是否为相同 id 的标签
        if (id == null) {
            throw new ServiceException(TAG_NAME_EXISTS);
        }
        if (!tag.getId().equals(id)) {
            throw new ServiceException(TAG_NAME_EXISTS);
        }
    }

    void validateTagHasUser(Long id) {
        Long count = memberUserService.getUserCountByTagId(id);
        if (count > 0) {
            throw new ServiceException(TAG_HAS_USER);
        }
    }

    @Override
    public MemberTagDO getTag(Long id) {
        return memberTagMapper.selectById(id);
    }

    @Override
    public List<MemberTagDO> getTagList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return memberTagMapper.selectByIds(ids);
    }

    @Override
    public PageResult<MemberTagDO> getTagPage(MemberTagPageReqVO pageReqVO) {
        return memberTagMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MemberTagDO> getTagList() {
        return memberTagMapper.selectList();
    }

}
