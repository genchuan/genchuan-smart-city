package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.record.MemberLevelRecordPageReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelRecordDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberlevel.MemberLevelRecordMapper;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel.MemberLevelRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 会员等级记录 Service 实现类
 *
 * @author owen
 */
@Service
@Validated
public class MemberLevelRecordServiceImpl implements MemberLevelRecordService {

    @Resource
    private MemberLevelRecordMapper levelLogMapper;

    @Override
    public MemberLevelRecordDO getLevelRecord(Long id) {
        return levelLogMapper.selectById(id);
    }

    @Override
    public PageResult<MemberLevelRecordDO> getLevelRecordPage(MemberLevelRecordPageReqVO pageReqVO) {
        return levelLogMapper.selectPage(pageReqVO);
    }

    @Override
    public void createLevelRecord(MemberLevelRecordDO levelRecord) {
        levelLogMapper.insert(levelRecord);
    }

}
