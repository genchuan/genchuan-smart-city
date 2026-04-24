package cn.iocoder.yudao.module.vehiclepass.dal.mysql.siteinput.endpark;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.endpark.EndParkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 结束停车 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EndParkMapper extends BaseMapperX<EndParkDO> {

    default PageResult<EndParkDO> selectPage(EndParkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EndParkDO>()
                .eqIfPresent(EndParkDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(EndParkDO::getSpaceId, reqVO.getSpaceId())
                .betweenIfPresent(EndParkDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(EndParkDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EndParkDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(EndParkDO::getOperatorId, reqVO.getOperatorId())
                .eqIfPresent(EndParkDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(EndParkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EndParkDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(EndParkDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(EndParkDO::getCreator, reqVO.getCreator())
                .eqIfPresent(EndParkDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(EndParkDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(EndParkDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(EndParkDO::getId));
    }

    /**
     * 分页查询（使用JOIN查询关联表）
     */
    IPage<EndParkRespVO> selectPageJoin(Page<?> page, @Param("reqVO") EndParkPageReqVO reqVO);

}