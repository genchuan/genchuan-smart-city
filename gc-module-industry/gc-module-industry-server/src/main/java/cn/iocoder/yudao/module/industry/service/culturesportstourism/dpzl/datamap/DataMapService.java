// DataMapServer.java
package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapRespVO;
import java.util.List;

public interface DataMapService {
    List<DataMapRespVO> getDataMapInfo(DataMapQueryReqVO queryVO);
}