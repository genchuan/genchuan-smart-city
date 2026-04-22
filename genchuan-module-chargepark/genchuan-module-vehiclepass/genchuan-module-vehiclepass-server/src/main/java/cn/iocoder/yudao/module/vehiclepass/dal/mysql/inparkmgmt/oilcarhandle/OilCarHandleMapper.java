package cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.oilcarhandle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.oilcarhandle.OilCarHandleDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 油车占位处置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OilCarHandleMapper extends BaseMapperX<OilCarHandleDO> {

    default PageResult<OilCarHandleDO> selectPage(OilCarHandlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OilCarHandleDO>()
                .eqIfPresent(OilCarHandleDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(OilCarHandleDO::getSpaceId, reqVO.getSpaceId())
                .betweenIfPresent(OilCarHandleDO::getIdentifyTime, reqVO.getIdentifyTime())
                .eqIfPresent(OilCarHandleDO::getOccupyType, reqVO.getOccupyType())
                .eqIfPresent(OilCarHandleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OilCarHandleDO::getStationId, reqVO.getStationId())
                .eqIfPresent(OilCarHandleDO::getHandleUserId, reqVO.getHandleUserId())
                .betweenIfPresent(OilCarHandleDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(OilCarHandleDO::getHandleMethod, reqVO.getHandleMethod())
                .eqIfPresent(OilCarHandleDO::getIgnoreReason, reqVO.getIgnoreReason())
                .eqIfPresent(OilCarHandleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OilCarHandleDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OilCarHandleDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(OilCarHandleDO::getCreator, reqVO.getCreator())
                .eqIfPresent(OilCarHandleDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(OilCarHandleDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(OilCarHandleDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(OilCarHandleDO::getId));
    }

    IPage<OilCarHandleRespVO> selectPageJoin(Page<?> page, @Param("reqVO") OilCarHandlePageReqVO reqVO);

}