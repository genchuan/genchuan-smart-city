package cn.iocoder.yudao.module.kitchen.api.aialertmessage;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.AiAlertMessageApi;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.dto.AiAlertMessagePageReqDTO;

import cn.iocoder.yudao.module.kitchen.api.aialertmessage.dto.AiAlertMessageRespDTO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.service.aialertmessage.AiAlertMessageService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController
@Validated
public class AiAlertMessageApiImpl implements AiAlertMessageApi {

    @Resource
    private AiAlertMessageService aiAlertMessageService;

    @Override
    public CommonResult<AiAlertMessageRespDTO> getAiAlertMessage(Long id) {
        AiAlertMessageDO message = aiAlertMessageService.getAiAlertMessage(id);
        return success(BeanUtils.toBean(message, AiAlertMessageRespDTO.class));
    }

    @Override
    public CommonResult<PageResult<AiAlertMessageRespDTO>> getAiAlertMessagePage(AiAlertMessagePageReqDTO pageReqDTO) {
        // 分页查询（直接复用你已有的 Service）
        AiAlertMessagePageReqVO reqVO = BeanUtils.toBean(pageReqDTO, AiAlertMessagePageReqVO.class);
        PageResult<AiAlertMessageRespVO> pageResult = aiAlertMessageService.getAiAlertMessagePage(reqVO);
        List<AiAlertMessageRespDTO> bean = BeanUtils.toBean(pageResult.getList(), AiAlertMessageRespDTO.class);

        PageResult<AiAlertMessageRespDTO> result = new PageResult<>();
        result.setList(bean);
        return success(result);
    }
}
