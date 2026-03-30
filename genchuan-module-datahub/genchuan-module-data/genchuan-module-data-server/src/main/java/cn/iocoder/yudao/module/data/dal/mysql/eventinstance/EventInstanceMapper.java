package cn.iocoder.yudao.module.data.dal.mysql.eventinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo.EventInstancePageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventinstance.EventInstanceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 监测事件实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EventInstanceMapper extends BaseMapperX<EventInstanceDO> {

    default PageResult<EventInstanceDO> selectPage(EventInstancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventInstanceDO>()
                .likeIfPresent(EventInstanceDO::getName, reqVO.getName())
                .eqIfPresent(EventInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(EventInstanceDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(EventInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(EventInstanceDO::getMonitorId, reqVO.getMonitorId())
                .likeIfPresent(EventInstanceDO::getMonitorName, reqVO.getMonitorName())
                .eqIfPresent(EventInstanceDO::getCoordinate, reqVO.getCoordinate())
                .eqIfPresent(EventInstanceDO::getEventLevel, reqVO.getEventLevel())
                .eqIfPresent(EventInstanceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(EventInstanceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EventInstanceDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(EventInstanceDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(EventInstanceDO::getMatterId, reqVO.getMatterId())
                .likeIfPresent(EventInstanceDO::getMatterName, reqVO.getMatterName())
                .eqIfPresent(EventInstanceDO::getWarningWay, reqVO.getWarningWay())
                .eqIfPresent(EventInstanceDO::getReportSource, reqVO.getReportSource())
                .eqIfPresent(EventInstanceDO::getDisposeLog, reqVO.getDisposeLog())
                .eqIfPresent(EventInstanceDO::getHandler, reqVO.getHandler())
                .betweenIfPresent(EventInstanceDO::getDealTime, reqVO.getDealTime())
                .eqIfPresent(EventInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EventInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EventInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EventInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventInstanceDO::getId));
    }

    // 新增方法：根据分类ID列表分页查询
    default PageResult<EventInstanceDO> selectPageByCategoryIds(EventInstancePageReqVO reqVO, List<String> categoryIds) {
        LambdaQueryWrapperX<EventInstanceDO> queryWrapper = new LambdaQueryWrapperX<EventInstanceDO>()
                .likeIfPresent(EventInstanceDO::getName, reqVO.getName())
                .eqIfPresent(EventInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(EventInstanceDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(EventInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(EventInstanceDO::getMonitorId, reqVO.getMonitorId())
                .likeIfPresent(EventInstanceDO::getMonitorName, reqVO.getMonitorName())
                .eqIfPresent(EventInstanceDO::getCoordinate, reqVO.getCoordinate())
                .eqIfPresent(EventInstanceDO::getEventLevel, reqVO.getEventLevel())
                .eqIfPresent(EventInstanceDO::getDescription, reqVO.getDescription())
                .eqIfPresent(EventInstanceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EventInstanceDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(EventInstanceDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(EventInstanceDO::getMatterId, reqVO.getMatterId())
                .likeIfPresent(EventInstanceDO::getMatterName, reqVO.getMatterName())
                .eqIfPresent(EventInstanceDO::getWarningWay, reqVO.getWarningWay())
                .eqIfPresent(EventInstanceDO::getReportSource, reqVO.getReportSource())
                .eqIfPresent(EventInstanceDO::getDisposeLog, reqVO.getDisposeLog())
                .eqIfPresent(EventInstanceDO::getHandler, reqVO.getHandler())
                .betweenIfPresent(EventInstanceDO::getDealTime, reqVO.getDealTime())
                .eqIfPresent(EventInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EventInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EventInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EventInstanceDO::getCreateTime, reqVO.getCreateTime())
                .in(EventInstanceDO::getCategoryId, categoryIds)  // 关键：按分类ID列表查询
                .orderByDesc(EventInstanceDO::getId);

        return selectPage(reqVO, queryWrapper);
    }
}