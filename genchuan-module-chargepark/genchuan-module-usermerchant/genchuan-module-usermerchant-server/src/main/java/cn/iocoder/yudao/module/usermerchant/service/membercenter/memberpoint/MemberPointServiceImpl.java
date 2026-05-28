package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberuser.MemberUserMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser.MemberUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberpoint.MemberPointMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

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

    @Resource
    private MemberUserMapper memberUserMapper;

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
//        return memberPointMapper.selectPage(pageReqVO);
        // 处理昵称模糊查询（如果有）
        if (StrUtil.isNotBlank(pageReqVO.getNickname())) {
            // 如果同时传了精确 userId，则以 userId 为准，忽略昵称
            if (pageReqVO.getUserId() == null) {
                // 查询匹配的用户ID列表
                LambdaQueryWrapper<MemberUserDO> wrapper = new LambdaQueryWrapper<>();
                wrapper.select(MemberUserDO::getId)
                        .like(MemberUserDO::getNickname, pageReqVO.getNickname())
                        .eq(MemberUserDO::getDeleted, 0);
                List<MemberUserDO> users = memberUserMapper.selectList(wrapper);
                List<Long> userIds = users.stream().map(MemberUserDO::getId).collect(Collectors.toList());
                if (userIds.isEmpty()) {
                    // 没有匹配的用户，直接返回空分页
                    return new PageResult<>(Collections.emptyList(), 0L);
                }
                pageReqVO.setUserIds(userIds);
            } // 如果已有精确userId，则忽略昵称（不清空userIds，也不设置）
        }

        // 执行分页查询（Mapper 会使用 userIds IN 条件）
        PageResult<MemberPointDO> pageResult = memberPointMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        // 填充用户昵称（用于列表展示）
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                MemberPointDO::getUserId,
                MemberPointDO::setNickname,
                "member_user", "id", "nickname"
        );

        return pageResult;
    }

    @Override
    @Transactional
    @LogRecord(type = TYPE_MEMBER_POINT, subType = SUB_TYPE_CHECK_MEMBER_POINT,
            bizNo = "{{#reqVO.id}}",
            success = SUCCESS_CHECK_MEMBER_POINT)
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
        // 记录操作日志上下文
        LogRecordContext.putVariable("reqVO", reqVO);
    }

}