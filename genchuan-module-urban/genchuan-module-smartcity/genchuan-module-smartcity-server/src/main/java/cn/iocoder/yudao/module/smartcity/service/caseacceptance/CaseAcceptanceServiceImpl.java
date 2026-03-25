package cn.iocoder.yudao.module.smartcity.service.caseacceptance;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseacceptance.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseacceptance.CaseAcceptanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.caseacceptance.CaseAcceptanceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 案件受理 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class CaseAcceptanceServiceImpl implements CaseAcceptanceService {

    @Resource
    private CaseAcceptanceMapper caseAcceptanceMapper;

    @Override
    public Long createCaseAcceptance(CaseAcceptanceSaveReqVO createReqVO) {
        // 插入
        CaseAcceptanceDO caseAcceptance = BeanUtils.toBean(createReqVO, CaseAcceptanceDO.class);
        caseAcceptanceMapper.insert(caseAcceptance);
        // 返回
        return caseAcceptance.getId();
    }

    @Override
    public void updateCaseAcceptance(CaseAcceptanceSaveReqVO updateReqVO) {
        // 校验存在
        validateCaseAcceptanceExists(updateReqVO.getId());
        // 更新
        CaseAcceptanceDO updateObj = BeanUtils.toBean(updateReqVO, CaseAcceptanceDO.class);
        caseAcceptanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteCaseAcceptance(Long id) {
        // 校验存在
        validateCaseAcceptanceExists(id);
        // 删除
        caseAcceptanceMapper.deleteById(id);
    }

    private void validateCaseAcceptanceExists(Long id) {
        if (caseAcceptanceMapper.selectById(id) == null) {
            throw exception(CASE_ACCEPTANCE_NOT_EXISTS);
        }
    }

    @Override
    public CaseAcceptanceDO getCaseAcceptance(Long id) {
        return caseAcceptanceMapper.selectById(id);
    }

    @Override
    public PageResult<CaseAcceptanceDO> getCaseAcceptancePage(CaseAcceptancePageReqVO pageReqVO) {
        return caseAcceptanceMapper.selectPage(pageReqVO);
    }

}