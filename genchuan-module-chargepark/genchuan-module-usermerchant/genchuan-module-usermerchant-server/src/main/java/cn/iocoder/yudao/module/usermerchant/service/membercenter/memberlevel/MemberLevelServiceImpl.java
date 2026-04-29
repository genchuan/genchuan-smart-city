package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberlevel.MemberLevelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员等级 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberLevelServiceImpl implements MemberLevelService {

    @Resource
    private MemberLevelMapper memberLevelMapper;

    @Override
    public Long createMemberLevel(MemberLevelSaveReqVO createReqVO) {
        // 插入
        MemberLevelDO memberLevel = BeanUtils.toBean(createReqVO, MemberLevelDO.class);
        memberLevelMapper.insert(memberLevel);

        // 返回
        return memberLevel.getId();
    }

    @Override
    public void updateMemberLevel(MemberLevelSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberLevelExists(updateReqVO.getId());
        // 更新
        MemberLevelDO updateObj = BeanUtils.toBean(updateReqVO, MemberLevelDO.class);
        memberLevelMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberLevel(Long id) {
        // 校验存在
        validateMemberLevelExists(id);
        // 删除
        memberLevelMapper.deleteById(id);
    }

    @Override
        public void deleteMemberLevelListByIds(List<Long> ids) {
        // 删除
        memberLevelMapper.deleteByIds(ids);
        }


    private void validateMemberLevelExists(Long id) {
        if (memberLevelMapper.selectById(id) == null) {
            throw exception(MEMBER_LEVEL_NOT_EXISTS);
        }
    }

    @Override
    public MemberLevelDO getMemberLevel(Long id) {
        return memberLevelMapper.selectById(id);
    }

    @Override
    public PageResult<MemberLevelDO> getMemberLevelPage(MemberLevelPageReqVO pageReqVO) {
        return memberLevelMapper.selectPage(pageReqVO);
    }

}