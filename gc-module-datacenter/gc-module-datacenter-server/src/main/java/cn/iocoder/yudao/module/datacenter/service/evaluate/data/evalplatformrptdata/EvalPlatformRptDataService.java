package cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalplatformrptdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalplatformrptdata.EvalPlatformRptDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 平台上报数据 Service 接口
 *
 * @author zcq
 */
public interface EvalPlatformRptDataService {

    /**
     * 创建平台上报数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalPlatformRptData(@Valid EvalPlatformRptDataSaveReqVO createReqVO);

    /**
     * 更新平台上报数据
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalPlatformRptData(@Valid EvalPlatformRptDataSaveReqVO updateReqVO);

    /**
     * 删除平台上报数据
     *
     * @param id 编号
     */
    void deleteEvalPlatformRptData(Long id);

    /**
     * 获得平台上报数据
     *
     * @param id 编号
     * @return 平台上报数据
     */
    EvalPlatformRptDataDO getEvalPlatformRptData(Long id);

    /**
     * 获得平台上报数据分页
     *
     * @param pageReqVO 分页查询
     * @return 平台上报数据分页
     */
    PageResult<EvalPlatformRptDataDO> getEvalPlatformRptDataPage(EvalPlatformRptDataPageReqVO pageReqVO);

}