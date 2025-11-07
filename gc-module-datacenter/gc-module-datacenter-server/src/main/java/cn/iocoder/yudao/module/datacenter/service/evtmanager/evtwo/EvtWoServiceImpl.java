package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtwo;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtwo.EvtWoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtwo.EvtWoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件工单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtWoServiceImpl implements EvtWoService {

    @Resource
    private EvtWoMapper evtWoMapper;

    @Override
    public Long createEvtWo(EvtWoSaveReqVO createReqVO) {
        // 插入
        EvtWoDO evtWo = BeanUtils.toBean(createReqVO, EvtWoDO.class);
        evtWoMapper.insert(evtWo);
        // 返回
        return evtWo.getId();
    }

    @Override
    public void updateEvtWo(EvtWoSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtWoExists(updateReqVO.getId());
        // 更新
        EvtWoDO updateObj = BeanUtils.toBean(updateReqVO, EvtWoDO.class);
        evtWoMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtWo(Long id) {
        // 校验存在
        validateEvtWoExists(id);
        // 删除
        evtWoMapper.deleteById(id);
    }

    private void validateEvtWoExists(Long id) {
        if (evtWoMapper.selectById(id) == null) {
            throw exception(EVT_WO_NOT_EXISTS);
        }
    }

    @Override
    public EvtWoDO getEvtWo(Long id) {
        return evtWoMapper.selectById(id);
    }

    @Override
    public PageResult<EvtWoDO> getEvtWoPage(EvtWoPageReqVO pageReqVO) {
        return evtWoMapper.selectPage(pageReqVO);
    }

}