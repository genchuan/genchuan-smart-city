package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig.MemberConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;

/**
 * 会员配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MemberConfigServiceImpl implements MemberConfigService {

    @Resource
    private MemberConfigMapper memberConfigMapper;

    @Override
    @LogRecord(type = TYPE_MEMBER_CONFIG, subType = SUB_TYPE_CREATE_MEMBER_CONFIG,
            bizNo = "{{#memberConfig.id}}",
            success = SUCCESS_CREATE_MEMBER_CONFIG)
    public Long createMemberConfig(MemberConfigSaveReqVO createReqVO) {
        // 插入
        MemberConfigDO memberConfig = BeanUtils.toBean(createReqVO, MemberConfigDO.class);
        memberConfigMapper.insert(memberConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("memberConfig", memberConfig);
        // 返回
        return memberConfig.getId();
    }

    @Override
    public void updateMemberConfig(MemberConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateMemberConfigExists(updateReqVO.getId());
        // 更新
        MemberConfigDO updateObj = BeanUtils.toBean(updateReqVO, MemberConfigDO.class);
        memberConfigMapper.updateById(updateObj);
    }

    @Override
    @LogRecord(type = TYPE_MEMBER_CONFIG, subType = SUB_TYPE_UPDATE_MEMBER_CONFIG,
            bizNo = "{{#updateReqVO.id}}",
            success = SUCCESS_UPDATE_MEMBER_CONFIG)
    public void deleteMemberConfig(Long id) {
        // 校验存在
        validateMemberConfigExists(id);
        // 删除
        memberConfigMapper.deleteById(id);
    }

    @Override
        public void deleteMemberConfigListByIds(List<Long> ids) {
        // 删除
        memberConfigMapper.deleteByIds(ids);
        }


    private void validateMemberConfigExists(Long id) {
        if (memberConfigMapper.selectById(id) == null) {
            throw exception(MEMBER_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public MemberConfigDO getMemberConfig(Long id) {
        return memberConfigMapper.selectById(id);
    }

    @Override
    public PageResult<MemberConfigDO> getMemberConfigPage(MemberConfigPageReqVO pageReqVO) {
        return memberConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean saveMemberConfig(MemberConfigSaveReqVO saveReqVO) {
        if (saveReqVO.getId() == null) {
            return createMemberConfig(saveReqVO) != null;
        }
        updateMemberConfig(saveReqVO);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MEMBER_CONFIG, subType = SUB_TYPE_UPDATE_CONFIG_STATUS,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_UPDATE_CONFIG_STATUS)
    public void updateConfigStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberConfigDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        memberConfigMapper.update(null, updateWrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("status", status);
    }

}