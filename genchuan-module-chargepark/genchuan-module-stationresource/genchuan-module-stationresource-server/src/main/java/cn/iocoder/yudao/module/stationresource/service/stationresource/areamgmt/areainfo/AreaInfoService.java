package cn.iocoder.yudao.module.stationresource.service.stationresource.areamgmt.areainfo;

import java.util.*;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AddReq;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AreaInfoUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaInfoChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 片区信息 Service 接口
 *
 * @author 亘川智城
 */
public interface AreaInfoService {

    /**
     * 创建片区信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAreaInfo(@Valid AreaInfoSaveReqVO createReqVO);

    /**
     * 更新片区信息
     *
     * @param updateReqVO 更新信息
     */
//    void updateAreaInfo(@Valid AreaInfoSaveReqVO updateReqVO);

    /**
     * 删除片区信息
     *
     * @param id 编号
     */
    void deleteAreaInfo(Long id);

    /**
    * 批量删除片区信息
    *
    * @param ids 编号
    */
    void deleteAreaInfoListByIds(List<Long> ids);

    /**
     * 获得片区信息
     *
     * @param id 编号
     * @return 片区信息
     */
    AreaInfoRespVO getAreaInfo(Long id);

    /**
     * 获得片区信息分页
     *
     * @param pageReqVO 分页查询
     * @return 片区信息分页
     */
    PageResult<AreaInfoRespVO> getAreaInfoPage(AreaInfoPageReqVO pageReqVO);

    Long addAreaInfo(AddReq createReqVO);

    ImportRespVO importAreaInfo(MultipartFile file,boolean updateSupport);

    void updateAreaInfoStatus(List<Long> ids, boolean b);

    void updateArea(AreaInfoUpdateReqVO reqVO);

    AreaInfoChartRespVO getAreaInfoChart();
}
