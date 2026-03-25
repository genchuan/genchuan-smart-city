package cn.iocoder.yudao.module.smartcity.service.caseclosure;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseclosure.CaseClosureDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.caseclosure.CaseClosureMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 案件结案 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class CaseClosureServiceImpl implements CaseClosureService {

    @Resource
    private CaseClosureMapper caseClosureMapper;

    @Override
    public Long createCaseClosure(CaseClosureSaveReqVO createReqVO) {
        // 插入
        CaseClosureDO caseClosure = BeanUtils.toBean(createReqVO, CaseClosureDO.class);
        caseClosureMapper.insert(caseClosure);
        // 返回
        return caseClosure.getId();
    }

    @Override
    public void updateCaseClosure(CaseClosureSaveReqVO updateReqVO) {
        // 校验存在
        validateCaseClosureExists(updateReqVO.getId());
        // 更新
        CaseClosureDO updateObj = BeanUtils.toBean(updateReqVO, CaseClosureDO.class);
        caseClosureMapper.updateById(updateObj);
    }

    @Override
    public void deleteCaseClosure(Long id) {
        // 校验存在
        validateCaseClosureExists(id);
        // 删除
        caseClosureMapper.deleteById(id);
    }

    private void validateCaseClosureExists(Long id) {
        if (caseClosureMapper.selectById(id) == null) {
            throw exception(CASE_CLOSURE_NOT_EXISTS);
        }
    }

    @Override
    public CaseClosureDO getCaseClosure(Long id) {
        return caseClosureMapper.selectById(id);
    }

    @Override
    public PageResult<CaseClosureDO> getCaseClosurePage(CaseClosurePageReqVO pageReqVO) {
        return caseClosureMapper.selectPage(pageReqVO);
    }

}