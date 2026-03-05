package cn.iocoder.yudao.module.industry.service.park.vas.parkeinvoice;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoicePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoiceSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkeinvoice.ParkEInvoiceDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 电子发票 Service 接口
 *
 * @author lxs
 */
public interface ParkEInvoiceService {

    /**
     * 创建电子发票
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkEInvoice(@Valid ParkEInvoiceSaveReqVO createReqVO);

    /**
     * 更新电子发票
     *
     * @param updateReqVO 更新信息
     */
    void updateParkEInvoice(@Valid ParkEInvoiceSaveReqVO updateReqVO);

    /**
     * 删除电子发票
     *
     * @param id 编号
     */
    void deleteParkEInvoice(Long id);

    /**
     * 获得电子发票
     *
     * @param id 编号
     * @return 电子发票
     */
    ParkEInvoiceDO getParkEInvoice(Long id);

    /**
     * 获得电子发票分页
     *
     * @param pageReqVO 分页查询
     * @return 电子发票分页
     */
    PageResult<ParkEInvoiceDO> getParkEInvoicePage(ParkEInvoicePageReqVO pageReqVO);

}
