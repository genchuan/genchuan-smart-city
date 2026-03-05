package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkeinvoice;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoicePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkeinvoice.ParkEInvoiceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 电子发票 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkEInvoiceMapper extends BaseMapperX<ParkEInvoiceDO> {

    default PageResult<ParkEInvoiceDO> selectPage(ParkEInvoicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkEInvoiceDO>()
                .eqIfPresent(ParkEInvoiceDO::getInvoiceNo, reqVO.getInvoiceNo())
                .eqIfPresent(ParkEInvoiceDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ParkEInvoiceDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(ParkEInvoiceDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkEInvoiceDO::getInvoiceType, reqVO.getInvoiceType())
                .eqIfPresent(ParkEInvoiceDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ParkEInvoiceDO::getTaxpayerId, reqVO.getTaxpayerId())
                .eqIfPresent(ParkEInvoiceDO::getAmount, reqVO.getAmount())
                .eqIfPresent(ParkEInvoiceDO::getInvoiceContent, reqVO.getInvoiceContent())
                .eqIfPresent(ParkEInvoiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkEInvoiceDO::getIssueTime, reqVO.getIssueTime())
                .eqIfPresent(ParkEInvoiceDO::getPdfUrl, reqVO.getPdfUrl())
                .betweenIfPresent(ParkEInvoiceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkEInvoiceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkEInvoiceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkEInvoiceDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkEInvoiceDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkEInvoiceDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkEInvoiceDO::getId));
    }

}
