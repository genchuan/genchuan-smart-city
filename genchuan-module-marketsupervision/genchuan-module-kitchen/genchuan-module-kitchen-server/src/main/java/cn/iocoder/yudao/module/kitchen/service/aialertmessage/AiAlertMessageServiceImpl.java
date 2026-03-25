package cn.iocoder.yudao.module.kitchen.service.aialertmessage;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.add.AddAiAlertMessageReq;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.aialertmessage.AiAlertMessageMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegaltypedict.IllegalTypeDictMapper;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.name.NameUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.AI_ALERT_MESSAGE_NOT_EXISTS;

/**
 * AI告警消息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AiAlertMessageServiceImpl implements AiAlertMessageService {

    @Resource
    private AiAlertMessageMapper aiAlertMessageMapper;

    @Resource
    private IllegalTypeDictMapper illegalTypeDictMapper;

    @Override
    public Long createAiAlertMessage(AiAlertMessageSaveReqVO createReqVO) {
        // 插入
        AiAlertMessageDO aiAlertMessage = BeanUtils.toBean(createReqVO, AiAlertMessageDO.class);
        aiAlertMessageMapper.insert(aiAlertMessage);
        // 返回
        return aiAlertMessage.getId();
    }

    @Override
    public void updateAiAlertMessage(AiAlertMessageSaveReqVO updateReqVO) {
        // 校验存在
        validateAiAlertMessageExists(updateReqVO.getId());
        // 更新
        AiAlertMessageDO updateObj = BeanUtils.toBean(updateReqVO, AiAlertMessageDO.class);
        aiAlertMessageMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiAlertMessage(Long id) {
        // 校验存在
        validateAiAlertMessageExists(id);
        // 删除
        aiAlertMessageMapper.deleteById(id);
    }

    private void validateAiAlertMessageExists(Long id) {
        if (aiAlertMessageMapper.selectById(id) == null) {
            throw exception(AI_ALERT_MESSAGE_NOT_EXISTS);
        }
    }

    @Override
    public AiAlertMessageDO getAiAlertMessage(Long id) {
        return aiAlertMessageMapper.selectById(id);
    }

    @Override
    public PageResult<AiAlertMessageRespVO> getAiAlertMessagePage(AiAlertMessagePageReqVO pageReqVO) {
        // 1查询 ai_alert_message 分页
        PageResult<AiAlertMessageDO> result = aiAlertMessageMapper.selectPage(pageReqVO);

        List<AiAlertMessageDO> list = result.getList();
        if (list.isEmpty()) {
            return new PageResult<>(); // 没有数据直接返回
        }

        List<AiAlertMessageRespVO> aiAlertMessageRespVOList = BeanUtils.toBean(list,AiAlertMessageRespVO.class);

        // 2收集 ai_ability_code，用于批量查询 illegal_type_dict
        Set<String> aiAbilityCodes = aiAlertMessageRespVOList.stream()
                .map(AiAlertMessageRespVO::getAiAbilityCode)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (!aiAbilityCodes.isEmpty()) {
            // 3批量查询 illegal_type_dict
            List<IllegalTypeDictDO> dictList = illegalTypeDictMapper.selectList(new LambdaQueryWrapperX<IllegalTypeDictDO>()
                    .in(IllegalTypeDictDO::getTypeCode, aiAbilityCodes)
                    .eq(IllegalTypeDictDO::getDeleted, 0));

            // 4转为 Map 便于匹配
            Map<String, String> typeNameMap = dictList.stream()
                    .collect(Collectors.toMap(IllegalTypeDictDO::getTypeCode, IllegalTypeDictDO::getTypeName));

            // 5回填 typeName
            aiAlertMessageRespVOList.forEach(aam -> aam.setAlertTypeName(typeNameMap.get(aam.getAiAbilityCode())));
        }

        PageResult<AiAlertMessageRespVO> result2 =new PageResult<>(aiAlertMessageRespVOList,result.getTotal());
        return result2;
    }

    // 预定义饮食相关违规类型编码（根据illegal_type_dict中与后厨、明厨亮灶相关的记录）
    private static final List<String> KITCHEN_ALERT_CODES = Arrays.asList(
            "100200", // 未戴口罩识别
            "100500", // 抽烟识别
            "100600", // 厨师帽识别
            "102300", // 老鼠识别
            "102500" // 未穿厨师服识别
//            "102000", // 垃圾暴露检测
//            "108600"  // 煤气罐识别
    );
    /**
     * 从饮食相关违规类型中随机获取一条记录
     */
    private IllegalTypeDictDO getRandomKitchenAlert() {
        List<IllegalTypeDictDO> list = illegalTypeDictMapper.selectList(
                new LambdaQueryWrapper<IllegalTypeDictDO>()
                        .in(IllegalTypeDictDO::getTypeCode, KITCHEN_ALERT_CODES)
                        .eq(IllegalTypeDictDO::getDeleted, 0)
        );
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        int index = RandomUtil.randomInt(list.size());
        return list.get(index);
    }
    @Override
    public Long addAiAlertMessage(AddAiAlertMessageReq reqVO) {

        // ================== 1. 参数兜底 ==================
        if (reqVO.getAlertCreateTime() == null) {
            reqVO.setAlertCreateTime(LocalDateTime.now());
        }

        // ================== 2. 随机生成数据 ==================

        // 1.生成随机设备关联用户ID列表
        if (reqVO.getUserIds() == null) {
//            List<Integer> userIdList = new ArrayList<>();
            int userNum = RandomUtil.randomInt(2, 4);
            StringBuilder userIdListStr = new StringBuilder("[");
            for (int i =0;i<=userNum;i++){
                Integer userId = RandomUtil.randomInt(50, 100);
//                userIdList.add(userId);
                if (i!=0){
                    userIdListStr.append(",");
                }
                userIdListStr.append(userId);
            }
            userIdListStr.append("]");
            reqVO.setUserIds(userIdListStr.toString());
        }

        //2. 随机 scene_id、ai_ability_code、alert_type
        // 如果sceneId、aiAbilityCode、alertType任一为空，则从饮食相关违规类型中随机选择一条，并全部覆盖（保证三者同源）
        if (reqVO.getSceneId() == null || reqVO.getAiAbilityCode() == null || reqVO.getAlertType() == null) {
            IllegalTypeDictDO dict = getRandomKitchenAlert();
            if (dict != null) {
                reqVO.setAiAbilityCode(dict.getTypeCode());
                // type_category 为字符串数字，转换为 Integer
                reqVO.setAlertType(Integer.valueOf(dict.getTypeCategory()));
                // scene_id 可随机生成（例如前缀加随机数），也可以使用 type_code 加后缀，这里简单生成
                reqVO.setSceneId("scene_" + RandomUtil.randomNumbers(6));
            } else {
                // 降级处理：如果表中无相关记录，使用默认值（例如未戴口罩）
                reqVO.setAiAbilityCode("100200");
                reqVO.setAlertType(15); // 未戴口罩对应的 type_category 为 '15'
                reqVO.setSceneId("scene_" + RandomUtil.randomNumbers(6));
            }
        }
        // ================== 2.1 生成图片 ==================
        if (reqVO.getSrcUrl() == null) {
            String imageUrl = getRandomImage(reqVO.getAiAbilityCode());
            reqVO.setSrcUrl(imageUrl);
        }

        //设备编码
        if (reqVO.getDeviceCode()==null){
            reqVO.setDeviceCode(NameUtil.generateCode("AIDEV"));
        }

        // 生成随机手机号（11位）
        if (reqVO.getDeviceAccount() == null) {
            reqVO.setDeviceAccount(generatePhone());
        }

        // 离岗时间 60~300 秒
        if (reqVO.getLeaveTime() == null) {
            reqVO.setLeaveTime(RandomUtil.randomInt(60, 301));
        }

        // 间隔时间 10~60 秒
        if (reqVO.getIntervalTime() == null) {
            reqVO.setIntervalTime(RandomUtil.randomInt(10, 61));
        }

        // 结束时间（当天随机时间 HH:mm）
        if (reqVO.getTimeSlotEnd() == null) {
            reqVO.setTimeSlotEnd(generateTime());
        }

        // ================== 3. alertParams 组装 ==================
        if (reqVO.getAlertParams() == null) {
            JSONObject params = new JSONObject();
            params.put("deviceCode", reqVO.getDeviceCode());
            params.put("alertType", reqVO.getAlertType());
            params.put("sceneId", reqVO.getSceneId());
            params.put("aiAbilityCode", reqVO.getAiAbilityCode());
//            params.put("time", reqVO.getAlertCreateTime());

            reqVO.setAlertParams(params.toString());
        }

        // ================== 4. VO -> DO ==================
        AiAlertMessageDO aiAlertMessage = BeanUtils.toBean(reqVO, AiAlertMessageDO.class);

        // ================== 5. 入库 ==================
        aiAlertMessageMapper.insert(aiAlertMessage);

        // ================== 6. 返回ID ==================
        return aiAlertMessage.getId();
    }

    /**
     * 不同违规类型对应的图片
     */
    private static final Map<String, List<String>> ALERT_IMAGE_MAP = new HashMap<>();

    static {
        // ================== 未戴口罩（100200） ==================
        ALERT_IMAGE_MAP.put("100200", Arrays.asList(
                "http://112.47.127.21:59000/shunchang/avatar/35347711-8f8a-46f8-94e2-50f3f05b574b.png", // 未戴口罩-场景1
                "http://112.47.127.21:59000/shunchang/avatar/f2ae4384-2c7b-4e90-b2eb-991d721e80f3.png", // 未戴口罩-场景1
                "http://112.47.127.21:59000/shunchang/avatar/0b0a4b5e-fe37-48d5-a6f3-ab468f82c9cc.png"  // 未戴口罩-场景2


        ));

        // ================== 抽烟（100500） ==================
        ALERT_IMAGE_MAP.put("100500", Arrays.asList(
                "http://112.47.127.21:59000/shunchang/avatar/4137080e-6033-4251-98c1-ad661a683573.png",   // 抽烟-厨房内
                "http://112.47.127.21:59000/shunchang/avatar/ad4f94a8-4919-4ee5-9214-65f45bd9af4d.png",   // 抽烟-厨房内
                "http://112.47.127.21:59000/shunchang/avatar/4f81c8a6-eb2b-4f20-af47-96e30441a9df.png"    // 抽烟-角落区域
        ));

        // ================== 未戴厨师帽（100600） ==================
        ALERT_IMAGE_MAP.put("100600", Arrays.asList(
                "http://112.47.127.21:59000/shunchang/avatar/1b7457c0-12ac-403a-8c5a-0a5e4d405b93.png",  // 未戴厨师帽
                "http://112.47.127.21:59000/shunchang/avatar/c9a19c4b-647f-46b4-bf6a-813f38cce4b8.png",  // 未戴厨师帽
                "http://112.47.127.21:59000/shunchang/avatar/dc220592-3102-4e5a-8f53-3f4ab7b8b75f.png"  // 未戴厨师帽
        ));

        // ================== 老鼠识别（102300） ==================
        ALERT_IMAGE_MAP.put("102300", Arrays.asList(
                "http://112.47.127.21:59000/shunchang/avatar/9ea8f6dc-faa3-4b58-ae12-4116f0337b01.png",    // 厨房老鼠出现
                "http://112.47.127.21:59000/shunchang/avatar/4dfc4029-4d33-4cb2-822b-36c8a431dee8.png",    // 厨房老鼠出现
                "http://112.47.127.21:59000/shunchang/avatar/502f1c2c-8b70-4383-abfb-6a4898c7f495.png"    // 厨房老鼠出现
        ));
    }

    /**
     * 根据违规类型随机获取图片
     */
    private String getRandomImage(String aiAbilityCode) {
        List<String> images = ALERT_IMAGE_MAP.get(aiAbilityCode);
        if (CollUtil.isEmpty(images)) {
            return "http://112.47.127.21:59000/shunchang/avatar/29904d39-8a4f-4c15-ac28-5b34c3781f11.png";
        }
        return images.get(RandomUtil.randomInt(images.size()));
    }

    /**
     * 生成随机手机号
     */
    private String generatePhone() {
        // 中国手机号规则（示例）
        String[] prefix = {"130","131","132","133","134","135","136","137","138","139"};
        StringBuilder sb = new StringBuilder(prefix[RandomUtil.randomInt(prefix.length)]);
        for (int i = 0; i < 8; i++) {
            sb.append(RandomUtil.randomInt(10));
        }
        return sb.toString();
    }

    /**
     * 生成当天随机时间 HH:mm
     */
    private String generateTime() {
        int hour = RandomUtil.randomInt(0, 24);
        int minute = RandomUtil.randomInt(0, 60);
        return String.format("%02d:%02d", hour, minute);
    }

}
