package cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.unplateenter;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.unplateenter.UnplateEnterDO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;


/**
 * 无牌入场 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface UnplateEnterMapper extends BaseMapperX<UnplateEnterDO> {

    default PageResult<UnplateEnterDO> selectPage(UnplateEnterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UnplateEnterDO>()
                .eqIfPresent(UnplateEnterDO::getCarType, reqVO.getCarType())
                .eqIfPresent(UnplateEnterDO::getCarColor, reqVO.getCarColor())
                .eqIfPresent(UnplateEnterDO::getPhone, reqVO.getPhone())
                .betweenIfPresent(UnplateEnterDO::getRegisterTime, reqVO.getRegisterTime())
                .eqIfPresent(UnplateEnterDO::getStatus, reqVO.getStatus())
                .eqIfPresent(UnplateEnterDO::getStationId, reqVO.getStationId())
                .eqIfPresent(UnplateEnterDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(UnplateEnterDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(UnplateEnterDO::getAuditComment, reqVO.getAuditComment())
                .eqIfPresent(UnplateEnterDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UnplateEnterDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(UnplateEnterDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(UnplateEnterDO::getCreator, reqVO.getCreator())
                .eqIfPresent(UnplateEnterDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(UnplateEnterDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(UnplateEnterDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(UnplateEnterDO::getId));
    }
    IPage<UnplateEnterRespVO> selectPageJoinStation(Page<?> page, @Param("reqVO") UnplateEnterPageReqVO reqVO);

    UnplateEnterRespVO selectByIdJoinStation(@Param("id") Long id);

    /**
     * 统计各场站无牌入场数量
     */
    List<Map<String, Object>> selectStationUnplateCount(@Param("reqVO") UnplateEnterChartReqVO reqVO);

    /**
     * 统计无牌入场总量和审核通过数
     */
    Map<String, Object> selectUnplateEnterStats(@Param("reqVO") UnplateEnterChartReqVO reqVO);

}