package cn.iocoder.yudao.module.industry.service.parkmng.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.parkmng.dpzl.datamap.ParkDataMapMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

@Service
@Validated
public class ParkDataServiceImpl implements ParkDataMapService {

    @Resource
    private ParkDataMapMapper parkDataMapMapper;

    @Override
    public List<ParkDataMapRespVO> getDataMapList(ParkDataMapQueryReqVO queryReqVO) {
        return parkDataMapMapper.selectDataMapList(queryReqVO);
    }

    @Override
    public ParkDataMapRespVO getDataMapById(Long id) {
        return parkDataMapMapper.selectDataMapById(id);
    }

}