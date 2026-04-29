package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import jakarta.validation.Valid;

import java.util.List;

public interface ExchangeCategoryService {

    PageResult<ExchangeCategoryDO> getPage(ExchangeCategoryPageReqVO reqVO);

    ExchangeCategoryDO get(Long id);

    Long create(@Valid ExchangeCategoryCreateReqVO reqVO);

    void update(@Valid ExchangeCategoryUpdateReqVO reqVO);

    void enable(Long id);

    void disable(Long id);

    ExchangeCategoryChartRespVO getChart();

    void importData(List<ExchangeCategoryImportExcelVO> list);

}
