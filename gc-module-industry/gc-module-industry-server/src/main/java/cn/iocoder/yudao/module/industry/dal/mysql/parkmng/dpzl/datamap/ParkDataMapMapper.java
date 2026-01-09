package cn.iocoder.yudao.module.industry.dal.mysql.parkmng.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkDataMapMapper {

    /**
     * 查询停车数据地图列表
     */
    List<ParkDataMapRespVO> selectDataMapList(ParkDataMapQueryReqVO queryReqVO);

    /**
     * 查询停车数据地图详情
     */
    ParkDataMapRespVO selectDataMapById(Long id);

}