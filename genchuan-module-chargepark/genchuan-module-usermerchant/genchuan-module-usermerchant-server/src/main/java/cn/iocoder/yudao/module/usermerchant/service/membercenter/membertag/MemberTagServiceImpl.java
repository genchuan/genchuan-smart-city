package cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.ImportValidator;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membertag.MemberTagMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;

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
    @LogRecord(type = TYPE_MEMBER_TAG, subType = SUB_TYPE_CREATE_MEMBER_TAG,
            bizNo = "{{#memberTag.id}}",
            success = SUCCESS_CREATE_MEMBER_TAG)
    public Long createMemberTag(MemberTagSaveReqVO createReqVO) {
        // 插入
        MemberTagDO memberTag = BeanUtils.toBean(createReqVO, MemberTagDO.class);
        memberTagMapper.insert(memberTag);
        // 记录操作日志上下文
        LogRecordContext.putVariable("memberTag", memberTag);
        // 返回
        return memberTag.getId();
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_TAG, subType = SUB_TYPE_UPDATE_MEMBER_TAG,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_MEMBER_TAG)
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

    @Override
    @LogRecord(type = TYPE_MEMBER_TAG, subType = SUB_TYPE_IMPORT_MEMBER_TAG,
            bizNo = "{{#list.stream().map(MemberTagImportExcelVO::getId).collect(T(java.util.stream.Collectors).toList())}}",
            success = SUCCESS_IMPORT_MEMBER_TAG)
    public Boolean importUsers(List<MemberTagImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            throw new ServiceException(EMPTY_LIST);
        }
        int rowNum = 2;
        for (MemberTagImportExcelVO vo : list) {
            ImportValidator.validateRequiredFields(vo, rowNum);
            // 直接新增，忽略用户传入的 ID，由数据库自增生成
            MemberTagDO insertDO = BeanUtils.toBean(vo, MemberTagDO.class);
            insertDO.setId(null);   // 确保 ID 不传入，使用数据库自增
            insertDO.setCreator(SecurityFrameworkUtils.getLoginUserNickname());
            insertDO.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
            memberTagMapper.insert(insertDO);
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("list", list);
        LogRecordContext.putVariable("updateSupport", updateSupport);
        return true;
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_TAG, subType = SUB_TYPE_UPDATE_TAG_STATUS,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_UPDATE_TAG_STATUS)
    public void updateTagStatus(List<Long> ids, Integer status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberTagDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberTagMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("status", status);
    }

}