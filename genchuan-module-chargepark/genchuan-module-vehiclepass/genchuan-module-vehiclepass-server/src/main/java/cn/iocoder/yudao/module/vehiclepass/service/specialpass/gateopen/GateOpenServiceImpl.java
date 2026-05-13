package cn.iocoder.yudao.module.vehiclepass.service.specialpass.gateopen;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenApproveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRejectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenExecuteReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenReapplyReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.gateopen.GateOpenDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.specialpass.gateopen.GateOpenMapper;
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
import static cn.iocoder.yudao.module.vehiclepass.constants.specialpass.GateOpenConstants.*;



/**
 * 开闸管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GateOpenServiceImpl implements GateOpenService {

    private static final int PARALLEL_THRESHOLD = 100;

    @Resource
    private GateOpenMapper openMapper;

    @Override
    public Long createOpen(GateOpenSaveReqVO createReqVO) {
        // 插入
        GateOpenDO open = BeanUtils.toBean(createReqVO, GateOpenDO.class);
        openMapper.insert(open);

        // 返回
        return open.getId();
    }

    @Override
    public Long createOpenApply(GateOpenCreateReqVO createReqVO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        GateOpenDO open = new GateOpenDO();
        open.setStationId(createReqVO.getStationId());
        open.setOpenReason(createReqVO.getOpenReason());
        open.setRemark(createReqVO.getRemark());
        open.setApplyUserId(currentUserId);
        open.setApplyTime(LocalDateTime.now());
        open.setStatus(STATUS_PENDING_APPROVAL);
        openMapper.insert(open);
        return open.getId();
    }

    @Override
    public void updateOpen(GateOpenSaveReqVO updateReqVO) {
        // 校验存在
        validateOpenExists(updateReqVO.getId());
        // 更新
        GateOpenDO updateObj = BeanUtils.toBean(updateReqVO, GateOpenDO.class);
        openMapper.updateById(updateObj);
    }

    @Override
    public void deleteOpen(Long id) {
        // 校验存在
        validateOpenExists(id);
        // 删除
        openMapper.deleteById(id);
    }

    @Override
    public void deleteOpenListByIds(List<Long> ids) {
        // 删除
        openMapper.deleteByIds(ids);
    }


    private void validateOpenExists(Long id) {
        if (openMapper.selectById(id) == null) {
            throw exception(OPEN_NOT_EXISTS);
        }
    }

    @Override
    public GateOpenDO getOpen(Long id) {
        return openMapper.selectById(id);
    }

    @Override
    public PageResult<GateOpenDO> getOpenPage(GateOpenPageReqVO pageReqVO) {
        return openMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GateOpenRespVO> getOpenPageWithJoin(GateOpenPageReqVO pageReqVO) {
        Page<GateOpenRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<GateOpenRespVO> pageResult = openMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void approve(GateOpenApproveReqVO reqVO) {
        GateOpenDO open = openMapper.selectById(reqVO.getId());
        if (open == null) {
            throw exception(OPEN_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        GateOpenDO updateObj = new GateOpenDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus(STATUS_APPROVED);
        updateObj.setAuditUserId(currentUserId);
        updateObj.setAuditTime(LocalDateTime.now());
        openMapper.updateById(updateObj);
    }

    @Override
    public void reject(GateOpenRejectReqVO reqVO) {
        GateOpenDO open = openMapper.selectById(reqVO.getId());
        if (open == null) {
            throw exception(OPEN_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        GateOpenDO updateObj = new GateOpenDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus(STATUS_REJECTED);
        updateObj.setAuditUserId(currentUserId);
        updateObj.setAuditTime(LocalDateTime.now());
        updateObj.setRejectReason(reqVO.getRejectReason());
        openMapper.updateById(updateObj);
    }

    @Override
    public void execute(GateOpenExecuteReqVO reqVO) {
        GateOpenDO open = openMapper.selectById(reqVO.getId());
        if (open == null) {
            throw exception(OPEN_NOT_EXISTS);
        }
        GateOpenDO updateObj = new GateOpenDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus(STATUS_EXECUTED);
        updateObj.setExecuteTime(LocalDateTime.now());
        openMapper.updateById(updateObj);
    }

    @Override
    public void reapply(GateOpenReapplyReqVO reqVO) {
        GateOpenDO open = openMapper.selectById(reqVO.getId());
        if (open == null) {
            throw exception(OPEN_NOT_EXISTS);
        }

        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        GateOpenDO updateObj = new GateOpenDO();
        updateObj.setId(reqVO.getId());
        updateObj.setOpenReason(reqVO.getOpenReason());
        updateObj.setRemark(reqVO.getRemark());
        updateObj.setApplyUserId(currentUserId);
        updateObj.setApplyTime(LocalDateTime.now());
        updateObj.setStatus(STATUS_PENDING_APPROVAL);
        updateObj.setAuditUserId(null);
        updateObj.setAuditTime(null);
        updateObj.setRejectReason(null);
        openMapper.updateById(updateObj);
    }

    @Override
    public GateOpenChartRespVO getChart(GateOpenChartReqVO reqVO) {
        // 查询开闸申请趋势
        List<Map<String, Object>> trendList = openMapper.selectOpenApplyTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<GateOpenChartRespVO.OpenApplyTrend> openApplyTrends = (trendList.size() > PARALLEL_THRESHOLD
                ? trendList.parallelStream()
                : trendList.stream())
            .map(trend -> {
                GateOpenChartRespVO.OpenApplyTrend item = new GateOpenChartRespVO.OpenApplyTrend();
                item.setDate(trend.get("date") != null ? trend.get("date").toString() : null);
                item.setCount(MapValueUtils.getLongValue(trend, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询各场站开闸量
        List<Map<String, Object>> stationList = openMapper.selectStationOpenCount(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<GateOpenChartRespVO.StationOpenCount> stationOpenCounts = (stationList.size() > PARALLEL_THRESHOLD
                ? stationList.parallelStream()
                : stationList.stream())
            .map(station -> {
                GateOpenChartRespVO.StationOpenCount item = new GateOpenChartRespVO.StationOpenCount();
                item.setStationName(station.get("stationName") != null ? station.get("stationName").toString() : null);
                item.setCount(MapValueUtils.getLongValue(station, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询申请量和审批通过率
        Map<String, Object> stats = openMapper.selectOpenStats(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        GateOpenChartRespVO.CardData cardData = new GateOpenChartRespVO.CardData();
        if (stats != null) {
            cardData.setApplyCount(MapValueUtils.getLongValue(stats, "applyCount"));
            cardData.setAuditPassRate(MapValueUtils.getDoubleValue(stats, "auditPassRate"));
        } else {
            cardData.setApplyCount(0L);
            cardData.setAuditPassRate(0.0);
        }

        // 组装返回
        GateOpenChartRespVO respVO = new GateOpenChartRespVO();
        respVO.setOpenApplyTrend(openApplyTrends);
        respVO.setStationOpenCount(stationOpenCounts);
        respVO.setCardData(cardData);
        return respVO;
    }

}