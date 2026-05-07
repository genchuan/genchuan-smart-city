package cn.iocoder.yudao.module.studentmgmt.service.newpush;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.PromoteCountReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.PromoteMgmtChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.NewPushStatusEnum;
import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.newpush.NewPushDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.newpush.NewPushMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 迎新推送 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class NewPushServiceImpl implements NewPushService {

    @Resource
    private NewPushMapper newPushMapper;
    @Resource
    private NotifyMessageSendApi notifySendApi;
    @Resource
    private StudentInfoMapper studentInfoMapper;
    @Override
    public Long createNewPush(NewPushSaveReqVO createReqVO) {
        // 插入
        NewPushDO newPush = BeanUtils.toBean(createReqVO, NewPushDO.class);
        newPushMapper.insert(newPush);

        // 返回
        return newPush.getId();
    }

    @Override
    public boolean updateNewPush(NewPushUpdateReqVO updateReqVO) {
        // 校验存在
        validateNewPushExists(updateReqVO.getId());
        // 更新
        NewPushDO updateObj = BeanUtils.toBean(updateReqVO, NewPushDO.class);
        int i = newPushMapper.updateById(updateObj);
        return i>0;
    }

    @Override
    public void deleteNewPush(Long id) {
        // 校验存在
        validateNewPushExists(id);
        // 删除
        newPushMapper.deleteById(id);
    }

    @Override
    public void deleteNewPushListByIds(List<Long> ids) {
        // 删除
        newPushMapper.deleteByIds(ids);
    }


    private NewPushDO validateNewPushExists(Long id) {
        NewPushDO newPushDO = newPushMapper.selectById(id);
        if (newPushDO == null) {
            throw exception(NEW_PUSH_NOT_EXISTS);
        }
        return newPushDO;
    }

    @Override
    public NewPushDO getNewPush(Long id) {
        return newPushMapper.selectById(id);
    }

    @Override
    public PageResult<NewPushDO> getNewPushPage(NewPushPageReqVO pageReqVO) {
        return newPushMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean config(NewPushConfigReqVO reqVO) {
        // 插入
        NewPushDO newPush = BeanUtils.toBean(reqVO, NewPushDO.class);
        newPush.setStatus(NewPushStatusEnum.UNPUSHED.getStatus());
        int i = newPushMapper.insert(newPush);
        return i > 0;
    }

    /**
     * 发送推送信息
     * @param userId 用户userId
     * @param templateCode 模板code
     * @param templateParams 模板参数
     */
    private void sendPushMessage(Long userId, String templateCode, Map<String, Object> templateParams) {
        // 发送站内信
        notifySendApi.sendSingleMessageToMember(new NotifySendSingleToUserReqDTO()
                .setUserId(userId).setTemplateCode(templateCode).setTemplateParams(templateParams));
    }

    @Override
    public Boolean push(NewPushPushReqVO reqVO) {
        int total = 0;
        Long[] ids = reqVO.getIds();

        // 1. 准备参数
        String templateCode = "newPush"; // 站内信模版，记得在【站内信管理】中配置噢
        Map<String, Object> templateParams = new HashMap<>();


        // 查询所有的新生信息，并查询该学生的userId
        int year = LocalDateTime.now().getYear();
        List<StudentInfoDO> studentInfoDOS = studentInfoMapper.selectStudentInfoByYear(year);

        for (Long id : ids) {
            NewPushDO newPush = validateNewPushExists(id);
            templateParams.put("content", newPush.getPushContent());

            /* // 给每一个新生发送推送信息
            for (StudentInfoDO studentInfoDO : studentInfoDOS) {
                // TODO 查询学生对应的账号
                Long userId = 1L;
                // 发送推送信息
                sendPushMessage(userId, templateCode, templateParams);
            }*/
            // 校验存在
            newPush.setStatus(NewPushStatusEnum.PUSHED.getStatus());
            newPush.setPushTime(LocalDateTime.now());
            // 更新

            int i = newPushMapper.updateById(newPush);
            total += i;
        }

        return total > 0;
    }

    @Override
    public NewPushChartRespVO chart(NewPushChartReqVO reqVO) {
        NewPushChartRespVO vo = new NewPushChartRespVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }
        // 1. 卡片数据
        vo = newPushMapper.selectTotalCount(year, NewPushStatusEnum.PUSHED.getStatus());
        // 如果统计为空，则设置为0
        if (vo == null) {
            vo = new NewPushChartRespVO();
        }
        if (vo.getWaitPushCount() == null) {
            vo.setWaitPushCount(0);
        }
        if (vo.getTotalCount() == null) {
            vo.setTotalCount(0);
        }
        if (vo.getFinishedCount() == null) {
            vo.setFinishedCount(0);
        }
        if (vo.getTotalPushNum() == null) {
            vo.setTotalPushNum(0);
        }
        if (vo.getAvgFinishRate() == null) {
            vo.setAvgFinishRate(BigDecimal.ZERO);
        }

        // 每日列表
        List<JSONObject> dateCountList = newPushMapper.selectDateCountList(year);
        List<String> dateList = new ArrayList<>();
        List<Integer> dailyPushList = new ArrayList<>();
        for (JSONObject jsonObject : dateCountList) {
            dateList.add(jsonObject.getString("date"));
            dailyPushList.add(jsonObject.getInteger("count"));

        }
        vo.setDateList(dateList);
        vo.setDailyPushList(dailyPushList);
        return vo;
    }

    @Override
    public NewPushIndexRespVO pushIndex(NewPushChartReqVO reqVO) {
        NewPushIndexRespVO vo = new NewPushIndexRespVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }
        vo = newPushMapper.selectPushCount(year, NewPushStatusEnum.PUSHED.getStatus());
        return vo;
    }

}