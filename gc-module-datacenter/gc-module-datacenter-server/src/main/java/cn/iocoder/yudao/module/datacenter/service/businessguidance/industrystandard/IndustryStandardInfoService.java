package cn.iocoder.yudao.module.datacenter.service.businessguidance.industrystandard;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.industrystandard.IndustryStandardInfoDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 行业规范信息 Service 接口
 *
 * @author Gyh
 */
public interface IndustryStandardInfoService {

    /**
     * 创建行业规范信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndustryStandardInfo(@Valid IndustryStandardInfoCreateReqVO createReqVO);

    /**
     * 更新行业规范信息
     *
     * @param updateReqVO 更新信息
     */
    void updateIndustryStandardInfo(@Valid IndustryStandardInfoUpdateReqVO updateReqVO);

    /**
     * 删除行业规范信息
     *
     * @param id 编号
     */
    void deleteIndustryStandardInfo(Long id);

    /**
     * 获得行业规范信息
     *
     * @param id 编号
     * @return 行业规范信息
     */
    IndustryStandardInfoDO getIndustryStandardInfo(Long id);

    /**
     * 获得行业规范信息分页
     *
     * @param pageReqVO 分页查询
     * @return 行业规范信息分页
     */
    PageResult<IndustryStandardInfoDO> getIndustryStandardInfoPage(@Valid IndustryStandardInfoPageReqVO pageReqVO);

    /**
     * 获得所有行业规范信息列表
     *
     * @return 行业规范信息列表
     */
    List<IndustryStandardInfoDO> getIndustryStandardInfoList();

    /**
     * 获得所有实施中的行业规范列表
     *
     * @return 行业规范信息列表
     */
    List<IndustryStandardInfoDO> getImplementingIndustryStandardInfoList();

    /**
     * 获得即将被替代的行业规范列表（距替代时间≤30天）
     *
     * @return 行业规范信息列表
     */
    List<IndustryStandardInfoDO> getReplacingIndustryStandardInfoList();

    /**
     * 检查规范编号是否唯一
     *
     * @param standardNo 规范编号
     * @param id 编号（排除自身）
     * @return 是否唯一
     */
    boolean checkStandardNoUnique(String standardNo, Long id);

    /**
     * 检查规范编号格式是否正确
     *
     * @param standardNo 规范编号
     * @return 是否正确
     */
    boolean validateStandardNoFormat(String standardNo);

    /**
     * 批量更新规范状态
     *
     * @param ids ID列表
     * @param standardStatus 规范状态
     */
    void updateStandardStatusBatch(List<Long> ids, String standardStatus);

    /**
     * 自动更新规范状态（定时任务调用）
     */
    void autoUpdateStandardStatus();

    /**
     * 创建规范替代版本
     *
     * @param createReplaceReqVO 替代版本创建信息
     * @return 新版本编号
     */
    Long createStandardReplace(@Valid IndustryStandardInfoCreateReplaceReqVO createReplaceReqVO);

    /**
     * 根据行业领域查询行业规范列表
     *
     * @param industryField 行业领域
     * @return 行业规范信息列表
     */
    List<IndustryStandardInfoDO> getIndustryStandardInfoListByIndustryField(String industryField);

    /**
     * 根据业务模块查询关联的行业规范列表
     *
     * @param businessModule 业务模块
     * @return 行业规范信息列表
     */
    List<IndustryStandardInfoDO> getIndustryStandardInfoListByBusinessModule(String businessModule);

    /**
     * 查询规范的所有替代版本
     *
     * @param standardNo 规范编号
     * @return 行业规范信息列表
     */
    List<IndustryStandardInfoDO> getStandardReplaceVersions(String standardNo);

    /**
     * 获取规范配套解读文件列表
     *
     * @param id 规范ID
     * @return 解读文件路径列表
     */
    List<String> getInterpretationFiles(Long id);
}