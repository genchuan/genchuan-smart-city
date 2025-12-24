package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao.AssetTbDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.id.AssetId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.*;

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

        Optional<AssetInfo> assetInfoOptional = client.getAssetInfoById(AssetId.fromString(id));
        try {
            return assetInfoOptional.orElse(null);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public PageData<Asset> getAllAssets(PageLink pageLink) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);
            return getAllAssets(pageLink, client);
        } finally {
            client.logout();
            client.close();
        }
    }

    private PageData<Asset> getAllAssets(PageLink pageLink, RestClient client) {
        try {
            String assetsUrl = url + "api/tenant/assets?pageSize=" + pageLink.getPageSize() + "&page=" + pageLink.getPage();
            String token = client.getToken();

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<PageData<Asset>> response = restTemplate.exchange(
                    assetsUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<PageData<Asset>>() {}
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取资产列表失败", e);
        }
    }

    @Override
    public List<Map<String, Object>> getAssetAttributes(String assetId) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建获取属性的URL
            String attributesUrl = url + "api/plugins/telemetry/ASSET/" + assetId + "/values/attributes";

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List> response = restTemplate.exchange(
                    attributesUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    List.class
            );

            return (List<Map<String, Object>>) response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取资产属性失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public List<Map<String, Object>> getAssetRelatedDevices(String assetId) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建获取关联设备的URL
            String relationsUrl = url + "api/relations/info?toId=" + assetId + "&toType=ASSET";

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List> response = restTemplate.exchange(
                    relationsUrl,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    List.class
            );

            List<Map<String, Object>> relations = (List<Map<String, Object>>) response.getBody();

            // 转换格式为期望的设备列表格式
            List<Map<String, Object>> devices = new ArrayList<>();
            if (relations != null) {
                for (Map<String, Object> relation : relations) {
                    Map<String, Object> from = (Map<String, Object>) relation.get("from");
                    if ("DEVICE".equals(from.get("entityType"))) {
                        Map<String, Object> device = new HashMap<>();
                        device.put("deviceName", relation.get("fromName"));
                        device.put("entityType", "DEVICE");
                        device.put("deviceId", from.get("id"));
                        devices.add(device);
                    }
                }
            }

            return devices;
        } catch (Exception e) {
            throw new RuntimeException("获取资产关联设备失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public Asset createAsset(Asset asset) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建创建资产的URL
            String createAssetUrl = url + "api/asset";

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            // 使用ObjectMapper将Asset对象转换为JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String assetJson = objectMapper.writeValueAsString(asset);

            HttpEntity<String> entity = new HttpEntity<>(assetJson, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Asset> response = restTemplate.exchange(
                    createAssetUrl,
                    org.springframework.http.HttpMethod.POST,
                    entity,
                    Asset.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("创建资产失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public void deleteAsset(String assetId) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建删除资产的URL
            String deleteAssetUrl = url + "api/asset/" + assetId;

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.exchange(
                    deleteAssetUrl,
                    org.springframework.http.HttpMethod.DELETE,
                    entity,
                    Void.class
            );

        } catch (Exception e) {
            throw new RuntimeException("删除资产失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public PageData<AssetProfile> getAssetProfiles(Integer pageSize, Integer page, String sortProperty, String sortOrder) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建获取资产配置的URL
            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("api/assetProfiles?pageSize=").append(pageSize)
                    .append("&page=").append(page);

            if (sortProperty != null && !sortProperty.isEmpty()) {
                urlBuilder.append("&sortProperty=").append(sortProperty);
            }
            if (sortOrder != null && !sortOrder.isEmpty()) {
                urlBuilder.append("&sortOrder=").append(sortOrder);
            }

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<PageData<AssetProfile>> response = restTemplate.exchange(
                    urlBuilder.toString(),
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<PageData<AssetProfile>>() {}
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("获取资产配置列表失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }


}