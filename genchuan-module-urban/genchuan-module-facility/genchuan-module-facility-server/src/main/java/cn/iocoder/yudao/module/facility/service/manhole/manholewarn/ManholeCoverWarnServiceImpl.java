package cn.iocoder.yudao.module.facility.service.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnDetailReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnDetailRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageRespVO;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManholeCoverWarnServiceImpl implements ManholeCoverWarnService {

    @Resource
    private SysWarnMapper sysWarnMapper;


    @Override
    public PageResult<ManholeCoverWarnPageRespVO> getWarnPage(ManholeCoverWarnPageReqVO reqVO) {

        // 分页
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(reqVO.getPageNo());
        pageParam.setPageSize(reqVO.getPageSize());

        // 查询
        List<ManholeCoverWarnPageRespVO> list = sysWarnMapper.selectManholeCoverWarnPage(reqVO, pageParam);
        Long total = sysWarnMapper.selectManholeCoverWarnCount(reqVO);

        return new PageResult<>(list, total);
    }

    @Override
    public ManholeCoverWarnDetailRespVO getWarnDetail(ManholeCoverWarnDetailReqVO reqVO) {
        return sysWarnMapper.selectManholeCoverWarnDetail(reqVO.getWarnId());
    }
}
