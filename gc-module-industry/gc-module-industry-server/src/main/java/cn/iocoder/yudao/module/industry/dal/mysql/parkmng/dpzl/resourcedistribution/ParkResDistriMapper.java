// ParkResDistriMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.parkmng.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkResDistriMapper {

    /**
     * 查询停车资源分布列表
     */
    List<ParkResDistriRespVO> selectParkResDistriList(ParkResDistriQueryReqVO queryReqVO);


}