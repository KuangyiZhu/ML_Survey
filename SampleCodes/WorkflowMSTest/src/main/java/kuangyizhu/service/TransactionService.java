/*
package kuangyizhu.service;

import jakarta.transaction.Transactional;
import kuangyizhu.repository.TransactionDAO;
import kuangyizhu.repository.TransactionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionDAO transactionDAO;

    @Transactional
    public void insert(String payload) {
        TransactionDO transactionDo = new TransactionDO();
        transactionDo.setTransactionData(payload);
        transactionDAO.save(transactionDo);
    }
}
*/
