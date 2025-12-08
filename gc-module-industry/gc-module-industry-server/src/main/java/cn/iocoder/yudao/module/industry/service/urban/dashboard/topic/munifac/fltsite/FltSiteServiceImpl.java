package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.fltsite;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.fltsite.FltSiteMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 市政设施专题-故障设施空间数据 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 FltSiteService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class FltSiteServiceImpl implements FltSiteService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private FltSiteMapper fltSiteMapper;

    /**
     * 查询市政设施专题-故障设施空间数据数据
     *
     * @param fltSiteQueryReqVO 查询条件 VO 对象
     * @return FltSiteRespVO 查询结果 VO 对象
     */
    @Override
    public List<FltSiteRespVO> getFltSite(FltSiteQueryReqVO fltSiteQueryReqVO) {
        return fltSiteMapper.getFltSite(fltSiteQueryReqVO);
    }
}
