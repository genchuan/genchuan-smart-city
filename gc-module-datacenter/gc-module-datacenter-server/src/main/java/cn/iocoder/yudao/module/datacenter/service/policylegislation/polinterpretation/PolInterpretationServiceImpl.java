package cn.iocoder.yudao.module.datacenter.service.policylegislation.polinterpretation;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.polinterpretation.PolInterpretationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.polinterpretation.PolInterpretationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 政策解读信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PolInterpretationServiceImpl implements PolInterpretationService {

    @Resource
    private PolInterpretationMapper polInterpretationMapper;

    @Override
    public Long createPolInterpretation(PolInterpretationSaveReqVO createReqVO) {
        // 插入
        PolInterpretationDO polInterpretation = BeanUtils.toBean(createReqVO, PolInterpretationDO.class);
        polInterpretationMapper.insert(polInterpretation);
        // 返回
        return polInterpretation.getId();
    }

    @Override
    public void updatePolInterpretation(PolInterpretationSaveReqVO updateReqVO) {
        // 校验存在
        validatePolInterpretationExists(updateReqVO.getId());
        // 更新
        PolInterpretationDO updateObj = BeanUtils.toBean(updateReqVO, PolInterpretationDO.class);
        polInterpretationMapper.updateById(updateObj);
    }

    @Override
    public void deletePolInterpretation(Long id) {
        // 校验存在
        validatePolInterpretationExists(id);
        // 删除
        polInterpretationMapper.deleteById(id);
    }

    private void validatePolInterpretationExists(Long id) {
        if (polInterpretationMapper.selectById(id) == null) {
            throw exception(POL_INTERPRETATION_NOT_EXISTS);
        }
    }

    @Override
    public PolInterpretationDO getPolInterpretation(Long id) {
        return polInterpretationMapper.selectById(id);
    }

    @Override
    public PageResult<PolInterpretationDO> getPolInterpretationPage(PolInterpretationPageReqVO pageReqVO) {
        return polInterpretationMapper.selectPage(pageReqVO);
    }

}