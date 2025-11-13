package cn.iocoder.yudao.module.datacenter.service.resmatchschedule;


import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resmatchschedule.ResMatchScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资源匹配调度 Service 接口
 *
 * @author 亘川智城
 */
public interface ResMatchScheduleService {

    /**
     * 创建资源匹配调度
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createResMatchSchedule(@Valid ResMatchScheduleSaveReqVO createReqVO);

    /**
     * 更新资源匹配调度
     *
     * @param updateReqVO 更新信息
     */
    void updateResMatchSchedule(@Valid ResMatchScheduleSaveReqVO updateReqVO);

    /**
     * 删除资源匹配调度
     *
     * @param id 编号
     */
    void deleteResMatchSchedule(Long id);

    /**
     * 获得资源匹配调度
     *
     * @param id 编号
     * @return 资源匹配调度
     */
    ResMatchScheduleDO getResMatchSchedule(Long id);

    /**
     * 获得资源匹配调度分页
     *
     * @param pageReqVO 分页查询
     * @return 资源匹配调度分页
     */
    PageResult<ResMatchScheduleDO> getResMatchSchedulePage(ResMatchSchedulePageReqVO pageReqVO);

}