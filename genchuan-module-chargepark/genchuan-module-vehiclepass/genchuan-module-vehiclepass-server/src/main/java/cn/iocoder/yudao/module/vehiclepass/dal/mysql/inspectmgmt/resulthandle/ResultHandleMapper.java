package cn.iocoder.yudao.module.vehiclepass.dal.mysql.inspectmgmt.resulthandle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.resulthandle.ResultHandleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 结果处置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ResultHandleMapper extends BaseMapperX<ResultHandleDO> {

    default PageResult<ResultHandleDO> selectPage(ResultHandlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ResultHandleDO>()
                .eqIfPresent(ResultHandleDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(ResultHandleDO::getViolationType, reqVO.getViolationType())
                .eqIfPresent(ResultHandleDO::getHandleMethod, reqVO.getHandleMethod())
                .eqIfPresent(ResultHandleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ResultHandleDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ResultHandleDO::getHandleUserId, reqVO.getHandleUserId())
                .betweenIfPresent(ResultHandleDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(ResultHandleDO::getRectifyStatus, reqVO.getRectifyStatus())
                .eqIfPresent(ResultHandleDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(ResultHandleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ResultHandleDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ResultHandleDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ResultHandleDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ResultHandleDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ResultHandleDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ResultHandleDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ResultHandleDO::getId));
    }

    /**
     * 分页查询（使用JOIN查询关联表）
     */
    IPage<ResultHandleRespVO> selectPageJoin(Page<?> page, @Param("reqVO") ResultHandlePageReqVO reqVO);

}