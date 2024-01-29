/*
package kuangyizhu.repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.SharedSessionContract;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionDO implements Serializable {
    // @Id means primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //use IDENTITY to let mysql decie the id.
    private Integer id;

    // let UUID intialized by java
    @Column(name = "transaction_id")
    private String transactionId = UUID.randomUUID().toString();

    @Column(name = "transaction_data")
    private String transactionData;

    @Column
    private Integer status = 0;

    //try to use JPA default value instead of sql default value
    @Column(name = "is_delete" )
    private Boolean isDelete = false;

    @Column(name = "error_msg")
    private String errorMsg;

    //@Transient 不会映射到数据库



}
*/
