package cn.iocoder.yudao.module.inspectop.service.devicemonitor.sharechargemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.sharechargemonitor.vo.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.sharechargemonitor.ShareChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.sharechargemonitor.ShareChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

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
    public Long createShareChargeMonitor(ShareChargeMonitorSaveReqVO createReqVO) {
        // 插入
        ShareChargeMonitorDO shareChargeMonitor = BeanUtils.toBean(createReqVO, ShareChargeMonitorDO.class);
        shareChargeMonitorMapper.insert(shareChargeMonitor);

        // 返回
        return shareChargeMonitor.getId();
    }

    @Override
    public void updateShareChargeMonitor(ShareChargeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateShareChargeMonitorExists(updateReqVO.getId());
        // 更新
        ShareChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, ShareChargeMonitorDO.class);
        shareChargeMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteShareChargeMonitor(Long id) {
        // 校验存在
        validateShareChargeMonitorExists(id);
        // 删除
        shareChargeMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteShareChargeMonitorListByIds(List<Long> ids) {
        // 删除
        shareChargeMonitorMapper.deleteByIds(ids);
        }


    private void validateShareChargeMonitorExists(Long id) {
        if (shareChargeMonitorMapper.selectById(id) == null) {
            throw exception(SHARE_CHARGE_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public ShareChargeMonitorDO getShareChargeMonitor(Long id) {
        return shareChargeMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<ShareChargeMonitorRespVO> getShareChargeMonitorPage(ShareChargeMonitorPageReqVO pageReqVO) {
        // 1. 创建 MyBatis-Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ShareChargeMonitorRespVO> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 2. 调用Mapper的关联查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ShareChargeMonitorRespVO> resultPage =
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
    public void alarmShareChargeMonitor(ShareChargeMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateShareChargeMonitorExists(alarmReqVO.getId());

        // 2. 更新告警备注
        ShareChargeMonitorDO updateObj = new ShareChargeMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());

        // 3. 执行更新操作
        shareChargeMonitorMapper.updateById(updateObj);
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