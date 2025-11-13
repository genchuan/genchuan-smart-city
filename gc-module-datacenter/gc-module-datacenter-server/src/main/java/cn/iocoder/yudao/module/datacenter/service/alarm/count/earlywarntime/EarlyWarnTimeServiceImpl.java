package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarntime;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntime.EarlyWarnTimeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarntime.EarlyWarnTimeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警时间维度统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnTimeServiceImpl implements EarlyWarnTimeService {

    @Resource
    private EarlyWarnTimeMapper earlyWarnTimeMapper;

    @Override
    public Long createEarlyWarnTime(EarlyWarnTimeSaveReqVO createReqVO) {
        // 插入
        EarlyWarnTimeDO earlyWarnTime = BeanUtils.toBean(createReqVO, EarlyWarnTimeDO.class);
        earlyWarnTimeMapper.insert(earlyWarnTime);
        // 返回
        return earlyWarnTime.getId();
    }

    @Override
    public void updateEarlyWarnTime(EarlyWarnTimeSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnTimeExists(updateReqVO.getId());
        // 更新
        EarlyWarnTimeDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnTimeDO.class);
        earlyWarnTimeMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnTime(Long id) {
        // 校验存在
        validateEarlyWarnTimeExists(id);
        // 删除
        earlyWarnTimeMapper.deleteById(id);
    }

    private void validateEarlyWarnTimeExists(Long id) {
        if (earlyWarnTimeMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_TIME_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnTimeDO getEarlyWarnTime(Long id) {
        return earlyWarnTimeMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnTimeDO> getEarlyWarnTimePage(EarlyWarnTimePageReqVO pageReqVO) {
        return earlyWarnTimeMapper.selectPage(pageReqVO);
    }

}