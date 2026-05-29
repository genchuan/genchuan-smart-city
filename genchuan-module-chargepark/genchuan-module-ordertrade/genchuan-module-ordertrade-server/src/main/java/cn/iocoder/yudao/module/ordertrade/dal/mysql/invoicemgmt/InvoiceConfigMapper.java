package cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.InvoiceConfigPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface InvoiceConfigMapper extends BaseMapperX<InvoiceConfigDO> {

    default PageResult<InvoiceConfigDO> selectPage(InvoiceConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InvoiceConfigDO>()
                .likeIfPresent(InvoiceConfigDO::getCategory, reqVO.getCategory())
                .likeIfPresent(InvoiceConfigDO::getTaxBody, reqVO.getTaxBody())
                .eqIfPresent(InvoiceConfigDO::getStatus, reqVO.getStatus())
                .likeIfPresent(InvoiceConfigDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(InvoiceConfigDO::getCreateTime, reqVO.getCreateTimeStart(), reqVO.getCreateTimeEnd())
                .orderByDesc(InvoiceConfigDO::getId));
    }

    @Select("SELECT category, COUNT(*) AS count FROM invoice_config WHERE deleted = 0 GROUP BY category")
    List<Map<String, Object>> selectGroupByCategory();

    @Select("SELECT COUNT(*) FROM invoice_config WHERE deleted = 0 AND status = 'enabled'")
    Long selectEnabledCount();
}
