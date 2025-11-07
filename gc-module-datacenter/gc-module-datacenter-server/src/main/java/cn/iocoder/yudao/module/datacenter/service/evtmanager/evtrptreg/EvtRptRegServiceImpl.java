package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtrptreg;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;


import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtrptreg.EvtRptRegDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtrptreg.EvtRptRegMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件接报登记 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtRptRegServiceImpl implements EvtRptRegService {

    @Resource
    private EvtRptRegMapper evtRptRegMapper;

    @Override
    public Long createEvtRptReg(EvtRptRegSaveReqVO createReqVO) {
        // 插入
        EvtRptRegDO evtRptReg = BeanUtils.toBean(createReqVO, EvtRptRegDO.class);
        evtRptRegMapper.insert(evtRptReg);
        // 返回
        return evtRptReg.getId();
    }

    @Override
    public void updateEvtRptReg(EvtRptRegSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtRptRegExists(updateReqVO.getId());
        // 更新
        EvtRptRegDO updateObj = BeanUtils.toBean(updateReqVO, EvtRptRegDO.class);
        evtRptRegMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtRptReg(Long id) {
        // 校验存在
        validateEvtRptRegExists(id);
        // 删除
        evtRptRegMapper.deleteById(id);
    }

    private void validateEvtRptRegExists(Long id) {
        if (evtRptRegMapper.selectById(id) == null) {
            throw exception(EVT_RPT_REG_NOT_EXISTS);
        }
    }

    @Override
    public EvtRptRegDO getEvtRptReg(Long id) {
        return evtRptRegMapper.selectById(id);
    }

    @Override
    public PageResult<EvtRptRegDO> getEvtRptRegPage(EvtRptRegPageReqVO pageReqVO) {
        return evtRptRegMapper.selectPage(pageReqVO);
    }

}