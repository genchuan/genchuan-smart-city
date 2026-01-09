package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coreobj;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.coreobj.UniversalCoreObjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 通用大屏核心对象分布视图 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 UniversalCoreObjService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class UniversalCoreObjServiceImpl implements UniversalCoreObjService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private UniversalCoreObjMapper universalCoreObjMapper;

    /**
     * 查询通用大屏核心对象分布视图数据
     *
     * @param universalCoreObjQueryReqVO 查询条件 VO 对象
     * @return UniversalCoreObjRespVO 查询结果 VO 对象
     */
    @Override
    public List<UniversalCoreObjRespVO> listUniversalCoreObj(UniversalCoreObjQueryReqVO universalCoreObjQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return universalCoreObjMapper.listUniversalCoreObj(universalCoreObjQueryReqVO);
    }
}
