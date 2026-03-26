package cn.iocoder.yudao.module.facility.service.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover.ManholeCoverMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
}
