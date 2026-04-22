package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointActivityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PointActivityServiceImpl implements PointActivityService {

    @Resource
    private PointActivityMapper pointActivityMapper;

    @Override
    public PageResult<PointActivityDO> getPage(PointActivityPageReqVO reqVO) {
        return pointActivityMapper.selectPage(reqVO);
    }

    @Override
    public PointActivityDO get(Long id) {
        return pointActivityMapper.selectById(id);
    }

    @Override
    public Long create(PointActivityCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        // 时间戳转换为 LocalDateTime
        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(reqVO.getStartTime()), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(reqVO.getEndTime()), ZoneId.systemDefault());
        // 校验时间合法性
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("开始时间不能晚于结束时间");
        }
        PointActivityDO pointActivity = BeanUtils.toBean(reqVO, PointActivityDO.class);
        pointActivity.setStartTime(startTime);
        pointActivity.setEndTime(endTime);
        pointActivity.setStatus("1"); // 待生效
        pointActivity.setJoinCount(0);
        pointActivity.setRemainPoint(reqVO.getRemainPoint());
        pointActivityMapper.insert(pointActivity);
        return pointActivity.getId();
    }

    @Override
    public void update(PointActivityUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        PointActivityDO updateObj = BeanUtils.toBean(reqVO, PointActivityDO.class);
        pointActivityMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        PointActivityDO pointActivity = validateExists(id);
        if (!"4".equals(pointActivity.getStatus())) { // 暂停中
            throw exception(POINT_ACTIVITY_STATUS_ERROR);
        }
        pointActivity.setStatus("2"); // 进行中
        pointActivity.setAuditTime(LocalDateTime.now());
        // auditorId 由 Controller 层通过 SecurityFrameworkUtils 获取后设置
        pointActivityMapper.updateById(pointActivity);
    }

    @Override
    public void pause(Long id) {
        PointActivityDO pointActivity = validateExists(id);
        if (!"2".equals(pointActivity.getStatus())) { // 进行中
            throw exception(POINT_ACTIVITY_STATUS_ERROR);
        }
        pointActivity.setStatus("4"); // 已暂停
        pointActivityMapper.updateById(pointActivity);
    }

    @Override
    public PointActivityChartRespVO getChart(PointActivityChartReqVO reqVO) {
        // TODO: trendList和参与人数后续实现
        // 活动总数
        Long activityCount = pointActivityMapper.selectCountByChart(reqVO);
        // 按类型分组的数量
        List<java.util.Map<String, Object>> typeCountList = pointActivityMapper.selectTypeCountList(reqVO);
        List<PointActivityChartRespVO.TypeCountItem> typeItems = typeCountList.stream()
                .map(map -> {
                    PointActivityChartRespVO.TypeCountItem item = new PointActivityChartRespVO.TypeCountItem();
                    item.setType((String) map.get("type"));
                    item.setCount(((Number) map.get("count")).intValue());
                    return item;
                }).toList();
        PointActivityChartRespVO respVO = new PointActivityChartRespVO();
        respVO.setActivityCount(activityCount.intValue());
        respVO.setTrendList(new ArrayList<>());
        respVO.setTypeCountList(typeItems);
        return respVO;
    }

    @Override
    public void importPointActivityList(List<PointActivityImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (PointActivityImportExcelVO excelVO : list) {
            // 校验名称唯一
            validateNameUnique(null, excelVO.getName());
            // 解析时间
            LocalDateTime startTime = LocalDateTime.parse(excelVO.getStartTime(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(excelVO.getEndTime(), formatter);
            // 校验时间合法性
            if (startTime.isAfter(endTime)) {
                throw new IllegalArgumentException("活动[" + excelVO.getName() + "]的开始时间不能晚于结束时间");
            }
            // 转换并保存
            PointActivityDO pointActivity = PointActivityDO.builder()
                    .name(excelVO.getName())
                    .type(excelVO.getType())
                    .startTime(startTime)
                    .endTime(endTime)
                    .rule(excelVO.getRule())
                    .description(excelVO.getDescription())
                    .stationIds(excelVO.getStationIds())
                    .status("1") // 待生效
                    .joinCount(0)
                    .remainPoint(0)
                    .build();
            pointActivityMapper.insert(pointActivity);
        }
    }

    private PointActivityDO validateExists(Long id) {
        PointActivityDO pointActivity = pointActivityMapper.selectById(id);
        if (pointActivity == null) {
            throw exception(POINT_ACTIVITY_NOT_EXISTS);
        }
        return pointActivity;
    }

    private void validateNameUnique(Long id, String name) {
        PointActivityDO existing = pointActivityMapper.selectOne(PointActivityDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(POINT_ACTIVITY_NAME_EXISTS);
        }
    }

    @Override
    public void activate(Long id) {
        PointActivityDO pointActivity = validateExists(id);
        if (!"1".equals(pointActivity.getStatus())) { // 待生效
            throw exception(POINT_ACTIVITY_STATUS_ERROR);
        }
        pointActivity.setStatus("2"); // 进行中
        pointActivity.setAuditTime(LocalDateTime.now());
        // auditorId 由 Controller 层通过 SecurityFrameworkUtils 获取后设置
        pointActivityMapper.updateById(pointActivity);
    }
}
