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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview.ScheduleViewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.scheduleview.ScheduleViewMapper;

// 新增导入
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
// 导入上面定义的常量
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;
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
    @LogRecord(type = SCHEDULE_VIEW_TYPE, subType = SCHEDULE_VIEW_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = SCHEDULE_VIEW_CREATE_SUCCESS)
    public Long createScheduleView(ScheduleViewSaveReqVO createReqVO) {
        // 插入
        ScheduleViewDO scheduleView = BeanUtils.toBean(createReqVO, ScheduleViewDO.class);
        scheduleViewMapper.insert(scheduleView);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return scheduleView.getId();
    }

    @Override
    @LogRecord(type = SCHEDULE_VIEW_TYPE, subType = SCHEDULE_VIEW_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = SCHEDULE_VIEW_UPDATE_SUCCESS)
    public void updateScheduleView(ScheduleViewSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        ScheduleViewDO oldScheduleView = validateScheduleViewExists(updateReqVO.getId());

        // 2. 更新
        ScheduleViewDO updateObj = BeanUtils.toBean(updateReqVO, ScheduleViewDO.class);
        scheduleViewMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        ScheduleViewSaveReqVO oldVO = BeanUtils.toBean(oldScheduleView, ScheduleViewSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = SCHEDULE_VIEW_TYPE, subType = SCHEDULE_VIEW_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = SCHEDULE_VIEW_DELETE_SUCCESS)
    public void deleteScheduleView(Long id) {
        // 校验存在
        validateScheduleViewExists(id);
        // 删除
        scheduleViewMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = SCHEDULE_VIEW_TYPE, subType = SCHEDULE_VIEW_DELETE_LIST_SUB_TYPE,
            success = SCHEDULE_VIEW_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteScheduleViewListByIds(List<Long> ids) {
        // 删除
        scheduleViewMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    // 修改验证方法，使其返回ScheduleViewDO对象，用于update方法的日志对比
    private ScheduleViewDO validateScheduleViewExists(Long id) {
        ScheduleViewDO scheduleView = scheduleViewMapper.selectById(id);
        if (scheduleView == null) {
            throw exception(SCHEDULE_VIEW_NOT_EXISTS);
        }
        return scheduleView; // 返回查询到的对象
    }

    // 以下方法不需要操作日志（查询方法）
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
    @LogRecord(type = SCHEDULE_VIEW_TYPE, subType = SCHEDULE_VIEW_APPLY_SHIFT_SUB_TYPE,
            bizNo = "{{#reqVO.id}}", success = SCHEDULE_VIEW_APPLY_SHIFT_SUCCESS)
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
        LocalDateTime newDate;
        try {
            // 先解析为LocalDate（仅日期），再转换为当天的开始时间（00:00:00）
            LocalDate localDate = LocalDate.parse(newDateStr);
            newDate = localDate.atStartOfDay();
        } catch (Exception e) {
            // 如果解析失败，可以尝试其他格式，或直接抛出业务异常
            // 这里根据您的需求，可以记录日志并抛出明确的业务异常
            throw new IllegalArgumentException("日期格式错误，请使用 yyyy-MM-dd 格式", e);
        }
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

        // 5. 设置日志上下文变量
        LogRecordContext.putVariable("reqVO", reqVO);

        return true;
    }

    @Override
    public ScheduleViewChartRespVO getScheduleViewChart(ScheduleViewChartReqVO reqVO) {
        ScheduleViewChartRespVO respVO = new ScheduleViewChartRespVO();

        // 1. 使用XML中的SQL查询图表数据 - 修改为List
        List<ScheduleViewChartData> chartDataList = scheduleViewMapper.selectScheduleViewChartData(reqVO);

        // 2. 构建日历数据
        List<ScheduleViewChartRespVO.CalendarData> calendarData = new ArrayList<>();

        // 用于统计用户排班次数
        Map<Long, Integer> userScheduleCountMap = new HashMap<>();

        for (ScheduleViewChartData data : chartDataList) {
            ScheduleViewChartRespVO.CalendarData calendarItem = new ScheduleViewChartRespVO.CalendarData();
            calendarItem.setDate(data.getScheduleDate().toLocalDate().toString());
            calendarItem.setUserId(data.getUserId());
            calendarItem.setShiftType(data.getShiftType());
            calendarItem.setUserName(data.getUserName());
            calendarData.add(calendarItem);

            // 统计每个用户的排班总数
            userScheduleCountMap.merge(data.getUserId(), 1, Integer::sum);
        }
        respVO.setCalendarData(calendarData);

        // 3. 构建人员分布数据
        List<ScheduleViewChartRespVO.UserData> userData = new ArrayList<>();

        for (ScheduleViewChartData data : chartDataList) {
            String userName = data.getUserName();
            Long userId = data.getUserId();

            // 检查是否已经添加过这个用户
            boolean userExists = userData.stream()
                    .anyMatch(u -> u.getUserName().equals(userName));

            if (!userExists) {
                ScheduleViewChartRespVO.UserData userDataItem = new ScheduleViewChartRespVO.UserData();
                userDataItem.setUserName(userName);
                // 从统计Map中获取该用户的总排班次数
                userDataItem.setCount(userScheduleCountMap.getOrDefault(userId, 0));
                userData.add(userDataItem);
            }
        }

        // 按排班次数降序排序
        userData.sort((a, b) -> b.getCount() - a.getCount());
        respVO.setUserData(userData);

        // 4. 使用XML中的SQL查询卡片数据
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