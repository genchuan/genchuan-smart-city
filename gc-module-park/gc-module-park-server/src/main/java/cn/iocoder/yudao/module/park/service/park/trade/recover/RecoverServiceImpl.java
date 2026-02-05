package cn.iocoder.yudao.module.park.service.park.trade.recover;

import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.VerifyOrderFreeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.GenerateArrearsQrCodeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.PreDiscountAutoCalculateReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.blackwhitelist.BlackWhiteListDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.recover.RecoverMapper;
import cn.iocoder.yudao.module.park.framework.common.QrCodeUtils;
import cn.iocoder.yudao.module.park.service.park.marketing.smoothstopcard.SmoothStopCardService;
import cn.iocoder.yudao.module.park.service.park.user.blackwhitelist.BlackWhiteListService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class RecoverServiceImpl implements  RecoverService{
    @Resource
    private RecoverMapper recoverMapper;

    @Resource
    private BlackWhiteListService blackWhiteListService;

    @Resource
    private SmoothStopCardService smoothStopCardService;
    @Override
    public String generateArrearsQrCode(GenerateArrearsQrCodeReqVO reqVO) {
        BigDecimal orginalAmount=reqVO.getOrginalAmount();
        String arrearsIdListStr=reqVO.getArrearsIdListStr();
        String carNumber=reqVO.getCarNumber();

        // 把金额和欠费ID列表拼接到 URL(前端用的url)， URL 编码UTF_8
        String payUrl = String.format(
                "https://yourdomain.com/pay?orginalAmount=%s&arrearsIds=%s&carNumber=%s",
                orginalAmount.toPlainString(),
                URLEncoder.encode(arrearsIdListStr, StandardCharsets.UTF_8)
        );

        // 3. 生成二维码 Base64
        return QrCodeUtils.generateBase64(payUrl, 300, 300);
    }

    @Override
    public BigDecimal preDiscountAutoCalculate(PreDiscountAutoCalculateReqVO reqVO) {
        //1.获取参数
        BigDecimal orginalAmount=reqVO.getOrginalAmount();
        String arrearsIdListStr=reqVO.getArrearsIdListStr();
        String carNumber=reqVO.getCarNumber();
        Long parkLotId= reqVO.getParkLotId();

        BigDecimal preDiscountAmount=orginalAmount;
        //2.判断是否白名单，如果是，最终金额为零
        if (blackWhiteListService.verifyWhitelistByCarNumber(carNumber)){
            preDiscountAmount=BigDecimal.ZERO;
            return preDiscountAmount;
        }

        //3.TODO 判断畅停卡是否生效，生效最终金额为零
        //规则：获取该用户所有畅停卡列表，且在有效时间内;判断是否有适合这个车场，且车牌号符合的畅停卡，有就生效
        VerifyOrderFreeReqVO verifyOrderFreeReqVO=new VerifyOrderFreeReqVO();
        verifyOrderFreeReqVO.setCarNumber(carNumber);
        verifyOrderFreeReqVO.setParkLotId(parkLotId);
        if (smoothStopCardService.verifyOrderFreeBySmoothCard(verifyOrderFreeReqVO).isVerify()){
            preDiscountAmount=BigDecimal.ZERO;
            return preDiscountAmount;
        }


        //4.返回最终金额
        return preDiscountAmount;

    }
}
