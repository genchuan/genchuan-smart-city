package cn.iocoder.yudao.module.usermerchant.service.membercenter.membersign;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersign.MemberSignDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membersign.MemberSignMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员签到 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberSignServiceImpl implements MemberSignService {

    @Resource
    private MemberSignMapper memberSignMapper;

    @Override
    public Long createMemberSign(MemberSignSaveReqVO createReqVO) {
        // 插入
        MemberSignDO memberSign = BeanUtils.toBean(createReqVO, MemberSignDO.class);
        memberSignMapper.insert(memberSign);

        // 返回
        return memberSign.getId();
    }

    @Override
    public void updateMemberSign(MemberSignSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberSignExists(updateReqVO.getId());
        // 更新
        MemberSignDO updateObj = BeanUtils.toBean(updateReqVO, MemberSignDO.class);
        memberSignMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberSign(Long id) {
        // 校验存在
        validateMemberSignExists(id);
        // 删除
        memberSignMapper.deleteById(id);
    }

    @Override
        public void deleteMemberSignListByIds(List<Long> ids) {
        // 删除
        memberSignMapper.deleteByIds(ids);
        }


    private void validateMemberSignExists(Long id) {
        if (memberSignMapper.selectById(id) == null) {
            throw exception(MEMBER_SIGN_NOT_EXISTS);
        }
    }

    @Override
    public MemberSignDO getMemberSign(Long id) {
        return memberSignMapper.selectById(id);
    }

    @Override
    public PageResult<MemberSignDO> getMemberSignPage(MemberSignPageReqVO pageReqVO) {
        return memberSignMapper.selectPage(pageReqVO);
    }

}