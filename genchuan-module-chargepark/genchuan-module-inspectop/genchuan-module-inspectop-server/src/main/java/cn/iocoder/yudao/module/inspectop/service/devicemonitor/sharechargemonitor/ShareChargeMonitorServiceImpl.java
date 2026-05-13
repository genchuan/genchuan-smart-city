package cn.iocoder.yudao.module.inspectop.service.devicemonitor.sharechargemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.sharechargemonitor.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.sharechargemonitor.ShareChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.sharechargemonitor.ShareChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 共享充电监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ShareChargeMonitorServiceImpl implements ShareChargeMonitorService {

    @Resource
    private ShareChargeMonitorMapper shareChargeMonitorMapper;

    @Override
    @LogRecord(type = SHARE_CHARGE_MONITOR_TYPE, subType = SHARE_CHARGE_MONITOR_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = SHARE_CHARGE_MONITOR_CREATE_SUCCESS)
    public Long createShareChargeMonitor(ShareChargeMonitorSaveReqVO createReqVO) {
        // 插入
        ShareChargeMonitorDO shareChargeMonitor = BeanUtils.toBean(createReqVO, ShareChargeMonitorDO.class);
        shareChargeMonitorMapper.insert(shareChargeMonitor);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return shareChargeMonitor.getId();
    }

    @Override
    @LogRecord(type = SHARE_CHARGE_MONITOR_TYPE, subType = SHARE_CHARGE_MONITOR_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = SHARE_CHARGE_MONITOR_UPDATE_SUCCESS)
    public void updateShareChargeMonitor(ShareChargeMonitorSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        ShareChargeMonitorDO oldShareChargeMonitor = validateShareChargeMonitorExists(updateReqVO.getId());

        // 2. 更新
        ShareChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, ShareChargeMonitorDO.class);
        shareChargeMonitorMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        ShareChargeMonitorSaveReqVO oldVO = BeanUtils.toBean(oldShareChargeMonitor, ShareChargeMonitorSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = SHARE_CHARGE_MONITOR_TYPE, subType = SHARE_CHARGE_MONITOR_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = SHARE_CHARGE_MONITOR_DELETE_SUCCESS)
    public void deleteShareChargeMonitor(Long id) {
        // 校验存在
        validateShareChargeMonitorExists(id);
        // 删除
        shareChargeMonitorMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = SHARE_CHARGE_MONITOR_TYPE, subType = SHARE_CHARGE_MONITOR_DELETE_LIST_SUB_TYPE,
            success = SHARE_CHARGE_MONITOR_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteShareChargeMonitorListByIds(List<Long> ids) {
        // 删除
        shareChargeMonitorMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    private ShareChargeMonitorDO validateShareChargeMonitorExists(Long id) {
        ShareChargeMonitorDO shareChargeMonitor = shareChargeMonitorMapper.selectById(id);
        if (shareChargeMonitor == null) {
            throw exception(SHARE_CHARGE_MONITOR_NOT_EXISTS);
        }
        return shareChargeMonitor; // 返回查询到的对象
    }

    @Override
    public ShareChargeMonitorDO getShareChargeMonitor(Long id) {
        return shareChargeMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<ShareChargeMonitorRespVO> getShareChargeMonitorPage(ShareChargeMonitorPageReqVO pageReqVO) {
        // 1. 创建 MyBatis-Plus 分页对象
        Page<ShareChargeMonitorRespVO> mpPage =
                new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 2. 调用Mapper的关联查询方法
        Page<ShareChargeMonitorRespVO> resultPage =
                shareChargeMonitorMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 3. 直接构造 PageResult 并返回
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    public ShareChargeMonitorLocationRespVO getShareChargeMonitorLocation(Long id) {
        // 校验记录是否存在
        validateShareChargeMonitorExists(id);

        // 从数据库查询定位信息（包含经度、纬度、场站名称）
        ShareChargeMonitorLocationRespVO locationRespVO = shareChargeMonitorMapper.selectLocationById(id);

        if (locationRespVO == null) {
            throw exception(SHARE_CHARGE_MONITOR_NOT_EXISTS);
        }

        // 注意：这里不设置deviceCode，留给Controller层处理模拟数据
        return locationRespVO;
    }

    @Override
    @LogRecord(type = SHARE_CHARGE_MONITOR_TYPE, subType = SHARE_CHARGE_MONITOR_ALARM_SUB_TYPE,
            bizNo = "{{#alarmReqVO.id}}", success = SHARE_CHARGE_MONITOR_ALARM_SUCCESS)
    public void alarmShareChargeMonitor(ShareChargeMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateShareChargeMonitorExists(alarmReqVO.getId());

        // 2. 更新告警备注
        ShareChargeMonitorDO updateObj = new ShareChargeMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());
        updateObj.setAlarmTime(LocalDateTime.now());

        // 3. 执行更新操作
        shareChargeMonitorMapper.updateById(updateObj);

        // 4. 设置日志上下文变量
        LogRecordContext.putVariable("alarmReqVO", alarmReqVO);
    }

    @Override
    public ShareChargeMonitorChartRespVO getShareChargeMonitorChart(ShareChargeMonitorChartReqVO reqVO) {
        // 创建响应对象
        ShareChargeMonitorChartRespVO result = new ShareChargeMonitorChartRespVO();

        // 1. 获取地图数据
        List<ShareChargeMonitorChartRespVO.MapData> mapDataList = shareChargeMonitorMapper.selectMapData(reqVO);
        result.setMapData(mapDataList);

        // 2. 获取趋势数据
        List<ShareChargeMonitorChartRespVO.TrendData> trendDataList = shareChargeMonitorMapper.selectTrendData(reqVO);
        result.setTrendData(trendDataList);

        // 3. 获取卡片数据
        ShareChargeMonitorChartRespVO.CardData cardData = shareChargeMonitorMapper.selectCardData(reqVO);
        result.setCardData(cardData);

        return result;
    }

}