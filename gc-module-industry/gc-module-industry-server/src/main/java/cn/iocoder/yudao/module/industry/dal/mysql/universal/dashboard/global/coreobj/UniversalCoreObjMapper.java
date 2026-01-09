package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.coreobj;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo.UniversalCoreObjRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通用大屏核心对象分布视图 Mapper
 *
 */
@Mapper
public interface UniversalCoreObjMapper {

    /**
     * 查询通用大屏核心对象分布视图
     *
     * @param universalCoreObjQueryReqVO 查询参数
     * @return UniversalCoreObjRespVO 结果
     */
    List<UniversalCoreObjRespVO> listUniversalCoreObj(UniversalCoreObjQueryReqVO universalCoreObjQueryReqVO);

}
