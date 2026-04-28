package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.identify;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.identify.IdentifyDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.identify.IdentifyMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;

import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.*;
/**
 * 车牌识别 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IdentifyServiceImpl implements IdentifyService {

    @Resource
    private IdentifyMapper identifyMapper;

    @Override
    public Long createIdentify(IdentifySaveReqVO createReqVO) {
        // 插入
        IdentifyDO identify = BeanUtils.toBean(createReqVO, IdentifyDO.class);
        identifyMapper.insert(identify);

        // 返回
        return identify.getId();
    }

    @Override
    public void updateIdentify(IdentifySaveReqVO updateReqVO) {
        // 校验存在
        validateIdentifyExists(updateReqVO.getId());
        // 更新
        IdentifyDO updateObj = BeanUtils.toBean(updateReqVO, IdentifyDO.class);
        identifyMapper.updateById(updateObj);
    }

    @Override
    public void deleteIdentify(Long id) {
        // 校验存在
        validateIdentifyExists(id);
        // 删除
        identifyMapper.deleteById(id);
    }

    @Override
    public void deleteIdentifyListByIds(List<Long> ids) {
        // 删除
        identifyMapper.deleteByIds(ids);
    }


    private void validateIdentifyExists(Long id) {
        if (identifyMapper.selectById(id) == null) {
            throw exception(IDENTIFY_NOT_EXISTS);
        }
    }

    @Override
    public IdentifyDO getIdentify(Long id) {
        return identifyMapper.selectById(id);
    }

    @Override
    public PageResult<IdentifyRespVO> getIdentifyPage(IdentifyPageReqVO pageReqVO) {
        return identifyMapper.selectPageWithStation(pageReqVO);
    }

    @Override
    public Boolean createIdentify(IdentifyCreateReqVO reqVO) {
        // 1. 复制属性
        IdentifyDO identify = new IdentifyDO();
        BeanUtil.copyProperties(reqVO, identify);

        // 2. 默认值
        if (identify.getConfidence() == null) {
            identify.setConfidence(new BigDecimal("0.00"));
        }
        identify.setIsCorrected(false); // 未修正

        // 3. 插入
        return identifyMapper.insert(identify) > 0;
    }

    @Override
    public Boolean correctIdentify(IdentifyCorrectReqVO reqVO) {
        // 1. 复制属性
        IdentifyDO identify = new IdentifyDO();
        BeanUtil.copyProperties(reqVO, identify);

        // 2. 强制设置为已修正（前端传 true，这里再次保证）
        identify.setIsCorrected(Boolean.TRUE);

        // 3. 根据ID更新
        return identifyMapper.updateById(identify) > 0;
    }

    @Override
    public PlateIdentifyChartRespVO getIdentifyChartData(PlateIdentifyChartReqVO reqVO) {
        String startTime = reqVO.getStartTime();
        String endTime = reqVO.getEndTime();
        Long stationId = reqVO.getStationId();

        PlateIdentifyChartRespVO resp = new PlateIdentifyChartRespVO();

        // ========== 1. 卡片数据 ==========
        Map<String, Object> cardMap = identifyMapper.selectCardData(startTime, endTime, stationId);
        PlateIdentifyChartRespVO.CardDataVO card = new PlateIdentifyChartRespVO.CardDataVO();

        long total = 0;
        long successNum = 0;
        BigDecimal avgDuration = BigDecimal.ZERO; // 平均耗时

        if (cardMap != null) {
            total = Optional.ofNullable(cardMap.get("total")).map(Object::toString).map(Long::parseLong).orElse(0L);
            successNum = Optional.ofNullable(cardMap.get("successNum")).map(Object::toString).map(Long::parseLong).orElse(0L);
            // 读取真实平均耗时（秒）
            avgDuration = Optional.ofNullable(cardMap.get("avgDuration"))
                    .map(Object::toString)
                    .map(BigDecimal::new)
                    .orElse(BigDecimal.ZERO);
        }

        // 成功率
        if (total > 0) {
            BigDecimal rate = new BigDecimal(successNum * 100).divide(new BigDecimal(total), 1, BigDecimal.ROUND_HALF_UP);
            card.setSuccessRate(rate);
        } else {
            card.setSuccessRate(BigDecimal.ZERO);
        }

        // 平均耗时（不写死！）
        card.setAvgDuration(avgDuration);
        resp.setCardData(card);

        // ========== 2. 折线图 ==========
        List<PlateIdentifyChartRespVO.SuccessRateTrendVO> trendList = new ArrayList<>();
        List<Map<String, Object>> dayList = identifyMapper.selectDayTrend(startTime, endTime, stationId);
        if (dayList == null) dayList = new ArrayList<>();

        for (Map<String, Object> map : dayList) {
            PlateIdentifyChartRespVO.SuccessRateTrendVO vo = new PlateIdentifyChartRespVO.SuccessRateTrendVO();
            vo.setDate(Optional.ofNullable(map.get("date")).map(Object::toString).orElse(""));

            long t = Optional.ofNullable(map.get("total")).map(Object::toString).map(Long::parseLong).orElse(0L);
            long s = Optional.ofNullable(map.get("successNum")).map(Object::toString).map(Long::parseLong).orElse(0L);

            if (t > 0) {
                vo.setRate(new BigDecimal(s * 100).divide(new BigDecimal(t), 1, BigDecimal.ROUND_HALF_UP));
            } else {
                vo.setRate(BigDecimal.ZERO);
            }
            trendList.add(vo);
        }
        resp.setSuccessRateTrend(trendList);

        // ========== 3. 柱状图 ==========
        List<PlateIdentifyChartRespVO.StationIdentifyCountVO> stationList = new ArrayList<>();
        List<Map<String, Object>> stationMapList = identifyMapper.selectStationCount(startTime, endTime, stationId);
        if (stationMapList == null) stationMapList = new ArrayList<>();

        for (Map<String, Object> map : stationMapList) {
            PlateIdentifyChartRespVO.StationIdentifyCountVO vo = new PlateIdentifyChartRespVO.StationIdentifyCountVO();
            vo.setStationName(Optional.ofNullable(map.get("stationName")).map(Object::toString).orElse("未知场地"));
            vo.setCount(Optional.ofNullable(map.get("count")).map(Object::toString).map(Long::parseLong).orElse(0L));
            stationList.add(vo);
        }
        resp.setStationIdentifyCount(stationList);

        return resp;
    }

}