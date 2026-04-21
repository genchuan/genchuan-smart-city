package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import jakarta.validation.Valid;

import java.util.List;

public interface PointActivityService {

    PageResult<PointActivityDO> getPage(PointActivityPageReqVO reqVO);

    PointActivityDO get(Long id);

    Long create(@Valid PointActivityCreateReqVO reqVO);

    void update(@Valid PointActivityUpdateReqVO reqVO);

    void enable(Long id);

    void pause(Long id);

    PointActivityChartRespVO getChart(String timeRange);

    /**
     * 导入积分活动列表
     */
    void importPointActivityList(List<PointActivityImportExcelVO> list);

}
