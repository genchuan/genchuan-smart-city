package cn.iocoder.yudao.module.datacenter.service.businessguidance.localregulation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.localregulation.LocalRegulationInfoDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 地方法规信息 Service 接口
 *
 * @author Gyh
 */
public interface LocalRegulationInfoService {

    /**
     * 创建地方法规信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLocalRegulationInfo(@Valid LocalRegulationInfoCreateReqVO createReqVO);

    /**
     * 更新地方法规信息
     *
     * @param updateReqVO 更新信息
     */
    void updateLocalRegulationInfo(@Valid LocalRegulationInfoUpdateReqVO updateReqVO);

    /**
     * 删除地方法规信息
     *
     * @param id 编号
     */
    void deleteLocalRegulationInfo(Long id);

    /**
     * 获得地方法规信息
     *
     * @param id 编号
     * @return 地方法规信息
     */
    LocalRegulationInfoDO getLocalRegulationInfo(Long id);

    /**
     * 获得地方法规信息分页
     *
     * @param pageReqVO 分页查询
     * @return 地方法规信息分页
     */
    PageResult<LocalRegulationInfoDO> getLocalRegulationInfoPage(@Valid LocalRegulationInfoPageReqVO pageReqVO);

    /**
     * 获得所有地方法规信息列表
     *
     * @return 地方法规信息列表
     */
    List<LocalRegulationInfoDO> getLocalRegulationInfoList();

    /**
     * 获得所有有效的地方法规列表
     *
     * @return 地方法规信息列表
     */
    List<LocalRegulationInfoDO> getValidLocalRegulationInfoList();

    /**
     * 获得即将失效的地方法规列表（距失效时间≤30天）
     *
     * @return 地方法规信息列表
     */
    List<LocalRegulationInfoDO> getExpiringLocalRegulationInfoList();

    /**
     * 检查法规编号是否唯一
     *
     * @param regulationNo 法规编号
     * @param id 编号（排除自身）
     * @return 是否唯一
     */
    boolean checkRegulationNoUnique(String regulationNo, Long id);

    /**
     * 批量更新法规状态
     *
     * @param ids ID列表
     * @param regulationStatus 法规状态
     */
    void updateRegulationStatusBatch(List<Long> ids, String regulationStatus);

    /**
     * 自动更新法规状态（定时任务调用）
     */
    void autoUpdateRegulationStatus();

    /**
     * 创建法规修订版本
     *
     * @param createRevisionReqVO 修订版本创建信息
     * @return 新版本编号
     */
    Long createRegulationRevision(@Valid LocalRegulationInfoCreateRevisionReqVO createRevisionReqVO);

    /**
     * 根据行政区划代码查询地方法规列表
     *
     * @param adminCode 行政区划代码
     * @return 地方法规信息列表
     */
    List<LocalRegulationInfoDO> getLocalRegulationInfoListByAdminCode(String adminCode);

    /**
     * 根据国家政策ID查询关联的地方法规列表
     *
     * @param nationalPolicyId 国家政策ID
     * @return 地方法规信息列表
     */
    List<LocalRegulationInfoDO> getLocalRegulationInfoListByNationalPolicyId(String nationalPolicyId);

    /**
     * 查询法规的所有修订版本
     *
     * @param regulationNo 法规编号
     * @return 地方法规信息列表
     */
    List<LocalRegulationInfoDO> getRegulationRevisionVersions(String regulationNo);
}