package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coreobj;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjRespVO;

import java.util.List;

/**
 * 通用大屏核心对象分布视图 Service 接口
 *
 */
public interface UniversalCoreObjService {

    /**
     * 查询通用大屏核心对象分布视图
     *
     * @param universalCoreObjQueryReqVO 查询条件
     * @return 查询结果
     */
    List<UniversalCoreObjRespVO> listUniversalCoreObj(UniversalCoreObjQueryReqVO universalCoreObjQueryReqVO);
}
