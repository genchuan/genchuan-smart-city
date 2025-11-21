package cn.iocoder.yudao.module.datacenter.service.policylegislation.industrystd;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.industrystd.IndustryStdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.industrystd.IndustryStdMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 行业规范信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IndustryStdServiceImpl implements IndustryStdService {

    @Resource
    private IndustryStdMapper industryStdMapper;

    @Override
    public Long createIndustryStd(IndustryStdSaveReqVO createReqVO) {
        // 插入
        IndustryStdDO industryStd = BeanUtils.toBean(createReqVO, IndustryStdDO.class);
        industryStdMapper.insert(industryStd);
        // 返回
        return industryStd.getId();
    }

    @Override
    public void updateIndustryStd(IndustryStdSaveReqVO updateReqVO) {
        // 校验存在
        validateIndustryStdExists(updateReqVO.getId());
        // 更新
        IndustryStdDO updateObj = BeanUtils.toBean(updateReqVO, IndustryStdDO.class);
        industryStdMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndustryStd(Long id) {
        // 校验存在
        validateIndustryStdExists(id);
        // 删除
        industryStdMapper.deleteById(id);
    }

    private void validateIndustryStdExists(Long id) {
        if (industryStdMapper.selectById(id) == null) {
            throw exception(INDUSTRY_STD_NOT_EXISTS);
        }
    }

    @Override
    public IndustryStdDO getIndustryStd(Long id) {
        return industryStdMapper.selectById(id);
    }

    @Override
    public PageResult<IndustryStdDO> getIndustryStdPage(IndustryStdPageReqVO pageReqVO) {
        return industryStdMapper.selectPage(pageReqVO);
    }

}