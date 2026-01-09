package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coremon;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.coremon.UniversalCoreMonMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 通用大屏核心要素运行监测 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 UniversalCoreMonService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class UniversalCoreMonServiceImpl implements UniversalCoreMonService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private UniversalCoreMonMapper universalCoreMonMapper;

    /**
     * 查询通用大屏核心要素运行监测数据
     *
     * @param universalCoreMonQueryReqVO 查询条件 VO 对象
     * @return UniversalCoreMonRespVO 查询结果 VO 对象
     */
    @Override
    public List<UniversalCoreMonRespVO> listUniversalCoreMon(UniversalCoreMonQueryReqVO universalCoreMonQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return universalCoreMonMapper.listUniversalCoreMon(universalCoreMonQueryReqVO);
    }
}
