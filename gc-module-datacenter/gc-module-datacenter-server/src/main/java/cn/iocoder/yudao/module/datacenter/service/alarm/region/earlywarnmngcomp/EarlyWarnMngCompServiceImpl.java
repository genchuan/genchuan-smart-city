package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo.EarlyWarnMngCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmngcomp.EarlyWarnMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmngcomp.EarlyWarnMngCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按管理部件预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnMngCompServiceImpl implements EarlyWarnMngCompService {

    @Resource
    private EarlyWarnMngCompMapper earlyWarnMngCompMapper;

    @Override
    public Long createEarlyWarnMngComp(EarlyWarnMngCompSaveReqVO createReqVO) {
        // 插入
        EarlyWarnMngCompDO earlyWarnMngComp = BeanUtils.toBean(createReqVO, EarlyWarnMngCompDO.class);
        earlyWarnMngCompMapper.insert(earlyWarnMngComp);
        // 返回
        return earlyWarnMngComp.getId();
    }

    @Override
    public void updateEarlyWarnMngComp(EarlyWarnMngCompSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnMngCompExists(updateReqVO.getId());
        // 更新
        EarlyWarnMngCompDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnMngCompDO.class);
        earlyWarnMngCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnMngComp(Long id) {
        // 校验存在
        validateEarlyWarnMngCompExists(id);
        // 删除
        earlyWarnMngCompMapper.deleteById(id);
    }

    private void validateEarlyWarnMngCompExists(Long id) {
        if (earlyWarnMngCompMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_MNG_COMP_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnMngCompDO getEarlyWarnMngComp(Long id) {
        return earlyWarnMngCompMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnMngCompDO> getEarlyWarnMngCompPage(EarlyWarnMngCompPageReqVO pageReqVO) {
        return earlyWarnMngCompMapper.selectPage(pageReqVO);
    }

}