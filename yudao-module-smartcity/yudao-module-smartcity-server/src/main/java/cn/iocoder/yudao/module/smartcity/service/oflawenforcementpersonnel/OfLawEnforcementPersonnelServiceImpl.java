package cn.iocoder.yudao.module.smartcity.service.oflawenforcementpersonnel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.oflawenforcementpersonnel.OfLawEnforcementPersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.oflawenforcementpersonnel.OfLawEnforcementPersonnelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 执法人员管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class OfLawEnforcementPersonnelServiceImpl implements OfLawEnforcementPersonnelService {

    @Resource
    private OfLawEnforcementPersonnelMapper ofLawEnforcementPersonnelMapper;

    @Override
    public Long createOfLawEnforcementPersonnel(OfLawEnforcementPersonnelSaveReqVO createReqVO) {
        // 插入
        OfLawEnforcementPersonnelDO ofLawEnforcementPersonnel = BeanUtils.toBean(createReqVO, OfLawEnforcementPersonnelDO.class);
        ofLawEnforcementPersonnelMapper.insert(ofLawEnforcementPersonnel);
        // 返回
        return ofLawEnforcementPersonnel.getId();
    }

    @Override
    public void updateOfLawEnforcementPersonnel(OfLawEnforcementPersonnelSaveReqVO updateReqVO) {
        // 校验存在
        validateOfLawEnforcementPersonnelExists(updateReqVO.getId());
        // 更新
        OfLawEnforcementPersonnelDO updateObj = BeanUtils.toBean(updateReqVO, OfLawEnforcementPersonnelDO.class);
        ofLawEnforcementPersonnelMapper.updateById(updateObj);
    }

    @Override
    public void deleteOfLawEnforcementPersonnel(Long id) {
        // 校验存在
        validateOfLawEnforcementPersonnelExists(id);
        // 删除
        ofLawEnforcementPersonnelMapper.deleteById(id);
    }

    private void validateOfLawEnforcementPersonnelExists(Long id) {
        if (ofLawEnforcementPersonnelMapper.selectById(id) == null) {
            throw exception(OF_LAW_ENFORCEMENT_PERSONNEL_NOT_EXISTS);
        }
    }

    @Override
    public OfLawEnforcementPersonnelDO getOfLawEnforcementPersonnel(Long id) {
        return ofLawEnforcementPersonnelMapper.selectById(id);
    }

    @Override
    public PageResult<OfLawEnforcementPersonnelDO> getOfLawEnforcementPersonnelPage(OfLawEnforcementPersonnelPageReqVO pageReqVO) {
        return ofLawEnforcementPersonnelMapper.selectPage(pageReqVO);
    }

}