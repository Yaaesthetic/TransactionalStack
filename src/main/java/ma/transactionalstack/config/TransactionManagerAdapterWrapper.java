//package ma.transactionalstack.config;
//
//import jakarta.transaction.NotSupportedException;
//import jakarta.transaction.SystemException;
//import org.hibernate.engine.transaction.jta.platform.internal.WebSphereExtendedJtaPlatform;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TransactionManagerAdapterWrapper {
//
//    private final WebSphereExtendedJtaPlatform.TransactionManagerAdapter transactionManagerAdapter;
//
//    @Autowired
//    public TransactionManagerAdapterWrapper(WebSphereExtendedJtaPlatform.TransactionManagerAdapter transactionManagerAdapter) {
//        this.transactionManagerAdapter = transactionManagerAdapter;
//    }
//
//    public void begin() throws SystemException, NotSupportedException {
//        transactionManagerAdapter.begin();
//    }
//
//    public void commit() {
//        transactionManagerAdapter.commit();
//    }
//}
