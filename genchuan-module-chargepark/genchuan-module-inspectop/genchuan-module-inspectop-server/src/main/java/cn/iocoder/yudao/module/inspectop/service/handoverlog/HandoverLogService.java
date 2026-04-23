package cn.iocoder.yudao.module.inspectop.service.handoverlog;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.handoverlog.HandoverLogDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 交接日志 Service 接口
 *
 * @author zhucongquan
 */
public interface HandoverLogService {

    /**
     * 创建交接日志
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHandoverLog(@Valid HandoverLogSaveReqVO createReqVO);

    /**
     * 更新交接日志
     *
     * @param updateReqVO 更新信息
     */
    void updateHandoverLog(@Valid HandoverLogSaveReqVO updateReqVO);

    /**
     * 删除交接日志
     *
     * @param id 编号
     */
    void deleteHandoverLog(Long id);

    /**
    * 批量删除交接日志
    *
    * @param ids 编号
    */
    void deleteHandoverLogListByIds(List<Long> ids);

    /**
     * 获得交接日志
     *
     * @param id 编号
     * @return 交接日志
     */
    HandoverLogDO getHandoverLog(Long id);

    /**
     * 获得交接日志分页
     *
     * @param pageReqVO 分页查询
     * @return 交接日志分页
     */
    PageResult<HandoverLogRespVO> getHandoverLogPage(HandoverLogPageReqVO pageReqVO);

    /**
     * 确认交接日志
     *
     * @param reqVO 确认信息
     * @return 确认结果
     */
    Boolean confirmHandoverLog(@Valid HandoverLogConfirmReqVO reqVO);

    /**
     * 获取交接日志统计图表数据
     *
     * @param reqVO 查询参数
     * @return 图表数据
     */
    HandoverLogChartRespVO getHandoverLogChart(HandoverLogChartReqVO reqVO);
}