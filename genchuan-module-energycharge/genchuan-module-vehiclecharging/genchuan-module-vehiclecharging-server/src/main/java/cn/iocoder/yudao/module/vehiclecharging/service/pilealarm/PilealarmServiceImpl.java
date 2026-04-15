package cn.iocoder.yudao.module.vehiclecharging.service.pilealarm;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pilealarm.PilealarmMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 充电桩告警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PilealarmServiceImpl implements PilealarmService {

    @Resource
    private PilealarmMapper pilealarmMapper;

    @Override
    public Long createPilealarm(PilealarmSaveReqVO createReqVO) {
        // 插入
        PilealarmDO pilealarm = BeanUtils.toBean(createReqVO, PilealarmDO.class);
        pilealarmMapper.insert(pilealarm);

        // 返回
        return pilealarm.getId();
    }

    @Override
    public void updatePilealarm(PilealarmSaveReqVO updateReqVO) {
        // 校验存在
        validatePilealarmExists(updateReqVO.getId());
        // 更新
        PilealarmDO updateObj = BeanUtils.toBean(updateReqVO, PilealarmDO.class);
        pilealarmMapper.updateById(updateObj);
    }

    @Override
    public void deletePilealarm(String id) {
        // 校验存在
        validatePilealarmExists(id);
        // 删除
        pilealarmMapper.deleteById(id);
    }

    @Override
    public void deletePilealarmListByIds(List<String> ids) {
        // 删除
        pilealarmMapper.deleteByIds(ids);
    }


    private void validatePilealarmExists(String id) {
        if (pilealarmMapper.selectById(id) == null) {
            throw exception(PILEALARM_NOT_EXISTS);
        }
    }

    @Override
    public PilealarmDO getPilealarm(String id) {
        return pilealarmMapper.selectById(id);
    }

    @Override
    public PageResult<PilealarmDO> getPilealarmPage(PilealarmPageReqVO pageReqVO) {
        return pilealarmMapper.selectPage(pageReqVO);
    }


    @Override
    public PageResult<NewPileAlarmRespVO> page(NewPileAlarmPageReqVO reqVO) {
        IPage<NewPileAlarmRespVO> page = pilealarmMapper.selectAlarmPage(
                MyBatisUtils.buildPage(reqVO),
                reqVO
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disPileAlarm(PileAlarmDisReqVO reqVO) {

        // 2. 查询告警
        PilealarmDO alarm = pilealarmMapper.selectById(reqVO.getId());
        if (alarm == null) {
            throw exception(PILEALARM_NOT_EXISTS);
        }

        // 3. 校验状态：必须是【未派单】才能派单
        if (!"未派单".equals(alarm.getStatus())) {
            throw exception("只有【未派单】的告警才能派单");
        }

        PilealarmDO update = new PilealarmDO();
        update.setId(alarm.getId());
        update.setHandlerId(Long.parseLong(reqVO.getHandleUserId())); // 处理人
        update.setStatus("已派单");                                   // 状态改为已派单

        return pilealarmMapper.updateById(update) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean handlePileAlarm(PileAlarmHandleReqVO reqVO) {
        // 1. 查询告警（自带租户 + 逻辑删除）
        PilealarmDO alarm = pilealarmMapper.selectById(reqVO.getId());
        if (alarm == null) {
            throw exception(PILEALARM_NOT_EXISTS);
        }

        // 2. 状态校验：必须是【已派单】
        if (!"已派单".equals(alarm.getStatus())) {
            throw exception("只有【已派单】的告警才能处置");
        }

        // 3. 执行更新（自动填充 updateBy / updateTime）
        PilealarmDO update = new PilealarmDO();
        update.setId(alarm.getId());
        update.setStatus("处置中");
        update.setHandleResult(reqVO.getDisposeMeasure());

        return pilealarmMapper.updateById(update) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean closePileAlarm(Long id) {
        // 1. 查询
        PilealarmDO alarm = pilealarmMapper.selectById(id);
        if (alarm == null) {
            throw exception(PILEALARM_NOT_EXISTS);
        }

        // 2. 状态校验
        if (!"处置中".equals(alarm.getStatus())) {
            throw exception("只有【处置中】的告警才能销单");
        }

        // 3. 更新
        PilealarmDO update = new PilealarmDO();
        update.setId(id);
        update.setStatus("已销单");

        return pilealarmMapper.updateById(update) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRemark(Long id, String remark) {
        PilealarmDO alarm = pilealarmMapper.selectById(id);
        if (alarm == null) {
            throw exception(PILEALARM_NOT_EXISTS);
        }

        PilealarmDO update = new PilealarmDO();
        update.setId(id);
        update.setRemark(remark);

        return pilealarmMapper.updateById(update) > 0;
    }


    @Override
    public PileAlarmChartRespVO getAlarmChart(PileAlarmChartReqVO reqVO) {
        // 1. 总统计
        PileAlarmChartRespVO resp = pilealarmMapper.selectAlarmChart(reqVO);
        if (resp == null) resp = new PileAlarmChartRespVO();

        int total = resp.getTotalCount() == null ? 0 : resp.getTotalCount();
        int handled = resp.getHandledCount() == null ? 0 : resp.getHandledCount();

        // 2. 处置率
        BigDecimal rate = BigDecimal.ZERO;
        if (total > 0) {
            rate = new BigDecimal(handled).divide(new BigDecimal(total), 2, RoundingMode.HALF_UP);
        }
        resp.setHandleRate(rate);

        // 3. 柱状图
        resp.setBarData(pilealarmMapper.selectBarData(reqVO));

        // 4. 饼图
        resp.setPieData(pilealarmMapper.selectPieData(reqVO));

        // 5. 卡片状态统计
        LambdaQueryWrapper<PilealarmDO> wrapper = Wrappers.lambdaQuery(PilealarmDO.class)
                .eq(PilealarmDO::getDeleted, 0)
                .ge(reqVO.getStartTime() != null, PilealarmDO::getCreateTime, reqVO.getStartTime())
                .le(reqVO.getEndTime() != null, PilealarmDO::getCreateTime, reqVO.getEndTime())
                .eq(reqVO.getStationId() != null, PilealarmDO::getStationId, reqVO.getStationId());

        List<PilealarmDO> all = pilealarmMapper.selectList(wrapper);

        PileAlarmChartRespVO.CardData card = new PileAlarmChartRespVO.CardData();
        card.setUnDisCount((int) all.stream().filter(s -> "未派单".equals(s.getStatus())).count());
        card.setDisCount((int) all.stream().filter(s -> "已派单".equals(s.getStatus())).count());
        card.setHandlingCount((int) all.stream().filter(s -> "处置中".equals(s.getStatus())).count());
        card.setClosedCount((int) all.stream().filter(s -> "已销单".equals(s.getStatus())).count());

        resp.setCardData(card);
        return resp;
    }

    @Override
    public List<PileAlarmChartRespVO.BarData> getDailyCount(PileAlarmDailyCountReqVO reqVO) {
        return pilealarmMapper.selectDailyCount(reqVO);
    }

    @Override
    public List<PileAlarmTypeRatioRespVO> getAlarmTypeRatio(PileAlarmDailyCountReqVO reqVO) {
        // 1. 查询各类型告警数量
        List<PileAlarmTypeRatioRespVO> ratioList = pilealarmMapper.selectAlarmTypeRatio(
                reqVO.getStartTime(),
                reqVO.getEndTime(),
                reqVO.getStationId());

        // 2. 无数据直接返回
        if (CollUtil.isEmpty(ratioList)) {
            return ratioList;
        }

        // 3. 计算总数量
        int total = ratioList.stream().mapToInt(PileAlarmTypeRatioRespVO::getValue).sum();
        BigDecimal totalBig = new BigDecimal(total);

        // 4. 计算占比（保留2位小数）
        ratioList.forEach(vo -> {
            BigDecimal value = new BigDecimal(vo.getValue());
            BigDecimal ratio = value.divide(totalBig, 2, RoundingMode.HALF_UP);
            vo.setRatio(ratio);
        });

        return ratioList;
    }

    @Override
    public PileAlarmHandleCountRespVO getHandleCount(PileAlarmDailyCountReqVO reqVO) {
        PileAlarmHandleCountRespVO resp = pilealarmMapper.selectHandleCount(
                reqVO.getStartTime(),
                reqVO.getEndTime(),
                reqVO.getStationId()
        );

        // 防止 null，统一返回 0
        if (resp == null) {
            resp = new PileAlarmHandleCountRespVO();
        }
        resp.setUnDisCount(resp.getUnDisCount() == null ? 0 : resp.getUnDisCount());
        resp.setDisCount(resp.getDisCount() == null ? 0 : resp.getDisCount());
        resp.setHandlingCount(resp.getHandlingCount() == null ? 0 : resp.getHandlingCount());
        resp.setClosedCount(resp.getClosedCount() == null ? 0 : resp.getClosedCount());
        resp.setTotalCount(resp.getTotalCount() == null ? 0 : resp.getTotalCount());

        return resp;
    }
}