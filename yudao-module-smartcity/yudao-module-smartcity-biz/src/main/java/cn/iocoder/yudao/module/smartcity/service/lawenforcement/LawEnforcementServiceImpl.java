package cn.iocoder.yudao.module.smartcity.service.lawenforcement;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcement.LawEnforcementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.lawenforcement.LawEnforcementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 城市管理执法 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class LawEnforcementServiceImpl implements LawEnforcementService {

    @Resource
    private LawEnforcementMapper lawEnforcementMapper;

    @Override
    public Long createLawEnforcement(LawEnforcementSaveReqVO createReqVO) {
        // 插入
        LawEnforcementDO lawEnforcement = BeanUtils.toBean(createReqVO, LawEnforcementDO.class);
        lawEnforcementMapper.insert(lawEnforcement);
        // 返回
        return lawEnforcement.getId();
    }

    @Override
    public void updateLawEnforcement(LawEnforcementSaveReqVO updateReqVO) {
        // 校验存在
        validateLawEnforcementExists(updateReqVO.getId());
        // 更新
        LawEnforcementDO updateObj = BeanUtils.toBean(updateReqVO, LawEnforcementDO.class);
        lawEnforcementMapper.updateById(updateObj);
    }

    @Override
    public void deleteLawEnforcement(Long id) {
        // 校验存在
        validateLawEnforcementExists(id);
        // 删除
        lawEnforcementMapper.deleteById(id);
    }

    private void validateLawEnforcementExists(Long id) {
        if (lawEnforcementMapper.selectById(id) == null) {
            throw exception(LAW_ENFORCEMENT_NOT_EXISTS);
        }
    }

    @Override
    public LawEnforcementDO getLawEnforcement(Long id) {
        return lawEnforcementMapper.selectById(id);
    }

    @Override
    public PageResult<LawEnforcementDO> getLawEnforcementPage(LawEnforcementPageReqVO pageReqVO) {
        return lawEnforcementMapper.selectPage(pageReqVO);
    }

}