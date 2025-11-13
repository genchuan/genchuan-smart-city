package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnlevel;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnlevel.EarlyWarnLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnlevel.EarlyWarnLevelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警等级维度统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnLevelServiceImpl implements EarlyWarnLevelService {

    @Resource
    private EarlyWarnLevelMapper earlyWarnLevelMapper;

    @Override
    public Long createEarlyWarnLevel(EarlyWarnLevelSaveReqVO createReqVO) {
        // 插入
        EarlyWarnLevelDO earlyWarnLevel = BeanUtils.toBean(createReqVO, EarlyWarnLevelDO.class);
        earlyWarnLevelMapper.insert(earlyWarnLevel);
        // 返回
        return earlyWarnLevel.getId();
    }

    @Override
    public void updateEarlyWarnLevel(EarlyWarnLevelSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnLevelExists(updateReqVO.getId());
        // 更新
        EarlyWarnLevelDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnLevelDO.class);
        earlyWarnLevelMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnLevel(Long id) {
        // 校验存在
        validateEarlyWarnLevelExists(id);
        // 删除
        earlyWarnLevelMapper.deleteById(id);
    }

    private void validateEarlyWarnLevelExists(Long id) {
        if (earlyWarnLevelMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_LEVEL_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnLevelDO getEarlyWarnLevel(Long id) {
        return earlyWarnLevelMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnLevelDO> getEarlyWarnLevelPage(EarlyWarnLevelPageReqVO pageReqVO) {
        return earlyWarnLevelMapper.selectPage(pageReqVO);
    }

}