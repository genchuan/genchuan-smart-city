package cn.iocoder.yudao.module.park.service.recognitionevents;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo.RecognitionEventsPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo.RecognitionEventsSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.recognitionevents.RecognitionEventsDO;
import cn.iocoder.yudao.module.park.dal.mysql.recognitionevents.RecognitionEventsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.RECOGNITION_EVENTS_NOT_EXISTS;

/**
 * 车牌识别事件 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class RecognitionEventsServiceImpl implements RecognitionEventsService {

    @Resource
    private RecognitionEventsMapper recognitionEventsMapper;

    @Override
    public Long createRecognitionEvents(RecognitionEventsSaveReqVO createReqVO) {
        // 插入
        RecognitionEventsDO recognitionEvents = BeanUtils.toBean(createReqVO, RecognitionEventsDO.class);
        recognitionEventsMapper.insert(recognitionEvents);
        // 返回
        return recognitionEvents.getId();
    }

    @Override
    public void updateRecognitionEvents(RecognitionEventsSaveReqVO updateReqVO) {
        // 校验存在
        validateRecognitionEventsExists(updateReqVO.getId());
        // 更新
        RecognitionEventsDO updateObj = BeanUtils.toBean(updateReqVO, RecognitionEventsDO.class);
        recognitionEventsMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecognitionEvents(Long id) {
        // 校验存在
        validateRecognitionEventsExists(id);
        // 删除
        recognitionEventsMapper.deleteById(id);
    }

    private void validateRecognitionEventsExists(Long id) {
        if (recognitionEventsMapper.selectById(id) == null) {
            throw exception(RECOGNITION_EVENTS_NOT_EXISTS);
        }
    }

    @Override
    public RecognitionEventsDO getRecognitionEvents(Long id) {
        return recognitionEventsMapper.selectById(id);
    }

    @Override
    public PageResult<RecognitionEventsDO> getRecognitionEventsPage(RecognitionEventsPageReqVO pageReqVO) {
        return recognitionEventsMapper.selectPage(pageReqVO);
    }

}
