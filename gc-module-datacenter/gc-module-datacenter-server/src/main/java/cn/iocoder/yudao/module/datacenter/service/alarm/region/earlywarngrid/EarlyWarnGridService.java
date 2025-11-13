package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarngrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarngrid.EarlyWarnGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按网格分域预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnGridService {

    /**
     * 创建按网格分域预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnGrid(@Valid EarlyWarnGridSaveReqVO createReqVO);

    /**
     * 更新按网格分域预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnGrid(@Valid EarlyWarnGridSaveReqVO updateReqVO);

    /**
     * 删除按网格分域预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnGrid(Long id);

    /**
     * 获得按网格分域预警告警统计
     *
     * @param id 编号
     * @return 按网格分域预警告警统计
     */
    EarlyWarnGridDO getEarlyWarnGrid(Long id);

    /**
     * 获得按网格分域预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按网格分域预警告警统计分页
     */
    PageResult<EarlyWarnGridDO> getEarlyWarnGridPage(EarlyWarnGridPageReqVO pageReqVO);

}