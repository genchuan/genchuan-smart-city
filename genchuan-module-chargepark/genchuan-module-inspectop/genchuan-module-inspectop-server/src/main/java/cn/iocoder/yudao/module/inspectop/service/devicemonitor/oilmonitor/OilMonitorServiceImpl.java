package cn.iocoder.yudao.module.inspectop.service.devicemonitor.oilmonitor;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.oilmonitor.vo.*;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.oilmonitor.OilMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.oilmonitor.OilMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

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
    @LogRecord(type = OIL_MONITOR_TYPE, subType = OIL_MONITOR_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = OIL_MONITOR_CREATE_SUCCESS)
    public Long createOilMonitor(OilMonitorSaveReqVO createReqVO) {
        // 插入
        OilMonitorDO oilMonitor = BeanUtils.toBean(createReqVO, OilMonitorDO.class);
        oilMonitorMapper.insert(oilMonitor);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return oilMonitor.getId();
    }

    @Override
    @LogRecord(type = OIL_MONITOR_TYPE, subType = OIL_MONITOR_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = OIL_MONITOR_UPDATE_SUCCESS)
    public void updateOilMonitor(OilMonitorSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        OilMonitorDO oldOilMonitor = validateOilMonitorExists(updateReqVO.getId());

        // 2. 更新
        OilMonitorDO updateObj = BeanUtils.toBean(updateReqVO, OilMonitorDO.class);
        oilMonitorMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        OilMonitorSaveReqVO oldVO = BeanUtils.toBean(oldOilMonitor, OilMonitorSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = OIL_MONITOR_TYPE, subType = OIL_MONITOR_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = OIL_MONITOR_DELETE_SUCCESS)
    public void deleteOilMonitor(Long id) {
        // 校验存在
        validateOilMonitorExists(id);
        // 删除
        oilMonitorMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = OIL_MONITOR_TYPE, subType = OIL_MONITOR_DELETE_LIST_SUB_TYPE,
            success = OIL_MONITOR_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteOilMonitorListByIds(List<Long> ids) {
        // 删除
        oilMonitorMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    private OilMonitorDO validateOilMonitorExists(Long id) {
        OilMonitorDO oilMonitor = oilMonitorMapper.selectById(id);
        if (oilMonitor == null) {
            throw exception(OIL_MONITOR_NOT_EXISTS);
        }
        return oilMonitor; // 返回查询到的对象
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
    @LogRecord(type = OIL_MONITOR_TYPE, subType = OIL_MONITOR_BATCH_PROCESS_SUB_TYPE,
            success = OIL_MONITOR_BATCH_PROCESS_SUCCESS, bizNo = "")
    public void batchProcessOilMonitor(OilMonitorBatchProcessReqVO batchProcessReqVO) {
        // 1. 校验ids不能为空
        if (CollUtil.isEmpty(batchProcessReqVO.getIds())) {
            throw exception(OIL_MONITOR_NOT_EXISTS);
        }

        // 2. 校验所有记录是否存在
        batchProcessReqVO.getIds().forEach(this::validateOilMonitorExists);

        // 3. 构建更新条件
        LambdaUpdateWrapper<OilMonitorDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(OilMonitorDO::getId, batchProcessReqVO.getIds());

        // 4. 动态设置更新字段 - 更新处置状态
        if (batchProcessReqVO.getProcessStatus() != null) {
            updateWrapper.set(OilMonitorDO::getProcessStatus, batchProcessReqVO.getProcessStatus());
        }

        // 5. 动态设置更新字段 - 更新处置进度
        if (batchProcessReqVO.getProcessProgress() != null) {
            updateWrapper.set(OilMonitorDO::getProcessProgress, batchProcessReqVO.getProcessProgress());
        }

        // 6. 执行更新
        oilMonitorMapper.update(null, updateWrapper);

        // 7. 设置日志上下文变量
        LogRecordContext.putVariable("ids", batchProcessReqVO.getIds());
        LogRecordContext.putVariable("processProgress", batchProcessReqVO.getProcessProgress() != null ?
                batchProcessReqVO.getProcessProgress() : "未设置");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = OIL_MONITOR_TYPE, subType = OIL_MONITOR_IGNORE_SUB_TYPE,
            bizNo = "{{#ignoreReqVO.id}}", success = OIL_MONITOR_IGNORE_SUCCESS)
    public void ignoreOilMonitor(OilMonitorIgnoreReqVO ignoreReqVO) {
        // 校验存在
        validateOilMonitorExists(ignoreReqVO.getId());

        // 更新忽略理由
        LambdaUpdateWrapper<OilMonitorDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OilMonitorDO::getId, ignoreReqVO.getId())
                .set(OilMonitorDO::getIgnoreReason, ignoreReqVO.getIgnoreReason());

        // 执行更新
        oilMonitorMapper.update(null, updateWrapper);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ignoreReqVO", ignoreReqVO);
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