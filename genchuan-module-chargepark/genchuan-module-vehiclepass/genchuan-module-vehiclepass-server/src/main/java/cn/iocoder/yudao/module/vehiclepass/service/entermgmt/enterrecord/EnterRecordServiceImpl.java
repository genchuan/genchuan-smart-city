package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.enterrecord;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.util.date.DateUtils;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.enterrecord.EnterRecordDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.enterrecord.EnterRecordMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.RECORD_NOT_EXISTS;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.RECORD_TYPE_NOT_MANUAL;

/**
 * 入场记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EnterRecordServiceImpl implements EnterRecordService {

    @Resource
    private EnterRecordMapper enterRecordMapper;

    @Override
    public Long createRecord(EnterRecordSaveReqVO createReqVO) {
        // 插入
        EnterRecordDO record = BeanUtils.toBean(createReqVO, EnterRecordDO.class);
        enterRecordMapper.insert(record);

        // 返回
        return record.getId();
    }

    @Override
    public void updateRecord(EnterRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateRecordExists(updateReqVO.getId());
        // 更新
        EnterRecordDO updateObj = BeanUtils.toBean(updateReqVO, EnterRecordDO.class);
        enterRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecord(Long id) {
        // 校验存在
        validateRecordExists(id);
        // 删除
        enterRecordMapper.deleteById(id);
    }

    @Override
    public void deleteRecordListByIds(List<Long> ids) {
        // 删除
        enterRecordMapper.deleteByIds(ids);
    }


    private void validateRecordExists(Long id) {
        if (enterRecordMapper.selectById(id) == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
    }

    @Override
    public EnterRecordDO getRecord(Long id) {
        return enterRecordMapper.selectById(id);
    }

    @Override
    public PageResult<EnterRecordDO> getRecordPage(EnterRecordPageReqVO pageReqVO) {
        return enterRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MyEnterRecordRespVO> getEnterRecordPage(MyEnterRecordPageReqVO reqVO) {
        Page<MyEnterRecordRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<MyEnterRecordRespVO> iPage = enterRecordMapper.selectEnterRecordPage(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    @Override
    public Boolean createEnterRecord(EnterRecordCreateReqVO req) {
        // 校验：必须是人工补录
        if (!"人工补录".equals(req.getRecordType())) {
            throw exception(RECORD_TYPE_NOT_MANUAL);
        }

        // 构建 DO
        EnterRecordDO entity = new EnterRecordDO();
        entity.setPlateNo(req.getPlateNo());
        entity.setPlateColor(req.getPlateColor());
        entity.setSpaceNo(req.getSpaceNo());
        // 时间戳转 LocalDateTime
        entity.setEnterTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(req.getEnterTime()), ZoneId.systemDefault()));
        entity.setRecordType(req.getRecordType());
        entity.setStatus(req.getStatus());
        entity.setStationId(req.getStationId());
        entity.setRemark(req.getRemark());
        entity.setProofImage(req.getProofImage());
        entity.setIsCorrected(false);

        // 数据库插入（数据操作都在服务层）
        enterRecordMapper.insert(entity);

        return true;
    }

    @Override
    public Boolean updateEnterRecord(EnterRecordUpdateReqVO req) {
        // 1. 校验记录是否存在
        EnterRecordDO record = enterRecordMapper.selectById(req.getId());
        if (record == null) {
            throw exception(RECORD_NOT_EXISTS);
        }

        // 2. 构建更新对象
        EnterRecordDO entity = new EnterRecordDO();
        entity.setId(req.getId());
        entity.setPlateNo(req.getPlateNo());
        entity.setPlateColor(req.getPlateColor());
        entity.setSpaceNo(req.getSpaceNo());
        entity.setEnterTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(req.getEnterTime()), ZoneId.systemDefault()));
        entity.setRecordType(req.getRecordType());
        entity.setStatus(req.getStatus());
        entity.setStationId(req.getStationId());
        entity.setRemark(req.getRemark());
        entity.setProofImage(req.getProofImage());

        // 3. 执行更新（数据操作）
        enterRecordMapper.updateById(entity);
        return true;
    }

    @Override
    public EnterRecordChartRespVO getChart(EnterRecordChartReqVO reqVO) {
        // 1. 秒级时间戳 → LocalDateTime（纯JDK，零依赖，永不报错）
        long startSecond = Long.parseLong(reqVO.getStartTime());
        long endSecond = Long.parseLong(reqVO.getEndTime());

        LocalDateTime startTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(startSecond),
                ZoneId.systemDefault()
        );
        LocalDateTime endTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(endSecond),
                ZoneId.systemDefault()
        );
        Long stationId = reqVO.getStationId();

        // 折线图
        List<EnterRecordChartRespVO.EnterCountTrend> trendList =
                enterRecordMapper.selectEnterCountTrend(startTime, endTime, stationId);

        // 柱状图
        List<EnterRecordChartRespVO.HourEnterCount> hourList =
                enterRecordMapper.selectHourEnterCount(startTime, endTime, stationId);

        // 今日入场量
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime todayEnd = LocalDateTime.now();
        Integer todayCount = Optional.ofNullable(
                enterRecordMapper.selectTodayEnterCount(todayStart, todayEnd, stationId)
        ).orElse(0);

        // 入场峰值
        Integer peak = hourList.stream()
                .map(EnterRecordChartRespVO.HourEnterCount::getCount)
                .max(Integer::compareTo)
                .orElse(0);

        // 卡片
        EnterRecordChartRespVO.CardData cardData = new EnterRecordChartRespVO.CardData();
        cardData.setTodayEnterCount(todayCount);
        cardData.setEnterPeak(peak);

        // 组装返回
        EnterRecordChartRespVO resp = new EnterRecordChartRespVO();
        resp.setEnterCountTrend(trendList);
        resp.setHourEnterCount(hourList);
        resp.setCardData(cardData);

        return resp;
    }


}