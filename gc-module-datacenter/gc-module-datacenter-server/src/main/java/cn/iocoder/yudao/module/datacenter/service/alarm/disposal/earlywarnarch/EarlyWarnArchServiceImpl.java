package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnarch.EarlyWarnArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnarch.EarlyWarnArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警处置归档 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnArchServiceImpl implements EarlyWarnArchService {

    @Resource
    private EarlyWarnArchMapper earlyWarnArchMapper;

    @Override
    public Long createEarlyWarnArch(EarlyWarnArchSaveReqVO createReqVO) {
        // 插入
        EarlyWarnArchDO earlyWarnArch = BeanUtils.toBean(createReqVO, EarlyWarnArchDO.class);
        earlyWarnArchMapper.insert(earlyWarnArch);
        // 返回
        return earlyWarnArch.getId();
    }

    @Override
    public void updateEarlyWarnArch(EarlyWarnArchSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnArchExists(updateReqVO.getId());
        // 更新
        EarlyWarnArchDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnArchDO.class);
        earlyWarnArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnArch(Long id) {
        // 校验存在
        validateEarlyWarnArchExists(id);
        // 删除
        earlyWarnArchMapper.deleteById(id);
    }

    private void validateEarlyWarnArchExists(Long id) {
        if (earlyWarnArchMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnArchDO getEarlyWarnArch(Long id) {
        return earlyWarnArchMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnArchDO> getEarlyWarnArchPage(EarlyWarnArchPageReqVO pageReqVO) {
        return earlyWarnArchMapper.selectPage(pageReqVO);
    }

}