package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class AiSceneMessageServiceImpl implements AiSceneMessageService {

    private static final String SUBSCRIBE_URL = "https://vcp.21cn.com/open/token/aiApplicationCenter/subscribeAiSceneMessage";
    private static final String UNSUBSCRIBE_URL = "https://vcp.21cn.com/open/token/aiApplicationCenter/unsubscribeAiSceneMessage";
    private static final String QUERY_URL = "https://vcp.21cn.com/open/token/aiApplicationCenter/querySubscribeAiSceneMessage";
    private static final String REFRESH_SRC_URL = "https://vcp.21cn.com/open/token/aiApplicationCenter/sourceDownloadUrl";

    @Override
    public CommonResult<?> subscribe(SubscribeReqVO reqVO) {
        return HttpClientUtil.postForm(SUBSCRIBE_URL, reqVO);
    }

    @Override
    public CommonResult<?> unsubscribe(UnsubscribeReqVO reqVO) {
        return HttpClientUtil.postForm(UNSUBSCRIBE_URL, reqVO);
    }

    @Override
    public CommonResult<?> querySubscription(QuerySubscribeReqVO reqVO) {
        return HttpClientUtil.postForm(QUERY_URL, reqVO);
    }

    @Override
    public CommonResult<?> refreshSrcUrl(RefreshSrcUrlReqVO reqVO) {
        return HttpClientUtil.postForm(REFRESH_SRC_URL, reqVO);
    }
}
