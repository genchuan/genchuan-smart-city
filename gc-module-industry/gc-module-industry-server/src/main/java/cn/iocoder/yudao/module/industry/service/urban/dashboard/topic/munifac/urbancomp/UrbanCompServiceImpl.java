package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.urbancomp;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.urbancomp.UrbanCompMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 市政设施专题-市政设施管理部件 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 UrbanCompService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class UrbanCompServiceImpl implements UrbanCompService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private UrbanCompMapper urbanCompMapper;

    /**
     * 查询市政设施专题-市政设施管理部件数据
     *
     * @param urbanCompQueryReqVO 查询条件 VO 对象
     * @return UrbanCompRespVO 查询结果 VO 对象
     */
    @Override
    public UrbanCompRespVO getUrbanComp(UrbanCompQueryReqVO urbanCompQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return urbanCompMapper.getUrbanComp(urbanCompQueryReqVO);
    }
}
