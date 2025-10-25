package cn.iocoder.yudao.module.datacenter.service.monevtdatacfg;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtdatacfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtdatacfg.MonEvtDataCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monevtdatacfg.MonEvtDataCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件数据配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonEvtDataCfgServiceImpl implements MonEvtDataCfgService {

    @Resource
    private MonEvtDataCfgMapper monEvtDataCfgMapper;

    @Override
    public Long createMonEvtDataCfg(MonEvtDataCfgSaveReqVO createReqVO) {
        // 插入
        MonEvtDataCfgDO monEvtDataCfg = BeanUtils.toBean(createReqVO, MonEvtDataCfgDO.class);
        monEvtDataCfgMapper.insert(monEvtDataCfg);
        // 返回
        return monEvtDataCfg.getId();
    }

    @Override
    public void updateMonEvtDataCfg(MonEvtDataCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateMonEvtDataCfgExists(updateReqVO.getId());
        // 更新
        MonEvtDataCfgDO updateObj = BeanUtils.toBean(updateReqVO, MonEvtDataCfgDO.class);
        monEvtDataCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonEvtDataCfg(Long id) {
        // 校验存在
        validateMonEvtDataCfgExists(id);
        // 删除
        monEvtDataCfgMapper.deleteById(id);
    }

    private void validateMonEvtDataCfgExists(Long id) {
        if (monEvtDataCfgMapper.selectById(id) == null) {
            throw exception(MON_EVT_DATA_CFG_NOT_EXISTS);
        }
    }

    @Override
    public MonEvtDataCfgDO getMonEvtDataCfg(Long id) {
        return monEvtDataCfgMapper.selectById(id);
    }

    @Override
    public PageResult<MonEvtDataCfgDO> getMonEvtDataCfgPage(MonEvtDataCfgPageReqVO pageReqVO) {
        return monEvtDataCfgMapper.selectPage(pageReqVO);
    }

}