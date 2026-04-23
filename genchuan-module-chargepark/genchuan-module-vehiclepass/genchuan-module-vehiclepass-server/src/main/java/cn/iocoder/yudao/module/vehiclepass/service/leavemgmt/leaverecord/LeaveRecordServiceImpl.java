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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.RECORD_NOT_EXISTS;

/**
 * 离场记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LeaveRecordServiceImpl implements LeaveRecordService {

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
        record.setEnterTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getEnterTime()), 0, java.time.ZoneOffset.ofHours(8)));
        record.setLeaveTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getLeaveTime()), 0, java.time.ZoneOffset.ofHours(8)));
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
        updateObj.setEnterTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getEnterTime()), 0, java.time.ZoneOffset.ofHours(8)));
        updateObj.setLeaveTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getLeaveTime()), 0, java.time.ZoneOffset.ofHours(8)));
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
        updateObj.setEnterTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getEnterTime()), 0, java.time.ZoneOffset.ofHours(8)));
        updateObj.setLeaveTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getLeaveTime()), 0, java.time.ZoneOffset.ofHours(8)));
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
        List<LeaveRecordChartRespVO.LeaveCountTrend> leaveCountTrends = new ArrayList<>();
        for (Map<String, Object> trend : trendList) {
            LeaveRecordChartRespVO.LeaveCountTrend item = new LeaveRecordChartRespVO.LeaveCountTrend();
            item.setDate(trend.get("date") != null ? trend.get("date").toString() : null);
            item.setCount(trend.get("count") != null ? Long.parseLong(trend.get("count").toString()) : 0L);
            leaveCountTrends.add(item);
        }

        // 查询各时段离场量
        List<Map<String, Object>> hourList = recordMapper.selectHourLeaveCount(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<LeaveRecordChartRespVO.HourLeaveCount> hourLeaveCounts = new ArrayList<>();
        for (Map<String, Object> hour : hourList) {
            LeaveRecordChartRespVO.HourLeaveCount item = new LeaveRecordChartRespVO.HourLeaveCount();
            item.setHour(hour.get("hour") != null ? hour.get("hour").toString() : null);
            item.setCount(hour.get("count") != null ? Long.parseLong(hour.get("count").toString()) : 0L);
            hourLeaveCounts.add(item);
        }

        // 查询今日离场量和离场峰值
        Map<String, Object> stats = recordMapper.selectLeaveStats(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        LeaveRecordChartRespVO.CardData cardData = new LeaveRecordChartRespVO.CardData();
        if (stats != null) {
            cardData.setTodayLeaveCount(stats.get("todayLeaveCount") != null ? Long.parseLong(stats.get("todayLeaveCount").toString()) : 0L);
            cardData.setLeavePeak(stats.get("leavePeak") != null ? Long.parseLong(stats.get("leavePeak").toString()) : 0L);
        } else {
            cardData.setTodayLeaveCount(0L);
            cardData.setLeavePeak(0L);
        }

        // 组装返回
        LeaveRecordChartRespVO respVO = new LeaveRecordChartRespVO();
        respVO.setLeaveCountTrend(leaveCountTrends);
        respVO.setHourLeaveCount(hourLeaveCounts);
        respVO.setCardData(cardData);
        return respVO;
    }

}