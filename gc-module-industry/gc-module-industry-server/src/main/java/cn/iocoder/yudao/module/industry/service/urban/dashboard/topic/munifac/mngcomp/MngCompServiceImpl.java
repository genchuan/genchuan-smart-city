package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.mngcomp;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.mngcomp.MngCompMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 市政设施专题-管理部件 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 MngCompService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class MngCompServiceImpl implements MngCompService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private MngCompMapper mngCompMapper;

    /**
     * 查询市政设施专题-管理部件数据
     *
     * @param mngCompQueryReqVO 查询条件 VO 对象
     * @return MngCompRespVO 查询结果 VO 对象
     */
    @Override
    public MngCompRespVO getMngComp(MngCompQueryReqVO mngCompQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return mngCompMapper.getMngComp(mngCompQueryReqVO);
    }
}
