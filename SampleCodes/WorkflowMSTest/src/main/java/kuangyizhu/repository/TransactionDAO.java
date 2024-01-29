/*
package kuangyizhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDAO extends JpaRepository<TransactionDO, Integer> {
    Optional<List<TransactionDO>> findByTransactionId(String transactionId);

    @Query(value = "select t from TransactionDO t where t.transactionId=:id")
    List<TransactionDO> findByIdParam(@Param("id") String id);

    @Query(value = "select transaction_data from transaction where transaction_id=:id", nativeQuery = true)
    List<String> findByIdParamNative(@Param("id") String id);
}
*/
