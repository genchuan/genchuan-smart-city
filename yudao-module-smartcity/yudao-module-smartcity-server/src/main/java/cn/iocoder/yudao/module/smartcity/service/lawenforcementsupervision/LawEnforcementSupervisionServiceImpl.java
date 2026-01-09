package cn.iocoder.yudao.module.smartcity.service.lawenforcementsupervision;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementsupervision.LawEnforcementSupervisionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.lawenforcementsupervision.LawEnforcementSupervisionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 执法监督 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class LawEnforcementSupervisionServiceImpl implements LawEnforcementSupervisionService {

    @Resource
    private LawEnforcementSupervisionMapper lawEnforcementSupervisionMapper;

    @Override
    public Long createLawEnforcementSupervision(LawEnforcementSupervisionSaveReqVO createReqVO) {
        // 插入
        LawEnforcementSupervisionDO lawEnforcementSupervision = BeanUtils.toBean(createReqVO, LawEnforcementSupervisionDO.class);
        lawEnforcementSupervisionMapper.insert(lawEnforcementSupervision);
        // 返回
        return lawEnforcementSupervision.getId();
    }

    @Override
    public void updateLawEnforcementSupervision(LawEnforcementSupervisionSaveReqVO updateReqVO) {
        // 校验存在
        validateLawEnforcementSupervisionExists(updateReqVO.getId());
        // 更新
        LawEnforcementSupervisionDO updateObj = BeanUtils.toBean(updateReqVO, LawEnforcementSupervisionDO.class);
        lawEnforcementSupervisionMapper.updateById(updateObj);
    }

    @Override
    public void deleteLawEnforcementSupervision(Long id) {
        // 校验存在
        validateLawEnforcementSupervisionExists(id);
        // 删除
        lawEnforcementSupervisionMapper.deleteById(id);
    }

    private void validateLawEnforcementSupervisionExists(Long id) {
        if (lawEnforcementSupervisionMapper.selectById(id) == null) {
            throw exception(LAW_ENFORCEMENT_SUPERVISION_NOT_EXISTS);
        }
    }

    @Override
    public LawEnforcementSupervisionDO getLawEnforcementSupervision(Long id) {
        return lawEnforcementSupervisionMapper.selectById(id);
    }

    @Override
    public PageResult<LawEnforcementSupervisionDO> getLawEnforcementSupervisionPage(LawEnforcementSupervisionPageReqVO pageReqVO) {
        return lawEnforcementSupervisionMapper.selectPage(pageReqVO);
    }

}