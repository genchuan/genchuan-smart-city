package cn.iocoder.yudao.module.smartcity.service.caseinvestigation;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseinvestigation.CaseInvestigationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.caseinvestigation.CaseInvestigationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 案件调查 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class CaseInvestigationServiceImpl implements CaseInvestigationService {

    @Resource
    private CaseInvestigationMapper caseInvestigationMapper;

    @Override
    public Long createCaseInvestigation(CaseInvestigationSaveReqVO createReqVO) {
        // 插入
        CaseInvestigationDO caseInvestigation = BeanUtils.toBean(createReqVO, CaseInvestigationDO.class);
        caseInvestigationMapper.insert(caseInvestigation);
        // 返回
        return caseInvestigation.getId();
    }

    @Override
    public void updateCaseInvestigation(CaseInvestigationSaveReqVO updateReqVO) {
        // 校验存在
        validateCaseInvestigationExists(updateReqVO.getId());
        // 更新
        CaseInvestigationDO updateObj = BeanUtils.toBean(updateReqVO, CaseInvestigationDO.class);
        caseInvestigationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCaseInvestigation(Long id) {
        // 校验存在
        validateCaseInvestigationExists(id);
        // 删除
        caseInvestigationMapper.deleteById(id);
    }

    private void validateCaseInvestigationExists(Long id) {
        if (caseInvestigationMapper.selectById(id) == null) {
            throw exception(CASE_INVESTIGATION_NOT_EXISTS);
        }
    }

    @Override
    public CaseInvestigationDO getCaseInvestigation(Long id) {
        return caseInvestigationMapper.selectById(id);
    }

    @Override
    public PageResult<CaseInvestigationDO> getCaseInvestigationPage(CaseInvestigationPageReqVO pageReqVO) {
        return caseInvestigationMapper.selectPage(pageReqVO);
    }

}