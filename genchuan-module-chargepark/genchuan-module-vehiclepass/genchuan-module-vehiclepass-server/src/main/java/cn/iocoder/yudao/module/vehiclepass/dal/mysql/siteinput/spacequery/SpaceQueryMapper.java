package cn.iocoder.yudao.module.vehiclepass.dal.mysql.siteinput.spacequery;

import java.util.*;
import java.util.List;
import java.util.Map;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryLocationRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.spacequery.SpaceQueryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * 泊位查询 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SpaceQueryMapper extends BaseMapperX<SpaceQueryDO> {

    default PageResult<SpaceQueryDO> selectPage(SpaceQueryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpaceQueryDO>()
                .eqIfPresent(SpaceQueryDO::getSpaceNo, reqVO.getSpaceNo())
                .betweenIfPresent(SpaceQueryDO::getQueryTime, reqVO.getQueryTime())
                .eqIfPresent(SpaceQueryDO::getQueryUserId, reqVO.getQueryUserId())
                .eqIfPresent(SpaceQueryDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(SpaceQueryDO::getSpaceStatus, reqVO.getSpaceStatus())
                .eqIfPresent(SpaceQueryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SpaceQueryDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(SpaceQueryDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(SpaceQueryDO::getCreator, reqVO.getCreator())
                .eqIfPresent(SpaceQueryDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(SpaceQueryDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(SpaceQueryDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(SpaceQueryDO::getId));
    }

    /**
     * 分页查询（使用JOIN查询关联表）
     */
    IPage<SpaceQueryRespVO> selectPageJoin(Page<?> page, @Param("reqVO") SpaceQueryPageReqVO reqVO);

    /**
     * 获取泊位查询定位信息
     */
    SpaceQueryLocationRespVO selectLocation(@Param("id") Long id);

    /**
     * 获取泊位位置分布（地图数据）
     */
    List<Map<String, Object>> selectSpaceLocationList(@Param("reqVO") SpaceQueryChartReqVO reqVO);

    /**
     * 获取泊位查询统计数据
     */
    Map<String, Object> selectChartData(@Param("reqVO") SpaceQueryChartReqVO reqVO);

}