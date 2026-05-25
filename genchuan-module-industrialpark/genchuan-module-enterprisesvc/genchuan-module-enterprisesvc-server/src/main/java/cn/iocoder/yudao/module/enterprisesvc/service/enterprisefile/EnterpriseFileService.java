package cn.iocoder.yudao.module.enterprisesvc.service.enterprisefile;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo.*;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.enterprisefile.EnterpriseFileDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 企业档案 Service 接口
 *
 * @author zhucongquan
 */
public interface EnterpriseFileService {

    /**
     * 创建企业档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnterpriseFile(@Valid EnterpriseFileCreateReqVO createReqVO);

    /**
     * 上传企业资质证明文件
     *
     * @param uploadReqVO 上传信息
     * @return 文件访问路径
     */
    String uploadEnterpriseFile(@Valid EnterpriseFileUploadReqVO uploadReqVO);

    /**
     * 更新企业档案
     *
     * @param updateReqVO 更新信息
     */
    void updateEnterpriseFile(@Valid EnterpriseFileSaveReqVO updateReqVO);

    /**
     * 删除企业档案
     *
     * @param id 编号
     */
    void deleteEnterpriseFile(Long id);

    /**
    * 批量删除企业档案
    *
    * @param ids 编号
    */
    void deleteEnterpriseFileListByIds(List<Long> ids);

    /**
     * 获得企业档案
     *
     * @param id 编号
     * @return 企业档案
     */
    EnterpriseFileDO getEnterpriseFile(Long id);

    /**
     * 获得企业档案分页
     *
     * @param pageReqVO 分页查询
     * @return 企业档案分页
     */
    PageResult<EnterpriseFileDO> getEnterpriseFilePage(EnterpriseFilePageReqVO pageReqVO);

    /**
     * 审核企业档案
     *
     * @param id 企业档案 ID
     */
    void auditEnterpriseFile(Long id);

    /**
     * 驳回企业档案
     *
     * @param rejectReqVO 驳回信息
     */
    void rejectEnterpriseFile(@Valid EnterpriseFileRejectReqVO rejectReqVO);

    /**
     * 重新提交企业档案
     *
     * @param resubmitReqVO 重新提交信息
     */
    void resubmitEnterpriseFile(@Valid EnterpriseFileResubmitReqVO resubmitReqVO);

    /**
     * 获取企业档案分布态势图表数据
     *
     * @return 图表数据
     */
    EnterpriseFileChartRespVO getEnterpriseFileChart();
}
