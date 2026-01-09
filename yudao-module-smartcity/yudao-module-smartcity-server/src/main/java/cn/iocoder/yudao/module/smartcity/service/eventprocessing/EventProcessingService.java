package cn.iocoder.yudao.module.smartcity.service.eventprocessing;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.EventProcessingPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.EventProcessingSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventprocessing.EventProcessingDO;
import jakarta.validation.Valid;

/**
 * 事件处理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EventProcessingService {

    /**
     * 创建事件处理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventProcessing(@Valid EventProcessingSaveReqVO createReqVO);

    /**
     * 更新事件处理
     *
     * @param updateReqVO 更新信息
     */
    void updateEventProcessing(@Valid EventProcessingSaveReqVO updateReqVO);

    /**
     * 删除事件处理
     *
     * @param id 编号
     */
    void deleteEventProcessing(Long id);

    /**
     * 获得事件处理
     *
     * @param id 编号
     * @return 事件处理
     */
    EventProcessingDO getEventProcessing(Long id);

    /**
     * 获得事件处理分页
     *
     * @param pageReqVO 分页查询
     * @return 事件处理分页
     */
    PageResult<EventProcessingDO> getEventProcessingPage(EventProcessingPageReqVO pageReqVO);

}