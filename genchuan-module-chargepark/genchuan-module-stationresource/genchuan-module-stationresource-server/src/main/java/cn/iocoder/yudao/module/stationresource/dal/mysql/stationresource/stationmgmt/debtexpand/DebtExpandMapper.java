package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.debtexpand;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.chart.DebtExpandChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.debtexpand.DebtExpandDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 联合追缴拓场配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DebtExpandMapper extends BaseMapperX<DebtExpandDO> {

    default PageResult<DebtExpandDO> selectPage(DebtExpandPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DebtExpandDO>()
                .eqIfPresent(DebtExpandDO::getStationId, reqVO.getStationId())
                .eqIfPresent(DebtExpandDO::getType, reqVO.getType())
                .eqIfPresent(DebtExpandDO::getRange, reqVO.getRange())
                .eqIfPresent(DebtExpandDO::getProgress, reqVO.getProgress())
                .eqIfPresent(DebtExpandDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DebtExpandDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(DebtExpandDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(DebtExpandDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(DebtExpandDO::getRecoveryRate, reqVO.getRecoveryRate())
                .eqIfPresent(DebtExpandDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DebtExpandDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(DebtExpandDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(DebtExpandDO::getCreator, reqVO.getCreator())
                .eqIfPresent(DebtExpandDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(DebtExpandDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(DebtExpandDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(DebtExpandDO::getId));
    }

    // 卡片统计
    DebtExpandChartRespVO.CardDataItem selectCardData();

    // 拓场进度趋势（按月）
    List<DebtExpandChartRespVO.ProgressLineItem> selectProgressLineList();

    // 追缴成功率（按场站）
    List<DebtExpandChartRespVO.RecoveryBarItem> selectRecoveryBarList();

    Page<DebtExpandRespVO> getPage(Page<DebtExpandRespVO> page, @Param("pageReqVO") DebtExpandPageReqVO pageReqVO);
}
