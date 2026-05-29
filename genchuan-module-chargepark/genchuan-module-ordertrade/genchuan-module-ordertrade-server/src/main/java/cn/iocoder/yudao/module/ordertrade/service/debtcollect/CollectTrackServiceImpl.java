package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectTrackDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.CollectTrackMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.COLLECT_TRACK_NOT_EXISTS;

/**
 * 追缴跟踪 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class CollectTrackServiceImpl implements CollectTrackService {

    @Resource private CollectTrackMapper collectTrackMapper;

    @Override public Long createCollectTrack(CollectTrackSaveReqVO v) {
        CollectTrackDO o = BeanUtils.toBean(v, CollectTrackDO.class);
        collectTrackMapper.insert(o); return o.getId();
    }
    @Override public void updateCollectTrack(CollectTrackSaveReqVO v) {
        validateExists(v.getId()); collectTrackMapper.updateById(BeanUtils.toBean(v, CollectTrackDO.class));
    }
    @Override public void deleteCollectTrack(Long id) { validateExists(id); collectTrackMapper.deleteById(id); }
    @Override public void deleteCollectTrackListByIds(List<Long> ids) { collectTrackMapper.deleteByIds(ids); }
    @Override public CollectTrackDO getCollectTrack(Long id) { return collectTrackMapper.selectById(id); }
    @Override public PageResult<CollectTrackDO> getCollectTrackPage(CollectTrackPageReqVO v) { return collectTrackMapper.selectPage(v); }
    @Override public CollectTrackChartRespVO getCollectTrackChart(CollectTrackChartReqVO v)  {
        CollectTrackChartRespVO resp = new CollectTrackChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = v.getStartTime();
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        resp.setTrendData(collectTrackMapper.selectTrend(start, end));
        resp.setMethodData(collectTrackMapper.selectGroupByCollectMethod(start, end));
        CollectTrackChartRespVO.CardData card = new CollectTrackChartRespVO.CardData();
        card.setWaitCollectCount(collectTrackMapper.selectCountByStatus("pending", start, end).intValue());
        Long all       = collectTrackMapper.selectCountByStatus(null, start, end);
        Long completed = collectTrackMapper.selectCountByStatus("completed", start, end);
        if (all != null && all > 0) {
            card.setCollectCompleteRate(new BigDecimal(completed).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setCollectCompleteRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }

    /** 推送（PUT /push）：发送追缴消息 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushCollectTrack(IdReqVO reqVO) {
        CollectTrackDO track = collectTrackMapper.selectById(reqVO.getId());
        if (track == null) throw exception(COLLECT_TRACK_NOT_EXISTS);
        CollectTrackDO update = new CollectTrackDO();
        update.setId(reqVO.getId());
        update.setStatus("collecting");
        update.setCollectTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        if (reqVO.getRemark() != null) update.setCollectProgress(reqVO.getRemark());
        collectTrackMapper.updateById(update);
    }

    /** 更新追缴进度（PUT /update-progress） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProgressCollectTrack(IdReqVO reqVO) {
        CollectTrackDO track = collectTrackMapper.selectById(reqVO.getId());
        if (track == null) throw exception(COLLECT_TRACK_NOT_EXISTS);
        CollectTrackDO update = new CollectTrackDO();
        update.setId(reqVO.getId());
        update.setStatus("completed");
        update.setCollectProgress(reqVO.getRemark());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        collectTrackMapper.updateById(update);
    }

    /** 转派（PUT /transfer）：更换执行人/片区 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferCollectTrack(CollectTrackTransferReqVO reqVO) {
        CollectTrackDO track = collectTrackMapper.selectById(reqVO.getId());
        if (track == null) throw exception(COLLECT_TRACK_NOT_EXISTS);
        CollectTrackDO update = new CollectTrackDO();
        update.setId(reqVO.getId());
        update.setTransferUserId(reqVO.getTransferUserId());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        if (reqVO.getRemark() != null) update.setCollectProgress( reqVO.getRemark());
        collectTrackMapper.updateById(update);
    }

    /** 归档（PUT /archive）：标记追缴完成 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void archiveCollectTrack(IdReqVO reqVO) {
        CollectTrackDO track = collectTrackMapper.selectById(reqVO.getId());
        if (track == null) throw exception(COLLECT_TRACK_NOT_EXISTS);
        CollectTrackDO update = new CollectTrackDO();
        update.setId(reqVO.getId());
        update.setStatus("completed");
        update.setCollectProgress("已归档");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        collectTrackMapper.updateById(update);
    }

    /** 批量推送（POST /batch-push） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchPushCollectTrack(IdsReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) return;
        Long operatorId = SecurityFrameworkUtils.getLoginUserId();
        for (Long id : reqVO.getIds()) {
            CollectTrackDO track = collectTrackMapper.selectById(id);
            if (track == null) continue;
            CollectTrackDO update = new CollectTrackDO();
            update.setId(id);
            update.setStatus("collecting");
            update.setCollectTime(LocalDateTime.now());
            update.setOperatorId(operatorId);
            if (reqVO.getRemark() != null) update.setCollectProgress(reqVO.getRemark());
            collectTrackMapper.updateById(update);
        }
    }

    private void validateExists(Long id) {
        if (collectTrackMapper.selectById(id) == null) throw exception(COLLECT_TRACK_NOT_EXISTS);
    }
}
