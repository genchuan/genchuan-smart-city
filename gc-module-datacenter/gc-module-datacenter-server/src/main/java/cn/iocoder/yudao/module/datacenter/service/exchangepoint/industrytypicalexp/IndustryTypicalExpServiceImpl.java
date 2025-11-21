package cn.iocoder.yudao.module.datacenter.service.exchangepoint.industrytypicalexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.industrytypicalexp.IndustryTypicalExpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.exchangepoint.industrytypicalexp.IndustryTypicalExpMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 行业典型经验 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IndustryTypicalExpServiceImpl implements IndustryTypicalExpService {

    @Resource
    private IndustryTypicalExpMapper industryTypicalExpMapper;

    @Override
    public Long createIndustryTypicalExp(IndustryTypicalExpSaveReqVO createReqVO) {
        // 插入
        IndustryTypicalExpDO industryTypicalExp = BeanUtils.toBean(createReqVO, IndustryTypicalExpDO.class);
        industryTypicalExpMapper.insert(industryTypicalExp);
        // 返回
        return industryTypicalExp.getId();
    }

    @Override
    public void updateIndustryTypicalExp(IndustryTypicalExpSaveReqVO updateReqVO) {
        // 校验存在
        validateIndustryTypicalExpExists(updateReqVO.getId());
        // 更新
        IndustryTypicalExpDO updateObj = BeanUtils.toBean(updateReqVO, IndustryTypicalExpDO.class);
        industryTypicalExpMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndustryTypicalExp(Long id) {
        // 校验存在
        validateIndustryTypicalExpExists(id);
        // 删除
        industryTypicalExpMapper.deleteById(id);
    }

    private void validateIndustryTypicalExpExists(Long id) {
        if (industryTypicalExpMapper.selectById(id) == null) {
            throw exception(INDUSTRY_TYPICAL_EXP_NOT_EXISTS);
        }
    }

    @Override
    public IndustryTypicalExpDO getIndustryTypicalExp(Long id) {
        return industryTypicalExpMapper.selectById(id);
    }

    @Override
    public PageResult<IndustryTypicalExpDO> getIndustryTypicalExpPage(IndustryTypicalExpPageReqVO pageReqVO) {
        return industryTypicalExpMapper.selectPage(pageReqVO);
    }

}