package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.oilcarhandle;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleIgnoreReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.oilcarhandle.OilCarHandleDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.oilcarhandle.OilCarHandleMapper;
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
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.vehiclepass.constants.inparkmgmt.OilCarHandleConstants.*;


/**
 * 油车占位处置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OilCarHandleServiceImpl implements OilCarHandleService {

    @Resource
    private OilCarHandleMapper carHandleMapper;

    @Override
    public Long createCarHandle(OilCarHandleSaveReqVO createReqVO) {
        // 插入
        OilCarHandleDO carHandle = BeanUtils.toBean(createReqVO, OilCarHandleDO.class);
        carHandleMapper.insert(carHandle);

        // 返回
        return carHandle.getId();
    }

    @Override
    public void updateCarHandle(OilCarHandleSaveReqVO updateReqVO) {
        // 校验存在
        validateCarHandleExists(updateReqVO.getId());
        // 更新
        OilCarHandleDO updateObj = BeanUtils.toBean(updateReqVO, OilCarHandleDO.class);
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarHandle(Long id) {
        // 校验存在
        validateCarHandleExists(id);
        // 删除
        carHandleMapper.deleteById(id);
    }

    @Override
    public void deleteCarHandleListByIds(List<Long> ids) {
        // 删除
        carHandleMapper.deleteByIds(ids);
    }


    private void validateCarHandleExists(Long id) {
        if (carHandleMapper.selectById(id) == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }
    }

    @Override
    public OilCarHandleDO getCarHandle(Long id) {
        return carHandleMapper.selectById(id);
    }

    @Override
    public PageResult<OilCarHandleDO> getCarHandlePage(OilCarHandlePageReqVO pageReqVO) {
        return carHandleMapper.selectPage(pageReqVO);
    }

    @Override
    public OilCarHandleRespVO getCarHandleWithStation(Long id) {
        OilCarHandleRespVO respVO = carHandleMapper.selectByIdJoinStation(id);
        if (respVO == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }
        return respVO;
    }

    @Override
    public PageResult<OilCarHandleRespVO> getCarHandlePageWithJoin(OilCarHandlePageReqVO pageReqVO) {
        Page<OilCarHandleRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<OilCarHandleRespVO> pageResult = carHandleMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchHandle(OilCarHandleBatchHandleReqVO reqVO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        List<OilCarHandleDO> existList = carHandleMapper.selectBatchIds(reqVO.getIds());
        if (existList.isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String handleType = reqVO.getHandleType();
        List<OilCarHandleDO> updateList = new ArrayList<>();
        for (OilCarHandleDO carHandle : existList) {
            OilCarHandleDO updateObj = new OilCarHandleDO();
            updateObj.setId(carHandle.getId());
            updateObj.setHandleUserId(currentUserId);
            updateObj.setHandleTime(now);

            if (HANDLE_TYPE_DISPOSE.equals(handleType)) {
                updateObj.setStatus(STATUS_PROCESSING);
                updateObj.setHandleMethod(HANDLE_METHOD_DISPOSED);
                updateObj.setHandleType(HANDLE_TYPE_DISPOSE);
            } else if (HANDLE_TYPE_IGNORE.equals(handleType)) {
                updateObj.setStatus(STATUS_CLOSED);
                updateObj.setIgnoreReason(IGNORE_REASON_BATCH);
                updateObj.setHandleType(HANDLE_TYPE_IGNORE);
            }
            updateList.add(updateObj);
        }
        carHandleMapper.updateBatch(updateList);
    }

    @Override
    public void handle(OilCarHandleHandleReqVO reqVO) {
        OilCarHandleDO carHandle = carHandleMapper.selectById(reqVO.getId());
        if (carHandle == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        OilCarHandleDO updateObj = new OilCarHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleUserId(currentUserId);
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setStatus(STATUS_PROCESSING);
        updateObj.setHandleMethod(reqVO.getHandleMethod());
        updateObj.setHandleType(HANDLE_TYPE_DISPOSE);
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public void ignore(OilCarHandleIgnoreReqVO reqVO) {
        OilCarHandleDO carHandle = carHandleMapper.selectById(reqVO.getId());
        if (carHandle == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        OilCarHandleDO updateObj = new OilCarHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleUserId(currentUserId);
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setStatus(STATUS_CLOSED);
        updateObj.setHandleType(HANDLE_TYPE_IGNORE);
        updateObj.setIgnoreReason(reqVO.getIgnoreReason());
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public void updateProgress(OilCarHandleUpdateProgressReqVO reqVO) {
        OilCarHandleDO carHandle = carHandleMapper.selectById(reqVO.getId());
        if (carHandle == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }

        OilCarHandleDO updateObj = new OilCarHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleProgress(reqVO.getHandleProgress());
        updateObj.setStatus(STATUS_CLOSED);
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public OilCarHandleChartRespVO getChart(OilCarHandleChartReqVO reqVO) {
        // 查询处置进度趋势
        List<Map<String, Object>> trendList = carHandleMapper.selectHandleProgressTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<OilCarHandleChartRespVO.HandleProgressTrend> handleProgressTrends = trendList.stream()
            .map(trend -> {
                OilCarHandleChartRespVO.HandleProgressTrend item = new OilCarHandleChartRespVO.HandleProgressTrend();
                item.setDate(trend.get("date") != null ? trend.get("date").toString() : null);
                item.setCount(MapValueUtils.getLongValue(trend, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询各场站处置量
        List<Map<String, Object>> stationList = carHandleMapper.selectStationHandleCount(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<OilCarHandleChartRespVO.StationHandleCount> stationHandleCounts = stationList.stream()
            .map(station -> {
                OilCarHandleChartRespVO.StationHandleCount item = new OilCarHandleChartRespVO.StationHandleCount();
                item.setStationName(station.get("stationName") != null ? station.get("stationName").toString() : null);
                item.setCount(MapValueUtils.getLongValue(station, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询待处置数和处置完成率
        Map<String, Object> stats = carHandleMapper.selectHandleStats(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        OilCarHandleChartRespVO.CardData cardData = new OilCarHandleChartRespVO.CardData();
        if (stats != null) {
            cardData.setWaitHandleCount(MapValueUtils.getLongValue(stats, "waitHandleCount"));
            cardData.setHandleCompleteRate(MapValueUtils.getDoubleValue(stats, "handleCompleteRate"));
        } else {
            cardData.setWaitHandleCount(0L);
            cardData.setHandleCompleteRate(0.0);
        }

        // 组装返回
        OilCarHandleChartRespVO respVO = new OilCarHandleChartRespVO();
        respVO.setHandleProgressTrend(handleProgressTrends);
        respVO.setStationHandleCount(stationHandleCounts);
        respVO.setCardData(cardData);
        return respVO;
    }

}