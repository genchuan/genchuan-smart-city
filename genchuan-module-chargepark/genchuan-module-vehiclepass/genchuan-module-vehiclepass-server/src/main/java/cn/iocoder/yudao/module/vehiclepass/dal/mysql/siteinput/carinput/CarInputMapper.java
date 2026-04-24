package cn.iocoder.yudao.module.vehiclepass.dal.mysql.siteinput.carinput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.carinput.CarInputDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 车辆录入 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CarInputMapper extends BaseMapperX<CarInputDO> {

    default PageResult<CarInputDO> selectPage(CarInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CarInputDO>()
                .eqIfPresent(CarInputDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(CarInputDO::getSpaceId, reqVO.getSpaceId())
                .betweenIfPresent(CarInputDO::getInputTime, reqVO.getInputTime())
                .eqIfPresent(CarInputDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CarInputDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(CarInputDO::getInputUserId, reqVO.getInputUserId())
                .eqIfPresent(CarInputDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(CarInputDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(CarInputDO::getAuditComment, reqVO.getAuditComment())
                .eqIfPresent(CarInputDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CarInputDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CarInputDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(CarInputDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CarInputDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(CarInputDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CarInputDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(CarInputDO::getId));
    }

    /**
     * 分页查询（使用JOIN查询关联表）
     */
    IPage<CarInputRespVO> selectPageJoin(Page<?> page, @Param("reqVO") CarInputPageReqVO reqVO);

}