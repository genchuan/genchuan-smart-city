package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarndispatch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarndispatch.EarlyWarnDispatchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarndispatch.EarlyWarnDispatchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警派单 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnDispatchServiceImpl implements EarlyWarnDispatchService {

    @Resource
    private EarlyWarnDispatchMapper earlyWarnDispatchMapper;

    @Override
    public Long createEarlyWarnDispatch(EarlyWarnDispatchSaveReqVO createReqVO) {
        // 插入
        EarlyWarnDispatchDO earlyWarnDispatch = BeanUtils.toBean(createReqVO, EarlyWarnDispatchDO.class);
        earlyWarnDispatchMapper.insert(earlyWarnDispatch);
        // 返回
        return earlyWarnDispatch.getId();
    }

    @Override
    public void updateEarlyWarnDispatch(EarlyWarnDispatchSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnDispatchExists(updateReqVO.getId());
        // 更新
        EarlyWarnDispatchDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnDispatchDO.class);
        earlyWarnDispatchMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnDispatch(Long id) {
        // 校验存在
        validateEarlyWarnDispatchExists(id);
        // 删除
        earlyWarnDispatchMapper.deleteById(id);
    }

    private void validateEarlyWarnDispatchExists(Long id) {
        if (earlyWarnDispatchMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_DISPATCH_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnDispatchDO getEarlyWarnDispatch(Long id) {
        return earlyWarnDispatchMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnDispatchDO> getEarlyWarnDispatchPage(EarlyWarnDispatchPageReqVO pageReqVO) {
        return earlyWarnDispatchMapper.selectPage(pageReqVO);
    }

}