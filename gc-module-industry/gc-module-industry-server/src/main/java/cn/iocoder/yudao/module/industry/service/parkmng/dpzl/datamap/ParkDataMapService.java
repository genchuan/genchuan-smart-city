package cn.iocoder.yudao.module.industry.service.parkmng.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapRespVO;
import java.util.List;

public interface ParkDataMapService {

    /**
     * 查询停车数据地图列表
     */
    List<ParkDataMapRespVO> getDataMapList(ParkDataMapQueryReqVO queryReqVO);

    /**
     * 根据ID查询停车数据地图详情
     */
    ParkDataMapRespVO getDataMapById(Long id);

}