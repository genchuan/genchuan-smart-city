package cn.iocoder.yudao.module.evaluate.service.pushrecord;

import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.pushrecord.PushRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.pushrecord.PushRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 结果推送记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PushRecordServiceImpl implements PushRecordService {

    @Resource
    private PushRecordMapper pushRecordMapper;

    @Override
    public Long createPushRecord(PushRecordSaveReqVO createReqVO) {
        // 插入
        PushRecordDO pushRecord = BeanUtils.toBean(createReqVO, PushRecordDO.class);
        pushRecordMapper.insert(pushRecord);
        // 返回
        return pushRecord.getId();
    }

    @Override
    public void updatePushRecord(PushRecordSaveReqVO updateReqVO) {
        // 校验存在
        validatePushRecordExists(updateReqVO.getId());
        // 更新
        PushRecordDO updateObj = BeanUtils.toBean(updateReqVO, PushRecordDO.class);
        pushRecordMapper.updateById(updateObj);
    }

    @Override
    public void deletePushRecord(Long id) {
        // 校验存在
        validatePushRecordExists(id);
        // 删除
        pushRecordMapper.deleteById(id);
    }

    private void validatePushRecordExists(Long id) {
        if (pushRecordMapper.selectById(id) == null) {
            throw exception(PUSH_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public PushRecordDO getPushRecord(Long id) {
        return pushRecordMapper.selectById(id);
    }

    @Override
    public PageResult<PushRecordDO> getPushRecordPage(PushRecordPageReqVO pageReqVO) {
        return pushRecordMapper.selectPage(pageReqVO);
    }

}