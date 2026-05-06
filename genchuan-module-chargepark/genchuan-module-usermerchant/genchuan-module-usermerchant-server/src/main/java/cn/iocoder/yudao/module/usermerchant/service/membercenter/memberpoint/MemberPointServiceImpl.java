package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberpoint.MemberPointMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 会员积分 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberPointServiceImpl implements MemberPointService {

    @Resource
    private MemberPointMapper memberPointMapper;

    @Override
    public Long createMemberPoint(MemberPointSaveReqVO createReqVO) {
        // 插入
        MemberPointDO memberPoint = BeanUtils.toBean(createReqVO, MemberPointDO.class);
        memberPointMapper.insert(memberPoint);

        // 返回
        return memberPoint.getId();
    }

    @Override
    public void updateMemberPoint(MemberPointSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberPointExists(updateReqVO.getId());
        // 更新
        MemberPointDO updateObj = BeanUtils.toBean(updateReqVO, MemberPointDO.class);
        memberPointMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberPoint(Long id) {
        // 校验存在
        validateMemberPointExists(id);
        // 删除
        memberPointMapper.deleteById(id);
    }

    @Override
        public void deleteMemberPointListByIds(List<Long> ids) {
        // 删除
        memberPointMapper.deleteByIds(ids);
        }


    private void validateMemberPointExists(Long id) {
        if (memberPointMapper.selectById(id) == null) {
            throw exception(MEMBER_POINT_NOT_EXISTS);
        }
    }

    @Override
    public MemberPointDO getMemberPoint(Long id) {
        return memberPointMapper.selectById(id);
    }

    @Override
    public PageResult<MemberPointDO> getMemberPointPage(MemberPointPageReqVO pageReqVO) {
        return memberPointMapper.selectPage(pageReqVO);
    }

}