

package com.example.hello_springboot.config.AdminExtractionBatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.hello_springboot.entity.UserTblEntityIn;
@Component
public class UserPreserveItemProcessor implements ItemProcessor<UserTblEntityIn, UserTblEntityIn> {

    @Override
    public UserTblEntityIn process(UserTblEntityIn item) throws Exception {
        // 1. 确保数据不为空
        if (item == null) return null;

        // 2. 业务状态过滤：例如只处理活跃用户 (USER_STATUS = '1')
        if (!"1".equals(item.getUserStatus())) {
            return null; // 非活跃用户不写入 CSV
        }

        // 3. 可以在这里对敏感数据脱敏（例如隐藏密码或部分邮箱）
        item.setUserPwd("********"); 

        return item;
    }
}