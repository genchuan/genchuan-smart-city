package cn.iocoder.yudao.module.inspectop.service.inspecttrack;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttrack.InspectTrackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 巡检轨迹 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectTrackServiceImpl implements InspectTrackService {

    @Resource
    private InspectTrackMapper inspectTrackMapper;

    @Override
    @LogRecord(type = INSPECT_TRACK_TYPE, subType = INSPECT_TRACK_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = INSPECT_TRACK_CREATE_SUCCESS)
    public Long createInspectTrack(InspectTrackSaveReqVO createReqVO) {
        // 插入
        InspectTrackDO inspectTrack = BeanUtils.toBean(createReqVO, InspectTrackDO.class);
        inspectTrackMapper.insert(inspectTrack);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return inspectTrack.getId();
    }

    @Override
    @LogRecord(type = INSPECT_TRACK_TYPE, subType = INSPECT_TRACK_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = INSPECT_TRACK_UPDATE_SUCCESS)
    public void updateInspectTrack(InspectTrackSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        InspectTrackDO oldInspectTrack = validateInspectTrackExists(updateReqVO.getId());

        // 2. 更新
        InspectTrackDO updateObj = BeanUtils.toBean(updateReqVO, InspectTrackDO.class);
        inspectTrackMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        InspectTrackSaveReqVO oldVO = BeanUtils.toBean(oldInspectTrack, InspectTrackSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = INSPECT_TRACK_TYPE, subType = INSPECT_TRACK_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = INSPECT_TRACK_DELETE_SUCCESS)
    public void deleteInspectTrack(Long id) {
        // 校验存在
        validateInspectTrackExists(id);
        // 删除
        inspectTrackMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = INSPECT_TRACK_TYPE, subType = INSPECT_TRACK_DELETE_LIST_SUB_TYPE,
            success = INSPECT_TRACK_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteInspectTrackListByIds(List<Long> ids) {
        // 删除
        inspectTrackMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    // 修改验证方法，使其返回InspectTrackDO对象，用于update方法的日志对比
    private InspectTrackDO validateInspectTrackExists(Long id) {
        InspectTrackDO inspectTrack = inspectTrackMapper.selectById(id);
        if (inspectTrack == null) {
            throw exception(INSPECT_TRACK_NOT_EXISTS);
        }
        return inspectTrack; // 返回查询到的对象
    }

    @Override
    public InspectTrackDO getInspectTrack(Long id) {
        return inspectTrackMapper.selectById(id);
    }

    @Override
    public PageResult<InspectTrackRespVO> getInspectTrackPage(InspectTrackPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        Page<InspectTrackRespVO> mpPage
                = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        Page<InspectTrackRespVO> resultPage =
                inspectTrackMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = INSPECT_TRACK_TYPE, subType = INSPECT_TRACK_CHECK_SUB_TYPE,
            bizNo = "{{#checkReqVO.id}}", success = INSPECT_TRACK_CHECK_SUCCESS)
    public Boolean checkInspectTrack(InspectTrackCheckReqVO checkReqVO) {
        // 1. 校验轨迹是否存在
        Long id = checkReqVO.getId();
        InspectTrackDO inspectTrack = inspectTrackMapper.selectById(id);
        if (inspectTrack == null) {
            throw exception(INSPECT_TRACK_NOT_EXISTS);
        }

        // 2. 获取当前登录用户信息（这里需要根据您的权限框架实现）
        // 假设有工具类可以获取当前登录用户
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        String userName = SecurityFrameworkUtils.getLoginUserNickname();

        // 3. 更新核查信息
        InspectTrackDO updateDO = new InspectTrackDO();
        updateDO.setId(id);
        updateDO.setCheckStatus("1"); // 1-已核查
        updateDO.setCheckRemark(checkReqVO.getCheckRemark());

        // 4. 执行更新
        int result = inspectTrackMapper.updateById(updateDO);

        // 5. 设置日志上下文变量
        LogRecordContext.putVariable("checkReqVO", checkReqVO);

        // 6. 返回结果
        return result > 0;
    }

    @Override
    public InspectTrackReplayRespVO getInspectTrackReplay(Long id) {
        // 1. 查询轨迹基础信息和巡检人员姓名
        InspectTrackRespVO track = inspectTrackMapper.selectWithJoinById(id);
        if (track == null) {
            throw exception(INSPECT_TRACK_NOT_EXISTS);
        }

        // 2. 构建响应VO
        InspectTrackReplayRespVO replayRespVO = new InspectTrackReplayRespVO();
        replayRespVO.setUserId(track.getUserId());
        replayRespVO.setUserName(track.getUserName());

        // 将 LocalDateTime 转换为时间戳（毫秒）
        if (track.getTrackTime() != null) {
            replayRespVO.setTrackTime(track.getTrackTime());
        }

        // 3. 解析轨迹点字符串为PointVO列表
        // 解析格式为：118.5865,24.9132|118.5880,24.9100|118.5923,24.9034|...
        if (track.getPoints() != null && !track.getPoints().isEmpty()) {
            try {
                List<InspectTrackReplayRespVO.PointVO> pointList = parsePoints(track.getPoints(), track.getTrackTime());
                replayRespVO.setPoints(pointList);
            } catch (Exception e) {
                // 解析失败时返回空列表
                replayRespVO.setPoints(new ArrayList<>());
            }
        } else {
            replayRespVO.setPoints(new ArrayList<>());
        }

        return replayRespVO;
    }

    /**
     * 解析轨迹点字符串
     * 格式：118.5865,24.9132|118.5880,24.9100|118.5923,24.9034|...
     * 每个轨迹点格式：经度,纬度
     * 用竖线分隔多个轨迹点
     */
    private List<InspectTrackReplayRespVO.PointVO> parsePoints(String pointsStr, LocalDateTime trackTime) {
        List<InspectTrackReplayRespVO.PointVO> pointList = new ArrayList<>();

        if (pointsStr == null || pointsStr.trim().isEmpty()) {
            return pointList;
        }

        // 分割轨迹点
        String[] pointArray = pointsStr.split("\\|");

        // 将轨迹时间转换为时间戳（字符串格式）
        String timeStr = "0";
        if (trackTime != null) {
            // 获取秒级时间戳
            timeStr = String.valueOf(trackTime.toEpochSecond(ZoneOffset.UTC));
        }

        // 遍历所有轨迹点
        for (int i = 0; i < pointArray.length; i++) {
            String point = pointArray[i].trim();
            if (!point.isEmpty()) {
                // 分割经纬度
                String[] coordinates = point.split(",");
                if (coordinates.length >= 2) {
                    InspectTrackReplayRespVO.PointVO pointVO = new InspectTrackReplayRespVO.PointVO();

                    try {
                        // 解析经度
                        pointVO.setLon(Double.parseDouble(coordinates[0].trim()));
                        // 解析纬度
                        pointVO.setLat(Double.parseDouble(coordinates[1].trim()));
                        // 设置时间戳
                        // 这里可以根据实际业务需求设置不同的时间
                        // 如果轨迹点没有独立的时间，可以统一使用轨迹时间
                        // 或者根据索引生成递增的时间
                        pointVO.setTime(calculatePointTime(trackTime, i, pointArray.length));
                    } catch (NumberFormatException e) {
                        // 解析失败，跳过这个点
                        continue;
                    }

                    pointList.add(pointVO);
                }
            }
        }

        return pointList;
    }

    /**
     * 计算轨迹点的时间
     * 这里根据轨迹时间和点索引生成时间戳
     * 可以根据实际业务需求调整
     */
    private String calculatePointTime(LocalDateTime trackTime, int index, int totalPoints) {
        if (trackTime == null) {
            return String.valueOf(System.currentTimeMillis() / 1000);
        }

        // 基础时间戳（秒）
        long baseTimestamp = trackTime.toEpochSecond(ZoneOffset.UTC);

        // 如果只有一个点，使用基础时间
        if (totalPoints <= 1) {
            return String.valueOf(baseTimestamp);
        }

        // 模拟时间递增，每个点增加1秒
        // 实际业务中，轨迹点应该有实际的时间信息
        long pointTimestamp = baseTimestamp + index;

        return String.valueOf(pointTimestamp);
    }

    @Override
    public InspectTrackChartRespVO getInspectTrackChart(InspectTrackChartReqVO reqVO) {
        InspectTrackChartRespVO respVO = new InspectTrackChartRespVO();

        // 1. 获取地图数据
        List<InspectTrackChartRespVO.MapDataVO> mapData = getMapData(reqVO);
        respVO.setMapData(mapData);

        // 2. 获取趋势数据
        List<InspectTrackChartRespVO.TrendDataVO> trendData = getTrendData(reqVO);
        respVO.setTrendData(trendData);

        // 3. 获取卡片数据
        InspectTrackChartRespVO.CardDataVO cardData = getCardData(reqVO);
        respVO.setCardData(cardData);

        return respVO;
    }

    /**
     * 获取地图数据
     * 查询每个用户的最新轨迹点
     */
    private List<InspectTrackChartRespVO.MapDataVO> getMapData(InspectTrackChartReqVO reqVO) {
        // 1. 查询用户列表及其轨迹点
        List<InspectTrackMapper.MapDataDTO> dataList = inspectTrackMapper.selectUserTrackForMap(reqVO);

        // 2. 转换数据格式
        List<InspectTrackChartRespVO.MapDataVO> result = new ArrayList<>();
        for (InspectTrackMapper.MapDataDTO dto : dataList) {
            InspectTrackChartRespVO.MapDataVO mapData = new InspectTrackChartRespVO.MapDataVO();
            mapData.setUserId(dto.getUserId());
            mapData.setUserName(dto.getUserName());
            mapData.setPoints(formatPoints(dto.getPoints()));
            result.add(mapData);
        }

        return result;
    }

    /**
     * 格式化轨迹点字符串
     * 从"118.5865,24.9132|118.5880,24.9100"转换为"[[118.5865,24.9132],[118.5880,24.9100]]"
     */
    private String formatPoints(String points) {
        if (points == null || points.trim().isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        String[] pointArray = points.split("\\|");

        for (int i = 0; i < pointArray.length; i++) {
            String point = pointArray[i].trim();
            if (!point.isEmpty()) {
                String[] coordinates = point.split(",");
                if (coordinates.length >= 2) {
                    sb.append("[").append(coordinates[0].trim()).append(",")
                            .append(coordinates[1].trim()).append("]");

                    if (i < pointArray.length - 1) {
                        sb.append(",");
                    }
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取趋势数据
     * 按时间统计巡检里程
     */
    private List<InspectTrackChartRespVO.TrendDataVO> getTrendData(InspectTrackChartReqVO reqVO) {
        List<InspectTrackMapper.TrendDataDTO> dataList = inspectTrackMapper.selectTrendData(reqVO);

        List<InspectTrackChartRespVO.TrendDataVO> result = new ArrayList<>();
        for (InspectTrackMapper.TrendDataDTO dto : dataList) {
            InspectTrackChartRespVO.TrendDataVO trendData = new InspectTrackChartRespVO.TrendDataVO();

            // 从完整日期中提取天数
            if (dto.getTime() != null) {
                // 假设 dto.getTime() 返回的是 "2024-01-15" 格式
                String dateStr = dto.getTime().toString();
                // 提取天数部分
                String day = dateStr.substring(dateStr.lastIndexOf("-") + 1);
                trendData.setTime(day);
            } else {
                trendData.setTime("");
            }

            trendData.setTotalMileage(dto.getTotalMileage());
            result.add(trendData);
        }

        return result;
    }

    /**
     * 获取卡片统计数据
     */
    private InspectTrackChartRespVO.CardDataVO getCardData(InspectTrackChartReqVO reqVO) {
        InspectTrackMapper.CardDataDTO dto = inspectTrackMapper.selectCardData(reqVO);

        InspectTrackChartRespVO.CardDataVO cardData = new InspectTrackChartRespVO.CardDataVO();
        if (dto != null) {
            cardData.setTotalMileage(dto.getTotalMileage() != null ? dto.getTotalMileage() : BigDecimal.ZERO);
            cardData.setTotalDuration(dto.getTotalDuration() != null ? dto.getTotalDuration() : 0);
        } else {
            cardData.setTotalMileage(BigDecimal.ZERO);
            cardData.setTotalDuration(0);
        }

        return cardData;
    }

}