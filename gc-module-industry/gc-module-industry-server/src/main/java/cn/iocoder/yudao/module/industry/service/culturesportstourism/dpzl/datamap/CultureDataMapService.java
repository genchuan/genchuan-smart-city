// DataMapServer.java
package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapRespVO;
import java.util.List;

public interface CultureDataMapService {
    List<CultureDataMapRespVO> getDataMapInfo(CultureDataMapQueryReqVO queryVO);
}