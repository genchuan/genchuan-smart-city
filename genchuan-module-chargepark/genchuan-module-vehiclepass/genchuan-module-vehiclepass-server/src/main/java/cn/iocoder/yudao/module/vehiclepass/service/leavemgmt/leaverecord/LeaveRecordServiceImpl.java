package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.leaverecord;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordUpdateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord.LeaveRecordDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.leaverecord.LeaveRecordMapper;
import cn.iocoder.yudao.module.vehiclepass.framework.util.MapValueUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.vehiclepass.constants.common.TimeConstants.*;


/**
 * 离场记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LeaveRecordServiceImpl implements LeaveRecordService {

    private static final int PARALLEL_THRESHOLD = 100;

    @Resource
    private LeaveRecordMapper recordMapper;

    @Override
    public Long createRecord(LeaveRecordSaveReqVO createReqVO) {
        // 插入
        LeaveRecordDO record = BeanUtils.toBean(createReqVO, LeaveRecordDO.class);
        recordMapper.insert(record);

        // 返回
        return record.getId();
    }

    @Override
    public void updateRecord(LeaveRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateRecordExists(updateReqVO.getId());
        // 更新
        LeaveRecordDO updateObj = BeanUtils.toBean(updateReqVO, LeaveRecordDO.class);
        recordMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecord(Long id) {
        // 校验存在
        validateRecordExists(id);
        // 删除
        recordMapper.deleteById(id);
    }

    @Override
    public void deleteRecordListByIds(List<Long> ids) {
        // 删除
        recordMapper.deleteByIds(ids);
    }


    private void validateRecordExists(Long id) {
        if (recordMapper.selectById(id) == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
    }

    @Override
    public LeaveRecordDO getRecord(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public PageResult<LeaveRecordDO> getRecordPage(LeaveRecordPageReqVO pageReqVO) {
        return recordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<LeaveRecordRespVO> getRecordPageWithJoin(LeaveRecordPageReqVO pageReqVO) {
        Page<LeaveRecordRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<LeaveRecordRespVO> pageResult = recordMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public Long createRecordSupplement(LeaveRecordCreateReqVO reqVO) {
        LeaveRecordDO record = new LeaveRecordDO();
        record.setPlateNo(reqVO.getPlateNo());
        try {
            long enterTimestamp = Long.parseLong(reqVO.getEnterTime());
            long leaveTimestamp = Long.parseLong(reqVO.getLeaveTime());
            record.setEnterTime(LocalDateTime.ofEpochSecond(enterTimestamp, 0, java.time.ZoneOffset.ofHours(DEFAULT_TIMEZONE_OFFSET_HOURS)));
            record.setLeaveTime(LocalDateTime.ofEpochSecond(leaveTimestamp, 0, java.time.ZoneOffset.ofHours(DEFAULT_TIMEZONE_OFFSET_HOURS)));
        } catch (NumberFormatException e) {
            throw exception(TIMESTAMP_PARSE_ERROR);
        }
        // 计算停车时长（分钟）
        long duration = java.time.Duration.between(record.getEnterTime(), record.getLeaveTime()).toMinutes();
        record.setParkDuration((int) duration);
        record.setStatus(reqVO.getStatus());
        record.setStationId(reqVO.getStationId());
        record.setRemark(reqVO.getRemark());
        record.setProofImage(reqVO.getProofImage());
        record.setIsCorrected(false);
        recordMapper.insert(record);
        return record.getId();
    }

    @Override
    public void updateRecordForEdit(LeaveRecordUpdateReqVO reqVO) {
        LeaveRecordDO record = recordMapper.selectById(reqVO.getId());
        if (record == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
        LeaveRecordDO updateObj = new LeaveRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setPlateNo(reqVO.getPlateNo());
        try {
            long enterTimestamp = Long.parseLong(reqVO.getEnterTime());
            long leaveTimestamp = Long.parseLong(reqVO.getLeaveTime());
            updateObj.setEnterTime(LocalDateTime.ofEpochSecond(enterTimestamp, 0, java.time.ZoneOffset.ofHours(DEFAULT_TIMEZONE_OFFSET_HOURS)));
            updateObj.setLeaveTime(LocalDateTime.ofEpochSecond(leaveTimestamp, 0, java.time.ZoneOffset.ofHours(DEFAULT_TIMEZONE_OFFSET_HOURS)));
        } catch (NumberFormatException e) {
            throw exception(TIMESTAMP_PARSE_ERROR);
        }
        long duration = java.time.Duration.between(updateObj.getEnterTime(), updateObj.getLeaveTime()).toMinutes();
        updateObj.setParkDuration((int) duration);
        updateObj.setStatus(reqVO.getStatus());
        updateObj.setStationId(reqVO.getStationId());
        updateObj.setRemark(reqVO.getRemark());
        updateObj.setProofImage(reqVO.getProofImage());
        recordMapper.updateById(updateObj);
    }

    @Override
    public void correctRecord(LeaveRecordCorrectReqVO reqVO) {
        LeaveRecordDO record = recordMapper.selectById(reqVO.getId());
        if (record == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
        LeaveRecordDO updateObj = new LeaveRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setPlateNo(reqVO.getPlateNo());
        try {
            long enterTimestamp = Long.parseLong(reqVO.getEnterTime());
            long leaveTimestamp = Long.parseLong(reqVO.getLeaveTime());
            updateObj.setEnterTime(LocalDateTime.ofEpochSecond(enterTimestamp, 0, java.time.ZoneOffset.ofHours(DEFAULT_TIMEZONE_OFFSET_HOURS)));
            updateObj.setLeaveTime(LocalDateTime.ofEpochSecond(leaveTimestamp, 0, java.time.ZoneOffset.ofHours(DEFAULT_TIMEZONE_OFFSET_HOURS)));
        } catch (NumberFormatException e) {
            throw exception(TIMESTAMP_PARSE_ERROR);
        }
        long duration = java.time.Duration.between(updateObj.getEnterTime(), updateObj.getLeaveTime()).toMinutes();
        updateObj.setParkDuration((int) duration);
        updateObj.setStatus(reqVO.getStatus());
        updateObj.setStationId(reqVO.getStationId());
        updateObj.setRemark(reqVO.getRemark());
        updateObj.setProofImage(reqVO.getProofImage());
        updateObj.setIsCorrected(reqVO.getIsCorrected());
        recordMapper.updateById(updateObj);
    }

    @Override
    public LeaveRecordChartRespVO getChart(LeaveRecordChartReqVO reqVO) {
        // 查询离场量趋势
        List<Map<String, Object>> trendList = recordMapper.selectLeaveCountTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<LeaveRecordChartRespVO.LeaveCountTrend> leaveCountTrends = (trendList.size() > PARALLEL_THRESHOLD
                ? trendList.parallelStream()
                : trendList.stream())
            .map(trend -> {
                LeaveRecordChartRespVO.LeaveCountTrend item = new LeaveRecordChartRespVO.LeaveCountTrend();
                item.setDate(trend.get("date") != null ? trend.get("date").toString() : null);
                item.setCount(MapValueUtils.getLongValue(trend, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询各时段离场量
        List<Map<String, Object>> hourList = recordMapper.selectHourLeaveCount(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<LeaveRecordChartRespVO.HourLeaveCount> hourLeaveCounts = (hourList.size() > PARALLEL_THRESHOLD
                ? hourList.parallelStream()
                : hourList.stream())
            .map(hour -> {
                LeaveRecordChartRespVO.HourLeaveCount item = new LeaveRecordChartRespVO.HourLeaveCount();
                item.setHour(hour.get("hour") != null ? hour.get("hour").toString() : null);
                item.setCount(MapValueUtils.getLongValue(hour, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询今日离场量和离场峰值
        LeaveRecordChartRespVO.CardData cardData = new LeaveRecordChartRespVO.CardData();
        Long todayLeaveCount = recordMapper.selectTodayLeaveCount(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        Long leavePeak = recordMapper.selectTodayLeavePeak(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        cardData.setTodayLeaveCount(todayLeaveCount != null ? todayLeaveCount : 0L);
        cardData.setLeavePeak(leavePeak != null ? leavePeak : 0L);

        // 组装返回
        LeaveRecordChartRespVO respVO = new LeaveRecordChartRespVO();
        respVO.setLeaveCountTrend(leaveCountTrends);
        respVO.setHourLeaveCount(hourLeaveCounts);
        respVO.setCardData(cardData);
        return respVO;
    }

}