package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.facdict;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.facdict.FacDictMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 市政设施专题-设施类型字典 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 FacDictService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class FacDictServiceImpl implements FacDictService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private FacDictMapper facDictMapper;

    /**
     * 查询市政设施专题-设施类型字典数据
     *
     * @param facDictQueryReqVO 查询条件 VO 对象
     * @return FacDictRespVO 查询结果 VO 对象
     */
    @Override
    public List<FacDictRespVO> getFacDict(FacDictQueryReqVO facDictQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return facDictMapper.getFacDict(facDictQueryReqVO);
    }
}
