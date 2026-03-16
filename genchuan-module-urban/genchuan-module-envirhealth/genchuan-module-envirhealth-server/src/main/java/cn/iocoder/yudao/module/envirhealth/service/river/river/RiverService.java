package cn.iocoder.yudao.module.envirhealth.service.river.river;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.detail.RiverDetailDO;
import jakarta.validation.Valid;

/**
 * 河道 Service 接口
 *
 * @author 芋道源码
 */
public interface RiverService {

    /**
     * 创建河道
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRiver(@Valid RiverSaveReqVO createReqVO);

    /**
     * 更新河道
     *
     * @param updateReqVO 更新信息
     */
    void updateRiver(@Valid RiverSaveReqVO updateReqVO);

    /**
     * 删除河道
     *
     * @param id 编号
     */
    void deleteRiver(Long id);

    /**
     * 获得河道
     *
     * @param id 编号
     * @return 河道
     */
    RiverDO getRiver(Long id);

    /**
     * 获得河道分页
     *
     * @param pageReqVO 分页查询
     * @return 河道分页
     */
    PageResult<RiverDO> getRiverPage(RiverPageReqVO pageReqVO);

    PageResult<RiverDetailDO> getRiverDetailPage(RiverPageReqVO pageReqVO);

}