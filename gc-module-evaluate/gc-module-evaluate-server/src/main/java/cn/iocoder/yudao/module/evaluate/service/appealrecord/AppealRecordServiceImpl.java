package cn.iocoder.yudao.module.evaluate.service.appealrecord;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealrecord.AppealRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.appealrecord.AppealRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 申诉复核 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AppealRecordServiceImpl implements AppealRecordService {

    @Resource
    private AppealRecordMapper appealRecordMapper;

    @Override
    public Long createAppealRecord(AppealRecordSaveReqVO createReqVO) {
        // 插入
        AppealRecordDO appealRecord = BeanUtils.toBean(createReqVO, AppealRecordDO.class);
        appealRecordMapper.insert(appealRecord);
        // 返回
        return appealRecord.getId();
    }

    @Override
    public void updateAppealRecord(AppealRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateAppealRecordExists(updateReqVO.getId());
        // 更新
        AppealRecordDO updateObj = BeanUtils.toBean(updateReqVO, AppealRecordDO.class);
        appealRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteAppealRecord(Long id) {
        // 校验存在
        validateAppealRecordExists(id);
        // 删除
        appealRecordMapper.deleteById(id);
    }

    private void validateAppealRecordExists(Long id) {
        if (appealRecordMapper.selectById(id) == null) {
            throw exception(APPEAL_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public AppealRecordDO getAppealRecord(Long id) {
        return appealRecordMapper.selectById(id);
    }

    @Override
    public PageResult<AppealRecordDO> getAppealRecordPage(AppealRecordPageReqVO pageReqVO) {
        return appealRecordMapper.selectPage(pageReqVO);
    }

}