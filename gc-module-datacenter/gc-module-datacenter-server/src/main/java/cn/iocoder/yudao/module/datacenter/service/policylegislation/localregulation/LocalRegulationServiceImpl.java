package cn.iocoder.yudao.module.datacenter.service.policylegislation.localregulation;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.localregulation.LocalRegulationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.localregulation.LocalRegulationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 地方法规信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LocalRegulationServiceImpl implements LocalRegulationService {

    @Resource
    private LocalRegulationMapper localRegulationMapper;

    @Override
    public Long createLocalRegulation(LocalRegulationSaveReqVO createReqVO) {
        // 插入
        LocalRegulationDO localRegulation = BeanUtils.toBean(createReqVO, LocalRegulationDO.class);
        localRegulationMapper.insert(localRegulation);
        // 返回
        return localRegulation.getId();
    }

    @Override
    public void updateLocalRegulation(LocalRegulationSaveReqVO updateReqVO) {
        // 校验存在
        validateLocalRegulationExists(updateReqVO.getId());
        // 更新
        LocalRegulationDO updateObj = BeanUtils.toBean(updateReqVO, LocalRegulationDO.class);
        localRegulationMapper.updateById(updateObj);
    }

    @Override
    public void deleteLocalRegulation(Long id) {
        // 校验存在
        validateLocalRegulationExists(id);
        // 删除
        localRegulationMapper.deleteById(id);
    }

    private void validateLocalRegulationExists(Long id) {
        if (localRegulationMapper.selectById(id) == null) {
            throw exception(LOCAL_REGULATION_NOT_EXISTS);
        }
    }

    @Override
    public LocalRegulationDO getLocalRegulation(Long id) {
        return localRegulationMapper.selectById(id);
    }

    @Override
    public PageResult<LocalRegulationDO> getLocalRegulationPage(LocalRegulationPageReqVO pageReqVO) {
        return localRegulationMapper.selectPage(pageReqVO);
    }

}