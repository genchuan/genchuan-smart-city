package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtclassifyallocate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocateSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;


import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtclassifyallocate.EvtClassifyAllocateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtclassifyallocate.EvtClassifyAllocateMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件分级分拨 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtClassifyAllocateServiceImpl implements EvtClassifyAllocateService {

    @Resource
    private EvtClassifyAllocateMapper evtClassifyAllocateMapper;

    @Override
    public Long createEvtClassifyAllocate(EvtClassifyAllocateSaveReqVO createReqVO) {
        // 插入
        EvtClassifyAllocateDO evtClassifyAllocate = BeanUtils.toBean(createReqVO, EvtClassifyAllocateDO.class);
        evtClassifyAllocateMapper.insert(evtClassifyAllocate);
        // 返回
        return evtClassifyAllocate.getId();
    }

    @Override
    public void updateEvtClassifyAllocate(EvtClassifyAllocateSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtClassifyAllocateExists(updateReqVO.getId());
        // 更新
        EvtClassifyAllocateDO updateObj = BeanUtils.toBean(updateReqVO, EvtClassifyAllocateDO.class);
        evtClassifyAllocateMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtClassifyAllocate(Long id) {
        // 校验存在
        validateEvtClassifyAllocateExists(id);
        // 删除
        evtClassifyAllocateMapper.deleteById(id);
    }

    private void validateEvtClassifyAllocateExists(Long id) {
        if (evtClassifyAllocateMapper.selectById(id) == null) {
            throw exception(EVT_CLASSIFY_ALLOCATE_NOT_EXISTS);
        }
    }

    @Override
    public EvtClassifyAllocateDO getEvtClassifyAllocate(Long id) {
        return evtClassifyAllocateMapper.selectById(id);
    }

    @Override
    public PageResult<EvtClassifyAllocateDO> getEvtClassifyAllocatePage(EvtClassifyAllocatePageReqVO pageReqVO) {
        return evtClassifyAllocateMapper.selectPage(pageReqVO);
    }

}