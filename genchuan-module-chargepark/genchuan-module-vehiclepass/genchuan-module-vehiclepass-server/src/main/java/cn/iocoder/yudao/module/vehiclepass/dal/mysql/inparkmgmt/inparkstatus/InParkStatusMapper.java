package cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.inparkstatus;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.inparkstatus.InParkStatusDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;


/**
 * 在停状态 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface InParkStatusMapper extends BaseMapperX<InParkStatusDO> {

    default PageResult<InParkStatusDO> selectPage(InParkStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InParkStatusDO>()
                .eqIfPresent(InParkStatusDO::getStationId, reqVO.getStationId())
                .eqIfPresent(InParkStatusDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(InParkStatusDO::getCarNo, reqVO.getCarNo())
                .betweenIfPresent(InParkStatusDO::getInTime, reqVO.getInTime())
                .betweenIfPresent(InParkStatusDO::getOverTime, reqVO.getOverTime())
                .eqIfPresent(InParkStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InParkStatusDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InParkStatusDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InParkStatusDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InParkStatusDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InParkStatusDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InParkStatusDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InParkStatusDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InParkStatusDO::getId));
    }

    IPage<InParkStatusRespVO> selectPageJoinSpaceStation(Page<?> page, @Param("reqVO") InParkStatusPageReqVO reqVO);

}