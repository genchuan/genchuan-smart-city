package cn.iocoder.yudao.module.inspectop.service.handoverlog;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.handoverlog.HandoverLogDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.handoverlog.HandoverLogMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 交接日志 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class HandoverLogServiceImpl implements HandoverLogService {

    @Resource
    private HandoverLogMapper handoverLogMapper;

    @Override
    public Long createHandoverLog(HandoverLogSaveReqVO createReqVO) {
        // 插入
        HandoverLogDO handoverLog = BeanUtils.toBean(createReqVO, HandoverLogDO.class);
        handoverLogMapper.insert(handoverLog);

        // 返回
        return handoverLog.getId();
    }

    @Override
    public void updateHandoverLog(HandoverLogSaveReqVO updateReqVO) {
        // 校验存在
        validateHandoverLogExists(updateReqVO.getId());
        // 更新
        HandoverLogDO updateObj = BeanUtils.toBean(updateReqVO, HandoverLogDO.class);
        handoverLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteHandoverLog(Long id) {
        // 校验存在
        validateHandoverLogExists(id);
        // 删除
        handoverLogMapper.deleteById(id);
    }

    @Override
        public void deleteHandoverLogListByIds(List<Long> ids) {
        // 删除
        handoverLogMapper.deleteByIds(ids);
        }


    private void validateHandoverLogExists(Long id) {
        if (handoverLogMapper.selectById(id) == null) {
            throw exception(HANDOVER_LOG_NOT_EXISTS);
        }
    }

    @Override
    public HandoverLogDO getHandoverLog(Long id) {
        return handoverLogMapper.selectById(id);
    }

    @Override
    public PageResult<HandoverLogRespVO> getHandoverLogPage(HandoverLogPageReqVO pageReqVO) {
        // 创建分页对象
        Page<HandoverLogRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的关联查询方法
        Page<HandoverLogRespVO> resultPage = handoverLogMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 直接构造PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmHandoverLog(HandoverLogConfirmReqVO reqVO) {
        // 1. 校验交接日志是否存在
        Long id = reqVO.getId();
        HandoverLogDO handoverLog = handoverLogMapper.selectById(id);
        if (handoverLog == null) {
            throw exception(HANDOVER_LOG_NOT_EXISTS);
        }

        // 2. 更新交接日志状态为2（已确认）
        HandoverLogDO updateObj = new HandoverLogDO();
        updateObj.setId(id);
        updateObj.setStatus("2");  // 状态更新为已确认

        // 3. 设置确认时间和当前操作用户
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
         updateObj.setConfirmUserId(currentUserId);
         updateObj.setConfirmTime(LocalDateTime.now());

        // 4. 执行更新
        int updateCount = handoverLogMapper.updateById(updateObj);

        // 5. 返回操作结果
        return updateCount > 0;
    }

    @Override
    public HandoverLogChartRespVO getHandoverLogChart(HandoverLogChartReqVO reqVO) {
        HandoverLogChartRespVO respVO = new HandoverLogChartRespVO();

        // 获取时间范围
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        // 1. 查询趋势统计数据
        List<Map<String, Object>> trendDataList = handoverLogMapper.selectTrendStatistics(startTime, endTime);
        List<HandoverLogChartRespVO.TrendData> trendData = new ArrayList<>();

        for (Map<String, Object> item : trendDataList) {
            HandoverLogChartRespVO.TrendData trendItem = new HandoverLogChartRespVO.TrendData();
            trendItem.setTime(item.get("time").toString());  // 格式：yyyy-MM-dd
            trendItem.setLogCount(((Number) item.get("logCount")).intValue());
            trendData.add(trendItem);
        }
        respVO.setTrendData(trendData);

        // 2. 查询卡片统计数据
        Map<String, Object> cardStats = handoverLogMapper.selectCardStatistics(startTime, endTime);
        HandoverLogChartRespVO.CardData cardData = new HandoverLogChartRespVO.CardData();

        if (cardStats != null && !cardStats.isEmpty()) {
            Integer totalCount = ((Number) cardStats.get("totalCount")).intValue();
            Integer confirmedCount = cardStats.get("confirmedCount") != null ?
                    ((Number) cardStats.get("confirmedCount")).intValue() : 0;

            cardData.setLogCount(totalCount);
            cardData.setConfirmRate(confirmedCount, totalCount);
        } else {
            // 如果没有数据，设置默认值
            cardData.setLogCount(0);
            cardData.setConfirmRate(0, 0);
        }
        respVO.setCardData(cardData);

        return respVO;
    }

}