package cn.iocoder.yudao.module.inspectop.service.scheduleview;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.handoverlog.HandoverLogDO;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.shiftapply.ShiftApplyDO;
import cn.iocoder.yudao.module.inspectop.dal.mysql.handoverlog.HandoverLogMapper;
import cn.iocoder.yudao.module.inspectop.dal.mysql.shiftapply.ShiftApplyMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview.ScheduleViewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.scheduleview.ScheduleViewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 排班查看 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ScheduleViewServiceImpl implements ScheduleViewService {

    @Resource
    private ScheduleViewMapper scheduleViewMapper;

    @Resource
    private ShiftApplyMapper shiftApplyMapper;

    @Resource
    private HandoverLogMapper handoverLogMapper;

    @Override
    public Long createScheduleView(ScheduleViewSaveReqVO createReqVO) {
        // 插入
        ScheduleViewDO scheduleView = BeanUtils.toBean(createReqVO, ScheduleViewDO.class);
        scheduleViewMapper.insert(scheduleView);

        // 返回
        return scheduleView.getId();
    }

    @Override
    public void updateScheduleView(ScheduleViewSaveReqVO updateReqVO) {
        // 校验存在
        validateScheduleViewExists(updateReqVO.getId());
        // 更新
        ScheduleViewDO updateObj = BeanUtils.toBean(updateReqVO, ScheduleViewDO.class);
        scheduleViewMapper.updateById(updateObj);
    }

    @Override
    public void deleteScheduleView(Long id) {
        // 校验存在
        validateScheduleViewExists(id);
        // 删除
        scheduleViewMapper.deleteById(id);
    }

    @Override
        public void deleteScheduleViewListByIds(List<Long> ids) {
        // 删除
        scheduleViewMapper.deleteByIds(ids);
        }


    private void validateScheduleViewExists(Long id) {
        if (scheduleViewMapper.selectById(id) == null) {
            throw exception(SCHEDULE_VIEW_NOT_EXISTS);
        }
    }

    @Override
    public ScheduleViewDO getScheduleView(Long id) {
        return scheduleViewMapper.selectById(id);
    }

    @Override
    public PageResult<ScheduleViewRespVO> getScheduleViewPage(ScheduleViewPageReqVO pageReqVO) {
        // 创建分页对象
        Page<ScheduleViewRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的关联查询方法
        Page<ScheduleViewRespVO> resultPage = scheduleViewMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 直接构造PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyShift(ShiftApplyReqVO reqVO) {
        // 1. 校验原排班是否存在
        Long scheduleId = reqVO.getId();
        ScheduleViewDO originalSchedule = scheduleViewMapper.selectById(scheduleId);
        if (originalSchedule == null) {
            throw exception(SCHEDULE_VIEW_NOT_EXISTS);
        }

        // 2. 创建换班申请记录
        ShiftApplyDO shiftApply = new ShiftApplyDO();
        shiftApply.setApplyUserId(originalSchedule.getUserId());  // 申请人是原排班人员
        shiftApply.setTargetUserId(reqVO.getTargetUserId());      // 换班对象
        shiftApply.setOldDate(originalSchedule.getScheduleDate()); // 原日期

        // 解析新日期字符串为 LocalDateTime
        String newDateStr = reqVO.getNewDate();
        LocalDateTime newDate = LocalDateTime.parse(newDateStr);
        shiftApply.setNewDate(newDate);

        shiftApply.setStatus("1");  // 待审核状态（字典值1）
        shiftApply.setReserve1(reqVO.getApplyRemark());  // 申请备注

        // 设置基础字段（从原排班继承）
        shiftApply.setCreator(originalSchedule.getCreator());
        shiftApply.setUpdater(originalSchedule.getUpdater());
        shiftApply.setCreateTime(LocalDateTime.now());
        shiftApply.setUpdateTime(LocalDateTime.now());

        shiftApplyMapper.insert(shiftApply);

        // 3. 创建交接日志记录
        HandoverLogDO handoverLog = new HandoverLogDO();
        handoverLog.setUserId(originalSchedule.getUserId());  // 交接人员
        handoverLog.setHandoverDate(LocalDateTime.now());     // 交接日期为当前时间

        // 构建交接内容
        String content = String.format("【换班申请】申请人与ID:%s换班，原排班日期：%s，新日期：%s。备注：%s",
                reqVO.getTargetUserId(),
                originalSchedule.getScheduleDate(),
                newDateStr,
                reqVO.getApplyRemark() != null ? reqVO.getApplyRemark() : "无");
        handoverLog.setContent(content);

        handoverLog.setStatus("1");  // 待确认状态（字典值1）

        // 设置基础字段
        handoverLog.setCreator(originalSchedule.getCreator());
        handoverLog.setUpdater(originalSchedule.getUpdater());
        handoverLog.setCreateTime(LocalDateTime.now());
        handoverLog.setUpdateTime(LocalDateTime.now());

        handoverLogMapper.insert(handoverLog);

        // 4. 更新原排班状态
        ScheduleViewDO updateSchedule = new ScheduleViewDO();
        updateSchedule.setId(scheduleId);
        updateSchedule.setStatus("1");  // 排班状态更新为待审核（字典值1）
        updateSchedule.setUpdateTime(LocalDateTime.now());
        updateSchedule.setUpdater(originalSchedule.getUpdater());

        scheduleViewMapper.updateById(updateSchedule);

        return true;
    }

    @Override
    public ScheduleViewChartRespVO getScheduleViewChart(ScheduleViewChartReqVO reqVO) {
        ScheduleViewChartRespVO respVO = new ScheduleViewChartRespVO();

        // 1. 使用XML中的高性能SQL查询图表数据
        Map<Long, ScheduleViewChartData> chartDataMap = scheduleViewMapper.selectScheduleViewChartData(reqVO);

        // 2. 构建日历数据
        List<ScheduleViewChartRespVO.CalendarData> calendarData = new ArrayList<>();
        for (ScheduleViewChartData data : chartDataMap.values()) {
            ScheduleViewChartRespVO.CalendarData calendarItem = new ScheduleViewChartRespVO.CalendarData();
            calendarItem.setDate(data.getScheduleDate().toLocalDate().toString());
            calendarItem.setUserId(data.getUserId());
            calendarItem.setShiftType(data.getShiftType());
            calendarItem.setUserName(data.getUserName());
            calendarData.add(calendarItem);
        }
        respVO.setCalendarData(calendarData);

        // 3. 构建人员分布数据
        List<ScheduleViewChartRespVO.UserData> userData = new ArrayList<>();
        Map<String, Integer> userScheduleCountMap = new HashMap<>();

        for (ScheduleViewChartData data : chartDataMap.values()) {
            String userName = data.getUserName();
            if (!userScheduleCountMap.containsKey(userName)) {
                userScheduleCountMap.put(userName, data.getScheduleCount());
            }
        }

        for (Map.Entry<String, Integer> entry : userScheduleCountMap.entrySet()) {
            ScheduleViewChartRespVO.UserData userDataItem = new ScheduleViewChartRespVO.UserData();
            userDataItem.setUserName(entry.getKey());
            userDataItem.setCount(entry.getValue());
            userData.add(userDataItem);
        }

        // 按排班次数降序排序
        userData.sort((a, b) -> b.getCount() - a.getCount());
        respVO.setUserData(userData);

        // 4. 使用XML中的高性能SQL查询卡片数据
        ScheduleViewChartCardData cardData = scheduleViewMapper.selectScheduleViewCardData(reqVO);
        if (cardData != null) {
            ScheduleViewChartRespVO.CardData respCardData = new ScheduleViewChartRespVO.CardData();
            respCardData.setScheduleCount(cardData.getScheduleCount());
            respCardData.setOnDutyCount(cardData.getOnDutyCount());
            respVO.setCardData(respCardData);
        } else {
            // 如果没有数据，设置默认值
            ScheduleViewChartRespVO.CardData respCardData = new ScheduleViewChartRespVO.CardData();
            respCardData.setScheduleCount(0);
            respCardData.setOnDutyCount(0);
            respVO.setCardData(respCardData);
        }

        return respVO;
    }

}