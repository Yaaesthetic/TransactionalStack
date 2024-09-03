package ma.transactionalstack.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Aspect
@Component
public class TransactionSynchronizationAspect {

    //@Before("@annotation(transactional)")
    @After("execution(* ma.transactionalstack.service.AuthenticationService.authenticate(..))")
    public void afterTransactionalMethod() {
        // Check if transaction synchronization is active
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            try {
                // Initialize Transaction Synchronization
                //TransactionSynchronizationManager.initSynchronization();
                System.out.println("Transaction Synchronization initialized.");
            } catch (IllegalStateException e) {
                System.err.println("Failed to initialize transaction synchronization: " + e.getMessage());
            }
        } else {
            System.out.println("Transaction Synchronization is already active.");
        }

        // Gather and log current transaction details
        logTransactionDetails();

        // Clear all transaction-related information
        System.out.println("Transaction-related information cleared after method execution.");
        TransactionSynchronizationManager.clear();

    }

    @Before("execution(* ma.transactionalstack.service.AuthenticationService.authenticate(..))")
    public void beforeTransactionalMethod() {
        // Register a TransactionSynchronization
        TransactionSynchronization synchronization = new TransactionSynchronization() {
            @Override
            public void suspend() {
                System.out.println("Synchronization suspended");
            }

            @Override
            public void resume() {
                System.out.println("Synchronization resumed");
            }

            @Override
            public void flush() {
                System.out.println("Synchronization flushed");
            }

            @Override
            public void beforeCommit(boolean readOnly) {
                System.out.println("Before Commit: " + readOnly);
            }

            @Override
            public void beforeCompletion() {
                System.out.println("Before Completion");
            }

            @Override
            public void afterCommit() {
                System.out.println("After Commit");
            }

            @Override
            public void afterCompletion(int status) {
                System.out.println("After Completion: " + status);
            }
        };
        TransactionSynchronizationManager.registerSynchronization(synchronization);

        // Gather and log registered synchronizations
        List<TransactionSynchronization> synchronizations = TransactionSynchronizationManager.getSynchronizations();
        System.out.println("Number of registered synchronizations: " + synchronizations.size());

        // Log synchronization details
        for (TransactionSynchronization sync : synchronizations) {
            System.out.println("Synchronization: " + sync.getClass().getSimpleName());
        }
    }

    private void logTransactionDetails() {
        // Log current transaction attributes
        System.out.println("Current Transaction Name: " + TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("Is Current Transaction Read-Only: " + TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        System.out.println("Current Transaction Isolation Level: " + TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        System.out.println("Is Actual Transaction Active: " + TransactionSynchronizationManager.isActualTransactionActive());
    }
}
