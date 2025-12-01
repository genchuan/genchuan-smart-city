package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.forceview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.LawForceViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo.LawForceViewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.forceview.LawForceViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 执法力量分布视图 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class LawForceViewServiceImpl implements LawForceViewService {

    @Resource
    private LawForceViewMapper lawForceViewMapper;

    @Override
    public LawForceViewRespVO getLawForceView(LawForceViewQueryReqVO lawForceViewQueryReqVO) {
        LawForceViewRespVO respVO = new LawForceViewRespVO();

        respVO.setStaffLocList(lawForceViewMapper.selectStaffLoc(lawForceViewQueryReqVO.getRegionShortCode()));
        respVO.setStaffLocListCount(respVO.getStaffLocList().size());

        respVO.setVehicleLocList(lawForceViewMapper.selectVehicleLoc(lawForceViewQueryReqVO.getRegionShortCode()));
        respVO.setVehicleLocListCount(respVO.getVehicleLocList().size());

        respVO.setStationLocList(lawForceViewMapper.selectStationLoc(lawForceViewQueryReqVO.getRegionShortCode()));
        respVO.setStationLocListCount(respVO.getStaffLocList().size());
        return respVO;
    }
}
