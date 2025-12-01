// ParkResDistriServiceImpl.java
package cn.iocoder.yudao.module.industry.service.parkmng.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.parkmng.dpzl.resourcedistribution.ParkResDistriMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.List;

@Service
@Validated
@Slf4j
public class ParkResDistriServiceImpl implements ParkResDistriService {

    @Resource
    private ParkResDistriMapper parkResDistriMapper;

    @Override
    public List<ParkResDistriRespVO> getParkResDistriList(ParkResDistriQueryReqVO queryReqVO) {
        return parkResDistriMapper.selectParkResDistriList(queryReqVO);
    }


}