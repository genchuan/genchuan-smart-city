package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.datamap.CultureDataMapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CultureDataMapServiceImpl implements CultureDataMapService {

    private final CultureDataMapMapper cultureDataMapMapper;

    @Override
    public List<CultureDataMapRespVO> getDataMapInfo(CultureDataMapQueryReqVO queryVO) {
        return cultureDataMapMapper.queryDataMap(queryVO);
    }
}