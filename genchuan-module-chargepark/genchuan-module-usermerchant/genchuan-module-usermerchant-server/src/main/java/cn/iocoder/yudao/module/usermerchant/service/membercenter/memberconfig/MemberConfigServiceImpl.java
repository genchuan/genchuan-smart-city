package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigPageReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigSaveReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigUpdateReqVO;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberconfig.MemberConfigConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig.MemberConfigMapper;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig.MemberConfigService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.MEMBER_CONFIG_NOT_EXISTS;

/**
 * 会员配置 Service 实现类
 *
 * @author QingX
 */
@Service
@Validated
public class MemberConfigServiceImpl implements MemberConfigService {

    @Resource
    private MemberConfigMapper memberConfigMapper;

    @Override
    public PageResult<MemberConfigDO> getMemberConfigPage(MemberConfigPageReqVO pageReqVO) {
        return memberConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public Long createMemberConfig(MemberConfigSaveReqVO createReqVO) {
        // 插入
        MemberConfigDO memberConfig = BeanUtils.toBean(createReqVO, MemberConfigDO.class);
        memberConfigMapper.insert(memberConfig);

        // 返回
        return memberConfig.getId();
    }

    @Override
    public void saveConfig(MemberConfigSaveReqVO saveReqVO) {
        // 存在，则进行更新
        MemberConfigDO dbConfig = getConfig();
        if (dbConfig != null) {
            memberConfigMapper.updateById(MemberConfigConvert.INSTANCE.convert(saveReqVO).setId(dbConfig.getId()));
            return;
        }
        // 不存在，则进行插入
        memberConfigMapper.insert(MemberConfigConvert.INSTANCE.convert(saveReqVO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfigStatus(List<Long> ids, String Status) {
        if (org.springframework.util.CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MemberConfigDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", Status);
        memberConfigMapper.update(null, updateWrapper);
    }

    @Override
    public MemberConfigDO getMemberConfig(Long id) {
        return memberConfigMapper.selectById(id);
    }

    @Override
    public void updateConfig(MemberConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateMemberConfigExists(updateReqVO.getId());
        // 更新
        MemberConfigDO updateObj = BeanUtils.toBean(updateReqVO, MemberConfigDO.class);
        memberConfigMapper.updateById(updateObj);
    }

    private void validateMemberConfigExists(Long id) {
        if (memberConfigMapper.selectById(id) == null) {
            throw new ServiceException(MEMBER_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public MemberConfigDO getConfig() {
        List<MemberConfigDO> list = memberConfigMapper.selectList();
        return CollectionUtils.getFirst(list);
    }

}