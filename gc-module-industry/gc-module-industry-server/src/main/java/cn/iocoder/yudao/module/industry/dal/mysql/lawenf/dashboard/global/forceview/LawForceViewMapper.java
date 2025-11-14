package cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.forceview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 执法力量分布视图 Mapper
 *
 * @author lxs
 */
@Mapper
public interface LawForceViewMapper {

        List<LawForceStaffLocVO> selectStaffLoc(@Param("regionShortCode") String regionShortCode);

        List<LawForceVehicleLocVO> selectVehicleLoc(@Param("regionShortCode") String regionShortCode);

        List<LawForceStationLocVO> selectStationLoc(@Param("regionShortCode") String regionShortCode);

        LawForceStaffCountVO selectRegionStaffCount(@Param("regionShortCode") String regionShortCode);

}
