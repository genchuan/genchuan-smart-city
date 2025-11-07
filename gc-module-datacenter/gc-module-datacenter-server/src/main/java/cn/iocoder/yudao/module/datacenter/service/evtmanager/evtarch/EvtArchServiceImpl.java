package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtarch.EvtArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtarch.EvtArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件办结归档 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtArchServiceImpl implements EvtArchService {

    @Resource
    private EvtArchMapper evtArchMapper;

    @Override
    public Long createEvtArch(EvtArchSaveReqVO createReqVO) {
        // 插入
        EvtArchDO evtArch = BeanUtils.toBean(createReqVO, EvtArchDO.class);
        evtArchMapper.insert(evtArch);
        // 返回
        return evtArch.getId();
    }

    @Override
    public void updateEvtArch(EvtArchSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtArchExists(updateReqVO.getId());
        // 更新
        EvtArchDO updateObj = BeanUtils.toBean(updateReqVO, EvtArchDO.class);
        evtArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtArch(Long id) {
        // 校验存在
        validateEvtArchExists(id);
        // 删除
        evtArchMapper.deleteById(id);
    }

    private void validateEvtArchExists(Long id) {
        if (evtArchMapper.selectById(id) == null) {
            throw exception(EVT_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public EvtArchDO getEvtArch(Long id) {
        return evtArchMapper.selectById(id);
    }

    @Override
    public PageResult<EvtArchDO> getEvtArchPage(EvtArchPageReqVO pageReqVO) {
        return evtArchMapper.selectPage(pageReqVO);
    }

}