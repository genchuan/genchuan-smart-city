package cn.iocoder.yudao.module.smartcity.service.enforcementpublicity;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.enforcementpublicity.EnforcementPublicityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.enforcementpublicity.EnforcementPublicityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 执法公示 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EnforcementPublicityServiceImpl implements EnforcementPublicityService {

    @Resource
    private EnforcementPublicityMapper enforcementPublicityMapper;

    @Override
    public Long createEnforcementPublicity(EnforcementPublicitySaveReqVO createReqVO) {
        // 插入
        EnforcementPublicityDO enforcementPublicity = BeanUtils.toBean(createReqVO, EnforcementPublicityDO.class);
        enforcementPublicityMapper.insert(enforcementPublicity);
        // 返回
        return enforcementPublicity.getId();
    }

    @Override
    public void updateEnforcementPublicity(EnforcementPublicitySaveReqVO updateReqVO) {
        // 校验存在
        validateEnforcementPublicityExists(updateReqVO.getId());
        // 更新
        EnforcementPublicityDO updateObj = BeanUtils.toBean(updateReqVO, EnforcementPublicityDO.class);
        enforcementPublicityMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnforcementPublicity(Long id) {
        // 校验存在
        validateEnforcementPublicityExists(id);
        // 删除
        enforcementPublicityMapper.deleteById(id);
    }

    private void validateEnforcementPublicityExists(Long id) {
        if (enforcementPublicityMapper.selectById(id) == null) {
            throw exception(ENFORCEMENT_PUBLICITY_NOT_EXISTS);
        }
    }

    @Override
    public EnforcementPublicityDO getEnforcementPublicity(Long id) {
        return enforcementPublicityMapper.selectById(id);
    }

    @Override
    public PageResult<EnforcementPublicityDO> getEnforcementPublicityPage(EnforcementPublicityPageReqVO pageReqVO) {
        return enforcementPublicityMapper.selectPage(pageReqVO);
    }

}