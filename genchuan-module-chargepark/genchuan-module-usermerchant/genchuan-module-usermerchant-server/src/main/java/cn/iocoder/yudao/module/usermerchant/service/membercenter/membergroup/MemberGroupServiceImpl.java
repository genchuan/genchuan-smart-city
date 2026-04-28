package cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupCreateReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupPageReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.MemberGroupUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.membergroup.MemberGroupConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membergroup.MemberGroupMapper;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup.MemberGroupService;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser.MemberUserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 用户分组 Service 实现类
 *
 * @author owen
 */
@Service
@Validated
@DS("member")
public class MemberGroupServiceImpl implements MemberGroupService {

    @Resource
    private MemberGroupMapper memberGroupMapper;

    @Resource
    private MemberUserService memberUserService;

    @Override
    public Long createGroup(MemberGroupCreateReqVO createReqVO) {
        // 插入
        MemberGroupDO group = MemberGroupConvert.INSTANCE.convert(createReqVO);
        memberGroupMapper.insert(group);
        // 返回
        return group.getId();
    }

    @Override
    public void updateGroup(MemberGroupUpdateReqVO updateReqVO) {
        // 校验存在
        validateGroupExists(updateReqVO.getId());
        // 更新
        MemberGroupDO updateObj = MemberGroupConvert.INSTANCE.convert(updateReqVO);
        memberGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteGroup(Long id) {
        // 校验存在
        validateGroupExists(id);
        // 校验分组下是否有用户
        validateGroupHasUser(id);
        // 删除
        memberGroupMapper.deleteById(id);
    }

    void validateGroupExists(Long id) {
        if (memberGroupMapper.selectById(id) == null) {
            throw new ServiceException(GROUP_NOT_EXISTS);
        }
    }

    void validateGroupHasUser(Long id) {
        Long count = memberUserService.getUserCountByGroupId(id);
        if (count > 0) {
            throw new ServiceException(GROUP_HAS_USER);
        }
    }

    @Override
    public MemberGroupDO getGroup(Long id) {
        return memberGroupMapper.selectById(id);
    }

    @Override
    public List<MemberGroupDO> getGroupList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return memberGroupMapper.selectByIds(ids);
    }

    @Override
    public PageResult<MemberGroupDO> getGroupPage(MemberGroupPageReqVO pageReqVO) {
        return memberGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MemberGroupDO> getGroupListByStatus(Integer status) {
        return memberGroupMapper.selectListByStatus(status);
    }

}
