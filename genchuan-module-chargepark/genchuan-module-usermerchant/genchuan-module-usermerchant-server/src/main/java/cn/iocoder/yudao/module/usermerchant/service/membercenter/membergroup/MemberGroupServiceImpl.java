package cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membergroup.MemberGroupMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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
    @LogRecord(type = TYPE_MEMBER_GROUP, subType = SUB_TYPE_CREATE_MEMBER_GROUP,
            bizNo = "{{#memberGroup.id}}",
            success = SUCCESS_CREATE_MEMBER_GROUP)
    public Long createMemberGroup(MemberGroupSaveReqVO createReqVO) {
        // 插入
        MemberGroupDO memberGroup = BeanUtils.toBean(createReqVO, MemberGroupDO.class);
        memberGroupMapper.insert(memberGroup);
        // 记录操作日志上下文
        LogRecordContext.putVariable("memberGroup", memberGroup);
        // 返回
        return memberGroup.getId();
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_GROUP, subType = SUB_TYPE_UPDATE_MEMBER_GROUP,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_MEMBER_GROUP)
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

    @Override
    public Boolean saveMemberGroup(MemberGroupSaveReqVO saveReqVO) {
        if (saveReqVO.getId() == null) {
            return createMemberGroup(saveReqVO) != null;
        }
        updateMemberGroup(saveReqVO);
        return true;
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_GROUP, subType = SUB_TYPE_UPDATE_GROUPS_STATUS,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_UPDATE_GROUPS_STATUS)
    @Transactional(rollbackFor = Exception.class)
    public void updateGroupStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberGroupDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberGroupMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("status", status);
    }

}