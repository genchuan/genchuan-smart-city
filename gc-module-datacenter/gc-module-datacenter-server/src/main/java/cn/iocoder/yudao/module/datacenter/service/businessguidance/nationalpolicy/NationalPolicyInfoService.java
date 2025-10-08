package cn.iocoder.yudao.module.datacenter.service.businessguidance.nationalpolicy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.nationalpolicy.NationalPolicyInfoDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 国家政策信息 Service 接口
 *
 * @author Gyh
 */
public interface NationalPolicyInfoService {

    /**
     * 创建国家政策信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNationalPolicyInfo(@Valid NationalPolicyInfoCreateReqVO createReqVO);

    /**
     * 更新国家政策信息
     *
     * @param updateReqVO 更新信息
     */
    void updateNationalPolicyInfo(@Valid NationalPolicyInfoUpdateReqVO updateReqVO);

    /**
     * 删除国家政策信息
     *
     * @param id 编号
     */
    void deleteNationalPolicyInfo(Long id);

    /**
     * 获得国家政策信息
     *
     * @param id 编号
     * @return 国家政策信息
     */
    NationalPolicyInfoDO getNationalPolicyInfo(Long id);

    /**
     * 获得国家政策信息分页
     *
     * @param pageReqVO 分页查询
     * @return 国家政策信息分页
     */
    PageResult<NationalPolicyInfoDO> getNationalPolicyInfoPage(@Valid NationalPolicyInfoPageReqVO pageReqVO);

    /**
     * 获得所有事件类型列表
     *
     * @return 国家政策信息列表
     */
    List<NationalPolicyInfoDO> getNationalPolicyInfoList();

    /**
     * 获得所有启用的事件大类列表
     *
     * @return 国家政策信息列表
     */
    List<NationalPolicyInfoDO> getValidNationalPolicyInfoList();

    /**
     * 根据大类代码获得启用的中类列表
     *
     * @param eventBigCode 大类代码
     * @return 国家政策信息列表
     */
    List<NationalPolicyInfoDO> getEnabledMidCategoriesByBigCode(String eventBigCode);

    /**
     * 根据中类代码获得启用的小类列表
     *
     * @param eventMidCode 中类代码
     * @return 国家政策信息列表
     */
    List<NationalPolicyInfoDO> getEnabledSmallCategoriesByMidCode(String eventMidCode);

    /**
     * 更新启用状态
     *
     * @param id 编号
     * @param enableStatus 启用状态
     */
    void updateEnableStatus(Long id, String enableStatus);

    /**
     * 检查代码唯一性
     *
     * @param eventBigCode 大类代码
     * @param eventMidCode 中类代码
     * @param eventSmallCode 小类代码
     * @param id 编号（排除自身）
     * @return 是否唯一
     */
    boolean checkCodeUnique(String eventBigCode, String eventMidCode, String eventSmallCode, Long id);

    /**
     * 根据ID列表获取事件类型列表
     *
     * @param ids ID列表
     * @return 国家政策信息列表
     */
    List<NationalPolicyInfoDO> getNationalPolicyInfoListByIds(List<Long> ids);

    /**
     * 根据小类代码获取事件类型
     *
     * @param eventSmallCode 小类代码
     * @return 国家政策信息
     */
    NationalPolicyInfoDO getNationalPolicyInfoBySmallCode(String eventSmallCode);
}
