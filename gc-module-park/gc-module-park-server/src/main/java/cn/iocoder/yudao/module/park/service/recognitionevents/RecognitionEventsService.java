package cn.iocoder.yudao.module.park.service.recognitionevents;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.recognitionevents.RecognitionEventsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车牌识别事件 Service 接口
 *
 * @author zhucongquan
 */
public interface RecognitionEventsService {

    /**
     * 创建车牌识别事件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecognitionEvents(@Valid RecognitionEventsSaveReqVO createReqVO);

    /**
     * 更新车牌识别事件
     *
     * @param updateReqVO 更新信息
     */
    void updateRecognitionEvents(@Valid RecognitionEventsSaveReqVO updateReqVO);

    /**
     * 删除车牌识别事件
     *
     * @param id 编号
     */
    void deleteRecognitionEvents(Long id);

    /**
     * 获得车牌识别事件
     *
     * @param id 编号
     * @return 车牌识别事件
     */
    RecognitionEventsDO getRecognitionEvents(Long id);

    /**
     * 获得车牌识别事件分页
     *
     * @param pageReqVO 分页查询
     * @return 车牌识别事件分页
     */
    PageResult<RecognitionEventsDO> getRecognitionEventsPage(RecognitionEventsPageReqVO pageReqVO);

}