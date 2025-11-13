package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmonevt.EarlyWarnMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmonevt.EarlyWarnMonEvtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按监测事件预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnMonEvtServiceImpl implements EarlyWarnMonEvtService {

    @Resource
    private EarlyWarnMonEvtMapper earlyWarnMonEvtMapper;

    @Override
    public Long createEarlyWarnMonEvt(EarlyWarnMonEvtSaveReqVO createReqVO) {
        // 插入
        EarlyWarnMonEvtDO earlyWarnMonEvt = BeanUtils.toBean(createReqVO, EarlyWarnMonEvtDO.class);
        earlyWarnMonEvtMapper.insert(earlyWarnMonEvt);
        // 返回
        return earlyWarnMonEvt.getId();
    }

    @Override
    public void updateEarlyWarnMonEvt(EarlyWarnMonEvtSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnMonEvtExists(updateReqVO.getId());
        // 更新
        EarlyWarnMonEvtDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnMonEvtDO.class);
        earlyWarnMonEvtMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnMonEvt(Long id) {
        // 校验存在
        validateEarlyWarnMonEvtExists(id);
        // 删除
        earlyWarnMonEvtMapper.deleteById(id);
    }

    private void validateEarlyWarnMonEvtExists(Long id) {
        if (earlyWarnMonEvtMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_MON_EVT_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnMonEvtDO getEarlyWarnMonEvt(Long id) {
        return earlyWarnMonEvtMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnMonEvtDO> getEarlyWarnMonEvtPage(EarlyWarnMonEvtPageReqVO pageReqVO) {
        return earlyWarnMonEvtMapper.selectPage(pageReqVO);
    }

}