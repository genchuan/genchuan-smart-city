package cn.iocoder.yudao.module.inspectop.service.oilmonitor;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.jsqlparser.expression.LambdaExpression;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.oilmonitor.OilMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.oilmonitor.OilMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 油车占位监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class OilMonitorServiceImpl implements OilMonitorService {

    @Resource
    private OilMonitorMapper oilMonitorMapper;

    @Override
    public Long createOilMonitor(OilMonitorSaveReqVO createReqVO) {
        // 插入
        OilMonitorDO oilMonitor = BeanUtils.toBean(createReqVO, OilMonitorDO.class);
        oilMonitorMapper.insert(oilMonitor);

        // 返回
        return oilMonitor.getId();
    }

    @Override
    public void updateOilMonitor(OilMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateOilMonitorExists(updateReqVO.getId());
        // 更新
        OilMonitorDO updateObj = BeanUtils.toBean(updateReqVO, OilMonitorDO.class);
        oilMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteOilMonitor(Long id) {
        // 校验存在
        validateOilMonitorExists(id);
        // 删除
        oilMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteOilMonitorListByIds(List<Long> ids) {
        // 删除
        oilMonitorMapper.deleteByIds(ids);
        }


    private void validateOilMonitorExists(Long id) {
        if (oilMonitorMapper.selectById(id) == null) {
            throw exception(OIL_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public OilMonitorDO getOilMonitor(Long id) {
        return oilMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<OilMonitorRespVO> getOilMonitorPage(OilMonitorPageReqVO pageReqVO) {
        // 创建MyBatis-Plus的分页对象
        Page<OilMonitorRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的自定义关联查询方法
        Page<OilMonitorRespVO> pageResult = oilMonitorMapper.selectPageWithJoin(mpPage, pageReqVO);

        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchProcessOilMonitor(OilMonitorBatchProcessReqVO batchProcessReqVO) {
        // 校验ids不能为空
        if (CollUtil.isEmpty(batchProcessReqVO.getIds())) {
            throw exception(OIL_MONITOR_NOT_EXISTS);
        }

        // 校验所有记录是否存在
        batchProcessReqVO.getIds().forEach(this::validateOilMonitorExists);

        // 批量更新处置进度
        LambdaUpdateWrapper<OilMonitorDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(OilMonitorDO::getId, batchProcessReqVO.getIds());

        // 只有当processProgress不为null时才更新
        if (batchProcessReqVO.getProcessProgress() != null) {
            updateWrapper.set(OilMonitorDO::getProcessProgress, batchProcessReqVO.getProcessProgress());
        }

        // 执行更新
        oilMonitorMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ignoreOilMonitor(OilMonitorIgnoreReqVO ignoreReqVO) {
        // 校验存在
        validateOilMonitorExists(ignoreReqVO.getId());

        // 更新忽略理由
        LambdaUpdateWrapper<OilMonitorDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OilMonitorDO::getId, ignoreReqVO.getId())
                .set(OilMonitorDO::getIgnoreReason, ignoreReqVO.getIgnoreReason());

        // 执行更新
        oilMonitorMapper.update(null, updateWrapper);
    }

    @Override
    public OilMonitorChartRespVO getOilMonitorChart(OilMonitorChartReqVO reqVO) {
        OilMonitorChartRespVO result = new OilMonitorChartRespVO();

        // 1. 获取趋势数据
        List<OilMonitorChartRespVO.TrendData> trendDataList = oilMonitorMapper.selectTrendData(reqVO);
        result.setTrendData(trendDataList);

        // 2. 获取场站数据
        List<OilMonitorChartRespVO.StationData> stationDataList = oilMonitorMapper.selectStationData(reqVO);
        result.setStationData(stationDataList);

        // 3. 获取卡片数据
        OilMonitorChartRespVO.CardData cardData = oilMonitorMapper.selectCardData(reqVO);
        result.setCardData(cardData);

        return result;
    }

}