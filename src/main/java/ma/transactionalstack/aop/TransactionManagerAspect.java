/*
package ma.transactionalstack.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class TransactionManagerAspect {

    // Pointcut to match all static methods in TransactionSynchronizationManager
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void allStaticMethods() {
    }

    // Around advice to intercept static method executions
    @Around("allStaticMethods()")
    public Object aroundStaticMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        // Before method execution
        System.out.println("Before static method invocation: " + joinPoint.getSignature().toShortString());

        // Utilize TransactionSynchronizationManager utilities before the method execution
        checkTransactionStatus();

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Utilize TransactionSynchronizationManager utilities after the method execution
        logResourcesAndSynchronizations();

        // After method execution
        System.out.println("After static method invocation: " + joinPoint.getSignature().toShortString());

        // Return the result of the method execution
        return result;
    }

    // Before advice to check if a transaction is active before any static method in TransactionSynchronizationManager
    @Before("allStaticMethods()")
    public void beforeStaticMethod(JoinPoint joinPoint) {
        System.out.println("Before static method: " + joinPoint.getSignature().toShortString());

        // Utilize TransactionSynchronizationManager utilities
        checkTransactionStatus();
    }

    // After advice to perform logic after any static method in TransactionSynchronizationManager
    @After("allStaticMethods()")
    public void afterStaticMethod(JoinPoint joinPoint) {
        System.out.println("After static method: " + joinPoint.getSignature().toShortString());

        // Utilize TransactionSynchronizationManager utilities
        logResourcesAndSynchronizations();
    }

    // After returning advice to capture the result of static method execution
    @AfterReturning(pointcut = "allStaticMethods()", returning = "result")
    public void afterReturningStaticMethod(JoinPoint joinPoint, Object result) {
        System.out.println("Static method returned: " + joinPoint.getSignature().toShortString() + ", result: " + result);

        // Utilize TransactionSynchronizationManager utilities
        logResourcesAndSynchronizations();
    }

    // After throwing advice to capture exceptions thrown by static methods
    @AfterThrowing(pointcut = "allStaticMethods()", throwing = "ex")
    public void afterThrowingStaticMethod(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Exception thrown in static method: " + joinPoint.getSignature().toShortString() + ", exception: " + ex.getMessage());

        // Utilize TransactionSynchronizationManager utilities
        logResourcesAndSynchronizations();
    }

    // Helper method to check transaction status and print relevant details
    private void checkTransactionStatus() {
        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        Integer isolationLevel = TransactionSynchronizationManager.getCurrentTransactionIsolationLevel();

        System.out.println("Transaction active: " + transactionActive);
        System.out.println("Transaction read-only: " + readOnly);
        System.out.println("Transaction name: " + transactionName);
        System.out.println("Transaction isolation level: " + isolationLevel);
    }

    // Helper method to log resources and synchronizations
    private void logResourcesAndSynchronizations() {
        Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();
        List<TransactionSynchronization> synchronizations = TransactionSynchronizationManager.getSynchronizations();

        System.out.println("Resources: " + resourceMap);
        System.out.println("Synchronizations: " + synchronizations);
    }
}
*/
