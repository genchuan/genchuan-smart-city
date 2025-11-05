package cn.iocoder.yudao.module.datacenter.service.emerplan.emerplanlib;


import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplanlib.EmerPlanLibDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 预案库 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerPlanLibService {

    /**
     * 创建预案库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerPlanLib(@Valid EmerPlanLibSaveReqVO createReqVO);

    /**
     * 更新预案库
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerPlanLib(@Valid EmerPlanLibSaveReqVO updateReqVO);

    /**
     * 删除预案库
     *
     * @param id 编号
     */
    void deleteEmerPlanLib(Long id);

    /**
     * 获得预案库
     *
     * @param id 编号
     * @return 预案库
     */
    EmerPlanLibDO getEmerPlanLib(Long id);

    /**
     * 获得预案库分页
     *
     * @param pageReqVO 分页查询
     * @return 预案库分页
     */
    PageResult<EmerPlanLibDO> getEmerPlanLibPage(EmerPlanLibPageReqVO pageReqVO);

}