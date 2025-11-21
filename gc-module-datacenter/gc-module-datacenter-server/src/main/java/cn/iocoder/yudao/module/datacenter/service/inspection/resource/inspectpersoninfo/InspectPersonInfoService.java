package cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectpersoninfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectpersoninfo.InspectPersonInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检人员信息 Service 接口
 *
 * @author zcq
 */
public interface InspectPersonInfoService {

    /**
     * 创建巡查巡检人员信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectPersonInfo(@Valid InspectPersonInfoSaveReqVO createReqVO);

    /**
     * 更新巡查巡检人员信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectPersonInfo(@Valid InspectPersonInfoSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检人员信息
     *
     * @param id 编号
     */
    void deleteInspectPersonInfo(Long id);

    /**
     * 获得巡查巡检人员信息
     *
     * @param id 编号
     * @return 巡查巡检人员信息
     */
    InspectPersonInfoDO getInspectPersonInfo(Long id);

    /**
     * 获得巡查巡检人员信息分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检人员信息分页
     */
    PageResult<InspectPersonInfoDO> getInspectPersonInfoPage(InspectPersonInfoPageReqVO pageReqVO);

}