package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.recrod.MemberPointRecordPageReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberpoint.vo.AppMemberPointRecordPageReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointRecordDO;
import cn.iocoder.yudao.module.usermerchant.enums.MemberPointBizTypeEnum;

/**
 * 用户积分记录 Service 接口
 *
 * @author QingX
 */
public interface MemberPointRecordService {

    /**
     * 【管理员】获得积分记录分页
     *
     * @param pageReqVO 分页查询
     * @return 签到记录分页
     */
    PageResult<MemberPointRecordDO> getPointRecordPage(MemberPointRecordPageReqVO pageReqVO);

    /**
     * 【会员】获得积分记录分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 签到记录分页
     */
    PageResult<MemberPointRecordDO> getPointRecordPage(Long userId, AppMemberPointRecordPageReqVO pageReqVO);

    /**
     * 创建用户积分记录
     *
     * @param userId  用户ID
     * @param point   变动积分
     * @param bizType 业务类型
     * @param bizId   业务编号
     */
    void createPointRecord(Long userId, Integer point, MemberPointBizTypeEnum bizType, String bizId);
}
