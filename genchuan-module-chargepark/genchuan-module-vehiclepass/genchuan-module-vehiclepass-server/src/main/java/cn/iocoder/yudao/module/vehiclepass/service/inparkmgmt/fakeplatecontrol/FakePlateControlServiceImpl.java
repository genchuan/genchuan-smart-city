package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.fakeplatecontrol;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlCheckReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlIgnoreReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.MyFakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.fakeplatecontrol.FakePlateControlDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.fakeplatecontrol.FakePlateControlMapper;
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
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.vehiclepass.constants.inparkmgmt.FakePlateControlConstants.*;


/**
 * 套牌管控 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FakePlateControlServiceImpl implements FakePlateControlService {

    @Resource
    private FakePlateControlMapper plateControlMapper;

    @Override
    public Long createPlateControl(FakePlateControlSaveReqVO createReqVO) {
        // 插入
        FakePlateControlDO plateControl = BeanUtils.toBean(createReqVO, FakePlateControlDO.class);
        plateControlMapper.insert(plateControl);

        // 返回
        return plateControl.getId();
    }

    @Override
    public void updatePlateControl(FakePlateControlSaveReqVO updateReqVO) {
        // 校验存在
        validatePlateControlExists(updateReqVO.getId());
        // 更新
        FakePlateControlDO updateObj = BeanUtils.toBean(updateReqVO, FakePlateControlDO.class);
        plateControlMapper.updateById(updateObj);
    }

    @Override
    public void deletePlateControl(Long id) {
        // 校验存在
        validatePlateControlExists(id);
        // 删除
        plateControlMapper.deleteById(id);
    }

    @Override
    public void deletePlateControlListByIds(List<Long> ids) {
        // 删除
        plateControlMapper.deleteByIds(ids);
    }


    private void validatePlateControlExists(Long id) {
        if (plateControlMapper.selectById(id) == null) {
            throw exception(PLATE_CONTROL_NOT_EXISTS);
        }
    }

    @Override
    public FakePlateControlDO getPlateControl(Long id) {
        return plateControlMapper.selectById(id);
    }

    @Override
    public PageResult<FakePlateControlDO> getPlateControlPage(FakePlateControlPageReqVO pageReqVO) {
        return plateControlMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MyFakePlateControlRespVO> getFakePlateControlPage(FakePlateControlPageReqVO pageReqVO) {
        Page<MyFakePlateControlRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<MyFakePlateControlRespVO> pageResult = plateControlMapper.selectPageJoinStationUser(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchHandle(FakePlateControlBatchHandleReqVO reqVO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        List<FakePlateControlDO> existList = plateControlMapper.selectBatchIds(reqVO.getIds());
        if (existList.isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String handleType = reqVO.getHandleType();
        List<FakePlateControlDO> updateList = new ArrayList<>();
        for (FakePlateControlDO plateControl : existList) {
            FakePlateControlDO updateObj = new FakePlateControlDO();
            updateObj.setId(plateControl.getId());
            updateObj.setHandleUserId(currentUserId);
            updateObj.setHandleTime(now);

            if (HANDLE_TYPE_CHECK.equals(handleType)) {
                updateObj.setStatus(STATUS_PROCESSING);
                updateObj.setHandleProgress(HANDLE_PROGRESS_CHECKED);
                updateObj.setHandleType(HANDLE_TYPE_CHECK);
            } else if (HANDLE_TYPE_IGNORE.equals(handleType)) {
                updateObj.setStatus(STATUS_CLOSED);
                updateObj.setIgnoreReason(IGNORE_REASON_BATCH);
                updateObj.setHandleType(HANDLE_TYPE_IGNORE);
            }
            updateList.add(updateObj);
        }
        plateControlMapper.updateBatch(updateList);
    }

    @Override
    public void check(FakePlateControlCheckReqVO reqVO) {
        // 校验记录存在
        FakePlateControlDO plateControl = plateControlMapper.selectById(reqVO.getId());
        if (plateControl == null) {
            throw exception(PLATE_CONTROL_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        // 更新记录
        FakePlateControlDO updateObj = new FakePlateControlDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleUserId(currentUserId);
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setStatus(STATUS_PROCESSING);
        updateObj.setHandleProgress(HANDLE_PROGRESS_CHECKED);
        updateObj.setHandleType(HANDLE_TYPE_CHECK);
        plateControlMapper.updateById(updateObj);
    }

    @Override
    public void ignore(FakePlateControlIgnoreReqVO reqVO) {
        // 校验记录存在
        FakePlateControlDO plateControl = plateControlMapper.selectById(reqVO.getId());
        if (plateControl == null) {
            throw exception(PLATE_CONTROL_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        // 更新记录
        FakePlateControlDO updateObj = new FakePlateControlDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleUserId(currentUserId);
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setStatus(STATUS_CLOSED);
        updateObj.setHandleType(HANDLE_TYPE_IGNORE);
        updateObj.setHandleProgress(HANDLE_PROGRESS_IGNORED);
        updateObj.setIgnoreReason(reqVO.getIgnoreReason());
        plateControlMapper.updateById(updateObj);
    }

    @Override
    public void updateProgress(FakePlateControlUpdateProgressReqVO reqVO) {
        // 校验记录存在
        FakePlateControlDO plateControl = plateControlMapper.selectById(reqVO.getId());
        if (plateControl == null) {
            throw exception(PLATE_CONTROL_NOT_EXISTS);
        }

        // 更新记录
        FakePlateControlDO updateObj = new FakePlateControlDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleProgress(reqVO.getHandleProgress());
        plateControlMapper.updateById(updateObj);
    }

    @Override
    public FakePlateControlChartRespVO getChart(FakePlateControlChartReqVO reqVO) {
        FakePlateControlChartRespVO respVO = new FakePlateControlChartRespVO();

        // 1. 套牌识别趋势
        List<Map<String, Object>> trendList = plateControlMapper.selectIdentifyTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<FakePlateControlChartRespVO.FakeIdentifyTrend> fakeIdentifyTrend = new ArrayList<>();
        if (trendList != null) {
            fakeIdentifyTrend = trendList.stream()
                .map(map -> {
                    FakePlateControlChartRespVO.FakeIdentifyTrend item = new FakePlateControlChartRespVO.FakeIdentifyTrend();
                    Object dateObj = map.get("date");
                    if (dateObj != null) {
                        item.setDate(dateObj.toString());
                    }
                    Object countObj = map.get("count");
                    if (countObj != null) {
                        item.setCount(((Number) countObj).longValue());
                    }
                    return item;
                })
                .collect(Collectors.toList());
        }
        respVO.setFakeIdentifyTrend(fakeIdentifyTrend);

        // 2. 各场站套牌数
        List<Map<String, Object>> stationList = plateControlMapper.selectStationFakeCount(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<FakePlateControlChartRespVO.StationFakeCount> stationFakeCount = new ArrayList<>();
        if (stationList != null) {
            stationFakeCount = stationList.stream()
                .map(map -> {
                    FakePlateControlChartRespVO.StationFakeCount item = new FakePlateControlChartRespVO.StationFakeCount();
                    item.setStationName((String) map.get("stationName"));
                    Object countObj = map.get("count");
                    if (countObj != null) {
                        item.setCount(((Number) countObj).longValue());
                    }
                    return item;
                })
                .collect(Collectors.toList());
        }
        respVO.setStationFakeCount(stationFakeCount);

        // 3. 卡片数据
        Map<String, Object> stats = plateControlMapper.selectHandleStats(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        FakePlateControlChartRespVO.CardData cardData = new FakePlateControlChartRespVO.CardData();
        if (stats != null) {
            Object waitHandleCountObj = stats.get("waitHandleCount");
            cardData.setWaitHandleCount(waitHandleCountObj != null ? ((Number) waitHandleCountObj).longValue() : 0L);
            Object rateObj = stats.get("handleCompleteRate");
            cardData.setHandleCompleteRate(rateObj != null ? ((Number) rateObj).doubleValue() : 0.0);
        }
        respVO.setCardData(cardData);

        return respVO;
    }

}