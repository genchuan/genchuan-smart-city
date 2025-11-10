package cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.evtprocesstrace;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTraceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.evtprocesstrace.EvtProcessTraceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.retroanalytmgmt.evtprocesstrace.EvtProcessTraceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 事件过程回溯 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtProcessTraceServiceImpl implements EvtProcessTraceService {

    @Resource
    private EvtProcessTraceMapper evtProcessTraceMapper;

    @Override
    public Long createEvtProcessTrace(EvtProcessTraceSaveReqVO createReqVO) {
        // 插入
        EvtProcessTraceDO evtProcessTrace = BeanUtils.toBean(createReqVO, EvtProcessTraceDO.class);
        evtProcessTraceMapper.insert(evtProcessTrace);
        // 返回
        return evtProcessTrace.getId();
    }

    @Override
    public void updateEvtProcessTrace(EvtProcessTraceSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtProcessTraceExists(updateReqVO.getId());
        // 更新
        EvtProcessTraceDO updateObj = BeanUtils.toBean(updateReqVO, EvtProcessTraceDO.class);
        evtProcessTraceMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtProcessTrace(Long id) {
        // 校验存在
        validateEvtProcessTraceExists(id);
        // 删除
        evtProcessTraceMapper.deleteById(id);
    }

    private void validateEvtProcessTraceExists(Long id) {
        if (evtProcessTraceMapper.selectById(id) == null) {
            throw exception(EVT_PROCESS_TRACE_NOT_EXISTS);
        }
    }

    @Override
    public EvtProcessTraceDO getEvtProcessTrace(Long id) {
        return evtProcessTraceMapper.selectById(id);
    }

    @Override
    public PageResult<EvtProcessTraceDO> getEvtProcessTracePage(EvtProcessTracePageReqVO pageReqVO) {
        return evtProcessTraceMapper.selectPage(pageReqVO);
    }

}