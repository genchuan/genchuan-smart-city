package cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.stationinfo;

import java.util.*;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StationInfoCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StationInfoUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.StationInfoChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 场站信息 Service 接口
 *
 * @author 亘川智城
 */
public interface StationInfoService {

    /**
     * 创建场站信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStationInfo(@Valid StationInfoSaveReqVO createReqVO);

    /**
     * 更新场站信息
     *
     * @param updateReqVO 更新信息
     */
    void updateStationInfo(@Valid StationInfoSaveReqVO updateReqVO);

    /**
     * 删除场站信息
     *
     * @param id 编号
     */
    void deleteStationInfo(Long id);

    /**
    * 批量删除场站信息
    *
    * @param ids 编号
    */
    void deleteStationInfoListByIds(List<Long> ids);

    /**
     * 获得场站信息
     *
     * @param id 编号
     * @return 场站信息
     */
    StationInfoDO getStationInfo(Long id);

    /**
     * 获得场站信息分页
     *
     * @param pageReqVO 分页查询
     * @return 场站信息分页
     */
    PageResult<StationInfoDO> getStationInfoPage(StationInfoPageReqVO pageReqVO);

    Long addStationInfo(StationInfoCreateReqVO reqVO);

    ImportRespVO importStationInfo(MultipartFile file, boolean updateSupport);

    void updateStationStatus(List<Long> ids, String status);

    void updateStation(StationInfoUpdateReqVO reqVO);

    StationInfoChartRespVO getStationInfoChart();
}
