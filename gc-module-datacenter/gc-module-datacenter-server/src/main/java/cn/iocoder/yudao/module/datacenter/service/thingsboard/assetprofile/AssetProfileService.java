package cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfilePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfileSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.assetprofile.AssetProfileDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;

/**
 * 资产配置信息 Service 接口
 *
 * @author zhucongquan
 */
public interface AssetProfileService {

    /**
     * 创建资产配置信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetProfile(@Valid AssetProfileSaveReqVO createReqVO);

    /**
     * 更新资产配置信息
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetProfile(@Valid AssetProfileSaveReqVO updateReqVO);

    /**
     * 删除资产配置信息
     *
     * @param id 编号
     */
    void deleteAssetProfile(Long id);

    /**
     * 获得资产配置信息
     *
     * @param id 编号
     * @return 资产配置信息
     */
    AssetProfileDO getAssetProfile(Long id);

    /**
     * 获得资产配置信息分页
     *
     * @param pageReqVO 分页查询
     * @return 资产配置信息分页
     */
    PageResult<AssetProfileDO> getAssetProfilePage(AssetProfilePageReqVO pageReqVO);

    /**
     * 同步ThingsBoard资产配置到本地数据库
     *
     * @return 同步结果信息
     */
    Map<String, Object> syncAssetProfilesFromThingsBoard();

    /**
     * 获取ThingsBoard资产配置分页列表
     *
     * @param pageSize 每页大小
     * @param page 页码
     * @param sortProperty 排序字段
     * @param sortOrder 排序方向
     * @return 资产配置分页数据
     */
    PageData<AssetProfile> getAssetProfilesFromThingsBoard(Integer pageSize, Integer page, String sortProperty, String sortOrder);

    /**
     * 获得所有资产配置信息
     *
     * @return 所有资产配置信息列表
     */
    List<AssetProfileDO> getAssetProfileList();
}