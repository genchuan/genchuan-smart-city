package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.facemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo.*;

import java.util.List;

/**
 * 人脸信息 Service 接口
 *
 * @author 亘川智城
 */
public interface FaceMgmtService {

    /**
     * 获得人脸信息分页
     */
    PageResult<FaceMgmtRespVO> getFaceMgmtPage(FaceMgmtPageReqVO pageReqVO);

    /**
     * 获得人脸信息
     */
    FaceMgmtRespVO getFaceMgmt(Long id);

    /**
     * 创建人脸信息 —— 新增人员，初始权限状态为"未授权"
     */
    Boolean createFaceMgmt(FaceMgmtCreateReqVO createReqVO);

    /**
     * 更新人脸信息
     */
    void updateFaceMgmt(FaceMgmtUpdateReqVO updateReqVO);

    /**
     * 删除人脸信息（批量）
     */
    void deleteFaceMgmt(List<Long> ids);

    /**
     * 人脸采集 —— 随机生成验证准确率，更新采集结果
     */
    FaceMgmtCollectRespVO collectFaceMgmt(FaceMgmtCollectReqVO reqVO);

    /**
     * 权限配置 —— 设置通行区域和权限有效期，不改变授权状态
     */
    Boolean configFaceMgmt(FaceMgmtConfigReqVO reqVO);

    /**
     * 通行验证 —— 查询已授权人脸库，取第一条记录模拟匹配
     */
    FaceMgmtVerifyRespVO verifyFaceMgmt(FaceMgmtVerifyReqVO reqVO);

    /**
     * 通行 —— 累加通行次数，记录最后通行时间
     */
    Boolean accessFaceMgmt(FaceMgmtAccessReqVO reqVO);

    /**
     * 禁用 —— 权限状态置为"已禁用"
     */
    Boolean disableFaceMgmt(FaceMgmtDisableReqVO reqVO);

    /**
     * 授权 —— 权限状态置为"已授权"，同时设置有效期
     */
    Boolean authFaceMgmt(FaceMgmtAuthReqVO reqVO);

    /**
     * 续期 —— 延长权限有效期，状态置为"已授权"
     */
    Boolean renewFaceMgmt(FaceMgmtRenewReqVO reqVO);

    /**
     * 人脸通行授权态势 —— 汇总统计（总数/授权/过期/未授权）及区域分布、时段趋势
     */
    FaceMgmtChartRespVO getFaceMgmtChart(Long startTime, Long endTime);

}
