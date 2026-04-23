package cn.iocoder.yudao.module.inspectop.service.assetinfo;

import java.util.*;

import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetinfo.AssetInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资产信息 Service 接口
 *
 * @author zhucongquan
 */
public interface AssetInfoService {

    /**
     * 创建资产信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetInfo(@Valid AssetInfoSaveReqVO createReqVO);

    /**
     * 更新资产信息
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetInfo(@Valid AssetInfoSaveReqVO updateReqVO);

    /**
     * 删除资产信息
     *
     * @param id 编号
     */
    void deleteAssetInfo(Long id);

    /**
    * 批量删除资产信息
    *
    * @param ids 编号
    */
    void deleteAssetInfoListByIds(List<Long> ids);

    /**
     * 获得资产信息
     *
     * @param id 编号
     * @return 资产信息
     */
    AssetInfoDO getAssetInfo(Long id);

    /**
     * 获得资产信息分页
     *
     * @param pageReqVO 分页查询
     * @return 资产信息分页
     */
    PageResult<AssetInfoRespVO> getAssetInfoPage(AssetInfoPageReqVO pageReqVO);

    // 在 AssetInfoService.java 接口中添加以下方法

    /**
     * 禁用资产信息
     *
     * @param id 资产ID
     */
    void disableAssetInfo(Long id);

    /**
     * 报废资产信息
     *
     * @param scrapReqVO 报废请求信息
     */
    void scrapAssetInfo(AssetInfoScrapReqVO scrapReqVO);

    /**
     * 导入资产信息
     *
     * @param file Excel文件
     * @param updateSupport 是否更新已存在的数据
     * @return 导入结果
     */
    ImportRespVO importAssetInfo(MultipartFile file, boolean updateSupport);

    /**
     * 获得资产信息图表统计
     *
     * @return 图表统计结果
     */
    AssetInfoChartRespVO getAssetInfoChart();

}