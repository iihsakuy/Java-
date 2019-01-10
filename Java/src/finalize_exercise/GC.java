package finalize_exercise;

public class GC {
    public static GC SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        //新建对象，对象此时的状态是(reachable,unfinalized)
        SAVE_HOOK = new GC();
        //将SAVE_HOOK设置为null，刚刚新建的对象不可达，此时的状态为(unreachable,unfinalized)
        SAVE_HOOK = null;
        //强制调用垃圾回收，系统发现刚刚创建的对象处于(unreachable,unfinalized)状态,而该对象覆盖了finalized方法,将这个对象放入F-Queue队列，由低优先级线程执行它的finalize方法，此时对象的状态变成(finalizer-reachable,finalizable)
        System.gc();//System.gc()用于调用垃圾收集器，在调用时，垃圾收集器将运行以回收未使用的内存空间。
        // sleep，目的是给低优先级线程从F-Queue队列取出对象并执行其finalize方法提供机会。在执行完对象的finalize方法中的super.finalize()时，对象的状态变成(unreachable,finalized)状态，但接下来在finalize方法中又执行了SAVE_HOOK = this;这句话，又有句柄指向这个对象了，对象又可达了。因此对象的状态又变成了(reachable, finalized)状态。
        Thread.sleep(500);
        if (null != SAVE_HOOK) {//此时对象应该处于(reachable, finalized)状态
            System.out.println("Yes,I'm still alive");
        } else {
            System.out.println("NO,I'm dead");
        }
        // 再一次将SAVE_HOOK放空，此时刚才复活的对象，状态变成(unreachable,finalized)
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        // 再一次强制系统回收垃圾，此时系统发现对象不可达，虽然覆盖了finalize方法，但已经执行过了，因此直接回收。
        if (null != SAVE_HOOK) {//此时对象应该处于(reachable, finalized)状态
            System.out.println("Yes,I'm still alive");
        } else {
            System.out.println("NO,I'm dead");
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Execute method finalize()");
        // 这句话让对象的状态由unreachable变成reachable，就是对象复活
        SAVE_HOOK = this;
    }
}
