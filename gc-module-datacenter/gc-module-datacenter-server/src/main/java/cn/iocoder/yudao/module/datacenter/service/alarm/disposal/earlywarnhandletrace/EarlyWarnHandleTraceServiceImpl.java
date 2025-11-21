package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnhandletrace;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTraceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandletrace.EarlyWarnHandleTraceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnhandletrace.EarlyWarnHandleTraceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警处置跟踪 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnHandleTraceServiceImpl implements EarlyWarnHandleTraceService {

    @Resource
    private EarlyWarnHandleTraceMapper earlyWarnHandleTraceMapper;

    @Override
    public Long createEarlyWarnHandleTrace(EarlyWarnHandleTraceSaveReqVO createReqVO) {
        // 插入
        EarlyWarnHandleTraceDO earlyWarnHandleTrace = BeanUtils.toBean(createReqVO, EarlyWarnHandleTraceDO.class);
        earlyWarnHandleTraceMapper.insert(earlyWarnHandleTrace);
        // 返回
        return earlyWarnHandleTrace.getId();
    }

    @Override
    public void updateEarlyWarnHandleTrace(EarlyWarnHandleTraceSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnHandleTraceExists(updateReqVO.getId());
        // 更新
        EarlyWarnHandleTraceDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnHandleTraceDO.class);
        earlyWarnHandleTraceMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnHandleTrace(Long id) {
        // 校验存在
        validateEarlyWarnHandleTraceExists(id);
        // 删除
        earlyWarnHandleTraceMapper.deleteById(id);
    }

    private void validateEarlyWarnHandleTraceExists(Long id) {
        if (earlyWarnHandleTraceMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_HANDLE_TRACE_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnHandleTraceDO getEarlyWarnHandleTrace(Long id) {
        return earlyWarnHandleTraceMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnHandleTraceDO> getEarlyWarnHandleTracePage(EarlyWarnHandleTracePageReqVO pageReqVO) {
        return earlyWarnHandleTraceMapper.selectPage(pageReqVO);
    }

}