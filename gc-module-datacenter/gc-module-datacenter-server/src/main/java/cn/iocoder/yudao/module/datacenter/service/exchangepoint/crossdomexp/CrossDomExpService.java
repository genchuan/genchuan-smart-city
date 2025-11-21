package cn.iocoder.yudao.module.datacenter.service.exchangepoint.crossdomexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.crossdomexp.CrossDomExpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 跨域经验交流 Service 接口
 *
 * @author 亘川智城
 */
public interface CrossDomExpService {

    /**
     * 创建跨域经验交流
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCrossDomExp(@Valid CrossDomExpSaveReqVO createReqVO);

    /**
     * 更新跨域经验交流
     *
     * @param updateReqVO 更新信息
     */
    void updateCrossDomExp(@Valid CrossDomExpSaveReqVO updateReqVO);

    /**
     * 删除跨域经验交流
     *
     * @param id 编号
     */
    void deleteCrossDomExp(Long id);

    /**
     * 获得跨域经验交流
     *
     * @param id 编号
     * @return 跨域经验交流
     */
    CrossDomExpDO getCrossDomExp(Long id);

    /**
     * 获得跨域经验交流分页
     *
     * @param pageReqVO 分页查询
     * @return 跨域经验交流分页
     */
    PageResult<CrossDomExpDO> getCrossDomExpPage(CrossDomExpPageReqVO pageReqVO);

}