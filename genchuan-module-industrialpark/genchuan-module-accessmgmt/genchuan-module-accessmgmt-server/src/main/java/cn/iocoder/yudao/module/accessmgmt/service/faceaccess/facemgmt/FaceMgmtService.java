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
     * 创建人脸信息
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
     * 获得人脸信息列表（导出用）
     */
    List<FaceMgmtRespVO> getFaceMgmtList(FaceMgmtPageReqVO pageReqVO);

    /**
     * 人脸采集
     */
    FaceMgmtCollectRespVO collectFaceMgmt(FaceMgmtCollectReqVO reqVO);

    /**
     * 权限配置
     */
    Boolean configFaceMgmt(FaceMgmtConfigReqVO reqVO);

    /**
     * 通行验证
     */
    FaceMgmtVerifyRespVO verifyFaceMgmt(FaceMgmtVerifyReqVO reqVO);

    /**
     * 通行
     */
    Boolean accessFaceMgmt(FaceMgmtAccessReqVO reqVO);

    /**
     * 禁用
     */
    Boolean disableFaceMgmt(FaceMgmtDisableReqVO reqVO);

    /**
     * 授权
     */
    Boolean authFaceMgmt(FaceMgmtAuthReqVO reqVO);

    /**
     * 续期
     */
    Boolean renewFaceMgmt(FaceMgmtRenewReqVO reqVO);

    /**
     * 人脸通行授权态势
     */
    FaceMgmtChartRespVO getFaceMgmtChart(String startTime, String endTime);

}
