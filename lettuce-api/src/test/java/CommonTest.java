import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yanghui
 * @date 2019-9-3
 */
@Slf4j
public class CommonTest {

    @Test
    public void testThreadPoolRejectJob() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(15));
        int num = 21;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for(int i=0 ; i<num; i++){
            try{
                pool.execute(()-> {
                    try{
                        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//                        if(new Random().nextInt(20) ==1){
//                            throw new RuntimeException("TEST");
//                        }
                    }finally {
                        countDownLatch.countDown();
                    }
                });
            }catch (Throwable e){
                countDownLatch.countDown();
                if(e instanceof RejectedExecutionException){
                    log.info("线程池拒绝任务数:"+atomicInteger.incrementAndGet());
                }
                else{
                    log.info(e.getMessage());
                }
            }
        }
        countDownLatch.await();
        log.info("执行完成任务数:"+pool.getCompletedTaskCount());
    }
}
