package cn.iocoder.yudao.module.datacenter.service.evtmanager.evttypecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;


import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evttypecfg.EvtTypeCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evttypecfg.EvtTypeCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件类型配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtTypeCfgServiceImpl implements EvtTypeCfgService {

    @Resource
    private EvtTypeCfgMapper evtTypeCfgMapper;

    @Override
    public Long createEvtTypeCfg(EvtTypeCfgSaveReqVO createReqVO) {
        // 插入
        EvtTypeCfgDO evtTypeCfg = BeanUtils.toBean(createReqVO, EvtTypeCfgDO.class);
        evtTypeCfgMapper.insert(evtTypeCfg);
        // 返回
        return evtTypeCfg.getId();
    }

    @Override
    public void updateEvtTypeCfg(EvtTypeCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtTypeCfgExists(updateReqVO.getId());
        // 更新
        EvtTypeCfgDO updateObj = BeanUtils.toBean(updateReqVO, EvtTypeCfgDO.class);
        evtTypeCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtTypeCfg(Long id) {
        // 校验存在
        validateEvtTypeCfgExists(id);
        // 删除
        evtTypeCfgMapper.deleteById(id);
    }

    private void validateEvtTypeCfgExists(Long id) {
        if (evtTypeCfgMapper.selectById(id) == null) {
            throw exception(EVT_TYPE_CFG_NOT_EXISTS);
        }
    }

    @Override
    public EvtTypeCfgDO getEvtTypeCfg(Long id) {
        return evtTypeCfgMapper.selectById(id);
    }

    @Override
    public PageResult<EvtTypeCfgDO> getEvtTypeCfgPage(EvtTypeCfgPageReqVO pageReqVO) {
        return evtTypeCfgMapper.selectPage(pageReqVO);
    }

}