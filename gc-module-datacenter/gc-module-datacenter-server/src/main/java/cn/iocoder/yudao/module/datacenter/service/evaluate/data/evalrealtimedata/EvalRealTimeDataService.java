package cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalrealtimedata;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalrealtimedata.EvalRealTimeDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 实时监测数据接入 Service 接口
 *
 * @author zcq
 */
public interface EvalRealTimeDataService {

    /**
     * 创建实时监测数据接入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalRealTimeData(@Valid EvalRealTimeDataSaveReqVO createReqVO);

    /**
     * 更新实时监测数据接入
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalRealTimeData(@Valid EvalRealTimeDataSaveReqVO updateReqVO);

    /**
     * 删除实时监测数据接入
     *
     * @param id 编号
     */
    void deleteEvalRealTimeData(Long id);

    /**
     * 获得实时监测数据接入
     *
     * @param id 编号
     * @return 实时监测数据接入
     */
    EvalRealTimeDataDO getEvalRealTimeData(Long id);

    /**
     * 获得实时监测数据接入分页
     *
     * @param pageReqVO 分页查询
     * @return 实时监测数据接入分页
     */
    PageResult<EvalRealTimeDataDO> getEvalRealTimeDataPage(EvalRealTimeDataPageReqVO pageReqVO);

}