package cn.iocoder.yudao.module.stationresource.service.stationresource.parkingspace.parkingspaceinfo;

import java.util.*;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.AddParkingSpaceInfoReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.BindParkingSpaceReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.ImportResultVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.statistics.ParkingSpaceChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 车位信息 Service 接口
 *
 * @author 亘川智城
 */
public interface ParkingSpaceInfoService {

    /**
     * 创建车位信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
//    Long createParkingSpaceInfo(@Valid ParkingSpaceInfoSaveReqVO createReqVO);

    /**
     * 更新车位信息
     *
     * @param updateReqVO 更新信息
     */
    void updateParkingSpaceInfo(@Valid ParkingSpaceInfoSaveReqVO updateReqVO);

    /**
     * 删除车位信息
     *
     * @param id 编号
     */
    void deleteParkingSpaceInfo(Long id);

    /**
    * 批量删除车位信息
    *
    * @param ids 编号
    */
    void deleteParkingSpaceInfoListByIds(List<Long> ids);

    /**
     * 获得车位信息
     *
     * @param id 编号
     * @return 车位信息
     */
    ParkingSpaceInfoRespVO getParkingSpaceInfo(Long id);

    /**
     * 获得车位信息分页
     *
     * @param pageReqVO 分页查询
     * @return 车位信息分页
     */
    PageResult<ParkingSpaceInfoRespVO> getParkingSpaceInfoPage(ParkingSpaceInfoPageReqVO pageReqVO);

    Long addParkingSpaceInfo(AddParkingSpaceInfoReqVO createReqVO);

    ImportResultVO importParkingSpaceInfo(MultipartFile file, boolean updateSupport);

    void bindParkingSpace(BindParkingSpaceReqVO reqVO);

    ParkingSpaceChartRespVO getParkingSpaceChart();
}
