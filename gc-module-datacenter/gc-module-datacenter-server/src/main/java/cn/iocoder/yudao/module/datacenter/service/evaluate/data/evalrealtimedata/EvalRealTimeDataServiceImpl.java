package cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalrealtimedata;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalrealtimedata.EvalRealTimeDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.data.evalrealtimedata.EvalRealTimeDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 实时监测数据接入 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalRealTimeDataServiceImpl implements EvalRealTimeDataService {

    @Resource
    private EvalRealTimeDataMapper evalRealTimeDataMapper;

    @Override
    public Long createEvalRealTimeData(EvalRealTimeDataSaveReqVO createReqVO) {
        // 插入
        EvalRealTimeDataDO evalRealTimeData = BeanUtils.toBean(createReqVO, EvalRealTimeDataDO.class);
        evalRealTimeDataMapper.insert(evalRealTimeData);
        // 返回
        return evalRealTimeData.getId();
    }

    @Override
    public void updateEvalRealTimeData(EvalRealTimeDataSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalRealTimeDataExists(updateReqVO.getId());
        // 更新
        EvalRealTimeDataDO updateObj = BeanUtils.toBean(updateReqVO, EvalRealTimeDataDO.class);
        evalRealTimeDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalRealTimeData(Long id) {
        // 校验存在
        validateEvalRealTimeDataExists(id);
        // 删除
        evalRealTimeDataMapper.deleteById(id);
    }

    private void validateEvalRealTimeDataExists(Long id) {
        if (evalRealTimeDataMapper.selectById(id) == null) {
            throw exception(EVAL_REAL_TIME_DATA_NOT_EXISTS);
        }
    }

    @Override
    public EvalRealTimeDataDO getEvalRealTimeData(Long id) {
        return evalRealTimeDataMapper.selectById(id);
    }

    @Override
    public PageResult<EvalRealTimeDataDO> getEvalRealTimeDataPage(EvalRealTimeDataPageReqVO pageReqVO) {
        return evalRealTimeDataMapper.selectPage(pageReqVO);
    }

}