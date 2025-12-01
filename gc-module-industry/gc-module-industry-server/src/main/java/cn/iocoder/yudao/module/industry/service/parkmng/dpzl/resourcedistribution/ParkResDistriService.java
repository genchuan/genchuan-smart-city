// ParkResDistriService.java
package cn.iocoder.yudao.module.industry.service.parkmng.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriRespVO;

import java.util.List;

public interface ParkResDistriService {

    /**
     * 获取停车资源分布列表
     */
    List<ParkResDistriRespVO> getParkResDistriList(ParkResDistriQueryReqVO queryReqVO);


}