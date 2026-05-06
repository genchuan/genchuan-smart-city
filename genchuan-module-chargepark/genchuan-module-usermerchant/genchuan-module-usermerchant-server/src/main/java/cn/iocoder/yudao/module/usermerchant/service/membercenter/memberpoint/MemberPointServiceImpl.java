package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberpoint.MemberPointMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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

    @Override
    @Transactional
    public void checkPointRecord(MemberPointCheckReqVO reqVO) {
        // 1. 查询积分记录
        MemberPointDO record = memberPointMapper.selectById(reqVO.getId());
        if (record == null) {
            throw new ServiceException(MEMBER_POINT_NOT_EXISTS);
        }
        // 2. 状态校验
        if (record.getStatus().equals(1)) {
            throw new ServiceException(MEMBER_POINT_ALREADY_NORMAL);
        }
        // 3. 更新
        record.setStatus(1);
        record.setCheckResult(reqVO.getCheckResult());
        record.setCheckTime(LocalDateTime.now());
        record.setCheckBy(SecurityFrameworkUtils.getLoginUserNickname()); // 芋道框架获取当前用户
        memberPointMapper.updateById(record);
        // 4. 可选：记录操作日志
    }

}