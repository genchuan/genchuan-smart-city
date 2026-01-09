package cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.Dao.AssetProfileTbDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.thingsboard.rest.client.RestClient;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.id.AssetProfileId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.Optional;

@Repository
public class AssetProfileTbDaoImpl implements AssetProfileTbDao {

    @Value("${thingsboard.url:http://127.0.0.1:8080/}")
    private String url;

    @Value("${thingsboard.username:test}")
    private String username;

    @Value("${thingsboard.password:test}")
    private String password;

    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @Override
    public AssetProfile getAssetProfileById(String id) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);
            Optional<AssetProfile> assetProfile = client.getAssetProfileById(AssetProfileId.fromString(id));
            return assetProfile.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("获取资产配置失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public AssetProfile createAssetProfile(AssetProfile assetProfile) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建创建资产配置的URL
            String createAssetProfileUrl = url + "api/assetProfile";

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            String assetProfileJson = objectMapper.writeValueAsString(assetProfile);
            HttpEntity<String> entity = new HttpEntity<>(assetProfileJson, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<AssetProfile> response = restTemplate.exchange(
                    createAssetProfileUrl,
                    org.springframework.http.HttpMethod.POST,
                    entity,
                    AssetProfile.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("创建资产配置失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public void deleteAssetProfile(String assetProfileId) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建删除资产配置的URL
            String deleteAssetProfileUrl = url + "api/assetProfile/" + assetProfileId;

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.exchange(
                    deleteAssetProfileUrl,
                    org.springframework.http.HttpMethod.DELETE,
                    entity,
                    Void.class
            );

        } catch (Exception e) {
            throw new RuntimeException("删除资产配置失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }

    @Override
    public AssetProfile updateAssetProfile(AssetProfile assetProfile) {
        RestClient client = new RestClient(url);
        try {
            client.login(username, password);

            // 构建更新资产配置的URL - 与新增接口相同
            String updateAssetProfileUrl = url + "api/assetProfile";

            String token = client.getToken();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            String assetProfileJson = objectMapper.writeValueAsString(assetProfile);
            HttpEntity<String> entity = new HttpEntity<>(assetProfileJson, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<AssetProfile> response = restTemplate.exchange(
                    updateAssetProfileUrl,
                    org.springframework.http.HttpMethod.POST, // 使用POST方法
                    entity,
                    AssetProfile.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("更新资产配置失败: " + e.getMessage(), e);
        } finally {
            client.logout();
            client.close();
        }
    }
}
