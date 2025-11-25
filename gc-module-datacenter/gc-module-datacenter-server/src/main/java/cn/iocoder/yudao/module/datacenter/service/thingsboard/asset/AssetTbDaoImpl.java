package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao.AssetTbDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.id.AssetId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.Optional;

@Service
public class AssetTbDaoImpl implements AssetTbDao {

    @Value("${thingsboard.url:http://127.0.0.1:8080/}")
    private String url;

    @Value("${thingsboard.username:test}")
    private String username;

    @Value("${thingsboard.password:test}")
    private String password;

    @Override
    public PageResult<Asset> getAssetPage(AssetPageReqVO pageReqVO) {
        PageResult<Asset> assetPageResult = new PageResult<>();
        RestClient client = new RestClient(url);
        client.login(username, password);

        PageLink pageLink = new PageLink(pageReqVO.getPageSize(), pageReqVO.getPageNo() - 1);
        PageData<Asset> tenantAssets = client.getTenantAssets(pageLink,"顺昌排口");

        assetPageResult.setList(tenantAssets.getData());
        assetPageResult.setTotal(tenantAssets.getTotalElements());

        client.logout();
        client.close();
        return assetPageResult;
    }

    @Override
    public Asset getAssetById(String id) {
        RestClient client = new RestClient(url);
        client.login(username, password);

        Optional<Asset> asset = client.getAssetById(AssetId.fromString(id));
        try {
            return asset.orElse(null);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public AssetInfo getAssetInfoById(String id) {
        RestClient client = new RestClient(url);
        client.login(username, password);

        // 获取资产详情
        Optional<AssetInfo> assetInfoOptional = client.getAssetInfoById(AssetId.fromString(id));

        try {
            if (assetInfoOptional.isPresent()) {
                AssetInfo assetInfo = assetInfoOptional.get();
                // 可以在这里添加额外的资产信息处理逻辑
                return assetInfo;
            }
            return null;
        } finally {
            client.logout();
            client.close();
        }
    }
}