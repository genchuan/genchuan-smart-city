package cn.iocoder.yudao.module.smartcity.service.reportofillegalconstructions;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.ReportOfIllegalConstructionsPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.ReportOfIllegalConstructionsSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.reportofillegalconstructions.ReportOfIllegalConstructionsDO;
import jakarta.validation.Valid;

/**
 * 违建上报 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ReportOfIllegalConstructionsService {

    /**
     * 创建违建上报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReportOfIllegalConstructions(@Valid ReportOfIllegalConstructionsSaveReqVO createReqVO);

    /**
     * 更新违建上报
     *
     * @param updateReqVO 更新信息
     */
    void updateReportOfIllegalConstructions(@Valid ReportOfIllegalConstructionsSaveReqVO updateReqVO);

    /**
     * 删除违建上报
     *
     * @param id 编号
     */
    void deleteReportOfIllegalConstructions(Long id);

    /**
     * 获得违建上报
     *
     * @param id 编号
     * @return 违建上报
     */
    ReportOfIllegalConstructionsDO getReportOfIllegalConstructions(Long id);

    /**
     * 获得违建上报分页
     *
     * @param pageReqVO 分页查询
     * @return 违建上报分页
     */
    PageResult<ReportOfIllegalConstructionsDO> getReportOfIllegalConstructionsPage(ReportOfIllegalConstructionsPageReqVO pageReqVO);

}