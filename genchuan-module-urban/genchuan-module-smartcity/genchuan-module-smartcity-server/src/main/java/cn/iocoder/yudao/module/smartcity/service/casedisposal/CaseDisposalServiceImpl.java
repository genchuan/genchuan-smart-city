package cn.iocoder.yudao.module.smartcity.service.casedisposal;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.casedisposal.CaseDisposalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.casedisposal.CaseDisposalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 案件处理 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class CaseDisposalServiceImpl implements CaseDisposalService {

    @Resource
    private CaseDisposalMapper caseDisposalMapper;

    @Override
    public Long createCaseDisposal(CaseDisposalSaveReqVO createReqVO) {
        // 插入
        CaseDisposalDO caseDisposal = BeanUtils.toBean(createReqVO, CaseDisposalDO.class);
        caseDisposalMapper.insert(caseDisposal);
        // 返回
        return caseDisposal.getId();
    }

    @Override
    public void updateCaseDisposal(CaseDisposalSaveReqVO updateReqVO) {
        // 校验存在
        validateCaseDisposalExists(updateReqVO.getId());
        // 更新
        CaseDisposalDO updateObj = BeanUtils.toBean(updateReqVO, CaseDisposalDO.class);
        caseDisposalMapper.updateById(updateObj);
    }

    @Override
    public void deleteCaseDisposal(Long id) {
        // 校验存在
        validateCaseDisposalExists(id);
        // 删除
        caseDisposalMapper.deleteById(id);
    }

    private void validateCaseDisposalExists(Long id) {
        if (caseDisposalMapper.selectById(id) == null) {
            throw exception(CASE_DISPOSAL_NOT_EXISTS);
        }
    }

    @Override
    public CaseDisposalDO getCaseDisposal(Long id) {
        return caseDisposalMapper.selectById(id);
    }

    @Override
    public PageResult<CaseDisposalDO> getCaseDisposalPage(CaseDisposalPageReqVO pageReqVO) {
        return caseDisposalMapper.selectPage(pageReqVO);
    }

}