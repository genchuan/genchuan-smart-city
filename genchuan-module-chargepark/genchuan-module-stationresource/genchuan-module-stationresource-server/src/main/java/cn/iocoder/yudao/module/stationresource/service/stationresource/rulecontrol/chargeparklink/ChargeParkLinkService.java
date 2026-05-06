package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.chargeparklink;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.chargeparklink.ChargeParkLinkDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 充停联动 Service 接口
 *
 * @author 亘川智城
 */
public interface ChargeParkLinkService {

    // ==================== 新增 ====================
    void createChargeParkLink(ChargeParkLinkCreateReqVO reqVO);

    // ==================== 更新 ====================
    void updateChargeParkLink(ChargeParkLinkUpdateReqVO reqVO);

    // ==================== 状态 ====================
    void enableChargeParkLink(List<Long> ids);
    void disableChargeParkLink(List<Long> ids);

    // ==================== 导入 ====================
    ChargeParkLinkImportResp importChargeParkLink(MultipartFile file, boolean updateSupport);

    // ==================== 图表 ====================
    ChargeParkLinkChartRespVO getChargeParkLinkChart();

    // ==================== 基础 ====================
    /**
     * 创建充停联动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChargeParkLink(@Valid ChargeParkLinkSaveReqVO createReqVO);

    /**
     * 更新充停联动
     *
     * @param updateReqVO 更新信息
     */
    void updateChargeParkLink(@Valid ChargeParkLinkSaveReqVO updateReqVO);

    /**
     * 删除充停联动
     *
     * @param id 编号
     */
    void deleteChargeParkLink(Long id);

    /**
    * 批量删除充停联动
    *
    * @param ids 编号
    */
    void deleteChargeParkLinkListByIds(List<Long> ids);

    /**
     * 获得充停联动
     *
     * @param id 编号
     * @return 充停联动
     */
    ChargeParkLinkDO getChargeParkLink(Long id);

    /**
     * 获得充停联动分页
     *
     * @param pageReqVO 分页查询
     * @return 充停联动分页
     */
    PageResult<ChargeParkLinkRespVO> getChargeParkLinkPage(ChargeParkLinkPageReqVO pageReqVO);

}
