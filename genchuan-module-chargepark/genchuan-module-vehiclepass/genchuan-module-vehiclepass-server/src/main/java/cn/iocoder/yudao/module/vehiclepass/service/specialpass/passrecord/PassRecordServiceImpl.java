package cn.iocoder.yudao.module.vehiclepass.service.specialpass.passrecord;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordCheckReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.passrecord.PassRecordDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.specialpass.passrecord.PassRecordMapper;
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
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.RECORD_NOT_EXISTS;


/**
 * 放行记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PassRecordServiceImpl implements PassRecordService {

    private static final int PARALLEL_THRESHOLD = 100;

    @Resource
    private PassRecordMapper recordMapper;

    @Override
    public Long createRecord(PassRecordSaveReqVO createReqVO) {
        // 插入
        PassRecordDO record = BeanUtils.toBean(createReqVO, PassRecordDO.class);
        recordMapper.insert(record);

        // 返回
        return record.getId();
    }

    @Override
    public void updateRecord(PassRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateRecordExists(updateReqVO.getId());
        // 更新
        PassRecordDO updateObj = BeanUtils.toBean(updateReqVO, PassRecordDO.class);
        recordMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecord(Long id) {
        // 校验存在
        validateRecordExists(id);
        // 删除
        recordMapper.deleteById(id);
    }

    @Override
    public void deleteRecordListByIds(List<Long> ids) {
        // 删除
        recordMapper.deleteByIds(ids);
    }


    private void validateRecordExists(Long id) {
        if (recordMapper.selectById(id) == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
    }

    @Override
    public PassRecordDO getRecord(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public PassRecordRespVO getPassRecordWithStation(Long id) {
        return recordMapper.selectByIdJoinStation(id);
    }

    @Override
    public PageResult<PassRecordDO> getRecordPage(PassRecordPageReqVO pageReqVO) {
        return recordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<PassRecordRespVO> getRecordPageWithJoin(PassRecordPageReqVO pageReqVO) {
        Page<PassRecordRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<PassRecordRespVO> pageResult = recordMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void check(PassRecordCheckReqVO reqVO) {
        PassRecordDO record = recordMapper.selectById(reqVO.getId());
        if (record == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
        PassRecordDO updateObj = new PassRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setCheckResult(reqVO.getCheckResult());
        updateObj.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        updateObj.setOperatorTime(LocalDateTime.now());
        recordMapper.updateById(updateObj);
    }

    @Override
    public PassRecordChartRespVO getChart(PassRecordChartReqVO reqVO) {
        // 查询放行量趋势
        List<Map<String, Object>> trendList = recordMapper.selectPassCountTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        List<PassRecordChartRespVO.PassCountTrend> passCountTrends = (trendList.size() > PARALLEL_THRESHOLD
                ? trendList.parallelStream()
                : trendList.stream())
            .map(trend -> {
                PassRecordChartRespVO.PassCountTrend item = new PassRecordChartRespVO.PassCountTrend();
                item.setDate(trend.get("date") != null ? trend.get("date").toString() : null);
                item.setCount(MapValueUtils.getLongValue(trend, "count"));
                return item;
            })
            .collect(Collectors.toList());

        // 查询今日放行量和异常放行占比
        Map<String, Object> stats = recordMapper.selectPassStats(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        PassRecordChartRespVO.CardData cardData = new PassRecordChartRespVO.CardData();
        if (stats != null) {
            cardData.setTodayPassCount(MapValueUtils.getLongValue(stats, "todayPassCount"));
            cardData.setAbnormalPassRate(MapValueUtils.getDoubleValue(stats, "abnormalPassRate"));
        } else {
            cardData.setTodayPassCount(0L);
            cardData.setAbnormalPassRate(0.0);
        }

        // 组装返回
        PassRecordChartRespVO respVO = new PassRecordChartRespVO();
        respVO.setPassCountTrend(passCountTrends);
        respVO.setCardData(cardData);
        return respVO;
    }

}