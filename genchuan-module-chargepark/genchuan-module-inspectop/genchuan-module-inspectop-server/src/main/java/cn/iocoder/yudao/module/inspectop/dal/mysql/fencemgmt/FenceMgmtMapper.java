package cn.iocoder.yudao.module.inspectop.dal.mysql.fencemgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt.FenceMgmtDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 电子围栏 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface FenceMgmtMapper extends BaseMapperX<FenceMgmtDO> {

    default PageResult<FenceMgmtDO> selectPage(FenceMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FenceMgmtDO>()
                .likeIfPresent(FenceMgmtDO::getName, reqVO.getName())
                .eqIfPresent(FenceMgmtDO::getArea, reqVO.getArea())
                .eqIfPresent(FenceMgmtDO::getUserId, reqVO.getUserId())
                .eqIfPresent(FenceMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FenceMgmtDO::getAlarmCount, reqVO.getAlarmCount())
                .eqIfPresent(FenceMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(FenceMgmtDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(FenceMgmtDO::getCreator, reqVO.getCreator())
                .eqIfPresent(FenceMgmtDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(FenceMgmtDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(FenceMgmtDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(FenceMgmtDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 inspect_user 表查询巡检人员姓名
     *
     * @param page  MyBatis-Plus 分页参数
     * @param reqVO 查询条件
     * @return 包含巡检人员姓名的分页结果
     */
    Page<FenceMgmtRespVO> selectPageWithJoin(
            @Param("page") com.baomidou.mybatisplus.extension.plugins.pagination.Page<FenceMgmtRespVO> page,
            @Param("reqVO") FenceMgmtPageReqVO reqVO);

    // 地图数据查询DTO
    @Data
    class MapDataDTO {
        private Long id;
        private String name;
        private String area;
        private String status;
    }

    // 卡片数据查询DTO
    @Data
    class CardDataDTO {
        private Integer fenceCount;
        private Integer alarmCount;
    }

    /**
     * 查询地图数据
     */
    List<MapDataDTO> selectFenceDataForMap();

    /**
     * 查询卡片统计数据
     */
    CardDataDTO selectChartCardData();

}