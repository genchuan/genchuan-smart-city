package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtrpt.MonEvtRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtrpt.MonEvtRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件统计报 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonEvtRptServiceImpl implements MonEvtRptService {

    @Resource
    private MonEvtRptMapper monEvtRptMapper;

    @Override
    public Long createMonEvtRpt(MonEvtRptSaveReqVO createReqVO) {
        // 插入
        MonEvtRptDO monEvtRpt = BeanUtils.toBean(createReqVO, MonEvtRptDO.class);
        monEvtRptMapper.insert(monEvtRpt);
        // 返回
        return monEvtRpt.getId();
    }

    @Override
    public void updateMonEvtRpt(MonEvtRptSaveReqVO updateReqVO) {
        // 校验存在
        validateMonEvtRptExists(updateReqVO.getId());
        // 更新
        MonEvtRptDO updateObj = BeanUtils.toBean(updateReqVO, MonEvtRptDO.class);
        monEvtRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonEvtRpt(Long id) {
        // 校验存在
        validateMonEvtRptExists(id);
        // 删除
        monEvtRptMapper.deleteById(id);
    }

    private void validateMonEvtRptExists(Long id) {
        if (monEvtRptMapper.selectById(id) == null) {
            throw exception(MON_EVT_RPT_NOT_EXISTS);
        }
    }

    @Override
    public MonEvtRptDO getMonEvtRpt(Long id) {
        return monEvtRptMapper.selectById(id);
    }

    @Override
    public PageResult<MonEvtRptDO> getMonEvtRptPage(MonEvtRptPageReqVO pageReqVO) {
        return monEvtRptMapper.selectPage(pageReqVO);
    }

}