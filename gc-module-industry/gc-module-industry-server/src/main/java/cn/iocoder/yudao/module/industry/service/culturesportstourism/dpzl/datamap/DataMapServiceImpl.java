package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.datamap.DataMapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataMapServiceImpl implements DataMapService {

    private final DataMapMapper dataMapMapper;

    @Override
    public List<DataMapRespVO> getDataMapInfo(DataMapQueryReqVO queryVO) {
        return dataMapMapper.queryDataMap(queryVO);
    }
}