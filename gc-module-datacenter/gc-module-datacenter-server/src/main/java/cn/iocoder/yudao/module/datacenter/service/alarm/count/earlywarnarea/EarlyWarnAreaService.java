package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnarea;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnarea.EarlyWarnAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警区域维度统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnAreaService {

    /**
     * 创建预警告警区域维度统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnArea(@Valid EarlyWarnAreaSaveReqVO createReqVO);

    /**
     * 更新预警告警区域维度统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnArea(@Valid EarlyWarnAreaSaveReqVO updateReqVO);

    /**
     * 删除预警告警区域维度统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnArea(Long id);

    /**
     * 获得预警告警区域维度统计
     *
     * @param id 编号
     * @return 预警告警区域维度统计
     */
    EarlyWarnAreaDO getEarlyWarnArea(Long id);

    /**
     * 获得预警告警区域维度统计分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警区域维度统计分页
     */
    PageResult<EarlyWarnAreaDO> getEarlyWarnAreaPage(EarlyWarnAreaPageReqVO pageReqVO);

}