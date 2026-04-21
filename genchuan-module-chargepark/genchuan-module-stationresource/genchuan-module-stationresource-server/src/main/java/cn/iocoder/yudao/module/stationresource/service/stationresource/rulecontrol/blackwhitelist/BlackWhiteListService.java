package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.blackwhitelist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.blackwhitelist.BlackWhiteListDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 黑白名单 Service 接口
 */
public interface BlackWhiteListService {

    /**
     * 获取图表统计数据
     */
    BlackWhiteListChartRespVO getChartData();

    /**
     * 批量生效名单
     */
    void enableList(List<Long> ids);

    /**
     * 批量禁用名单
     */
    void disableList(List<Long> ids);

    /**
     * 创建黑白名单
     */
    void createList(BlackWhiteListCreateReqVO createReqVO);

    /**
     * 更新黑白名单
     */
    void updateList(BlackWhiteListUpdateReqVO updateReqVO);

    /**
     * 导入黑白名单
     */
    BlackWhiteListImportResp importList(MultipartFile file, boolean updateSupport) throws Exception;

    /**
     * 获取名单详情
     */
    BlackWhiteListDO getListInfo(Long id);

    /**
     * 获得名单分页
     */
    PageResult<BlackWhiteListDO> getListPage(BlackWhiteListPageReqVO pageReqVO);
}
