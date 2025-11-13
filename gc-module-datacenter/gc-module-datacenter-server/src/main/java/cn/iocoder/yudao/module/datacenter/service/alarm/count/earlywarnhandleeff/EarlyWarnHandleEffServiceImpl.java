package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnhandleeff;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnhandleeff.EarlyWarnHandleEffDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnhandleeff.EarlyWarnHandleEffMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警处置效率统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnHandleEffServiceImpl implements EarlyWarnHandleEffService {

    @Resource
    private EarlyWarnHandleEffMapper earlyWarnHandleEffMapper;

    @Override
    public Long createEarlyWarnHandleEff(EarlyWarnHandleEffSaveReqVO createReqVO) {
        // 插入
        EarlyWarnHandleEffDO earlyWarnHandleEff = BeanUtils.toBean(createReqVO, EarlyWarnHandleEffDO.class);
        earlyWarnHandleEffMapper.insert(earlyWarnHandleEff);
        // 返回
        return earlyWarnHandleEff.getId();
    }

    @Override
    public void updateEarlyWarnHandleEff(EarlyWarnHandleEffSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnHandleEffExists(updateReqVO.getId());
        // 更新
        EarlyWarnHandleEffDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnHandleEffDO.class);
        earlyWarnHandleEffMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnHandleEff(Long id) {
        // 校验存在
        validateEarlyWarnHandleEffExists(id);
        // 删除
        earlyWarnHandleEffMapper.deleteById(id);
    }

    private void validateEarlyWarnHandleEffExists(Long id) {
        if (earlyWarnHandleEffMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_HANDLE_EFF_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnHandleEffDO getEarlyWarnHandleEff(Long id) {
        return earlyWarnHandleEffMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnHandleEffDO> getEarlyWarnHandleEffPage(EarlyWarnHandleEffPageReqVO pageReqVO) {
        return earlyWarnHandleEffMapper.selectPage(pageReqVO);
    }

}