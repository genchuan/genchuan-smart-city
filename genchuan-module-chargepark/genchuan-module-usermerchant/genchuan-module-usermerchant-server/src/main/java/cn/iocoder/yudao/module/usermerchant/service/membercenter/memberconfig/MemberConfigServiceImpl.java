package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig.MemberConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

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
    public Long createMemberConfig(MemberConfigSaveReqVO createReqVO) {
        // 插入
        MemberConfigDO memberConfig = BeanUtils.toBean(createReqVO, MemberConfigDO.class);
        memberConfigMapper.insert(memberConfig);

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

}