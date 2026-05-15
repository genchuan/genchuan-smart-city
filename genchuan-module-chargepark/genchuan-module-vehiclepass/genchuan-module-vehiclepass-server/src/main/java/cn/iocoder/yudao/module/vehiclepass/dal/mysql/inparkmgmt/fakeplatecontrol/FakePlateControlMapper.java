package cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.fakeplatecontrol;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.MyFakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.fakeplatecontrol.FakePlateControlDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 套牌管控 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FakePlateControlMapper extends BaseMapperX<FakePlateControlDO> {

    default PageResult<FakePlateControlDO> selectPage(FakePlateControlPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FakePlateControlDO>()
                .eqIfPresent(FakePlateControlDO::getPlateNo, reqVO.getPlateNo())
                .betweenIfPresent(FakePlateControlDO::getIdentifyTime, reqVO.getIdentifyTime())
                .eqIfPresent(FakePlateControlDO::getMatchScene, reqVO.getMatchScene())
                .eqIfPresent(FakePlateControlDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FakePlateControlDO::getStationId, reqVO.getStationId())
                .eqIfPresent(FakePlateControlDO::getHandleUserId, reqVO.getHandleUserId())
                .betweenIfPresent(FakePlateControlDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(FakePlateControlDO::getHandleProgress, reqVO.getHandleProgress())
                .eqIfPresent(FakePlateControlDO::getIgnoreReason, reqVO.getIgnoreReason())
                .eqIfPresent(FakePlateControlDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FakePlateControlDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(FakePlateControlDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(FakePlateControlDO::getCreator, reqVO.getCreator())
                .eqIfPresent(FakePlateControlDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(FakePlateControlDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(FakePlateControlDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(FakePlateControlDO::getId));
    }

    FakePlateControlRespVO selectByIdJoinStation(@Param("id") Long id);

    IPage<MyFakePlateControlRespVO> selectPageJoinStationUser(Page<?> page, @Param("reqVO") FakePlateControlPageReqVO reqVO);

    /**
     * 查询套牌识别趋势
     */
    List<Map<String, Object>> selectIdentifyTrend(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

    /**
     * 查询各场站套牌数
     */
    List<Map<String, Object>> selectStationFakeCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

    /**
     * 查询待处置数和处置完成率
     */
    Map<String, Object> selectHandleStats(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);
}