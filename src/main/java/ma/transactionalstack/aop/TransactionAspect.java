//package ma.transactionalstack.aop;
//
//
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class TransactionAspect {
//
//    // Pointcut for java.sql.Connection's beginRequest
//    @Pointcut("execution(* java.sql.Connection.beginRequest(..))")
//    public void beginRequestMethod() {
//    }
//
//    // Pointcut for java.sql.Connection's commit
//    @Pointcut("execution(* java.sql.Connection.commit(..))")
//    public void commitMethod() {
//    }
//
//    // Pointcut for java.sql.Connection's rollback
//    @Pointcut("execution(* java.sql.Connection.rollback(..))")
//    public void rollbackMethod() {
//    }
//
//    // Pointcut for PlatformTransactionManager's getTransaction
//    @Pointcut("execution(* org.springframework.transaction.PlatformTransactionManager.getTransaction(..))")
//    public void getTransactionMethod() {
//    }
//
//    // Pointcut for PlatformTransactionManager's commit
//    @Pointcut("execution(* org.springframework.transaction.PlatformTransactionManager.commit(..))")
//    public void platformCommitMethod() {
//    }
//
//    // Pointcut for PlatformTransactionManager's rollback
//    @Pointcut("execution(* org.springframework.transaction.PlatformTransactionManager.rollback(..))")
//    public void platformRollbackMethod() {
//    }
//
//    // Advices with System.out.println
//    @After("beginRequestMethod()")
//    public void beforeBeginRequest() {
//        System.out.println("beginRequest() method called on java.sql.Connection.");
//    }
//
//    @After("commitMethod()")
//    public void beforeCommit() {
//        System.out.println("commit() method called on java.sql.Connection.");
//    }
//
//    @After("rollbackMethod()")
//    public void beforeRollback() {
//        System.out.println("rollback() method called on java.sql.Connection.");
//    }
//
//    @Before("getDataSourceMethod()")
//    public void beforeGetDataSource() {
//        System.out.println("getDataSource() method called on DataSourceTransactionManager.");
//    }
//
//    @Before("getTransactionMethod()")
//    public void beforeGetTransaction() {
//        System.out.println("getTransaction() method called on PlatformTransactionManager.");
//    }
//
//    @Before("platformCommitMethod()")
//    public void beforePlatformCommit() {
//        System.out.println("commit() method called on PlatformTransactionManager.");
//    }
//
//    @Before("platformRollbackMethod()")
//    public void beforePlatformRollback() {
//        System.out.println("rollback() method called on PlatformTransactionManager.");
//    }
//}


/*

Spring AOP: Spring AOP is proxy-based,
which means that it only intercepts method calls made on beans that are managed by the Spring container.
The methods in the PlatformTransactionManager are likely invoked internally by Spring's transaction management infrastructure
rather than by your application code directly.

*/
