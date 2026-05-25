package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberlevel.MemberLevelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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
    @LogRecord(type = TYPE_MEMBER_LEVEL, subType = SUB_TYPE_CREATE_MEMBER_LEVEL,
            bizNo = "{{#memberLevel.id}}",
            success = SUCCESS_CREATE_MEMBER_LEVEL)
    public Long createMemberLevel(MemberLevelSaveReqVO createReqVO) {
        // 插入
        MemberLevelDO memberLevel = BeanUtils.toBean(createReqVO, MemberLevelDO.class);
        memberLevelMapper.insert(memberLevel);
        // 记录操作日志上下文
        LogRecordContext.putVariable("memberLevel", memberLevel);
        // 返回
        return memberLevel.getId();
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_LEVEL, subType = SUB_TYPE_UPDATE_MEMBER_LEVEL,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_MEMBER_LEVEL)
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

//    @Override
//    public PageResult<MemberLevelDO> getMemberLevelPage(MemberLevelPageReqVO pageReqVO) {
//        return memberLevelMapper.selectPage(pageReqVO);
//    }

    @Override
    public PageResult<MemberLevelDO> getMemberLevelPage(MemberLevelPageReqVO pageReqVO) {
        // 构建分页对象
        Page<MemberLevelDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 调用新方法，查询并填充用户数量
        IPage<MemberLevelDO> pageResult = memberLevelMapper.selectPageWithUserCount(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_LEVEL, subType = SUB_TYPE_UPDATE_LEVEL_STATUS,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_UPDATE_LEVEL_STATUS)
    public void updateLevelStatus(List<Long> ids, Integer status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberLevelDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberLevelMapper.update(null, updateWrapper);
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("status", status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveMemberLevel(MemberLevelSaveReqVO saveReqVO) {
        if (saveReqVO.getId() == null) {
            return createMemberLevel(saveReqVO) != null;
        }
        updateMemberLevel(saveReqVO);
        return true;
    }

}