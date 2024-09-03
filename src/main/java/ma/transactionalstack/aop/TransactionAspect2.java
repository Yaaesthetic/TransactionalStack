/*
package ma.transactionalstack.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@Aspect
@Component
public class TransactionAspect2 {
    @Pointcut("execution(* org.springframework.transaction.support.TransactionSynchronizationManager.*(..))")
    public void allMethods() {
    }

    @Around("allMethods()")
    public Object aroundTransactionalMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // Before method execution
        // Logic before method execution
        System.out.println("Before method invocation: " + joinPoint.getSignature().toShortString());

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Logic after method execution
        System.out.println("After method invocation: " + joinPoint.getSignature().toShortString());

        // Return the result of the method execution
        return result;
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalMethod() {
        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("Transaction started: " + transactionActive);
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void afterTransactionalMethod() {
        System.out.println("A @Transactional method has been executed.");
    }

    // Pointcut for methods annotated with @Transactional
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void annotationTransactionalMethods() {}

    // Advice to execute before transactional methods
    @Before("annotationTransactionalMethods()")
    public void beforeAnnotationTransactionalMethod() {
        System.out.println("A @Transactional method is about to be invoked.");
    }

    // Advice to execute after transactional methods
    @After("annotationTransactionalMethods()")
    public void afterAnnotationTransactionalMethod() {
        System.out.println("A @Transactional method is ended.");
    }
}*/
