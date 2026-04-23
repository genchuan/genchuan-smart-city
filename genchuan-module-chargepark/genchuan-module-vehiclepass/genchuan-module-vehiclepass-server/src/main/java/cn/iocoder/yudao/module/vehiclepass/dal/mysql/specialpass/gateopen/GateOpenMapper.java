package cn.iocoder.yudao.module.vehiclepass.dal.mysql.specialpass.gateopen;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.gateopen.GateOpenDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 开闸管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface GateOpenMapper extends BaseMapperX<GateOpenDO> {

    default PageResult<GateOpenDO> selectPage(GateOpenPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GateOpenDO>()
                .eqIfPresent(GateOpenDO::getStationId, reqVO.getStationId())
                .eqIfPresent(GateOpenDO::getOpenReason, reqVO.getOpenReason())
                .eqIfPresent(GateOpenDO::getApplyUserId, reqVO.getApplyUserId())
                .betweenIfPresent(GateOpenDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(GateOpenDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GateOpenDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(GateOpenDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(GateOpenDO::getExecuteTime, reqVO.getExecuteTime())
                .eqIfPresent(GateOpenDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(GateOpenDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GateOpenDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(GateOpenDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(GateOpenDO::getCreator, reqVO.getCreator())
                .eqIfPresent(GateOpenDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(GateOpenDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(GateOpenDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(GateOpenDO::getId));
    }

    IPage<GateOpenRespVO> selectPageJoin(Page<?> page, @Param("reqVO") GateOpenPageReqVO reqVO);

}