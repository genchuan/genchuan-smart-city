package cn.iocoder.yudao.module.kitchen.service.aialertmessage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import jakarta.validation.Valid;

/**
 * AI告警消息 Service 接口
 *
 * @author 亘川智城
 */
public interface AiAlertMessageService {

    /**
     * 创建AI告警消息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAiAlertMessage(@Valid AiAlertMessageSaveReqVO createReqVO);

    /**
     * 更新AI告警消息
     *
     * @param updateReqVO 更新信息
     */
    void updateAiAlertMessage(@Valid AiAlertMessageSaveReqVO updateReqVO);

    /**
     * 删除AI告警消息
     *
     * @param id 编号
     */
    void deleteAiAlertMessage(Long id);

    /**
     * 获得AI告警消息
     *
     * @param id 编号
     * @return AI告警消息
     */
    AiAlertMessageDO getAiAlertMessage(Long id);

    /**
     * 获得AI告警消息分页
     *
     * @param pageReqVO 分页查询
     * @return AI告警消息分页
     */
    PageResult<AiAlertMessageDO> getAiAlertMessagePage(AiAlertMessagePageReqVO pageReqVO);

}
