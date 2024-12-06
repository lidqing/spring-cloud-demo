package org.example.service;

import org.example.dao.BarHibernateDAO;
import org.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {
    @Autowired
    BarHibernateDAO barHibernateDAO;

    //@Transactional("hibernateTransaction")
    @Transactional
    public Account updateAccount(String userId) {
        barHibernateDAO.executeSQL(String.format("update sys.user_account set account=account-100 where user_id='%s'", userId));
        if ("admin2".equalsIgnoreCase(userId)) {
            throw new RuntimeException("业务异常，回滚事务");
        }
        return null;
    }
}
